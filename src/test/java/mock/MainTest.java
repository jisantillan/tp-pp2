package mock;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.domingus.app.Domingus;
import org.domingus.interfaces.NotificationPlatform;
import org.domingus.interfaces.Source;

public class MainTest implements NotificationPlatform {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Iniciando DomingusApp");

		Source dataSender = new DataSender(2000);
		Domingus domingus = new Domingus(dataSender);
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