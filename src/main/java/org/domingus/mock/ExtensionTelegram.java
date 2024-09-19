package org.domingus.mock;

import org.domingus.interfaces.Notificable;

public class ExtensionTelegram implements Notificable { //TODO agregar esta clase al repositorio de extension

	@Override
	public void sendMessage(String message) {
		System.out.println("Soy Telegram y has recibido una notificacion!!");
		System.out.println(message);
	}

}