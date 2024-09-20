package org.domingus.mock;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.domingus.interfaces.Notificable;

public class ExtensionTelegram implements Notificable {

	// Reemplaza con tu token del bot de Telegram
	private static final String TELEGRAM_BOT_TOKEN = "17785419552:AAElsFSdQPqIGNU_jIBeE0QUQvhRQzT67UI";

	// Reemplaza con tu chat ID o el ID del grupo
	private static final String TELEGRAM_CHAT_ID = "1245307063";

	@Override
	public void sendMessage(String message) {
		try {
			String apiUrl = "https://api.telegram.org/bot" + TELEGRAM_BOT_TOKEN + "/sendMessage";
			String urlParameters = "chat_id=" + TELEGRAM_CHAT_ID + "&text=" + message;

			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			// Enviar el mensaje
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(urlParameters.getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();

			// Obtener la respuesta
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				System.out.println("Mensaje enviado correctamente a Telegram");
			} else {
				System.out.println("Error al enviar el mensaje a Telegram: " + responseCode);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
