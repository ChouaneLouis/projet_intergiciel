package RMI;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class HRecordImpl extends UnicastRemoteObject implements HRecord {
    private String name;
    private String number;
    private String adress;

    public HRecordImpl(String n, String num, String a) throws RemoteException {
        this.name = n;
        this.number = num;
        this.adress = a;
    }

    @Override
    public String getAdress() throws RemoteException {
        return this.adress;
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    @Override
    public String getNumber() throws RemoteException {
        return this.number;
    }

    @Override
    public void printInfo() throws RemoteException {
        System.out.println("Name : " + this.name + "\nNumber : " + this.number + "\nAdress : " + this.adress + "\n");

    }

}