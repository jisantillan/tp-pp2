import java.io.FileNotFoundException;

import org.domingus.app.Domingus;
import org.domingus.init.DomingusFactory;
import org.domingus.interfaces.Observer;
import org.domingus.interfaces.Source;

import static java.util.Objects.nonNull;

public class Main implements Observer {
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Iniciando DomingusApp");
		Source sender = new SourceSender();

		Integer timeInterval = null;
		try	{
			timeInterval = Integer.valueOf(args[0]);
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			System.out.println("Error obteniendo los segundos por parámetro");
		}

		if(nonNull(timeInterval)) {
			Timer timer = new Timer(timeInterval, (Runnable) sender);

			DomingusFactory factory = new DomingusFactory();
			Domingus domingus = factory.create(sender, "src\\test\\resources\\extensions\\");

			Main main = new Main();
			domingus.addObserver(main);
			domingus.addCurrentObserver(main.getClass().getSimpleName());

			timer.run();
		}

	}

	@Override
	public void update(Object object) {
		System.out.println("\nHa llegado una notificación");
		System.out.println(object);
	}


}