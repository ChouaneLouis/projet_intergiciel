package CompHagent;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;

public class ServiceExampleImpl implements ServiceExample {

    public FileInputStream getFile() throws IOException {
        File file = new File("./CompHagent/exemple.txt");
        return new FileInputStream(file);
    }

}
