package test.TestHelloWorld;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.util.HashMap;

import src.Hagent.*;

public class ServerExample extends ServerAbs {

    public ServerExample(Socket newSock) {
        this.s = newSock;
        this.services = new HashMap<String, Service>();
        this.services.put("Test", new ServiceExample());
        this.serversList = new Node[0];
    }

    public static void main(String[] args) {
        System.out.println("Starting Server");
        if (args.length != 3) {
            System.err.println("Usage: java ServerAbs <name> <address> <port>");
            System.exit(1);
        }
        try {
            myNode = new Node(args[0], args[1], Integer.parseInt(args[2]));
            ServerSocket ss = new ServerSocket(myNode.getPort());
            while (true) {
                try {
                    Socket s = ss.accept();
                    Thread t = new Thread(new ServerExample(s)); // Classe abstraite -> pas de Constructeur, comment faire ?
                    t.start();

                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        } catch (IOException e) {
            System.err.println("PB ServerSocket");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Port must be an integer");
            e.printStackTrace();
        }
    }

}
