package HotelHagent;

import java.util.List;

import Hagent.AgentAbs;

public class AgentExemple extends AgentAbs {

    public List<String> dataName;
    public List<HRecord> data;

    public AgentExemple() {}

    @Override
    protected void runState(int state) {
        switch (state) {
            case 0:
                System.out.println("State 0: Je suis chez le client, je m'apprete a partir");
                move(getKnownNode("Server1"), 1);
                break;
            case 1:
                System.out.println("State 1: Je suis chez le serveur, je dis Hello World");
                InfoService service = (InfoServiceImpl) localServer.getService("Test");
                
                for (String name : dataName) {
                    HRecord record = service.getDataFromName(name);
                    data.add(record);
                }

                finish();
                break;
        }
    }
}