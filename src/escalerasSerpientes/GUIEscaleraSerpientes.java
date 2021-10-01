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

public class GUIEscaleraSerpientes extends JFrame {

	private static final long serialVersionUID = -142202431892957518L;

	private DibujoEscalerasSerpientes panelDibujo;
	private DibujoJugador panelJugadores;
	private JPanel panelTablero, componentes, botones;
	private JLabel mensajes, cargando;
	private ArrayList<JLabel> tableroVista;
	private Escucha escucha;
	private EscuchaPlay escuchaplay;
	private JButton dado, reproducir, salir, botonReiniciar;
	private JLayeredPane capas = new JLayeredPane();

	private ControlJuego control;

	private MediaPlay play;
	private String ruta;
	private static int contador = 1;

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

		// A�ADIR AL TABLERO

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

		// A�ADIR BOTONES
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

		// A�ADIENDO COMPONENTES AL PANEL DERECHO
		componentes.add(botones, BorderLayout.NORTH);
		componentes.add(dadoPanel, BorderLayout.SOUTH);
		componentes.add(mensajePanel, BorderLayout.CENTER);

		// A�ADIR COMPONENTES A LA VENTANA
		add(capas, BorderLayout.CENTER);
		add(componentes, BorderLayout.EAST);

	}

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

	private static Scene initScene() {
		Group root = new Group();
		Scene scene = new Scene(root, javafx.scene.paint.Color.ALICEBLUE);
		return scene;
	}

	public String aleatorio() {
		Random ran = new Random();
		ruta = String.valueOf(ran.nextInt(10) + 1);
		return ruta;
	}

	public void lanzarD(int i) {
		escucha.lanzarDado(i);
	}

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

	private class Escucha implements ActionListener {
		private Thread mover, reiniciarVentana;
		private boolean tirar = true;

		@Override
		public synchronized void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == dado && tirar && !control.isWin()) {
				tirar = false;

				try {
					control.initNPC();
					control.lanzar(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (e.getSource() == salir) {
				System.exit(0);
			} else if (e.getSource() == botonReiniciar && mover != null) {
				botonReiniciar.setEnabled(false);
				reinicio();
				
			}
		}

		private void lanzarDado(int jugador) {
			// TODO Auto-generated method stub

			mover = new Thread(new Runnable() {

				@Override
				public synchronized void run() {
//					System.out.println("Inicia movimiento de " + control.getName(jugador));

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
//								System.out.println("Espera interrumpida");
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
					} else if (control.isWin()) {
						JOptionPane.showMessageDialog(null, "Ha ganado el " + control.getName(jugador));
						dado.setEnabled(false);
					}

//					System.out.println("Termine de mover con " + control.getName(jugador));
				}
			});

			mover.start();
		}

		private void reinicio() {
			// TODO Auto-generated method stub

			reiniciarVentana = new Thread(new Runnable() {

				@Override
				public synchronized void run() {
					// TODO Auto-generated method stub

					remove(capas);
					remove(componentes);

					add(cargando, BorderLayout.CENTER);

					repaint();
					revalidate();

					control.setTerminar(true);

					esperarHilos();

					panelJugadores.reiniciar();

					mensajes.setText("Es el turno de tirar del " + control.getName(1));
					dado.setIcon(new ImageIcon("src/imagenes/dado.png"));

					repaint();
					revalidate();

					tirar = true;

					control.setTerminar(false);
					panelJugadores.setTerminar(false);

					dado.setEnabled(true);
					botonReiniciar.setEnabled(true);

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

						add(capas, BorderLayout.CENTER);
						add(componentes, BorderLayout.EAST);

						repaint();
						revalidate();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				private void esperarHilos() {
					// TODO Auto-generated method stub
					control.isTerminado();

					while (mover.getState() != State.TERMINATED)
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					while (panelJugadores.getPrueba().getState() != State.TERMINATED)
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}

			});

			reiniciarVentana.start();
		}

	}

//CLASE PARA EL MANEJO DE LA REPRODUCCION DE LA MUSICA
	private class EscuchaPlay implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
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
