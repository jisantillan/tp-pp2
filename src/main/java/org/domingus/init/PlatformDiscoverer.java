package org.domingus.init;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.domingus.interfaces.Notificable;

public class PlatformDiscoverer {

    private static final String JAR_EXTENSION = ".jar";

    public Set<Notificable> discover(String directoryPath) throws FileNotFoundException {
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            throw new FileNotFoundException("Location does not exist: " + directoryPath);
        }

        return exploreDirectory(directoryPath);
    }

    private Set<Notificable> exploreDirectory(String directoryPath) {
        Set<Notificable> platforms = new HashSet<>();
        scanFilesInPath(new File(directoryPath), platforms);
        return platforms;
    }

    private void scanFilesInPath(File currentPath, Set<Notificable> platforms) {
        if (!currentPath.exists()) {
            return;
        }

        if (currentPath.isDirectory()) {
            File[] files = currentPath.listFiles();
            if (files != null) {
                for (File file : files) {
                    scanFilesInPath(file, platforms);
                }
            }
        } else if (currentPath.isFile() && currentPath.getName().endsWith(JAR_EXTENSION)) {
            platforms.addAll(discoverPlatformsInJar(currentPath));
        }
    }

    private Set<Notificable> discoverPlatformsInJar(File jarFile) {
        Set<Notificable> platforms = new HashSet<>();

        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration<JarEntry> jarEntries = jar.entries();

            while (jarEntries.hasMoreElements()) {
                JarEntry entry = jarEntries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    platforms.add(createInstanceFromClass(jarFile, entry));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading jar file: " + e.getMessage());
        }

        return platforms;
    }

    private Notificable createInstanceFromClass(File jarFile, JarEntry entry) {
        Notificable instance = null;
        try {
            Class<?> clazz = loadClassFromJar(jarFile, entry.getName());
            if (clazz != null && Notificable.class.isAssignableFrom(clazz)) {
                instance = (Notificable) clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.err.println("Error instantiating class: " + e.getMessage());
        }
        return instance;
    }

    private Class<?> loadClassFromJar(File jarFile, String className) {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{jarFile.toURI().toURL()});
            String formattedClassName = className.replace("/", ".").replace(".class", "");
            return Class.forName(formattedClassName, true, classLoader);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading class from jar: " + e.getMessage());
            return null;
        }
    }
}