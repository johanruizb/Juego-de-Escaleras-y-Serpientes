package escalerasSerpientes;

public class Jugador {
	private int posicion = 1;
	private String nombre = null;

	public Jugador(String s) {
		nombre = s;
	}

	public int getPosicion() {
		return posicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setPosicion(int posicion) {
		if (posicion < 7 && posicion > 0)
			this.posicion += posicion;
	}

	public void escalera() {
		System.out.println("Escalera");

		posicion += 18;
	}

	public void serpiente() {
		System.out.println("Serpiente");

		posicion -= 18;
	}
}
