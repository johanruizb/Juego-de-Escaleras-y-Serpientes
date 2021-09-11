package escalerasSerpientes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Tirar extends JFrame {
	private Sonido sonido;


	//private MediaPlay play;

	//private MediaPlay play;


	private MediaPlay play;


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
		sonido = new Sonido();


		//play = new MediaPlay();

//		play = new MediaPlay();


//		play = new MediaPlay();

		JButton b = new JButton("Reproducir");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				sonido.reproductorMusic("audio1");


			//play.MediaPlay("audio2.mp3");
				
		//play = new MediaPlay("audio2.mp3");

		play = new MediaPlay("audio2.mp3");


			}

		});
		add(b, BorderLayout.CENTER);
	}

}
