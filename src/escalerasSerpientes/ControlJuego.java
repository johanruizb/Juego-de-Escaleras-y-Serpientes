package escalerasSerpientes;

import java.lang.Thread.State;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class ControlJuego. Clase que se encarga del control logico del juego.
 */
public class ControlJuego {

	/** The dado referencia a la clase dado. */
	private Dado dado;

	/** The tablero referencia a la clase tablero. */
	private TableroJuego tablero;

	/** The j 1. */
	private Jugador j1;

	/** The j 3. */
	private NPC j2, j3;

	/** The i. */
	private volatile int turno = 1, dado1;

	/** The interfaz. */
	private Runnable interfaz;

	/** The mover. */
	private Runnable mover;

	/** The control. */
	private Thread control;

	/** The n 3. */
	private Thread n2, n3;

	/** The recurso referencia a la clase encargada de la vista del juego. */
	private GUIEscaleraSerpientes recurso;

	/**
	 * Instantiates a new control juego. Contructor de la clase encargado de lanzar
	 * los controles de la lagica del juego
	 *
	 * @param recurso the recurso
	 */
	public ControlJuego(GUIEscaleraSerpientes recurso) {
		this.recurso = recurso;
		initControl();
	}

	/**
	 * Inits the control. Metodo encargado de crear las instancias del los objectos
	 * que interactuan en el juego
	 */
	private void initControl() {
		// TODO Auto-generated method stub
		dado = new Dado();
		tablero = new TableroJuego();

		j1 = new Jugador("Jugador", 1);
		j2 = new NPC("NPC 1", 2, this);
		j3 = new NPC("NPC 2", 3, this);
	}

	/**
	 * Gets the name. Metodo especializado en cacturar los nombres de los jugadores
	 * que integran el juego
	 *
	 * @param i the i argumento interno del metotodo
	 * @return the name debuelve el nombre del jugador
	 */
	public String getName(int i) {
		switch (i) {
		case 1:
			return j1.getNombre();
		case 2:
			return j2.getNombre();
		case 3:
			return j3.getNombre();
		default:
			return null;
		}
	}

	/**
	 * Lanzar. Metodo especializado en controlar el turno de lanzamiento del dado
	 * entre los NPC
	 *
	 * @param i el turno del NPC que ejecuta el metodo
	 * @param r the r
	 * @throws InterruptedException the interrupted exception
	 */
	public void lanzar(int i, Runnable r) throws InterruptedException {
		if (i != turno) {

			switch (i) {
			case 2:
				synchronized (j2) {
					j2.wait();
				}
				break;
			case 3:
				synchronized (j3) {
					j3.wait();
				}
				break;
			}
		}

		turno = i;
		recurso.lanzarD(turno);

	}

