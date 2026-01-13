package CompRMI;

import java.io.FileInputStream;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerExempleImpl extends UnicastRemoteObject implements ServerExemple {
    private byte[] data;

    public ServerExempleImpl() throws RemoteException {
        try {
            FileInputStream fis = new FileInputStream("CompRMI/exemple.txt");
            this.data = fis.readAllBytes();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getData() throws RemoteException {
        return this.data;
    }

    public static void main(String[] args) {
        // Source - https://stackoverflow.com/a
        // Posted by anirudha mundada
        // Retrieved 2025-11-25, License - CC BY-SA 3.0

        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(2000);
            registry.list();
        } catch (RemoteException e) {
            try {
                registry = LocateRegistry.createRegistry(2000);
            } catch (Exception ex) {
                System.err.println("Failed to open registery");
            }
        }

        try {
            ServerExemple ns = new ServerExempleImpl();
            Naming.rebind("//" + InetAddress.getLocalHost().getHostName() + ":" + 2000 + "/infoserver", ns);
            System.out.println("Info Server ready !");

        } catch (Exception e) {
            System.err.println("Erreur dans le Naming\n");
            e.printStackTrace();
        }
    }
}
