package escalerasSerpientes;

public class Jugador {
	private int posicion = 1, turno = -1;
	private String nombre = null;

	public Jugador(String s, int i) {
		nombre = s;
		turno = i;
	}

	public int getPosicion() {
		return posicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setPosicion(int posicion) {
//		if (posicion >= 1 && posicion <= 6)
			this.posicion += posicion;
	}

	public int getTurno() {
		return turno;
	}

	public void escalera() {
//		System.out.println("Escalera");
		posicion += 18;
	}

	public void serpiente() {
//		System.out.println("Serpiente");
		posicion -= 18;
	}

	public void reset() {
		// TODO Auto-generated method stub
		posicion = 1;
	}
}
