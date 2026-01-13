package Hagent.Serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class AgentObjectInputStream extends ObjectInputStream {

    private final ClassLoader agentClassLoader;

    public AgentObjectInputStream(InputStream in, ClassLoader loader) throws IOException {
        super(in);
        this.agentClassLoader = loader;
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {

        String className = desc.getName();
        return Class.forName(className, false, agentClassLoader);
    }
}
