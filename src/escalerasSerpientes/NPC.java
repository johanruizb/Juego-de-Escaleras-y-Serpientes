package escalerasSerpientes;

// TODO: Auto-generated Javadoc
/**
 * The Class NPC.
 */
public class NPC implements Runnable {

	/** The turno. */
	private int posicion = 1, turno = 0;

	/** The nombre. */
	private String nombre = null;

	/** The recurso. */
	private ControlJuego recurso;

	/**
	 * Instantiates a new npc.
	 *
	 * @param s the s
	 * @param i the i
	 * @param r the r
	 */
	public NPC(String s, int i, ControlJuego r) {
		nombre = s;
		turno = i;
		recurso = r;
	}

	/**
	 * Escalera. Aumenta la posicion del jugador cuando se pare sobre una escalera
	 * al terminar su turno
	 */
	public void escalera() {
		posicion += 18;
	}

	/**
	 * Serpiente. Reduce la posicion del jugador cuando se pare sobre una serpiente
	 * al terminar su turno
	 */
	public void serpiente() {
		posicion -= 18;
	}

	@Override
	public void run() {
		try {
			recurso.lanzar(turno, this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the posicion.
	 *
	 * @return the posicion
	 */
	public int getPosicion() {
		return posicion;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the posicion.
	 *
	 * @param posicion the new posicion
	 */
	public void setPosicion(int posicion) {
		if (posicion < 7 && posicion > 0)
			this.posicion += posicion;
	}

	/**
	 * Reset. Reinicia la posicion del jugador
	 */
	public void reset() {
		// TODO Auto-generated method stub
		posicion = 1;
	}

}
