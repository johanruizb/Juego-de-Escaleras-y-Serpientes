package escalerasSerpientes;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DibujoJugador extends JPanel implements Runnable {

	private static final long serialVersionUID = -1333670092518533289L;
	public static int ESPERA = 17;

	// Juego
	private ImageIcon[] j = new ImageIcon[] { new ImageIcon("src/imagenes/j1.png"),
			new ImageIcon("src/imagenes/npc.png"), new ImageIcon("src/imagenes/npc.png") };
	private int[][] p = new int[][] { { 8, 368 }, { 8, 368 }, { 8, 368 } };

	private int[] target = new int[] { -1, -1 };
	private int targetJugador = -1, movimientos = 0;
	// Tableros
	private ArrayList<Integer> limites = new ArrayList<>(Arrays.asList(368, 288, 208, 128, 48));
	private ArrayList<Integer> limites2 = new ArrayList<>(Arrays.asList(368, 328, 288, 248, 208, 168, 128, 88, 48, 8));

	private ArrayList<ArrayList<Integer>> serpientes = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> escaleras = new ArrayList<ArrayList<Integer>>();

	// Hilos
	private DibujoJugador dis = this;
	private Thread prueba;
	private Runnable test1;

	public DibujoJugador(ArrayList<ArrayList<Integer>> s, ArrayList<ArrayList<Integer>> e) {
		// TODO Auto-generated constructor stub

		serpientes.addAll(s);
		escaleras.addAll(e);

		this.setSize(400, 400);
		this.setOpaque(false);
	}

	public void setPosition(int m, int jugador, Runnable r) {
		targetJugador = jugador - 1;

		movimientos = m;
		test1 = r;

		prueba = new Thread(dis);
		prueba.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		boolean j1j2X = false, j1j2Y = false;
		boolean j1j3X = false, j1j3Y = false;
		boolean j2j3X = false, j2j3Y = false;

		// J1 y J2 estan en la misma posicion
		if (targetJugador != 0 && targetJugador != 1) {
			j1j2X = p[0][0] == p[1][0];
			j1j2Y = p[0][1] == p[1][1];
		}

		// J1 y J3 estan en la misma posicion
		if (targetJugador != 0 && targetJugador != 2) {
			j1j3X = p[0][0] == p[2][0];
			j1j3Y = p[0][1] == p[2][1];
		}

		// J2 y J3 estan en la misma posicion
		if (targetJugador != 1 && targetJugador != 2) {
			j2j3X = p[1][0] == p[2][0];
			j2j3Y = p[1][1] == p[2][1];
		}

		if (j1j2X && j2j3X && j1j2Y && j2j3Y) {

			g.drawImage(j[0].getImage(), p[0][0] + 10, p[0][1], null);
			g.drawImage(j[1].getImage(), p[1][0] - 10, p[1][1] - 10, null);
			g.drawImage(j[2].getImage(), p[2][0] - 10, p[2][1] + 10, null);

		} else if (j1j2X && j1j2Y) {

			g.drawImage(j[0].getImage(), p[0][0] + 10, p[0][1], null);
			g.drawImage(j[1].getImage(), p[1][0] - 10, p[1][1], null);
			g.drawImage(j[2].getImage(), p[2][0], p[2][1], null);

		} else if (j1j3X && j1j3Y) {
			g.drawImage(j[1].getImage(), p[1][0], p[1][1], null);

			g.drawImage(j[0].getImage(), p[0][0] + 10, p[0][1], null);
			g.drawImage(j[2].getImage(), p[2][0] - 10, p[2][1], null);

		} else if (j2j3X && j2j3Y) {
			g.drawImage(j[0].getImage(), p[0][0], p[0][1], null);

			g.drawImage(j[1].getImage(), p[1][0] + 10, p[1][1], null);
			g.drawImage(j[2].getImage(), p[2][0] - 10, p[2][1], null);

		} else {
			g.drawImage(j[0].getImage(), p[0][0], p[0][1], null);
			g.drawImage(j[1].getImage(), p[1][0], p[1][1], null);
			g.drawImage(j[2].getImage(), p[2][0], p[2][1], null);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		animarCasillas(movimientos);
	}

	private void animarCasillas(int n) {

		boolean tope = false;

		int altura = p[targetJugador][1];
		int mov = 0;

		while (!(mov == n)) {

			if (limites.contains(p[targetJugador][1]) && p[targetJugador][0] < 368) {
				p[targetJugador][0]++;

			} else if (!limites.contains(p[targetJugador][1]) && p[targetJugador][0] > 8 && !tope) {
				p[targetJugador][0]--;

			} else {
				tope = true;
			}

			if (limites2.contains(p[targetJugador][0]) && !tope) {
				mov++;
			}

			if (tope && altura - p[targetJugador][1] < 40) {
				p[targetJugador][1]--;
			} else if (altura - p[targetJugador][1] == 40) {
				tope = false;
				altura = p[targetJugador][1];
				mov++;
			}

			repaint();

			try {
				Thread.sleep(ESPERA);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("La espera ha sido interrumpida");
			}

		}

		for (ArrayList<Integer> list1 : serpientes) {
			if (p[targetJugador][0] == list1.get(0) && p[targetJugador][1] == list1.get(1)) {

				target[0] = list1.get(2);
				target[1] = list1.get(3);

				animarObjetos();
				break;
			}
		}

		for (ArrayList<Integer> list1 : escaleras) {
			if (p[targetJugador][0] == list1.get(0) && p[targetJugador][1] == list1.get(1)) {

				target[0] = list1.get(2);
				target[1] = list1.get(3);

				animarObjetos();
				break;
			}
		}

		targetJugador = -1;

		synchronized (test1) {
			prueba.interrupt();
			test1.notifyAll();
		}
	}

	private void animarObjetos() {

		while (!(p[targetJugador][0] == target[0] && p[targetJugador][1] == target[1])) {

			if (p[targetJugador][0] < target[0] && p[targetJugador][0] < 368) {
				p[targetJugador][0]++;
			} else if (p[targetJugador][0] > target[0] && p[targetJugador][0] > 8) {
				p[targetJugador][0]--;
			}

			if (p[targetJugador][1] < target[1]) {
				p[targetJugador][1]++;
			} else if (p[targetJugador][1] > target[1]) {
				p[targetJugador][1]--;
			}

			repaint();

			try {
				Thread.sleep(ESPERA);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("La espera ha sido interrumpida");
			}
		}
	}

}
