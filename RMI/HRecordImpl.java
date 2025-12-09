package RMI;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class HRecordImpl extends UnicastRemoteObject implements HRecord {
    private String name;
    private String numero;
    private String adress;

    public HRecordImpl(String n, String num, String a) throws RemoteException {
        this.name = n;
        this.numero = num;
        this.adress = a;
    }

    @Override
    public String getAdress() {
        return this.adress;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getNumero() {
        return this.numero;
    }

}