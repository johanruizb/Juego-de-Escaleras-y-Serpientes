package escalerasSerpientes;

import java.util.ArrayList;

public class ControlJuego {
	private Dado dado;
	private TableroJuego tablero = new TableroJuego();
	private Jugador j1, j2, j3;

	public ControlJuego() {
		dado = new Dado();
	}

	public void setName(String s) {
		if (j1 == null) {
			j1 = new Jugador(s);
			j2 = new Jugador("NPC 1");
			j3 = new Jugador("NPC 2");
		}
	}

	public int lanzarDados(int i) {
		int d1 = dado.caraDado();

		switch (i) {
		case 1:
			j1.setPosicion(d1);

//			System.out.println("J1: " + j1.getPosicion());

			boolean[] x = pisando(j1);

			if (x[0]) {
				j1.escalera();
//				System.out.println("J1: " + j1.getPosicion());
			} else if (x[1]) {
				j1.serpiente();
//				System.out.println("J1: " + j1.getPosicion());
			}
			break;
		case 2:
			j2.setPosicion(d1);

//			System.out.println("J2: " + j2.getPosicion());

			boolean[] x1 = pisando(j2);

			if (x1[0]) {
				j2.escalera();
//				System.out.println("J2: " + j2.getPosicion());

			} else if (x1[1]) {
				j2.serpiente();
//				System.out.println("J2: " + j2.getPosicion());

			}
			break;
		case 3:
			j3.setPosicion(d1);

//			System.out.println("J3: " + j3.getPosicion());

			boolean[] x2 = pisando(j3);

			if (x2[0]) {
				j3.escalera();
//				System.out.println("J3: " + j3.getPosicion());

			} else if (x2[1]) {
				j3.serpiente();
//				System.out.println("J3: " + j3.getPosicion());

			}
			break;
		default:
			break;
		}

		return d1;
	}

	private boolean[] pisando(Jugador j) {
		// TODO Auto-generated method stub

		boolean escalera = false;
		boolean serpiente = false;

		for (ArrayList<Integer> list1 : tablero.getSerpientes()) {
			if (list1.get(0) == j.getPosicion()) {
				serpiente = true;
			} else {
				for (ArrayList<Integer> list2 : tablero.getEscaleras()) {
					if (list2.get(0) == j.getPosicion()) {
						escalera = true;
					}

				}
			}
		}

		return new boolean[] { escalera, serpiente };
	}

	// ----------

	public boolean isWin() {

		if (j1.getPosicion() >= 100) {
			return true;
		} else if (j2.getPosicion() >= 100) {
			return true;
		} else if (j3.getPosicion() >= 100) {
			return true;
		}

		return false;
	}

	public ArrayList<ArrayList<Integer>> getTablero() {
		return tablero.getTablero();
	}

	public ArrayList<ArrayList<Integer>> getSerpientes() {
		return tablero.getSerpientes();
	}

	public ArrayList<ArrayList<Integer>> getEscaleras() {
		return tablero.getEscaleras();
	}

}
