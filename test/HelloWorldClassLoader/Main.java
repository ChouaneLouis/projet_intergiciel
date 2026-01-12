package HelloWorldClassLoader;
import HagentClassLoader.Commun.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import HagentClassLoader.Client.*;

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
            String[] dependancies = {
                "../../src/HagentClassLoader/Client/AgentAbs.class",
                "../../src/HagentClassLoader/Client/ConflictingNodeException.class",
                "./AgentExample.class"};
            agent.init("AgentHelloWorld", myNode);
            agent.addKnownNode(servNode);
            agent.run();

            System.out.println("Waiting....");
            Socket s = ss.accept();
            System.out.println("Recieved agent");

            InputStream inputIS = s.getInputStream();

            // deserialize
            ObjectInputStream ois = new ObjectInputStream(inputIS);

            Map<String, byte[]> dataMap = (Map<String, byte[]>) ois.readObject();
            Loader loader = new Loader();
            for (String className : dataMap.keySet()) {

                loader.addClass(className, dataMap.get(className));
            }

            InputStream is = s.getInputStream();
            DataInputStream in = new DataInputStream(is);
            int objLen = in.readInt();
            byte[] objBytes = in.readNBytes(objLen);

            ois = new ObjectInputStream(
                    new ByteArrayInputStream(objBytes)) {

                @Override
                protected Class<?> resolveClass(ObjectStreamClass desc)
                        throws IOException, ClassNotFoundException {
                    return loader.loadClass(desc.getName());
                }
            };

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