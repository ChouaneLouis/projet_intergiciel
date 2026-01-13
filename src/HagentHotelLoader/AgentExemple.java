package HagentHotelLoader;
import Hagent.Client.*;
import Hagent.Commun.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class AgentExemple extends AgentAbs {

    private List<String> hotelNames;
    private Map<String,HRecord> infos;

    public AgentExemple() {}

    @Override
    protected void runState(int state) {
        switch (state) {
            case 0:
                System.out.println("State 0: Je suis chez le client, je m'apprete a partir");
                move(getKnownNode("Server1"), 1);
                break;
            case 1:
                System.out.println("State 1: Je suis chez le NamesServer, je récupère les noms");
                NamesService serviceNames = (NamesService) localServer.getService("names");
                this.hotelNames = serviceNames.getNames();
                move(getKnownNode("Server2"), 2);
                break;
            case 2:
                this.infos = new HashMap<>();
                System.out.println("State 1: Je suis chez le InfoServer, je récupère les informations");
                InfoService serviceInfo = (InfoService) localServer.getService("infos");

                for (String name : hotelNames){
                    System.out.println("Info for " + name);
                    HRecord info = serviceInfo.getDataFromName(name);
                    this.infos.put(name,info);
                }
                finish();
                break;
        }
    }

    @Override
    protected String getAgentJarPath () {
        return "HagentHotelLoader/HagentHotel.jar";
    }

    public Map<String,HRecord> getInfo(){
        return this.infos;
    }
}