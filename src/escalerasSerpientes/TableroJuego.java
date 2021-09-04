package escalerasSerpientes;

import java.util.ArrayList;

public class TableroJuego {

	ArrayList<ArrayList<Integer>> tablero = new ArrayList<ArrayList<Integer>>(10);

	public TableroJuego() {
		initTablero();
		viewList();
	}

	private void initTablero() {
		for (int i = 0; i < 10; i++) {
			tablero.add(i, new ArrayList<Integer>(10));
			for (int j = 0; j < 10; j++) {
				tablero.get(i).add(0);
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

}
