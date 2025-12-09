package RMI;

import java.util.LinkedList;

import java.rmi.*;

public interface NamesServer extends Remote {

    public LinkedList<String> getNames() throws RemoteException;
}
