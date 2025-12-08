package src.Hagent;

import java.net.Socket;

public class ServerExample extends ServerAbs {

    public ServerExample(Socket newSock) {
        this.s = newSock;
        this.services.put("Test", new ServiceExample());

    }

    @Override
    protected ServerAbs newServer(Socket s) {
        return new ServerExample(s);
    }

}
