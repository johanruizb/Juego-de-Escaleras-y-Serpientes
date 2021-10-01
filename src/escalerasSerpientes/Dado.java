package escalerasSerpientes;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Dado. Clase dado se encarga de generar un numero aleatorio de 1 a 6
 * para las caras del dado
 * 
 * /*
 */
public class Dado {

	/** The numero entero donde se almacena el valor generado pro el random. */
	private int numero;

	/**
	 * Cara dado genera un numero entero aleatorio .
	 *
	 * @return the int
	 */
	public int caraDado() {
		Random ran = new Random();
		numero = ran.nextInt(6) + 1;
		return numero;
	}
}
