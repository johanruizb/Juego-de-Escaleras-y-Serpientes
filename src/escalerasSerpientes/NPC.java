package escalerasSerpientes;

public class NPC implements Runnable {

	private int posicion = 1, turno = 0;
	private String nombre = null;
	private ControlJuego recurso;

	public NPC(String s, int i, ControlJuego r) {
		nombre = s;
		turno = i;
		recurso = r;
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
		try {
			recurso.lanzar(turno, this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public void reset() {
		// TODO Auto-generated method stub
		posicion = 1;
	}

}
