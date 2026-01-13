package CompHagent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

import Hagent.Client.*;

public class AgentExemple extends AgentAbs {

    private byte[] fileData;
    public int originalSize;

    public AgentExemple() {
    }

    @Override
    protected void runState(int state) {
        switch (state) {
            case 0:
                System.out.println("State 0: Je suis chez le client, je m'apprete a partir");
                move(getKnownNode("Server1"), 1);
                break;
            case 1:
                System.out.println("State 1: Je suis chez le serveur, je dis récupere le fichier");
                ServiceExample service = (ServiceExample) localServer.getService("FileReader");
                try {
                    byte[] data = service.getFile().readAllBytes();
                    originalSize = data.length;
                    fileData = compress(data);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("compression de : " + ((1.0 - (double)fileData.length / (double)originalSize) * 100) + "%");
                finish();
                break;
        }
    }

    public byte[] getFileData() throws IOException {
        return decompress(fileData);
    }

    @Override
    protected String getAgentJarPath () {
        return "CompHagent/AgentExemple.jar";
    }

    private static byte[] compress(byte[] in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DeflaterOutputStream defl = new DeflaterOutputStream(out);
        defl.write(in);
        defl.flush();
        defl.close();

        return out.toByteArray();
        
    }

    private static byte[] decompress(byte[] in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InflaterOutputStream infl = new InflaterOutputStream(out);
        infl.write(in);
        infl.flush();
        infl.close();

        return out.toByteArray();
    }
}