package CompHagent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

import Hagent.Client.*;

public class AgentExemple extends AgentAbs {

    private byte[] fileData = new byte[0];
    public int originalSize;
    private int itnb;

    public AgentExemple(int itnb) {
        this.itnb = itnb;
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
                    ByteArrayOutputStream allFilesStream = new ByteArrayOutputStream();

                    for (int i = 0; i < itnb; i++) {
                        byte[] data = service.getFile().readAllBytes();
                        allFilesStream.write(data);
                    }

                    byte[] allData = allFilesStream.toByteArray();
                    originalSize = allData.length;

                    fileData = compress(allData);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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