package CompRMI;

import java.rmi.Naming;
import java.util.List;
import java.util.ArrayList;

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
            ServerExemple ns = (ServerExemple) Naming
                    .lookup("//" + args[0] + ":" + 2000 + "/infoserver");
            

            List<byte[]> list = new ArrayList<>();
            for (int i = 0; i < iterations_nb; i++) {
                // Geting name list from names server
                list.add(ns.getData());

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Test finished in " + (System.currentTimeMillis() - startTime) + " ms\n");
    }

}
