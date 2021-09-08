package escalerasSerpientes;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import escalerasSerpientes.Dibujo;

public class GUIEscaleraSerpientes extends JFrame{
 
	private Dibujo dibujo;
	
	public GUIEscaleraSerpientes() {
		initGUI();
		this.setTitle("Esacaleras y Serpientes");
		this.setVisible(true);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initGUI() {
		dibujo = new Dibujo();
		add(dibujo,BorderLayout.CENTER);
		
	}
	

}