	/**
	 * Lanzar. Metodo que usa el jugador para lanzar los dados y moverse
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	public void lanzar() throws InterruptedException {
		recurso.lanzarD(1);
	}

	/**
	 * Lanzar dados. Lanzar. Metodo especializado en controlar el turno de
	 * lanzamiento del dado del jugador humano y los no jugadores
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	public void lanzarDados() throws InterruptedException {

		control = new Thread(new Runnable() {

			@Override
			public synchronized void run() {
				mover = this;
				dado1 = dado.caraDado();

				switch (turno) {

				case 1:

					if (j1.getPosicion() + dado1 > 100)
						dado1 = 100 - j1.getPosicion();

					j1.setPosicion(dado1);

					synchronized (interfaz) {
						interfaz.notify();
					}

					boolean[] casilla = pisando(j1);
					if (casilla[0])
						j1.escalera();

					else if (casilla[1])
						j1.serpiente();

					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					turno++;

					synchronized (j2) {
						j2.notify();
					}
					break;

				case 2:

					if (j2.getPosicion() + dado1 > 100)
						dado1 = 100 - j2.getPosicion();

					j2.setPosicion(dado1);

					synchronized (interfaz) {
						interfaz.notify();
					}

					boolean[] casilla1 = pisando(j2);
					if (casilla1[0])
						j2.escalera();
					else if (casilla1[1])
						j2.serpiente();

					try {
						wait();
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

					turno++;

					synchronized (j3) {
						j3.notify();
					}
					break;

				case 3:

					if (j3.getPosicion() + dado1 > 100)
						dado1 = 100 - j3.getPosicion();

					j3.setPosicion(dado1);

					synchronized (interfaz) {
						interfaz.notify();
					}

					boolean[] casilla2 = pisando(j3);
					if (casilla2[0])
						j3.escalera();
					else if (casilla2[1])
						j3.serpiente();

					try {
						wait();
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

					turno = 1;
					break;
				default:
					break;
				}

			}
		}, "Mover control");

		control.start();
	}

	/**
	 * Pisando. Comprueba si el NPC "j" esta pisando una serpiente o una escalera.
	 *
	 * @param j the j
	 * @return the boolean[]
	 */
	private boolean[] pisando(NPC j) {
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

	/**
	 * Pisando. Comprueba si el Jugador "j" esta pisando una serpiente o una
	 * escalera.
	 *
	 * @param j the j
	 * @return the boolean[]
	 */
	private boolean[] pisando(Jugador j) {

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

	/**
	 * Checks if is win. Metodo encargado de determinar si hay un ganador del juego
	 *
	 * @return true, if is win
	 */
	public boolean isWin() {

		if (j1.getPosicion() == 100 || j2.getPosicion() == 100 || j3.getPosicion() == 100) {
			return true;
		}

		return false;
	}

	/**
	 * Inits the NPC. Este metodo es el encargado de crear las intancias de los
	 * hilos para los NPC y realizar su iniciacion.
	 */
	public void initNPC() {
		turno = 1;

		n2 = null;
		n3 = null;

		n2 = new Thread(j2, "N2");
		n3 = new Thread(j3, "N3");

		n2.start();
		n3.start();

		turno = 1;
	}

	/**
	 * Reiniciar. Metodo especializado en reiniciar los jugadores NPC y jugador
	 * humano y ademas crear un nuevo tablero de juego.
	 */
	public void reiniciar() {
		turno = 1;

		tablero.reset();

		j1.reset();
		j2.reset();
		j3.reset();
	}

	// ----------

	/**
	 * Gets the tablero.
	 *
	 * @return the tablero
	 */
	public ArrayList<ArrayList<Integer>> getTablero() {
		return tablero.getTablero();
	}

	/**
	 * Gets the serpientes.
	 *
	 * @return the serpientes
	 */
	public ArrayList<ArrayList<Integer>> getSerpientes() {
		return tablero.getSerpientes();
	}

	/**
	 * Gets the escaleras.
	 *
	 * @return the escaleras
	 */
	public ArrayList<ArrayList<Integer>> getEscaleras() {
		return tablero.getEscaleras();
	}

	/**
	 * Sets the thread. Asigna una referencia al hilo del movimiento en la interfaz
	 * para comunicarse con �l.
	 *
	 * @param r the new thread
	 */
	public void setThread(Runnable r) {
		// TODO Auto-generated method stub
		interfaz = r;
	}

	/**
	 * Gets the dado 1.
	 *
	 * @return the dado 1
	 */
	public int getDado1() {
		return dado1;
	}

	/**
	 * Sets the terminar. Metodo usado para bajar la bandera de terminacion de los
	 * hilos.
	 */
	public void notificar() {
		if (n2.getState() != State.TERMINATED)
			synchronized (j2) {
				j2.notify();
			}
		if (n3.getState() != State.TERMINATED)
			synchronized (j3) {
				j3.notify();
			}
	}

	/**
	 * Gets the mover.
	 *
	 * @return the mover
	 */
	public Runnable getMover() {
		return mover;
	}
}
