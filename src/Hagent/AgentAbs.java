package src.Hagent;

import java.util.HashMap;

public abstract class AgentAbs implements Agent {

    private String name;
    private int state = 0;
    private HashMap<String, Node> knownNode;
    private execptionRecord = null;

    public void init(String name, Node origin) {
        this.name = name;
        this.knownNode = new HashMap<>();
        this.knownNode.put("orgin", origin);
        
    }

    public void setup(Server localServer) {
        for (Node n : localServer.getServer()) {

        }
    }

    public void run() {
        try {
            runState(state);
        }
        catch (Exception e) {

        }
    }

    protected abstract void runState(int state);

    protected void move(Node target, int newState) {}

    protected void finish() {}


}
