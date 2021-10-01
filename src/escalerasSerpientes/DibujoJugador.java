package escalerasSerpientes;

import java.awt.Graphics;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class DibujoJugador.
 */
public class DibujoJugador extends JPanel implements Runnable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1333670092518533289L;
	
	/** The espera. */
	public static int ESPERA = 17;

	/** The j. */
	// Juego
	private ImageIcon[] j = new ImageIcon[] { new ImageIcon("src/imagenes/j1.png"),
			new ImageIcon("src/imagenes/npc.png"), new ImageIcon("src/imagenes/npc.png") };
	
	/** The p. */
	private int[][] p = new int[][] { { 8, 368 }, { 8, 368 }, { 8, 368 } };

	/** The target. */
	private int[] target = new int[] { -1, -1 };
	
	/** The movimientos. */
	private int targetJugador = -1, movimientos = 0;

	/** The limites. */
	// Tableros
	private ArrayList<Integer> limites = new ArrayList<>(Arrays.asList(368, 288, 208, 128, 48));
	
	/** The limites 2. */
	private ArrayList<Integer> limites2 = new ArrayList<>(Arrays.asList(368, 328, 288, 248, 208, 168, 128, 88, 48, 8));

	/** The serpientes. */
	private ArrayList<ArrayList<Integer>> serpientes = new ArrayList<ArrayList<Integer>>();
	
	/** The escaleras. */
	private ArrayList<ArrayList<Integer>> escaleras = new ArrayList<ArrayList<Integer>>();

	/** The dis. */
	// Hilos
	private DibujoJugador dis = this;
	
	/** The x 1. */
	private Thread prueba, x1;
	
	/** The r 2. */
	private Runnable test1, r1, r2;

	/** The terminar. */
	// Banderas
	private boolean terminar = false;

	/**
	 * Instantiates a new dibujo jugador.
	 *
	 * @param s the s
	 * @param e the e
	 */
	public DibujoJugador(ArrayList<ArrayList<Integer>> s, ArrayList<ArrayList<Integer>> e) {
		// TODO Auto-generated constructor stub
		serpientes.addAll(s);
		escaleras.addAll(e);

		this.setSize(400, 400);
		this.setOpaque(false);
	}

	/**
	 * Sets the list.
	 *
	 * @param auxPoint the aux point
	 * @param auxPoint2 the aux point 2
	 */
	public void setList(ArrayList<ArrayList<Integer>> auxPoint, ArrayList<ArrayList<Integer>> auxPoint2) {
		serpientes.clear();
		escaleras.clear();

		serpientes.addAll(auxPoint);
		escaleras.addAll(auxPoint2);
	}

	/**
	 * Reiniciar.
	 */
	public void reiniciar() {
		terminar = true;

		x1 = new Thread(new Runnable() {

			@Override
			public synchronized void run() {
				// TODO Auto-generated method stub
				r1 = this;

				while (prueba.getState() != State.TERMINATED)
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				p = null;
				target = null;

				p = new int[][] { { 8, 368 }, { 8, 368 }, { 8, 368 } };
				target = new int[] { -1, -1 };
				targetJugador = -1;
				movimientos = 0;

//				System.out.println("Termine PanelJugador");

			}
		});

		x1.start();
	}

	/**
	 * Sets the position.
	 *
	 * @param m the m
	 * @param jugador the jugador
	 * @param r the r
	 */
	public void setPosition(int m, int jugador, Runnable r) {

		targetJugador = jugador - 1;

		movimientos = m;
		test1 = r;

		prueba = new Thread(dis);
		prueba.start();
	}

	/**
	 * Sets the thread.
	 *
	 * @param r the new thread
	 */
	public void setThread(Runnable r) {
		test1 = r;
	}

	/**
	 * Paint component.
	 *
	 * @param g the g
	 */
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

	/**
	 * Run.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		r2 = this;

		animarCasillas(movimientos);
	}

	/**
	 * Animar casillas.
	 *
	 * @param n the n
	 */
	private synchronized void animarCasillas(int n) {

		boolean tope = false;

		int altura = p[targetJugador][1];
		int mov = 0;

		while (!(mov == n) && !terminar) {

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
//				System.out.println("La espera ha sido interrumpida");
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

		repaint();

		synchronized (test1) {
			test1.notify();
		}

	}

	/**
	 * Animar objetos.
	 */
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
//				System.out.println("La espera ha sido interrumpida");
			}
		}
	}

	/**
	 * Gets the prueba.
	 *
	 * @return the prueba
	 */
	public Thread getPrueba() {
		return prueba;
	}

	/**
	 * Sets the terminar.
	 *
	 * @param b the new terminar
	 */
	public void setTerminar(boolean b) {
		// TODO Auto-generated method stub
		terminar = b;
	}

}
