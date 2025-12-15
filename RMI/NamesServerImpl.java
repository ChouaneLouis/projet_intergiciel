package RMI;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.LinkedList;

public class NamesServerImpl extends UnicastRemoteObject implements NamesServer {
    private LinkedList<String> names;

    public NamesServerImpl() throws RemoteException {
        this.names = new LinkedList<>(Arrays.asList(
                "The Golden Horizon Hotel",
                "Silverwave Resort & Spa",
                "Blue Orchid Inn",
                "Emerald Coast Retreat",
                "The Grand Maple Lodge",
                "Crimson Peak Hotel",
                "Sunset Crest Suites",
                "The Whispering Pines Resort",
                "Oceanview Serenity Hotel",
                "Royal Harbor Boutique Hotel",
                "Mountain Breeze Chalet",
                "Starlight Haven",
                "The Velvet Garden Hotel",
                "Crescent Bay Lodge",
                "Azure Sky Resort",
                "Amberstone Suites",
                "Riverside Lantern Inn",
                "Moonridge Palace",
                "Harborlight Grand Hotel",
                "Sapphire Spring Retreat"));
    }

    @Override
    public LinkedList<String> getNames() throws RemoteException {
        System.out.println("Sending names...\n");
        return this.names;
    }

    public static void main(String[] args) {
        // Source - https://stackoverflow.com/a
        // Posted by anirudha mundada
        // Retrieved 2025-11-25, License - CC BY-SA 3.0

        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(1099);// use any no. less than 55000
            registry.list();
            // This call will throw an exception if the registry does not already exist
        } catch (RemoteException e) {
            try {
                registry = LocateRegistry.createRegistry(1099);
            } catch (Exception ex) {
                System.err.println("Failed to open registery");
            }
        }

        try {
            NamesServer ns = new NamesServerImpl();
            Naming.rebind("//" + InetAddress.getLocalHost().getHostName() + ":" + 1099 + "/namesserver", ns);
            System.out.println("Names Server ready !");

        } catch (Exception e) {
            System.err.println("Erreur dans le Naming\n");
            e.printStackTrace();
        }
    }
}
