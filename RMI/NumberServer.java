package RMI;

import java.rmi.*;

public interface NumberServer extends Remote {

    public HRecord getDataFromName(String name) throws RemoteException;

}
