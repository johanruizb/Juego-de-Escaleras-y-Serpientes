package escalerasSerpientes;
import java.util.Random;

public class Dado {
	private int numero;
	/*Clase dado se encarga de generar un numero aleatorio de 1 a 6 para las caras del dado
	 * */
	public int caraDado() {
		Random ran = new Random();
		numero = ran.nextInt(6)+1;
		return numero;
	}
}
