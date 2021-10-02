package escalerasSerpientes;

import java.util.ArrayList;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneradorEscalerasSerpientes. Clase que se encarga de generar las
 * posiciones de las escaleras y las serpientes
 */
public class GeneradorEscalerasSerpientes {

	/** The serpientes. */
	private ArrayList<ArrayList<Integer>> escaleras = new ArrayList<>(), serpientes = new ArrayList<>();

	/**
	 * Instantiates a new generador escaleras serpientes.
	 */
	public GeneradorEscalerasSerpientes() {
		// TODO Auto-generated constructor stub
		initEscaleras();
		initSerpientes();
	}

	/**
	 * Reset. Limpia y añade unas posiciones nuevas.
	 */
	public void reset() {
		escaleras.clear();
		serpientes.clear();

		initEscaleras();
		initSerpientes();
	}

	/**
	 * Inits the escaleras. Crea "aleatoriamente" las posiciones de las escaleras
	 * siguiendo un cierto patron
	 */
	private void initEscaleras() {
		// TODO Auto-generated method stub
		Random temp = new Random();

		int randomN1 = temp.nextInt(5) + 10;
		int aumenta = 18;

		int zona = 0;

		while (!(escaleras.size() == 5)) {

			if (!isContained(randomN1, serpientes, escaleras) && !isContained(randomN1 + 18, serpientes, escaleras)) {

				ArrayList<Integer> temporal = new ArrayList<>(2);

				temporal.add(randomN1);
				temporal.add(randomN1 + aumenta);

				zona += 11;

				escaleras.add(temporal);
			}
			randomN1 = temp.nextInt(5) + 18 + zona;
		}
	}

	/**
	 * Inits the serpientes. Crea "aleatoriamente" las posiciones de las serpientes
	 * siguiendo un cierto patron
	 */
	private void initSerpientes() {
		// TODO Auto-generated method stub
		Random temp = new Random();

		int randomN1 = temp.nextInt(5) + 25;
		int reduce = 18;

		int zona = 0;

		while (!(serpientes.size() == 5)) {

			if (!isContained(randomN1, serpientes, escaleras) && !isContained(randomN1 - 18, serpientes, escaleras)) {

				ArrayList<Integer> temporal = new ArrayList<>(2);

				temporal.add(randomN1);
				temporal.add(randomN1 - reduce);

				zona += 11;

				serpientes.add(temporal);
			}
			randomN1 = temp.nextInt(5) + 25 + zona;

		}

	}

	/**
	 * Checks if is contained. Funcion auxiliar que ayuda en la creacion de las
	 * serpientes y escaleras
	 *
	 * @param in     the in
	 * @param array  the array
	 * @param array2 the array 2
	 * @return true, if is contained
	 */
	private boolean isContained(Integer in, ArrayList<ArrayList<Integer>> array, ArrayList<ArrayList<Integer>> array2) {

		for (ArrayList<Integer> lista : array) {
			for (Integer integ : lista) {
				if (integ == in)
					return true;
			}
		}

		for (ArrayList<Integer> lista : array2) {
			for (Integer integ : lista) {
				if (integ == in)
					return true;
			}
		}

		return false;
	}

	/**
	 * Gets the escaleras.
	 *
	 * @return the escaleras
	 */
	public ArrayList<ArrayList<Integer>> getEscaleras() {
		return escaleras;
	}

	/**
	 * Gets the serpientes.
	 *
	 * @return the serpientes
	 */
	public ArrayList<ArrayList<Integer>> getSerpientes() {
		return serpientes;
	}

}
