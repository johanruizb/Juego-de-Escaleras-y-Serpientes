package escalerasSerpientes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;

public class Tirar extends JFrame {

	// private MediaPlay play;

	// private MediaPlay play;

	private MediaPlay play;
	//private String ruta;
	private JButton reproducir;
	private static int contador;
	private EscuchaPlay escuchaplay;
 private String ruta = "src/sonidos/1.mp3";
	public Tirar() {
		initGUI();
		this.setTitle("Esacaleras y Serpientes");
		this.setVisible(true);
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
//		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initGUI() {
		// TODO Auto-generated method stub
        escuchaplay = new EscuchaPlay();
		JFXPanel panel = new JFXPanel();
		play = new MediaPlay(aleatorio());

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Scene scene = initScene();
				panel.setScene(scene);
			}

		});

		reproducir = new JButton(new ImageIcon("src/imagenes/on.png"));
		reproducir.addActionListener(escuchaplay); 
		reproducir.setBorder(null);
		reproducir.setContentAreaFilled(false);

		

		add(reproducir, BorderLayout.CENTER);
		add(panel, BorderLayout.NORTH);
	}

	private static Scene initScene() {

		Group root = new Group();
		Scene scene = new Scene(root, javafx.scene.paint.Color.ALICEBLUE);
		return (scene);

	}
	public String aleatorio() {
		Random ran = new Random();
		ruta = String.valueOf(ran.nextInt(2)+1);
		return ruta;
	}
	private class EscuchaPlay implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == reproducir) {
				contador++;
			 
			    	 play.pausar();
			}
			if (contador == 2) {

				reproducir.setIcon(new ImageIcon("src/imagenes/off.png"));
				play.pausar();
			}
			if (contador == 3) {
				contador = 1;
				reproducir.setIcon(new ImageIcon("src/imagenes/on.png"));
				play.reproducir();
			}
		}

	}

}
