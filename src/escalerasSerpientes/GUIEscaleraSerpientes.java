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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class GUIEscaleraSerpientes.
 */
public class GUIEscaleraSerpientes extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -142202431892957518L;

	/** The panel dibujo. */
	private PanelEscalerasSerpientes panelDibujo;

	/** The panel jugadores. */
	private PanelJugadores panelJugadores;

	/** The botones. */
	private JPanel panelTablero, componentes, botones;

	/** The cargando. */
	private JLabel mensajes, cargando;

	/** The tablero vista. */
	private ArrayList<JLabel> tableroVista;

	/** The escucha. */
	private Escucha escucha;

	/** The boton reiniciar. */
	private JButton dado, salir, botonReiniciar;

	/** The capas. */
	private JLayeredPane capas = new JLayeredPane();

	/** The control. */
	private ControlJuego control;

	/** The ruta. */
	private String ruta;

	/**
	 * Instantiates a new GUI escalera serpientes.
	 */
	public GUIEscaleraSerpientes() {

		// TODO Auto-generated method stub
		initGUI();

		this.setTitle("Esacaleras y Serpientes");
		this.setVisible(true);
		this.setSize(650, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Inits the GUI. Inicializa los componentes de la ventana y los a�ade.
	 */
	private void initGUI() {

		escucha = new Escucha();
		control = new ControlJuego(this);

		capas.setSize(500, 500);

		// Pre-carga pantalla de carga
		cargando = new JLabel("Cargando... ", new ImageIcon("src/imagenes/cargando.gif"), SwingConstants.CENTER);

		// TABLERO GUI
		componentes = new JPanel(new BorderLayout());
		panelTablero = new JPanel(new GridLayout(10, 10));
		panelTablero.setOpaque(false);
		panelTablero.setBounds(18, 6, 400, 400);

		// A�ADIR AL TABLERO

		tableroVista = new ArrayList<>(100);
		addTablero();

		// EXTRAER POSICIONES EN PIXELES DE ESCALERAS Y SERPIENTES
		ArrayList<ArrayList<Integer>> pSerpientes = new ArrayList<>();
		pSerpientes.addAll(posicionSerpientes());

		ArrayList<ArrayList<Integer>> pEscaleras = new ArrayList<>();
		pEscaleras.addAll(posicionEscaleras());
		// ---

		// DIBUJO
		panelDibujo = new PanelEscalerasSerpientes(pSerpientes, pEscaleras);
		panelDibujo.setSize(500, 500);

		// JUGADORES
		panelJugadores = new PanelJugadores(pSerpientes, pEscaleras);
		panelJugadores.setBounds(18, 6, 400, 400);

		// A�ADIR LAS CAPAS
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

		// A�ADIENDO COMPONENTES AL PANEL DERECHO
		componentes.add(botones, BorderLayout.NORTH);
		componentes.add(dadoPanel, BorderLayout.SOUTH);
		componentes.add(mensajePanel, BorderLayout.CENTER);

		// A�ADIR COMPONENTES A LA VENTANA
		add(capas, BorderLayout.CENTER);
		add(componentes, BorderLayout.EAST);

	}

	/**
	 * Adds the tablero. A�ade las casillas al panel del tablero, se a�aden 1 por 1.
	 */
	private void addTablero() {

		ArrayList<ArrayList<Integer>> tablero = control.getTablero();
		int k = 0;
		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < 10; j++) {

				JLabel temp = new JLabel();
				temp.setText(String.valueOf(tablero.get(i).get(j)));
				temp.setPreferredSize(new Dimension(20, 20));
				temp.setVerticalAlignment(SwingConstants.CENTER);
				temp.setHorizontalAlignment(SwingConstants.CENTER);
				temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

				tableroVista.add(temp);
				panelTablero.add(tableroVista.get(k));
				k++;
			}
		}
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

		ArrayList<ArrayList<Integer>> tablero = control.getTablero();
		ArrayList<ArrayList<Integer>> escaleras = new ArrayList<>();

		ArrayList<ArrayList<Integer>> auxPoint2 = new ArrayList<>();

		escaleras.addAll(control.getEscaleras());

		for (int i = 0; i < escaleras.size(); i++) {
			int fila = -1;
			int columna = -1;

			auxPoint2.add(new ArrayList<>());

			for (int j = 0; j < 2; j++) {
				if (String.valueOf(escaleras.get(i).get(j)).length() > 1 && escaleras.get(i).get(j) % 10 != 0) {
					fila = 9 - escaleras.get(i).get(j) / 10;

				} else if (String.valueOf(escaleras.get(i).get(j)).length() == 1 || escaleras.get(i).get(j) == 10) {
					fila = 9;

				} else if (escaleras.get(i).get(j) % 10 == 0) {
					fila = 10 - escaleras.get(i).get(j) / 10;

				}

				columna = tablero.get(fila).indexOf(escaleras.get(i).get(j));

				auxPoint2.get(i).add(columna * 40 + 8);
				auxPoint2.get(i).add(fila * 40 + 8);
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

		ArrayList<ArrayList<Integer>> tablero = control.getTablero();
		ArrayList<ArrayList<Integer>> serpientes = new ArrayList<>();

		ArrayList<ArrayList<Integer>> auxPoint = new ArrayList<>();

		serpientes.addAll(control.getSerpientes());

		for (int i = 0; i < serpientes.size(); i++) {
			int fila = -1;
			int columna = -1;

			auxPoint.add(new ArrayList<>());

			for (int j = 0; j < 2; j++) {
				if (String.valueOf(serpientes.get(i).get(j)).length() > 1 && serpientes.get(i).get(j) % 10 != 0) {
					fila = 9 - serpientes.get(i).get(j) / 10;

				} else if (String.valueOf(serpientes.get(i).get(j)).length() == 1 || serpientes.get(i).get(j) == 10) {
					fila = 9;

				} else if (serpientes.get(i).get(j) % 10 == 0) {
					fila = 10 - serpientes.get(i).get(j) / 10;

				}

				columna = tablero.get(fila).indexOf(serpientes.get(i).get(j));

				auxPoint.get(i).add(columna * 40 + 8);
				auxPoint.get(i).add(fila * 40 + 8);
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

			if (e.getSource() == dado && tirar && !control.isWin()) {
				tirar = false;
				botonReiniciar.setEnabled(false);
				try {
					control.initNPC();
					control.lanzar();
				} catch (InterruptedException e1) {

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

			mover = new Thread(new Runnable() {

				@Override
				public synchronized void run() {

					control.setThread(this);

					if (!control.isWin()) {

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
							if (control.isWin()) {
								JOptionPane.showMessageDialog(null, "Ha ganado el " + control.getName(jugador));
								dado.setEnabled(false);
								botonReiniciar.setEnabled(true);
								mensajes.setText("Ha ganado el " + control.getName(jugador));

								control.notificar();

							} else if (jugador < 3) {
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

					}

					if (!control.isWin() && jugador == 3) {
						tirar = true;
						botonReiniciar.setEnabled(true);
					}
					synchronized (control.getMover()) {
						control.getMover().notify();
						mover.interrupt();
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

			reiniciarVentana = new Thread(new Runnable() {

				@Override
				public synchronized void run() {

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

						e.printStackTrace();
					}

				}

			});

			reiniciarVentana.start();
		}

	}

}
