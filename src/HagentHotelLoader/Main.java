package HagentHotelLoader;

import Hagent.Commun.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Main {

    public static void printInfo(HRecord info) {
        try {
            System.out.println("Name : " + info.getName() + "\nNumber : " + info.getAdress() + "\nAdress : "
                    + info.getNumber() + "\n");
        } catch (Exception e) {
            System.out.println("Failed to print info");
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        System.out.println("Starting HagentHotelLoader Test");

        Node namesNode = new Node("Server1", "localhost", 8081); //Nodes à passer en paramètre
        Node infoNode = new Node("Server2", "localhost", 8082); //Nodes à passer en paramètre
        Node myNode = new Node("origin", "localhost", 8080);

        try {
            ServerSocket ss = new ServerSocket(myNode.getPort());

            AgentExemple agent = new AgentExemple();

            agent.init("AgentHelloWorld", myNode);
            agent.addKnownNode(namesNode);
            agent.addKnownNode(infoNode);
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
            AgentExemple ag = (AgentExemple) ois.readObject();

            System.out.println("Agent " + ag.getName() + " received at destination:");
            if (ag.getExceptionRecord() != null) {
                System.out.println("Agent Exception Record:");
                ag.getExceptionRecord().getException().printStackTrace();
            } else {
                System.out.println("No Exception in Agent - printing values\n");
                Map<String,HRecord> infos = ag.getInfo();

                for (Map.Entry<String, HRecord> entry : infos.entrySet()) {
                    printInfo(entry.getValue());
                }
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