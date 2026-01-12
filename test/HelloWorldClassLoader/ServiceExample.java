package HelloWorldClassLoader;

import HagentClassLoader.Service;

public class ServiceExample implements Service {

    public void sayHello() {
        System.out.println("Hello\n");
    }

    public void sayWorld() {
        System.out.println("World\n");
    }

}
