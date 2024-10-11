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

import org.domingus.interfaces.Notifier;

public class Discoverer {

    private static final String JAR_EXTENSION = ".jar";

    public Set<Notifier> discover(String directoryPath) throws FileNotFoundException {
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            return new HashSet<>();
        }

        return exploreDirectory(directoryPath);
    }

    private Set<Notifier> exploreDirectory(String directoryPath) {
        Set<Notifier> notifiers = new HashSet<>();
        scanFilesInPath(new File(directoryPath), notifiers);
        return notifiers;
    }

    private void scanFilesInPath(File currentPath, Set<Notifier> notifiers) {
        if (!currentPath.exists()) {
            return;
        }

        if (currentPath.isDirectory()) {
            File[] files = currentPath.listFiles();
            if (files != null) {
                for (File file : files) {
                    scanFilesInPath(file, notifiers);
                }
            }
        } else if (currentPath.isFile() && currentPath.getName().endsWith(JAR_EXTENSION)) {
            notifiers.addAll(discoverNotifiersInJar(currentPath));
        }
    }

    private Set<Notifier> discoverNotifiersInJar(File jarFile) {
        Set<Notifier> notifiers = new HashSet<>();

        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration<JarEntry> jarEntries = jar.entries();

            while (jarEntries.hasMoreElements()) {
                JarEntry entry = jarEntries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    notifiers.add(createInstanceFromClass(jarFile, entry));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading jar file: " + e.getMessage());
        }

        return notifiers;
    }

    private Notifier createInstanceFromClass(File jarFile, JarEntry entry) {
        Notifier instance = null;
        try {
            Class<?> clazz = loadClassFromJar(jarFile, entry.getName());
            if (clazz != null && Notifier.class.isAssignableFrom(clazz)) {
                instance = (Notifier) clazz.getDeclaredConstructor().newInstance();
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