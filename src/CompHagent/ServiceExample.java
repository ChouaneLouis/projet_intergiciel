package CompHagent;
import Hagent.Commun.Service;

import java.io.FileInputStream;
import java.io.IOException;

public interface ServiceExample extends Service {

    public FileInputStream getFile() throws IOException;

}
