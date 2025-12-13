package RMI;

import java.rmi.*;

public interface InfoServer extends Remote {

    public HRecord getDataFromName(String name) throws RemoteException;

}
