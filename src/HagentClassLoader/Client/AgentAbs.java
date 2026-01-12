package HagentClassLoader.Client;

import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class AgentAbs implements Agent {

    private String name;
    private int state = 0;
    private HashMap<String, Node> knownNode;
    private ExceptionRecord execptionRecord = null;
    private Map<String, byte[]> classBytesByName;;

    protected Server localServer;

    public void init(String name, Node origin, String... dependanciesPaths) {
        this.name = name;
        this.knownNode = new HashMap<>();
        this.knownNode.put("origin", origin);
        this.classBytesByName = getClassBytes(dependanciesPaths);
    }

    protected Map<String, byte[]> getClassBytes(String... dependancies) {
        Map<String, byte[]> map = new HashMap<>();

        for (String string : dependancies) {

            try {
                Path classFile = Paths.get(string);
                byte[] classBytes = Files.readAllBytes(classFile);
                map.put(string, classBytes);
            } catch (IOException e) {
                System.out.println("Erreur dans la récupération des dépendances");
                map = null;
                break;
            }
        }
        return map;
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
        } catch (Exception e) {
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
            OutputStream os = socket.getOutputStream();

            // Serialization, Object.
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.flush();
            byte[] objectBytes = baos.toByteArray();

            DataOutputStream out = new DataOutputStream(os);

            // Envoie des codes de classe
            ObjectOutputStream oosMap = new ObjectOutputStream(os);
            oosMap.writeObject(classBytesByName);

            // Envoie de l'objet.
            out.writeInt(objectBytes.length);
            out.write(objectBytes);
            oos.close();

        } catch (UnknownHostException e) {
            throw new RuntimeException("Unknown host: " + target.getAddress(), e);
        } catch (java.io.IOException e) {
            throw new RuntimeException("I/O error while moving agent to " + target.getName(), e);
        }
    }

    protected void finish() {
        move(getKnownNode("origin"), -1);
        System.out.println("moving back to origin");
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
}
