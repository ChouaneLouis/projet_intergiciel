public interface Server {
    public void run();

    public void join(...);

    public Object getService(String serviceName);

    public ... getServer(String serverName);
}
