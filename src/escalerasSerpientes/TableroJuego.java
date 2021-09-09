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
//		viewList();
//		viewList2();
	}

	private void initTablero() {

		// int k = 1;
		int m = 100, n = 100;

//		for (int i = 0; i < 10; i++) {
//			tablero.add(i, new ArrayList<Integer>(10));
//			for (int j = 0; j < 10; j++) {
//				tablero.get(i).add(k);
//				k++;
//			}
//		}

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

	private void viewList() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(tablero.get(i).get(j) + " ");
				if (j == 9)
					System.out.println();
			}
		}
	}

	private void viewList2() {
		for (int i = 0; i < escaleras.size(); i++) {
			for (int j = 0; j < escaleras.get(0).size(); j++) {
				System.out.print(escaleras.get(i).get(j) + " ");
				if (j == escaleras.get(0).size() - 1)
					System.out.println();
			}
		}
	}

	public ArrayList<ArrayList<Integer>> getTablero() {
		return tablero;
	}

	public ArrayList<ArrayList<Integer>> getSerpientes() {
		return escaleraSerpiente.getSerpientes();
	}

}
