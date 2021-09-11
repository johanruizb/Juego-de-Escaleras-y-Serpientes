package escalerasSerpientes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		JFXPanel panel = new JFXPanel();
		play = new MediaPlay("audio2");

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Scene scene = initScene();
				panel.setScene(scene);
			}

		});

		JButton b = new JButton("Reproducir");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				play.reproducir();
			}
		});

		add(b, BorderLayout.CENTER);
		add(panel, BorderLayout.NORTH);
	}

	private static Scene initScene() {

		Group root = new Group();
		Scene scene = new Scene(root, javafx.scene.paint.Color.ALICEBLUE);
		return (scene);

	}

}
