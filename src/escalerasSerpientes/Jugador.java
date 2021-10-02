package escalerasSerpientes;

// TODO: Auto-generated Javadoc
/**
 * The Class Jugador. Clase que almacena la informacion de jugador.
 */
public class Jugador {

	/** The turno. */
	private int posicion = 1, turno = -1;

	/** The nombre. */
	private String nombre = null;

	/**
	 * Instantiates a new jugador.
	 *
	 * @param s the s
	 * @param i the i
	 */
	public Jugador(String s, int i) {
		nombre = s;
		turno = i;
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
		if (posicion >= 1 && posicion <= 6)
			this.posicion += posicion;
	}

	/**
	 * Gets the turno.
	 *
	 * @return the turno
	 */
	public int getTurno() {
		return turno;
	}

	/**
	 * Escalera.
	 */
	public void escalera() {
		posicion += 18;
	}

	/**
	 * Serpiente.
	 */
	public void serpiente() {
		posicion -= 18;
	}

	/**
	 * Reset.
	 */
	public void reset() {
		posicion = 1;
	}
}
