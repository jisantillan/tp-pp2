package org.domingus;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.domingus.app.Domingus;
import org.domingus.interfaces.Notificable;

public class DomingusApp implements Notificable{
	
	public static void main(String[] args) {
		System.out.println("Iniciando DomingusApp!!");
		
		Domingus domingus = new Domingus();
		domingus.addObserver(new DomingusApp());
		try {
			domingus.init(args);
		} catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException
				| IllegalAccessException | InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessage(String message) {
		System.out.println("Ha llegado una notificación!");
		System.out.println(message);
	}

}