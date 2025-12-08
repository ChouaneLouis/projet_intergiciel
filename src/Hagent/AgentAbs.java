package src.Hagent;

public abstract class AgentAbs implements Agent, Serializible {

    private int state = 0;

    public void init(String name, Node origin) {}

    public void setup(Hashtable<String, Object> ns) {}

    public void run() {}

    private abstract void runState(int state);

    private void move(Node target, int newState) {}

    private void finish() {}


}
