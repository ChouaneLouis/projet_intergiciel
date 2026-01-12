package Hagent;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
// import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.NoSuchElementException;

public abstract class ServerAbs implements Server {
    protected Node[] serversList;
    protected HashMap<String, Service> services;
    protected static Node myNode;

    protected Socket s;

    public void run() {
        try {
            System.out.println("recieved new request");

            DataInputStream dis = new DataInputStream(s.getInputStream());

            byte[] jarBytes = receiveJar(dis);
            byte[] stateBytes = receiveState(dis);

            ClassLoader parent = ClassLoader.getSystemClassLoader();
            AgentClassLoader agentClassLoader = new AgentClassLoader(parent, jarBytes);

            ObjectInputStream ois = new AgentObjectInputStream(new ByteArrayInputStream(stateBytes), agentClassLoader);

            try {
                Agent agent = (Agent) ois.readObject();
                agent.setup(this);
                agent.run();
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found");
                e.printStackTrace();
            }

            dis.close();
            System.out.println("End of thread");
            s.close();

        } catch (IOException e) {
            System.err.println("PB run() Server");
            e.printStackTrace();
        }
    }

    public Service getService(String serviceName) throws NoSuchElementException {
        Service service = null;
        if (services.containsKey(serviceName)) {
            service = services.get(serviceName);
        } else {
            throw new NoSuchElementException();
        }
        return service;
    }

    public Node[] getServers() {
        return this.serversList;
    }

    public static byte[] receiveJar(DataInputStream dis) throws IOException {

        int jarSize = dis.readInt();
        if (jarSize <= 0) {
            throw new IOException("Invalid JAR size: " + jarSize);
        }

        return dis.readNBytes(jarSize);
    }

    public static byte[] receiveState(DataInputStream dis) throws IOException {

        int stateSize = dis.readInt();
        if (stateSize <= 0) {
            throw new IOException("Invalid state size: " + stateSize);
        }

        return dis.readNBytes(stateSize);
    }


    /*
     * Créer le ServerSocket et attend les connexions entrantes.
    */
    // public static void main(String[] args) {
    //     System.out.println("Starting Server");
    //     if (args.length != 3) {
    //         System.err.println("Usage: java ServerAbs <name> <address> <port>");
    //         System.exit(1);
    //     }
    //     try {
    //         myNode = new Node(args[0], args[1], Integer.parseInt(args[2]));
    //         ServerSocket ss = new ServerSocket(myNode.getPort());
    //         while (true) {
    //             try {
    //                 Socket s = ss.accept();
    //                 Thread t = new Thread(newServer(s)); // Classe abstraite -> pas de Constructeur, comment faire ?
    //                 t.start();

    //             } catch (IOException e) {
    //                 System.err.println(e);
    //             }
    //         }
    //     } catch (IOException e) {
    //         System.err.println("PB ServerSocket");
    //         e.printStackTrace();
    //     } catch (NumberFormatException e) {
    //         System.err.println("Port must be an integer");
    //         e.printStackTrace();
    //     }
    // }

}
