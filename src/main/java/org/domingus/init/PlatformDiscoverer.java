package org.domingus.init;

import org.domingus.interfaces.NotificationPlatform;

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

public class PlatformDiscoverer {

    private final String JAR_EXTENSION = ".jar";
    public Set<NotificationPlatform> discover(String path) throws FileNotFoundException {
        File directory = new File(path);

        if (!directory.exists()) {
            throw new FileNotFoundException("Location does not exist: " + path);
        }

        return findClasses(path);
    }

    private Set<NotificationPlatform> findClasses(String path) {
        Set<NotificationPlatform> platforms = new HashSet<>();
        findClassesInPath(new File(path), platforms);
        return platforms;
    }

    private void findClassesInPath(File path, Set<NotificationPlatform> platforms) {
        if (!path.exists()) {
            return;
        }

        if (path.isDirectory()) {
            File[] files = path.listFiles();
            if (files != null) {
                for (File file : files) {
                    findClassesInPath(file, platforms);
                }
            }
        } else if (path.isFile() && path.getName().endsWith(JAR_EXTENSION)) {
            platforms.addAll(findPlatformsInJar(path));
        }
    }

    private Set<NotificationPlatform> findPlatformsInJar(File jarFile) {
        Set<NotificationPlatform> platforms = new HashSet<>();

        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration<JarEntry> entries = jar.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    instantiateClassFromJar(jarFile, entry, platforms);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading jar file: " + e.getMessage());
        }

        return platforms;
    }

    private void instantiateClassFromJar(File jarFile, JarEntry entry, Set<NotificationPlatform> platforms) {
        try {
            Class<?> cls = loadClassFromJar(jarFile, entry.getName());
            if (cls != null && NotificationPlatform.class.isAssignableFrom(cls)) {
                platforms.add((NotificationPlatform) cls.getDeclaredConstructor().newInstance());
            }
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            System.out.println("Error instantiating class: " + e.getMessage());
        }
    }

    private Class<?> loadClassFromJar(File jarFile, String className) {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{jarFile.toURI().toURL()});
            String canonicalClassName = className.replace("/", ".").replace(".class", "");
            return Class.forName(canonicalClassName, true, classLoader);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading class from jar: " + e.getMessage());
            return null;
        }
    }

}
