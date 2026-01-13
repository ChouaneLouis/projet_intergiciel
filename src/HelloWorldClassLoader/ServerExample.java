package HelloWorldClassLoader;
import Hagent.Serveur.*;
import Hagent.Commun.*;

import java.net.Socket;
import java.util.HashMap;

public class ServerExample extends ServerAbs {

    public ServerExample(Socket s) {
        super(s);
        this.services = new HashMap<String, Service>();
        this.services.put("Test", new ServiceExampleImpl());
        this.serversList = new Node[0];
    }

    public ServerExample(Node n) {
        super(n);
    }

    @Override
    public ServerAbs newServer(Socket s) {
        return new ServerExample(s);
    }

    public static void main(String[] args) {
        System.out.println("Starting Server");
        if (args.length != 3) {
            System.err.println("Usage: java ServerAbs <name> <address> <port>");
            System.exit(1);
        }
        myNode = new Node(args[0], args[1], Integer.parseInt(args[2]));
        new ServerExample(myNode);
    }

}
