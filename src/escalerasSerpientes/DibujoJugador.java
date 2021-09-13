package escalerasSerpientes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DibujoJugador extends JPanel implements Runnable {

	private static final long serialVersionUID = -1333670092518533289L;
	private ImageIcon j1 = new ImageIcon("src/imagenes/j1.png");
//			, j2, j3;
	private int[][] p = new int[][] { { 8, 368 }, { 30, 30 }, { 30, 30 } };
	private boolean finalizado = false;

	private int[] target = new int[] { -1, -1 };
	private int targetJugador = 1;

	private Thread prueba = new Thread(this);

	private ArrayList<Integer> limites = new ArrayList<>(Arrays.asList(368, 288, 208, 128, 48));

	public DibujoJugador() {
		// TODO Auto-generated constructor stub
		this.setSize(400, 400);
		this.setOpaque(false);
	}

	public synchronized void setPosition(int x, int y, int jugador) {

		if (prueba.getState() == Thread.State.NEW) {
			targetJugador = jugador - 1;

			target[0] = x;
			target[1] = y;

			prueba.start();

			System.out.println(prueba.getState());
		} else {
			prueba.interrupt();
			System.out.println(prueba.getState());

		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < 1; i++) {
			g.drawImage(j1.getImage(), p[i][0], p[i][1], null);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		animar();
	}

	private synchronized void animar() {

		boolean tope = false;
//		boolean sumar = sentido(p[targetJugador][0]);

		int altura = p[targetJugador][1];

		while (!finalizado) {

//			System.out.println("es: " + (p[targetJugador][0] > target[0] && p[targetJugador][0] != 26));

//			System.out.println("X es: " + (p[targetJugador][0]));
//			System.out.println("Y es: " + (p[targetJugador][1]));

//			System.out.println("X target es: " + (target[0]));
//			System.out.println("Tope es: " + tope);

			if (limites.contains(p[targetJugador][1]) && p[targetJugador][0] < 368) {
				p[targetJugador][0]++;

			} else if (!limites.contains(p[targetJugador][1]) && p[targetJugador][0] > 8 && !tope) {
				p[targetJugador][0]--;

			} else {
				tope = true;
			}

			if (p[targetJugador][1] < target[1] && tope && p[targetJugador][1] - altura < 40) {
				p[targetJugador][1]++;
			} else if (p[targetJugador][1] > target[1] && tope && altura - p[targetJugador][1] < 40) {
				p[targetJugador][1]--;
			} else {
				tope = false;
				altura = p[targetJugador][1];
			}

			repaint();

			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (p[targetJugador][0] == target[0] && p[targetJugador][1] == target[1]) {
				finalizado = true;
			}
		}

//		notifyAll();
	}
}
