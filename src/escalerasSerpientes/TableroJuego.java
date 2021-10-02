package escalerasSerpientes;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class TableroJuego. Clase que contiene la informacion del tablero y las
 * escaleras, serpientes.
 */
public class TableroJuego {

	/** The tablero. */
	private ArrayList<ArrayList<Integer>> tablero = new ArrayList<>(10);

	/** The escalera serpiente. */
	private GeneradorEscalerasSerpientes escaleraSerpiente;

	/** The serpientes. */
	private ArrayList<ArrayList<Integer>> escaleras = new ArrayList<>(), serpientes = new ArrayList<>();

	/**
	 * Instantiates a new tablero juego.
	 */
	public TableroJuego() {

		escaleraSerpiente = new GeneradorEscalerasSerpientes();

		escaleras.addAll(escaleraSerpiente.getEscaleras());
		serpientes.addAll(escaleraSerpiente.getSerpientes());

		initTablero();
	}

	/**
	 * Reset. Reinicia el tablero
	 */
	public void reset() {

		escaleras.clear();
		serpientes.clear();

		escaleraSerpiente.reset();

		escaleras.addAll(escaleraSerpiente.getEscaleras());
		serpientes.addAll(escaleraSerpiente.getSerpientes());
	}

	/**
	 * Inits the tablero. Inicia un tablero nuevo. Unicamente con numeros.
	 */
	private void initTablero() {

		int m = 100, n = 100;

		for (int i = 0; i < 10; i++) {
			tablero.add(i, new ArrayList<Integer>(10));

			for (int j = 0; j < 10 && (i + 1) % 2 != 0; j++) {
				tablero.get(i).add(m);
				m--;
			}
			for (int k = 0; k < 10 && (i + 1) % 2 == 0; k++) {
				tablero.get(i).add(n);
				n++;
			}

			if ((i + 1) % 2 != 0) {
				n = m - 9;
			} else {
				m -= 10;
			}

		}

	}

	/**
	 * Gets the tablero.
	 *
	 * @return the tablero
	 */
	public ArrayList<ArrayList<Integer>> getTablero() {
		return tablero;
	}

	/**
	 * Gets the serpientes.
	 *
	 * @return the serpientes
	 */
	public ArrayList<ArrayList<Integer>> getSerpientes() {
		return escaleraSerpiente.getSerpientes();
	}

	/**
	 * Gets the escaleras.
	 *
	 * @return the escaleras
	 */
	public ArrayList<ArrayList<Integer>> getEscaleras() {
		return escaleraSerpiente.getEscaleras();
	}

}
