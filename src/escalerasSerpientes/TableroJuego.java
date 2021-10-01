package escalerasSerpientes;

import java.util.ArrayList;

public class TableroJuego {

	private ArrayList<ArrayList<Integer>> tablero = new ArrayList<ArrayList<Integer>>(10);
	private EscalerasSerpientes escaleraSerpiente;

	private ArrayList<ArrayList<Integer>> escaleras = new ArrayList<>(), serpientes = new ArrayList<>();

	public TableroJuego() {

		escaleraSerpiente = new EscalerasSerpientes();

		escaleras.addAll(escaleraSerpiente.getEscaleras());
		serpientes.addAll(escaleraSerpiente.getSerpientes());

		initTablero();
	}

	public void reset() {
		tablero.clear();

		escaleras.clear();
		serpientes.clear();

		escaleraSerpiente.reset();

		escaleras.addAll(escaleraSerpiente.getEscaleras());
		serpientes.addAll(escaleraSerpiente.getSerpientes());

		initTablero();
	}

	private void initTablero() {

		int m = 100, n = 100;

		for (int i = 0; i < 10; i++) {
			tablero.add(i, new ArrayList<Integer>(10));

			for (int j = 0; j < 10 && ((i + 1) % 2) != 0; j++) {
				tablero.get(i).add(m);
				m--;
			}
			for (int k = 0; k < 10 && ((i + 1) % 2) == 0; k++) {
				tablero.get(i).add(n);
				n++;
			}

			if (((i + 1) % 2) != 0) {
				n = m - 9;
			} else {
				m -= 10;
			}

		}

	}

	public ArrayList<ArrayList<Integer>> getTablero() {
		return tablero;
	}

	public ArrayList<ArrayList<Integer>> getSerpientes() {
		return escaleraSerpiente.getSerpientes();
	}

	public ArrayList<ArrayList<Integer>> getEscaleras() {
		return escaleraSerpiente.getEscaleras();
	}

}
