package TestRMI;

import java.rmi.*;

public interface InfoServer extends Remote {

    public HRecord getDataFromName(String name) throws RemoteException;

}
