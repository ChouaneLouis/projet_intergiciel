package HagentHotelLoader;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashMap;
import Hagent.Commun.*;
import Hagent.Serveur.*;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NamesServerImpl extends ServerAbs{

    public NamesServerImpl(Socket s) {
        super(s);
        this.services = new HashMap<String, Service>();
        this.services.put("names",new NamesServiceImpl());
        this.serversList = new Node[0];
    }

    public NamesServerImpl(Node n) {
        super(n);
    }

    @Override
    public ServerAbs newServer(Socket s) {
        return new NamesServerImpl(s);
    }

    public static void main(String[] args) {
        System.out.println("Starting Server");
        if (args.length != 3) {
            System.err.println("Usage: java NamesServerImpl <name> <address> <port>");
            System.exit(1);
        }
        myNode = new Node(args[0], args[1], Integer.parseInt(args[2]));
        new NamesServerImpl(myNode);
    }
}
