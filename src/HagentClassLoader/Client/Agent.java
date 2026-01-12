package HagentClassLoader.Client;

//Version du prof
public interface Agent extends java.io.Serializable {
    public void init(String name, Node origin, String... dependanciesPaths);

    public void setup(Server localServer);

    public void run();

    public void addKnownNode(Node node);

    public String getName();

    public ExceptionRecord getExceptionRecord();
}