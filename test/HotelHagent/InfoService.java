package HotelHagent;

import Hagent.Service;

public interface InfoService extends Service {

    public HRecord getDataFromName(String name);

}
