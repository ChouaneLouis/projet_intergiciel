package test.TestHelloWorld;

import src.Hagent.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting HelloWorld Test");

        // TODO Creation du thread pour récuperer l'agent une fois fini

        // TODO Creation d'un serveur pour recevoir l'agent (sur un thread séparé)

        AgentExemple agent = new AgentExemple();
        agent.addKnownNode(new Node("Server1", "localhost", 8081));
        agent.init("AgentHelloWorld", new Node("origin", "localhost", 8080));
        agent.run();

        // TODO Programme cabale de récupérer l'agent apres son finish

        System.out.println("HelloWorld Test Finished");
    }
}