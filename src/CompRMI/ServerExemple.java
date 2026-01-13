package CompRMI;


import java.rmi.*;

public interface ServerExemple extends Remote {

    public byte[] getData() throws RemoteException;
}
