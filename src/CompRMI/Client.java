package CompRMI;

import java.rmi.Naming;
import java.util.List;
import java.util.ArrayList;

public class Client {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage : java TestRMI.Client <data_server_ip>");
            return;
        }
        int[] iterations_nb = {1, 1, 1, 1, 10, 20, 30, 40, 50, 75, 100};


        try {
            // Getting stub for first server
            ServerExemple ns = (ServerExemple) Naming
                    .lookup("//" + args[0] + ":" + 2000 + "/infoserver");
            

            List<byte[]> list = new ArrayList<>();
            for (int itnp : iterations_nb) {
                System.out.println("Begining test speed for RMI with " + itnp + " iterations\n");
                long startTime = System.currentTimeMillis();
                // Geting name list from names server
                for (int i = 0; i < itnp; i++) {
                    list.add(ns.getData());
                }

                System.out.println("Test finished in " + (System.currentTimeMillis() - startTime) + " ms\n");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

}
