package Hagent;

import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.jar.JarFile;

public abstract class AgentAbs implements Agent {

    private String name;
    private int state = 0;
    private HashMap<String, Node> knownNode;
    private ExceptionRecord execptionRecord;
    private byte[] jarBytes;

    protected Server localServer;

    protected abstract String getAgentJarPath();

    public void init(String name, Node origin) {
        this.name = name;
        this.knownNode = new HashMap<>();
        this.knownNode.put("origin", origin);
        try  {
            this.jarBytes = readJarFromPath(getAgentJarPath());
        } catch (IOException e) {
            new ExceptionRecord(e, this.name, -1);
        }
    }

    public void setup(Server localServer) {
        for (Node n : localServer.getServers()) {
            addKnownNode(n);
        }
        this.localServer = localServer;
    }

    public void run() {
        try {
            runState(state);
        }
        catch (Exception e) {
            execptionRecord = new ExceptionRecord(e, this.name, state);
            finish();
        }
    }

    protected abstract void runState(int state);

    protected void move(Node target, int newState) {
        localServer = null;
        state = newState;
        try {
            Socket socket = new Socket(target.getAddress(), target.getPort());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            dos.writeInt(jarBytes.length);
            dos.write(jarBytes);

            byte[] stateBytes = serializeAgent(this);
            dos.writeInt(stateBytes.length);
            dos.write(stateBytes);

            dos.flush();
            dos.close();

            socket.close();
        }
        catch (UnknownHostException e) {
            throw new RuntimeException("Unknown host: " + target.getAddress(), e);
        }
        catch (java.io.IOException e) {
            throw new RuntimeException("I/O error while moving agent to " + target.getName(), e);
        }
    }

    protected void finish() {
        move(getKnownNode("origin"), -1);
    }

    public ExceptionRecord getExceptionRecord() {
        return execptionRecord;
    }

    protected Node getKnownNode(String name) {
        return knownNode.get(name);
    }

    public void addKnownNode(Node node) {
        if (knownNode.containsKey(node.getName()) && !knownNode.get(node.getName()).equals(node)) {
            throw new ConflictingNodesException("Node " + node.getName() + " has conflicting definitions");
        }
        this.knownNode.put(node.getName(), node);
    }

    public String getName() {
        return this.name;
    }

    public static byte[] readJarFromPath(String jarPath) throws IOException {
        Path path = Paths.get(jarPath);

        if (!Files.exists(path)) {
            throw new FileNotFoundException("Agent JAR not found: " + jarPath);
        }

        return Files.readAllBytes(path);
    }

    public static byte[] serializeAgent(Agent agent) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(agent);
        oos.flush();

        return bos.toByteArray();
    }

}
