package RMI;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class Client {

    public static void printInfo(HRecord info) {
        try {
            System.out.println("Name : " + info.getName() + "\nNumber : " + info.getAdress() + "\nAdress : "
                    + info.getNumber() + "\n");
        } catch (Exception e) {
            System.out.println("Failed to print info");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<>();

        try {
            // Getting stubs
            NamesServer ns = (NamesServer) Naming
                    .lookup("//" + args[0] + ":" + 1099 + "/namesserver");

            list = ns.getNames();
            System.out.println("Successfully consulted names server !\nNames fetched : " + list.toString());
        } catch (Exception ex) {
            System.err.println("Error catched during names lookup :");
            ex.printStackTrace();
        }

        // Getting info

        try {
            // Get stub
            InfoServer is = (InfoServer) Naming
                    .lookup("//" + args[0] + ":" + 2000 + "/infoserver");

            for (String string : list) {

                System.out.println("Info for " + string);
                HRecord info = is.getDataFromName(string);
                printInfo(info);
            }
            System.out.println("Succesfully consulted info server !\n");
        } catch (Exception e) {
            System.err.println("Error during info gathering");
            e.printStackTrace();
        }

        System.out.println("Done !");
    }

}
