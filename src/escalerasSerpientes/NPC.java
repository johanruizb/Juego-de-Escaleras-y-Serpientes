package escalerasSerpientes;

public class NPC implements Runnable {

	private int posicion = 1, numero = 0;
	private String nombre = null;

	public NPC(String s, int i) {
		nombre = s;
		numero = i;
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
//		System.out.println("Escalera");

		posicion += 18;
	}

	public void serpiente() {
//		System.out.println("Serpiente");

		posicion -= 18;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
