import java.io.FileNotFoundException;

import org.domingus.app.Domingus;
import org.domingus.init.DomingusFactory;
import org.domingus.interfaces.Observer;
import org.domingus.interfaces.Source;

public class Main implements Observer {
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Iniciando DomingusApp");

		Source sender = new SourceSender();
		Timer timer = new Timer(2000, (Runnable) sender);

		DomingusFactory factory = new DomingusFactory();
		Domingus domingus = factory.create(sender, "src\\test\\resources\\extensions\\");

		Main main = new Main();
		domingus.addObserver(main);
		domingus.addCurrentObserver(main.getClass().getSimpleName());

		timer.run();
	}

	@Override
	public void update(Object object) {
		System.out.println("\nHa llegado una notificación");
		System.out.println(object);
	}


}