package escalerasSerpientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIEscaleraSerpientes extends JFrame {

	private Dibujo dibujo;
	private JPanel tableroJuego;
	private ArrayList<JLabel> tablero = new ArrayList<JLabel>(100);

	private TableroJuego t1;

	public GUIEscaleraSerpientes() {
		initGUI();
		this.setTitle("Esacaleras y Serpientes");
		this.setVisible(true);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
//		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void initGUI() {
		getContentPane().setLayout(new FlowLayout());

		dibujo = new Dibujo();

		tableroJuego = new JPanel();
		tableroJuego.setPreferredSize(new Dimension(380, 400));

		t1 = new TableroJuego();

		ArrayList<ArrayList<Integer>> t2 = t1.getTablero();

		int k = 0;

		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < 10; j++) {

				JLabel temp = new JLabel();
				temp.setText(String.valueOf(t2.get(i).get(j)));
				temp.setPreferredSize(new Dimension(30, 30));
				temp.setVerticalAlignment(JLabel.CENTER);
				temp.setHorizontalAlignment(JLabel.CENTER);
				temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

				tablero.add(temp);
				dibujo.add(tablero.get(k));
				k++;
			}
		}

		// tableroJuego.add(dibujo);
		// repaint();
		// dibujo.setPintar(true);

		add(dibujo);
	}

}
