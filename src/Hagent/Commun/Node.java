package Hagent.Commun;

public class Node implements java.io.Serializable {
    private String name;
    private String address;
    private int port;

    public Node(String n, String a, int p) {
        this.name = n;
        this.address = a;
        this.port = p;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public int getPort() {
        return this.port;
    }

    public boolean equals(Node other) {
        return other != null 
            && this.name.equals(other.name)
            && this.address.equals(other.address)
            && this.port == other.port;
    }
}