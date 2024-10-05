package org.domingus.test;

import org.domingus.app.Domingus;
import org.domingus.interfaces.NotificationPlatform;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MainTestEmptySource implements NotificationPlatform {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando DomingusApp");

        Domingus domingus = new Domingus(null);
        System.out.println("Se instancio Domingus");

        domingus.addObserver(new MainTest());
        try {
            domingus.init("src\\main\\resources\\extensions\\");
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException
                 | IllegalAccessException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("\nHa llegado una notificación");
        System.out.println(message);
    }
}
