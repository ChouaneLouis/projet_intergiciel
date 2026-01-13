package HagentHotelLoader;
import Hagent.Commun.*;
import java.util.NoSuchElementException;

public interface InfoService extends Service {

    public HRecord getDataFromName(String name) throws NoSuchElementException;

}