
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.net.ServerSocket;
import java.net.Socket;

public class App2 {

    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(8081);
        Socket ss = s.accept();
        InputStream is = ss.getInputStream();
        DataInputStream in = new DataInputStream(is);

        String className = in.readUTF();
        int classLen = in.readInt();
        byte[] classBytes = in.readNBytes(classLen);

        int objLen = in.readInt();
        byte[] objBytes = in.readNBytes(objLen);

        Loader loader = new Loader();
        loader.addClass(className, classBytes);

        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(objBytes)) {

            @Override
            protected Class<?> resolveClass(ObjectStreamClass desc)
                    throws IOException, ClassNotFoundException {
                return loader.loadClass(desc.getName());
            }
        };

        Auto obj = (Auto) ois.readObject();
        obj.getCouleur();
        s.close();
        
    }

}
