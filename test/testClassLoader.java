import java.io.IOError;
import java.io.IOException;
import java.util.jar.JarFile;

import java.io.InputStream;

public class testClassLoader {

    public static void main(String[] args) {

        // read (File) file from socket

        String file = args[0];

        try {
            JarFile jf = new JarFile(file);
            InputStream is = jf.getInputStream(jf.getEntry("AgentImpl.class"));
            // create classloader using the .class file
        } catch (IOException e) {
            System.err.println("Erreur à l'ouverture du jar");
            e.printStackTrace();
        }

    }
}
