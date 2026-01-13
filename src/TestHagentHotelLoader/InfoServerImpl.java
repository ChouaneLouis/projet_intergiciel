package TestHagentHotelLoader;

import Hagent.Commun.*;
import Hagent.Serveur.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class InfoServerImpl extends ServerAbs {

        Map<String, HRecord> hotelMap;

        public InfoServerImpl(Socket s) {
                super(s);
                this.services = new HashMap<String, Service>();
                this.services.put("infos",new InfoServiceImpl());
                this.serversList = new Node[0]; //pas de noeuds connus
        }

        public InfoServerImpl(Node n) {
                super(n);
        }

        @Override
        public ServerAbs newServer(Socket s) {
                return new InfoServerImpl(s);
        }

        public static void main(String[] args) {
                System.out.println("Starting Server");
                if (args.length != 3) {
                System.err.println("Usage: java InfoServerImpl <name> <address> <port>");
                System.exit(1);
                }
                myNode = new Node(args[0], args[1], Integer.parseInt(args[2]));
                new InfoServerImpl(myNode);
        }

}
