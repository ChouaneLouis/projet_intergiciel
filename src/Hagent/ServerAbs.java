package src.Hagent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.NoSuchElementException;

public abstract class ServerAbs implements Server {
    private Node[] serversList;
    private HashMap<String, Service> services;
    private Node myNode;

    public void run() {
        // TODO que faire quand thread crée
    }

    public void join(Object hagentSerialized) {
        Agent hagent = (Agent) hagentSerialized;
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

    public void main(String args[]) { // static ?
        try {
            ServerSocket ss = new ServerSocket(this.myNode.getPort());
            while (true) {
                try {
                    Socket s = ss.accept();
                    Thread lb = new ServerAbs(); // Classe abstraite -> pas de Constructeur, comment faire ?
                    lb.start();

                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        } catch (IOException e) {
            System.err.println("PB ServerSocket");
            System.err.println(e);
        }
    }
}
