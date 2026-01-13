package TestRMI;

import java.rmi.*;

public interface HRecord extends Remote {
    public String getName() throws RemoteException;

    public String getNumber() throws RemoteException;

    public String getAdress() throws RemoteException;

    public void printInfo() throws RemoteException;
}
