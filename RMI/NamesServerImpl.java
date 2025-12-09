package RMI;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.LinkedList;

public class NamesServerImpl implements NamesServer {
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
        return this.names;
    }

    public static void main(String[] args) {

    }
}
