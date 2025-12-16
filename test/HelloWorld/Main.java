package HelloWorld;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Hagent.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting HelloWorld Test");

        Node servNode = new Node("Server1", "localhost", 8081);
        Node myNode = new Node("origin", "localhost", 8080);

        try {
            ServerSocket ss = new ServerSocket(myNode.getPort());

            AgentExemple agent = new AgentExemple();
            agent.init("AgentHelloWorld", myNode);
            agent.addKnownNode(servNode);
            agent.run();

            Socket s = ss.accept();
            InputStream inputIS = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputIS);
            Agent ag = (Agent) ois.readObject();
            System.out.println("Agent " + ag.getName() + " received at destination:");
            if (ag.getExceptionRecord() != null) {
                System.out.println("Agent Exception Record:");
                ag.getExceptionRecord().getException().printStackTrace();
            } else {
                System.out.println("No Exception in Agent");
            }
        } catch (IOException e) {
            System.err.println("PB ServerSocket");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur ClassNotFound");
            e.printStackTrace();
        }

        System.out.println("HelloWorld Test Finished");
    }
}