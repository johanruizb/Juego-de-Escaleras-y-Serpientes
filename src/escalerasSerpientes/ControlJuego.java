package escalerasSerpientes;

import java.util.ArrayList;
import java.util.Iterator;

public class ControlJuego {
	private Dado dado;
	private TableroJuego tablero = new TableroJuego();
	private Jugador j1, j2, j3;

	private int esc = -1, serp = -1;

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

			boolean[] x = pisando(j1);

			if (x[0]) {
				j1.escalera();
				esc = 1;
			} else if (x[1]) {
				j1.serpiente();
				serp = 1;
			}
			break;
		case 2:
			j2.setPosicion(d1);
			boolean[] x1 = pisando(j2);

			if (x1[0]) {
				j2.escalera();
				esc = 2;
			} else if (x1[1]) {
				j2.serpiente();
				serp = 2;
			}
			break;
		case 3:
			j3.setPosicion(d1);
			boolean[] x2 = pisando(j3);

			if (x2[0]) {
				j3.escalera();
				esc = 3;
			} else if (x2[1]) {
				j3.serpiente();
				serp = 3;
			}
			break;
		default:
			break;
		}

		return d1;
	}

	private boolean[] pisando(Jugador j) {
		// TODO Auto-generated method stub

		int fila = -1;

		boolean escalera = false;
		boolean serpiente = false;

		for (int i = 0; i < tablero.getSerpientes().size(); i++) {
			if (tablero.getEscaleras().get(i).contains(j))
				escalera = true;
			else if (tablero.getSerpientes().get(i).contains(j))
				serpiente = true;
		}

//		if (String.valueOf(j.getPosicion()).length() > 1 && j.getPosicion() % 10 != 0) {
//			fila = 4 - ((int) j.getPosicion() / 10);
//		} else if (String.valueOf(j.getPosicion()).length() == 1 || j.getPosicion() == 10) {
//			fila = 9;
//		} else if (j.getPosicion() % 10 == 0) {
//			fila = 10 - ((int) j.getPosicion() / 10);
//		}

		return new boolean[] { escalera, serpiente };
	}

	public void resetEscSerp() {
		esc = -1;
		serp = -1;
	}

	// ----------
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
