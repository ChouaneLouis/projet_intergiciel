package TestRMI;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.out.println("Usage : java TestRMI.Client <names_server_ip> <info_server_ip> [<iterations_nb>]");
            return;
        }
        int iterations_nb = 100;
        if (args.length == 3) {
            try {
                iterations_nb = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("Third argument must be an integer corresponding to the number of iterations");
                return;
            }
        }

        System.out.println("Begining test speed for RMI with " + iterations_nb + " iterations\n");
        long startTime = System.currentTimeMillis();

        try {
            // Getting stub for first server
            NamesServer ns = (NamesServer) Naming
                    .lookup("//" + args[0] + ":" + 1099 + "/namesserver");
            
            // Getting stub for second server
            InfoServer is = (InfoServer) Naming
                    .lookup("//" + args[1] + ":" + 2000 + "/infoserver");

            for (int i = 0; i < iterations_nb; i++) {
                // Geting name list from names server
                List<String> list = ns.getNames();

                // Getting data from info server
                for (String name : list) {
                    @SuppressWarnings("unused")
                    HRecord hr = is.getDataFromName(name);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Test finished in " + (System.currentTimeMillis() - startTime) + " ms\n");
    }

}
