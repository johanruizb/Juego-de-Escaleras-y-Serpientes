package escalerasSerpientes;

import java.lang.Thread.State;
import java.util.ArrayList;

public class ControlJuego {
	private Dado dado;
	private TableroJuego tablero;
	private Jugador j1;
	private NPC j2, j3;
	private int turno = 1, dado1, i;
	private Runnable interfaz;
	private Thread control;
	private Thread n2, n3;
	private boolean terminar = false;

	private Runnable mover, reiniciar;

	private GUIEscaleraSerpientes recurso;

	public ControlJuego(GUIEscaleraSerpientes recurso) {
		this.recurso = recurso;
		initControl();
	}

	private void initControl() {
		// TODO Auto-generated method stub
		dado = new Dado();
		tablero = new TableroJuego();

		j1 = new Jugador("Jugador", 1);
		j2 = new NPC("NPC 1", 2, this);
		j3 = new NPC("NPC 2", 3, this);
	}

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

	public void lanzar(int i) throws InterruptedException {
		if (i != turno) {
			switch (i) {
			case 2:
				synchronized (j2) {
//					System.out.println("Entra NPC 1 a espera");
					j2.wait();
				}
				break;
			case 3:
				synchronized (j3) {
//					System.out.println("Entra NPC 2 a espera");
					j3.wait();
				}
				break;
			}
		}

		if (!terminar) {
			this.i = i;
			recurso.lanzarD(i);
		} else {
//			System.out.println("Adios");
		}
	}

	public void lanzarDados() throws InterruptedException {

		control = new Thread(new Runnable() {

			@Override
			public synchronized void run() {
				// TODO Auto-generated method stub
				mover = this;

				//
				dado1 = dado.caraDado();

				switch (i) {
				case 1:

					if (j1.getPosicion() + dado1 > 100)
						dado1 = (100 - j1.getPosicion());

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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					turno++;
					if (!terminar)
						synchronized (j2) {
							j2.notify();
						}
					break;
				case 2:
//					System.out.println("Inicia NPC 1");
					if (j2.getPosicion() + dado1 > 100)
						dado1 = (100 - j2.getPosicion());

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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					turno++;

					if (!terminar)
						synchronized (j3) {
							j3.notify();
						}
					break;
				case 3:
//					System.out.println("Inicia NPC 2");
					if (j3.getPosicion() + dado1 > 100)
						dado1 = (100 - j3.getPosicion());

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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					turno = 1;
					break;
				default:
					break;
				}

			}
		});

		control.start();
	}

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

	private boolean[] pisando(Jugador j) {
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

	public boolean isWin() {

		if (j1.getPosicion() == 100) {
			return true;
		} else if (j2.getPosicion() == 100) {
			return true;
		} else if (j3.getPosicion() == 100) {
			return true;
		}

		return false;
	}

	public void initNPC() {

		n2 = new Thread(j2);
		n3 = new Thread(j3);

		n2.start();
		n3.start();
	}

	public void reiniciar() {
		dado = null;

		tablero.reset();

		j1.reset();
		j2.reset();
		j3.reset();

		turno = 1;

		initControl();
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

	public void setThread(Runnable r) {
		// TODO Auto-generated method stub
		interfaz = r;
	}

	public int getDado1() {
		return dado1;
	}

	public boolean isTerminar() {
		return terminar;
	}

	public void setTerminar(boolean terminar) {
		this.terminar = terminar;

		if (n2.getState() != State.TERMINATED) {
			synchronized (j2) {
				j2.notify();
			}
		}
		if (n3.getState() != State.TERMINATED) {
			synchronized (j3) {
				j3.notify();
			}
		}

		synchronized (interfaz) {
			interfaz.notify();
		}
	}

	public Runnable getMover() {
		return mover;
	}

	public boolean isTerminado() {
		// TODO Auto-generated method stub

		while (n3.getState() != State.TERMINATED && n2.getState() != State.TERMINATED
				&& control.getState() != State.TERMINATED) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		reiniciar();

		return true;
	}

}
