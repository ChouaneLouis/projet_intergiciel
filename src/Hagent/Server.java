package src.Hagent;

public interface Server extends Runnable {
    public void main();

    public void run();

    public void join(Object hagentSerialized);

    public Object getService(String serviceName);

    public Node[] getServers();
}
