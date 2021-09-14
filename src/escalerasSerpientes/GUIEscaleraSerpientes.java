package escalerasSerpientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;

public class GUIEscaleraSerpientes extends JFrame {

	private static final long serialVersionUID = -142202431892957518L;
	private DibujoEscalerasSerpientes dibujo;
	private DibujoJugador jugadores;
	private JPanel tableroJuego, componentes;
	private ArrayList<JLabel> tablero = new ArrayList<JLabel>(100);
	private Escucha escucha;
	private EscuchaPlay escuchaplay;
	private JButton mover, reproducir;
	private TableroJuego t1;
	private MediaPlay play;
	private String ruta;
	private static int contador;
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
		escuchaplay = new EscuchaPlay();

		JLayeredPane capas = new JLayeredPane();
		capas.setSize(500, 500);

		// TABLERO GUI
		componentes = new JPanel(new BorderLayout());
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
		ArrayList<ArrayList<Integer>> pSerpientes = new ArrayList<>();
		pSerpientes.addAll(posicionSerpientes());

		ArrayList<ArrayList<Integer>> pEscaleras = new ArrayList<>();
		pEscaleras.addAll(posicionEscaleras());
		// ---
		// DIBUJO
		dibujo = new DibujoEscalerasSerpientes(pSerpientes, pEscaleras);
		dibujo.setSize(500, 500);

		// JUGADORES
		jugadores = new DibujoJugador(pSerpientes, pEscaleras);
		jugadores.setBounds(18, 6, 400, 400);

		// AÑADIR LAS CAPAS
		capas.add(tableroJuego, new Integer(2));
		capas.add(dibujo, new Integer(1));
		capas.add(jugadores, new Integer(3));

		// BOTON

		mover = new JButton("Mover");
		mover.addActionListener(escucha);

		// REPRODUCTOR
		JFXPanel panelsonido = new JFXPanel();
		play = new MediaPlay(aleatorio());

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Scene scene = initScene();
				panelsonido.setScene(scene);
			}

		});
		// BORON DE REPRODUCCION
		reproducir = new JButton(new ImageIcon("src/imagenes/on.png"));
		reproducir.addActionListener(escuchaplay);
		reproducir.setBorder(null);
		reproducir.setContentAreaFilled(false);

		// add(b, BorderLayout.CENTER);
		// add(panel, BorderLayout.NORTH);

		// AÑADIENDO COMPONENTES AL PANEL DE LA VISTA

		componentes.add(panelsonido, BorderLayout.NORTH);
		componentes.add(reproducir, BorderLayout.CENTER);
		componentes.add(mover, BorderLayout.SOUTH);

		// AÑADIR COMPONENTES A LA VENTANA

		add(capas, BorderLayout.CENTER);
		// add(mover, BorderLayout.EAST);
		add(componentes, BorderLayout.EAST);
	}

	private static Scene initScene() {

		Group root = new Group();
		Scene scene = new Scene(root, javafx.scene.paint.Color.ALICEBLUE);
		return (scene);

	}

	public String aleatorio() {
		Random ran = new Random();
		ruta = String.valueOf(ran.nextInt(7) + 1);
		return ruta;
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

				auxPoint2.get(i).add((columna * 40) + 8);
				auxPoint2.get(i).add((fila * 40) + 8);
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

				auxPoint.get(i).add((columna * 40) + 8);
				auxPoint.get(i).add((fila * 40) + 8);
			}

		}

		return auxPoint;
	}

	private class Escucha implements ActionListener {

		@Override
		public synchronized void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			mover.setEnabled(false);
			jugadores.setPosition(21, 1);
//			mover.setEnabled(true);

		}
	}

//CLASE PARA EL MANEJO DE LA REPRODUCCION DE LA MUSICA
	private class EscuchaPlay implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == reproducir) {
				contador++;
				play.reproducir();
			}
			if (contador == 2) {

				reproducir.setIcon(new ImageIcon("src/imagenes/off.png"));
				play.pausar();
			}
			if (contador == 3) {
				contador = 1;
				reproducir.setIcon(new ImageIcon("src/imagenes/on.png"));
				play.reproducir();
			}
		}

	}
}
