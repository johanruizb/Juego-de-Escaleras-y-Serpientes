package escalerasSerpientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUIEscaleraSerpientes extends JFrame {

	private static final long serialVersionUID = -142202431892957518L;
	private DibujoEscalerasSerpientes dibujo;
	private DibujoJugador jugadores;
	private JPanel tableroJuego;
	private ArrayList<JLabel> tablero = new ArrayList<JLabel>(100);
	private Escucha escucha;
	private JButton mover;
	private TableroJuego t1;
	//
	private JFrame dis = this;

	public GUIEscaleraSerpientes() {

		// TODO Auto-generated method stub
		initGUI();
		this.setTitle("Esacaleras y Serpientes");
		this.setVisible(true);
		this.setSize(650, 450);
		this.setLocationRelativeTo(null);
//				this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initGUI() {

		escucha = new Escucha();

		JLayeredPane capas = new JLayeredPane();
		capas.setSize(500, 500);

		// TABLERO GUI

		tableroJuego = new JPanel(new GridLayout(10, 10));
//		tableroJuego.setSize(500, 500);
		tableroJuego.setOpaque(false);
		tableroJuego.setBounds(18, 6, 400, 400);

		t1 = new TableroJuego();
		ArrayList<ArrayList<Integer>> t2 = t1.getTablero();
		int k = 0;

		// AÑADIR AL TABLERO

		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < 10; j++) {

				JLabel temp = new JLabel();
				temp.setText(String.valueOf(t2.get(i).get(j)));
				temp.setPreferredSize(new Dimension(20, 20));
				temp.setVerticalAlignment(JLabel.CENTER);
				temp.setHorizontalAlignment(JLabel.CENTER);
				temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

				tablero.add(temp);
				tableroJuego.add(tablero.get(k));
				k++;
			}
		}

		// EXTRAER POSICIONES EN PIXELES DE ESCALERAS Y SERPIENTES
		ArrayList<ArrayList<Integer>> auxPoint = new ArrayList<>();
		auxPoint.addAll(posicionSerpientes());

		ArrayList<ArrayList<Integer>> auxPoint2 = new ArrayList<>();
		auxPoint2.addAll(posicionEscaleras());
		// ---
		// DIBUJO
		dibujo = new DibujoEscalerasSerpientes(auxPoint, auxPoint2);
		dibujo.setSize(500, 500);

		// JUGADORES
		jugadores = new DibujoJugador();
		jugadores.setBounds(18, 6, 400, 400);

		// AÑADIR LAS CAPAS
		capas.add(tableroJuego, new Integer(2));
//		capas.add(dibujo, new Integer(1));
		capas.add(jugadores, new Integer(3));

		// BOTON

		mover = new JButton("Mover");
		mover.addActionListener(escucha);

		// AÑADIR COMPONENTES A LA VENTANA
		add(capas, BorderLayout.CENTER);
		add(mover, BorderLayout.EAST);

	}

	private ArrayList<ArrayList<Integer>> posicionEscaleras() {
		// TODO Auto-generated method stub

		ArrayList<ArrayList<Integer>> t2 = t1.getTablero();

		ArrayList<ArrayList<Integer>> escaleras = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> auxPoint2 = new ArrayList<>();

		escaleras.addAll(t1.getEscaleras());

		for (int i = 0; i < escaleras.size(); i++) {
			int fila = -1;
			int columna = -1;

			auxPoint2.add(new ArrayList<>());

			for (int j = 0; j < 2; j++) {
				if (String.valueOf(escaleras.get(i).get(j)).length() > 1 && escaleras.get(i).get(j) % 10 != 0) {
					fila = 9 - ((int) escaleras.get(i).get(j) / 10);

				} else if (String.valueOf(escaleras.get(i).get(j)).length() == 1 || escaleras.get(i).get(j) == 10) {
					fila = 9;

				} else if (escaleras.get(i).get(j) % 10 == 0) {
					fila = 10 - ((int) escaleras.get(i).get(j) / 10);

				}

				columna = t2.get(fila).indexOf(escaleras.get(i).get(j));

				auxPoint2.get(i).add((columna * 40) + 30);
				auxPoint2.get(i).add((fila * 40) + 25);
			}

		}

		return auxPoint2;
	}

	private ArrayList<ArrayList<Integer>> posicionSerpientes() {
		// TODO Auto-generated method stub

		ArrayList<ArrayList<Integer>> t2 = t1.getTablero();

		ArrayList<ArrayList<Integer>> serpientes = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> auxPoint = new ArrayList<>();

		serpientes.addAll(t1.getSerpientes());

		for (int i = 0; i < serpientes.size(); i++) {
			int fila = -1;
			int columna = -1;

			auxPoint.add(new ArrayList<>());

			for (int j = 0; j < 2; j++) {
				if (String.valueOf(serpientes.get(i).get(j)).length() > 1 && serpientes.get(i).get(j) % 10 != 0) {
					fila = 9 - ((int) serpientes.get(i).get(j) / 10);

				} else if (String.valueOf(serpientes.get(i).get(j)).length() == 1 || serpientes.get(i).get(j) == 10) {
					fila = 9;

				} else if (serpientes.get(i).get(j) % 10 == 0) {
					fila = 10 - ((int) serpientes.get(i).get(j) / 10);

				}

				columna = t2.get(fila).indexOf(serpientes.get(i).get(j));

				auxPoint.get(i).add((columna * 40) + 30);
				auxPoint.get(i).add((fila * 40) + 30);
			}

		}

		return auxPoint;
	}

	private class Escucha implements ActionListener {

		@Override
		public synchronized void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			mover.setEnabled(false);
			jugadores.setPosition(8, 8, 1);
//			mover.setEnabled(true);

		}
	}
}
