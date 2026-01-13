package TestHagentHotelLoader;
import java.io.Serializable;

public interface HRecord extends Serializable{
    public String getName();

    public String getNumber();

    public String getAdress();

    public void printInfo();
}
