import java.io.FileNotFoundException;

import org.domingus.app.Domingus;
import org.domingus.init.DomingusFactory;
import org.domingus.interfaces.Observer;
import org.domingus.interfaces.Source;

public class Main implements Observer {
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Iniciando DomingusApp");

		Source dataSender = new SourceSender(2000);
		DomingusFactory factory = new DomingusFactory();
		Domingus domingus = factory.create(dataSender, "src\\test\\resources\\extensions\\");

		Main main = new Main();
		domingus.addObserver(main);
		domingus.addCurrentObserver(main.getClass().getSimpleName());

	}

	@Override
	public void update(Object object) {
		System.out.println("\nHa llegado una notificación");
		System.out.println(object);
	}


}