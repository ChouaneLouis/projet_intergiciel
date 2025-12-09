package src.Hagent;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public abstract class AgentAbs implements Agent {

    private String name;
    private int state = 0;
    private HashMap<String, Node> knownNode;
    private ExceptionRecord execptionRecord = null;

    protected Server localServer;

    public void init(String name, Node origin) {
        this.name = name;
        this.knownNode = new HashMap<>();
        this.knownNode.put("origin", origin);
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
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeObject(this);
            oos.flush();
            oos.close();

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

    protected ExceptionRecord getExceptionRecord() {
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
