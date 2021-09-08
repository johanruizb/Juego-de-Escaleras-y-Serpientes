package escalerasSerpientes;

import java.awt.EventQueue;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TableroJuego prueba = new TableroJuego();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				GUIEscaleraSerpientes gui = new GUIEscaleraSerpientes();
			}
		});

	}

}
