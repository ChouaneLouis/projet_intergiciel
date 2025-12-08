package test.TestHelloWorld;

import java.net.Socket;

import src.Hagent.ServerAbs;

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
