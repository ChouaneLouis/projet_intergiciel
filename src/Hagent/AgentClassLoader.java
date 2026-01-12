package Hagent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class AgentClassLoader extends ClassLoader {

    private final Map<String, byte[]> classes = new HashMap<>();

    public AgentClassLoader(ClassLoader parent, byte[] jarBytes)
            throws IOException {
        super(parent);
        loadJar(jarBytes);
    }

    private void loadJar(byte[] jarBytes) throws IOException {
        try (JarInputStream jis =
                 new JarInputStream(new ByteArrayInputStream(jarBytes))) {

            JarEntry entry;
            while ((entry = jis.getNextJarEntry()) != null) {

                if (!entry.getName().endsWith(".class")) {
                    continue;
                }

                String className = entry.getName()
                        .replace('/', '.')
                        .replace(".class", "");

                byte[] bytecode = jis.readAllBytes();
                classes.put(className, bytecode);
            }
        }
    }

    @Override
    protected Class<?> findClass(String name)
            throws ClassNotFoundException {

        byte[] bytecode = classes.get(name);
        if (bytecode == null) {
            throw new ClassNotFoundException(name);
        }

        return defineClass(name, bytecode, 0, bytecode.length);
    }
}
