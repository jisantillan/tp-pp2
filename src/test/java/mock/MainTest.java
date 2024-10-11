package mock;

import java.io.FileNotFoundException;

import org.domingus.app.Domingus;
import org.domingus.init.DomingusFactory;
import org.domingus.interfaces.Notifier;
import org.domingus.interfaces.Source;

public class MainTest implements Notifier {
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		System.out.println("Iniciando DomingusApp");

		Source dataSender = new DataSender(2000);
		DomingusFactory factory = new DomingusFactory();
		Domingus domingus = factory.create(dataSender, "src\\test\\resources\\extensions\\");

		domingus.addObserver(new MainTest());

	}

	@Override
	public void notify(String message) {
		System.out.println("\nHa llegado una notificación");
		System.out.println(message);
	}

}