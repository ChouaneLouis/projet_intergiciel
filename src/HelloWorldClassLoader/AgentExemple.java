package HelloWorldClassLoader;
import Hagent.Client.*;

public class AgentExemple extends AgentAbs {

    public AgentExemple() {
    }

    @Override
    protected void runState(int state) {
        switch (state) {
            case 0:
                System.out.println("State 0: Je suis chez le client, je m'apprete a partir");
                move(getKnownNode("Server1"), 1);
                break;
            case 1:
                System.out.println("State 1: Je suis chez le serveur, je dis Hello World");
                ServiceExample service = (ServiceExample) localServer.getService("Test");
                service.sayHello();
                service.sayWorld();
                finish();
                break;
        }
    }

    @Override
    protected String getAgentJarPath () {
        return "HelloWorldClassLoader/AgentExemple.jar";
    }
}