package org.domingus.mock;

import org.domingus.interfaces.Notificable;

public class ExtensionWhatsapp implements Notificable { //TODO agregar esta clase al repositorio de extension

	@Override
	public void sendMessage(String message) {
		System.out.println("Soy Whatsapp y has recibido una notificacion!!");
		System.out.println(message);
	}

}