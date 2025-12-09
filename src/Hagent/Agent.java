package src.Hagent;


//Version du prof
public interface Agent extends java.io.Serializable {
    public void init(String name, Node origin);

    public void setup(Server localServer);

    public void run();

    public void addKnownNode(Node node);

    public String getName();
}