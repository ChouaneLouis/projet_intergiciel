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
        long startTime = 0;
        if (args.length != 4) {
            System.err.println("Usage: Main <homeAddress> <homePort> <destAddress> <destPort> ");
            return;
        }
        try {
            Node home = new Node("origin", args[0], Integer.parseInt(args[1]));
            Node dest = new Node("Server1", args[2], Integer.parseInt(args[3]));
            int[] iterations_nb = {1, 1, 1, 1, 10, 20, 30, 40, 50, 75, 100};

            ss = new ServerSocket(home.getPort());

            for (int itnb : iterations_nb) {
                System.out.println("Begining test speed for hagent with " + itnb + " iterations\n");
                startTime = System.currentTimeMillis();

                AgentExemple agent = new AgentExemple(itnb);
                agent.init("AgentHelloWorld", home);
                agent.addKnownNode(dest);
                agent.run();

                System.out.println("Waiting....");
                Socket s = ss.accept();

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
                }

                System.out.println("Test finished in " + (System.currentTimeMillis() - startTime) + " ms\n");
            }
            ss.close();

        } catch (NumberFormatException e) {
            System.err.println("Port and iterations numbers must be integers.");
            return;
        } catch (Exception e) {
            System.err.println("Error initializing or running the agent:");
            e.printStackTrace();
        }

    }
}