package TestHagentHotelLoader;
import Hagent.Client.*;
import Hagent.Commun.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class AgentExemple extends AgentAbs {

    private List<String> hotelNames;
    private List<HRecord> infos;
    private int iterations;

    public AgentExemple(int iterations_nb) {
        this.iterations = iterations_nb;
    }

    @Override
    protected void runState(int state) {
        switch (state) {
            case 0:
                System.out.println("State 0: Je suis chez le client, je m'apprete a partir");
                move(getKnownNode("Server1"), 1);
                break;
            case 1:
                System.out.println("State 1: Je suis chez le NamesServer, je récupère les noms");
                this.hotelNames = new LinkedList<>();
                NamesService serviceNames = (NamesService) localServer.getService("names");
                for (int i = 0; i < this.iterations ; i++) {
                    this.hotelNames.addAll(serviceNames.getNames());
                }
                move(getKnownNode("Server2"), 2);
                break;
            case 2:
                this.infos = new LinkedList<>();
                System.out.println("State 1: Je suis chez le InfoServer, je récupère les informations");
                InfoService serviceInfo = (InfoService) localServer.getService("infos");

                for (String name : hotelNames){
                    System.out.println("Info for " + name);
                    HRecord info = serviceInfo.getDataFromName(name);
                    this.infos.add(info);
                }
                finish();
                break;
        }
    }

    @Override
    protected String getAgentJarPath () {
        return "TestHagentHotelLoader/HagentHotel.jar";
    }

    public List<HRecord> getInfo(){
        return this.infos;
    }
}