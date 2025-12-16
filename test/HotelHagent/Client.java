package HotelHagent;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.List;
import java.util.ArrayList;

import Hagent.Node;
import Hagent.Agent;

public class Client {

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
        System.out.println("Starting HelloWorld Test");

        Node servNode = new Node("Server1", "localhost", 8081);
        Node myNode = new Node("origin", "localhost", 8080);

        try {
            ServerSocket ss = new ServerSocket(myNode.getPort());

            AgentExemple agent = new AgentExemple();
            agent.init("AgentHelloWorld", myNode);
            agent.dataName = new ArrayList<>(java.util.Arrays.asList(args));
            agent.data = new ArrayList<>();
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
                for (HRecord info : ((AgentExemple) ag).data) {
                    printInfo(info);
                }
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
