public class Node {
    private String name;
    private int port;

    public Node(String n, int p) {
        this.name = n;
        this.port = p;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int newPort) {
        this.port = newPort;
    }
}