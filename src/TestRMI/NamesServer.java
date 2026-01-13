package TestRMI;

import java.util.List;

import java.rmi.*;

public interface NamesServer extends Remote {

    public List<String> getNames() throws RemoteException;
}
