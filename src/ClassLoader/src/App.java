import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) throws Exception {
        Socket s = new  Socket("localhost", 8081);
        Auto auto = new Voiture();
        OutputStream os = s.getOutputStream();
        
        // Lecture code du .class 
        Path classFile = Paths.get("/mnt/hgfs/vms/ClassLoader/bin/Voiture.class");
        byte[] classBytes = Files.readAllBytes(classFile);

        // Serialization, Object. 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(auto);
        oos.flush();
        byte[] objectBytes = baos.toByteArray();

        DataOutputStream out = new DataOutputStream(os);

        // Envoie du nom de la classe
        out.writeUTF("Voiture");
        out.writeInt(classBytes.length);
        out.write(classBytes);

        // Envoie de l'objet. 
        out.writeInt(objectBytes.length);
        out.write(objectBytes);
        s.close();

    }
}
