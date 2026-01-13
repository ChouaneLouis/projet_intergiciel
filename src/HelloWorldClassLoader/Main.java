package HelloWorldClassLoader;

import Hagent.Commun.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting HelloWorld Test");

        for (String string : args) {
            System.out.println(string);
        }

        Node servNode = new Node("Server1", "localhost", 8081);
        Node myNode = new Node("origin", "localhost", 8080);

        try {
            ServerSocket ss = new ServerSocket(myNode.getPort());

            AgentExemple agent = new AgentExemple();

            agent.init("AgentHelloWorld", myNode);
            agent.addKnownNode(servNode);
            agent.run();

            System.out.println("Waiting....");
            Socket s = ss.accept();
            System.out.println("Recieved agent");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            int jarSize = dis.readInt();
            dis.readNBytes(jarSize);
            int stateSize = dis.readInt();
            byte[] agentBytes = dis.readNBytes(stateSize);

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(agentBytes));
            Agent ag = (Agent) ois.readObject();

            System.out.println("Agent " + ag.getName() + " received at destination:");
            if (ag.getExceptionRecord() != null) {
                System.out.println("Agent Exception Record:");
                ag.getExceptionRecord().getException().printStackTrace();
            } else {
                System.out.println("No Exception in Agent");
            }
            ss.close();
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