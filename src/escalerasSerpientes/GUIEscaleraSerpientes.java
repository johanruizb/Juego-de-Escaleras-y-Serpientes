package escalerasSerpientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;

// TODO: Auto-generated Javadoc
/**
 * The Class GUIEscaleraSerpientes.
 */
public class GUIEscaleraSerpientes extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -142202431892957518L;

	/** The panel dibujo. */
	private DibujoEscalerasSerpientes panelDibujo;

	/** The panel jugadores. */
	private DibujoJugador panelJugadores;

	/** The botones. */
	private JPanel panelTablero, componentes, botones;

	/** The cargando. */
	private JLabel mensajes, cargando;

	/** The tablero vista. */
	private ArrayList<JLabel> tableroVista;

	/** The escucha. */
	private Escucha escucha;

	/** The escuchaplay. */
	private EscuchaPlay escuchaplay;

	/** The boton reiniciar. */
	private JButton dado, reproducir, salir, botonReiniciar;

	/** The capas. */
	private JLayeredPane capas = new JLayeredPane();

	/** The control. */
	private ControlJuego control;

	/** The play. */
	private MediaPlay play;

	/** The ruta. */
	private String ruta;

	/** The contador. */
	private static int contador = 1;

	/**
	 * Instantiates a new GUI escalera serpientes.
	 */
	public GUIEscaleraSerpientes() {

		// TODO Auto-generated method stub
		initGUI();

		play.reproducir();

		this.setTitle("Esacaleras y Serpientes");
		this.setVisible(true);
		this.setSize(650, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Inits the GUI. Inicializa los componentes de la ventana y los añade.
	 */
	private void initGUI() {

		escucha = new Escucha();
		escuchaplay = new EscuchaPlay();
		control = new ControlJuego(this);

		capas.setSize(500, 500);

		// Pre-carga pantalla de carga
		cargando = new JLabel("Cargando... ", new ImageIcon("src/imagenes/cargando.gif"), JLabel.CENTER);

		// TABLERO GUI
		componentes = new JPanel(new BorderLayout());
		panelTablero = new JPanel(new GridLayout(10, 10));
		panelTablero.setOpaque(false);
		panelTablero.setBounds(18, 6, 400, 400);

		// AÑADIR AL TABLERO

		tableroVista = new ArrayList<JLabel>(100);
		addTablero();

		// EXTRAER POSICIONES EN PIXELES DE ESCALERAS Y SERPIENTES
		ArrayList<ArrayList<Integer>> pSerpientes = new ArrayList<>();
		pSerpientes.addAll(posicionSerpientes());

		ArrayList<ArrayList<Integer>> pEscaleras = new ArrayList<>();
		pEscaleras.addAll(posicionEscaleras());
		// ---

		// DIBUJO
		panelDibujo = new DibujoEscalerasSerpientes(pSerpientes, pEscaleras);
		panelDibujo.setSize(500, 500);

		// JUGADORES
		panelJugadores = new DibujoJugador(pSerpientes, pEscaleras);
		panelJugadores.setBounds(18, 6, 400, 400);

		// AÑADIR LAS CAPAS
		capas.add(panelTablero, new Integer(2));
		capas.add(panelDibujo, new Integer(1));
		capas.add(panelJugadores, new Integer(3));

		// BOTONES ---
		botones = new JPanel();
		botones.setPreferredSize(new Dimension(210, 50));

		// DADO
		JPanel dadoPanel = new JPanel();
		dado = new JButton(new ImageIcon("src/imagenes/dado.png"));
		dado.addActionListener(escucha);
		dado.setBorder(null);
		dado.setContentAreaFilled(false);
		dadoPanel.add(dado);

		// SALIR
		salir = new JButton(new ImageIcon("src/imagenes/salir.png"));
		salir.addActionListener(escucha);
		salir.setBorder(null);
		salir.setContentAreaFilled(false);

		// REINICIAR
		botonReiniciar = new JButton(new ImageIcon("src/imagenes/reiniciar.png"));
		botonReiniciar.addActionListener(escucha);
		botonReiniciar.setBorder(null);
		botonReiniciar.setContentAreaFilled(false);

		// REPRODUCTOR
		JFXPanel panelsonido = new JFXPanel();
		panelsonido.setVisible(false);

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

		// AÑADIR BOTONES
		botones.add(panelsonido);
		botones.add(reproducir);

		JLabel l1 = new JLabel();
		l1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		botones.add(l1);
		botones.add(botonReiniciar);

		l1 = new JLabel();
		l1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		botones.add(l1);
		botones.add(salir);

		// MENSAJES
		JPanel mensajePanel = new JPanel();
		mensajes = new JLabel();
		mensajes.setText("Es el turno de tirar del Jugador");
		mensajes.setBorder(BorderFactory.createEmptyBorder(250, 0, 0, 0));

		mensajePanel.add(mensajes);

		// AÑADIENDO COMPONENTES AL PANEL DERECHO
		componentes.add(botones, BorderLayout.NORTH);
		componentes.add(dadoPanel, BorderLayout.SOUTH);
		componentes.add(mensajePanel, BorderLayout.CENTER);

		// AÑADIR COMPONENTES A LA VENTANA
		add(capas, BorderLayout.CENTER);
		add(componentes, BorderLayout.EAST);

	}

	/**
	 * Adds the tablero. Añade las casillas al panel del tablero, se añaden 1 por 1.
	 */
	private void addTablero() {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Integer>> tablero = control.getTablero();
		int k = 0;
		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < 10; j++) {

				JLabel temp = new JLabel();
				temp.setText(String.valueOf(tablero.get(i).get(j)));
				temp.setPreferredSize(new Dimension(20, 20));
				temp.setVerticalAlignment(JLabel.CENTER);
				temp.setHorizontalAlignment(JLabel.CENTER);
				temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

				tableroVista.add(temp);
				panelTablero.add(tableroVista.get(k));
				k++;
			}
		}
	}

	/**
	 * Inits the scene. Inicia la escena. Necesaria para reproducir la musica.
	 *
	 * @return the scene
	 */
	private static Scene initScene() {
		Group root = new Group();
		Scene scene = new Scene(root, javafx.scene.paint.Color.ALICEBLUE);
		return scene;
	}

	/**
	 * Aleatorio. Devuelve un numero aleatorio para la musica
	 *
	 * @return the string
	 */
	public String aleatorio() {
		Random ran = new Random();
		ruta = String.valueOf(ran.nextInt(10) + 1);
		return ruta;
	}

	/**
	 * Lanzar D. Un metodo que se usa en el control para poder lanzar el dado y
	 * verse el movimiento de las fichas
	 *
	 * @param i the i
	 */
	public void lanzarD(int i) {
		escucha.lanzarDado(i);
	}

	/**
	 * Posicion escaleras. Extrae la posicion de las escaleras en pixeles para luego
	 * ser pintadas con Graphics
	 *
	 * @return the array list
	 */
	private ArrayList<ArrayList<Integer>> posicionEscaleras() {
		// TODO Auto-generated method stub

		ArrayList<ArrayList<Integer>> tablero = control.getTablero();
		ArrayList<ArrayList<Integer>> escaleras = new ArrayList<ArrayList<Integer>>();

		ArrayList<ArrayList<Integer>> auxPoint2 = new ArrayList<>();

		escaleras.addAll(control.getEscaleras());

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

				columna = tablero.get(fila).indexOf(escaleras.get(i).get(j));

				auxPoint2.get(i).add((columna * 40) + 8);
				auxPoint2.get(i).add((fila * 40) + 8);
			}

		}

		return auxPoint2;
	}

	/**
	 * Posicion serpientes.Extrae la posicion de las serpientes en pixeles para
	 * luego ser pintadas con Graphics
	 *
	 * @return the array list
	 */
	private ArrayList<ArrayList<Integer>> posicionSerpientes() {
		// TODO Auto-generated method stub

		ArrayList<ArrayList<Integer>> tablero = control.getTablero();
		ArrayList<ArrayList<Integer>> serpientes = new ArrayList<ArrayList<Integer>>();

		ArrayList<ArrayList<Integer>> auxPoint = new ArrayList<>();

		serpientes.addAll(control.getSerpientes());

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

				columna = tablero.get(fila).indexOf(serpientes.get(i).get(j));

				auxPoint.get(i).add((columna * 40) + 8);
				auxPoint.get(i).add((fila * 40) + 8);
			}

		}

		return auxPoint;
	}

	/**
	 * The Class Escucha. Clase que controla los eventos de los botones
	 */
	private class Escucha implements ActionListener {

		/** The reiniciar ventana. */
		private Thread mover, reiniciarVentana;

		/** The tirar. */
		private boolean tirar = true;

		/**
		 * Action performed.
		 * 
		 * Lamza el dado del jugador y luego sincroniza el movimiento de su personaje.
		 *
		 * Si se quiere reiniciar lanza el Thread encargado del reinicio
		 *
		 * @param e the e
		 */
		@Override
		public synchronized void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == dado && tirar && !control.isWin()) {
				tirar = false;
				botonReiniciar.setEnabled(false);
				try {
					control.initNPC();
					control.lanzar();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (e.getSource() == salir) {
				System.exit(0);
			} else if (e.getSource() == botonReiniciar) {
				botonReiniciar.setEnabled(false);
				reinicio();

			}
		}

		/**
		 * Lanzar dado. Lanza el dado del jugador o de los NPCs dependiendo de su turno
		 *
		 * @param jugador the jugador
		 */
		private void lanzarDado(int jugador) {
			// TODO Auto-generated method stub

			mover = new Thread(new Runnable() {

				@Override
				public synchronized void run() {

					control.setThread(this);

					try {
						control.lanzarDados();

						this.wait();
						int movimientos = control.getDado1();

						dado.setIcon(new ImageIcon("src/imagenes/" + movimientos + ".png"));

						panelJugadores.setPosition(movimientos, jugador, this);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					try {
						this.wait();

						if (jugador < 3) {
							mensajes.setText("Es el turno de tirar del " + control.getName(jugador + 1));
							dado.setIcon(new ImageIcon("src/imagenes/dado.png"));

							try {
								Thread.sleep(1500);
							} catch (InterruptedException e1) {
								System.out.println("Espera interrumpida");
							}
						} else
							mensajes.setText("Es el turno de tirar del " + control.getName(1));

						dado.setIcon(new ImageIcon("src/imagenes/dado.png"));

					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

					synchronized (control.getMover()) {
						control.getMover().notify();
					}

					if (!control.isWin() && jugador == 3) {
						tirar = true;
						botonReiniciar.setEnabled(true);
					} else if (control.isWin()) {
						JOptionPane.showMessageDialog(null, "Ha ganado el " + control.getName(jugador));
						dado.setEnabled(false);
					}

				}
			});

			mover.start();
		}

		/**
		 * Reinicio. Metodo encargado de crear e iniciar el Thread para reiniciar el
		 * juego.
		 */
		private void reinicio() {
			// TODO Auto-generated method stub

			reiniciarVentana = new Thread(new Runnable() {

				@Override
				public synchronized void run() {
					// TODO Auto-generated method stub

					capas.setVisible(false);
					componentes.setVisible(false);

					add(cargando, BorderLayout.CENTER);

					repaint();
					revalidate();

					mensajes.setText("Es el turno de tirar del " + control.getName(1));
					dado.setIcon(new ImageIcon("src/imagenes/dado.png"));

					repaint();
					revalidate();

					tirar = true;

					control.reiniciar();
					panelJugadores.reiniciar();

					dado.setEnabled(true);

					// EXTRAER POSICIONES EN PIXELES DE ESCALERAS Y SERPIENTES
					ArrayList<ArrayList<Integer>> pSerpientes = new ArrayList<>();
					pSerpientes.addAll(posicionSerpientes());

					ArrayList<ArrayList<Integer>> pEscaleras = new ArrayList<>();
					pEscaleras.addAll(posicionEscaleras());
					// ---

					panelDibujo.setList(pSerpientes, pEscaleras);
					panelJugadores.setList(pSerpientes, pEscaleras);

					try {
						Thread.sleep(1500);
						remove(cargando);

						capas.setVisible(true);
						componentes.setVisible(true);
						botonReiniciar.setEnabled(true);

						repaint();
						revalidate();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			});

			reiniciarVentana.start();
		}

	}

	/**
	 * The Class EscuchaPlay. Clase encargada de la reproduccion de la musica
	 */
//CLASE PARA EL MANEJO DE LA REPRODUCCION DE LA MUSICA
	private class EscuchaPlay implements ActionListener {

		/**
		 * Action performed.
		 *
		 * @param e the e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == reproducir) {
				if (contador == 1) {
					contador++;
					reproducir.setIcon(new ImageIcon("src/imagenes/off.png"));
					play.pausar();
				} else if (contador == 2) {
					contador = 1;
					reproducir.setIcon(new ImageIcon("src/imagenes/on.png"));
					play.reproducir();
				}
			}

		}

	}

}
