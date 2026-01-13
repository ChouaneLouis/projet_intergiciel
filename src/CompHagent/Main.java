package CompHagent;

import Hagent.Commun.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        ServerSocket ss = null;
        if (args.length != 4) {
            System.err.println("Usage: Main <homeAddress> <homePort> <destAddress> <destPort>");
            return;
        }
        try {
            Node home = new Node("origin", args[0], Integer.parseInt(args[1]));
            Node dest = new Node("Server1", args[2], Integer.parseInt(args[3]));

            ss = new ServerSocket(home.getPort());

            System.out.println("Starting Compression Test");

            AgentExemple agent = new AgentExemple();
            agent.init("AgentHelloWorld", home);
            agent.addKnownNode(dest);
            agent.run();
        } catch (NumberFormatException e) {
            System.err.println("Port numbers must be integers.");
        } catch (Exception e) {
            System.err.println("Error initializing or running the agent:");
            e.printStackTrace();
        }

        try {

            System.out.println("Waiting....");
            Socket s = ss.accept();
            System.out.println("Recieved agent");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            int jarSize = dis.readInt();
            dis.readNBytes(jarSize);
            int stateSize = dis.readInt();
            byte[] agentBytes = dis.readNBytes(stateSize);

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(agentBytes));
            AgentExemple ag = (AgentExemple) ois.readObject();

            System.out.println("Agent " + ag.getName() + " received at destination:");
            if (ag.getExceptionRecord() != null) {
                System.out.println("Agent Exception Record:");
                ag.getExceptionRecord().getException().printStackTrace();
            } else {
                System.out.println("No Exception in Agent");
            }
            ss.close();

            System.out.println("Original Size: " + ag.originalSize);
            System.out.println("Decompressed Size: " + ag.getFileData().length);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("HelloWorld Test Finished");
    }
}