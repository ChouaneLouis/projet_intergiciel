package src.Hagent;

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

            InputStream inputIS = s.getInputStream();

            // deserialize
            ObjectInputStream ois = new ObjectInputStream(inputIS);
            try {
                Agent ag = (Agent) ois.readObject();

                // Agent fait sa tâche
                ag.setup(this);
                ag.run();

            } catch (ClassNotFoundException e) {
                System.err.println("Erreur ClassNotFound");
                e.printStackTrace();
            }

            // Tout fermer
            ois.close();
            inputIS.close();
            System.out.println("End of thread");
            s.close();

        } catch (IOException e) {
            System.err.println("PB run() Server");
            e.printStackTrace();
        }
    }

    public Service getService(String serviceName) throws NoSuchElementException { // TODO, à mettre dans l'agent et le
                                                                                  // renvoyer au départ ?
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
