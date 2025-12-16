package Hagent;

public interface Server extends Runnable {

    public void run();

    public Object getService(String serviceName);

    public Node[] getServers();
}
