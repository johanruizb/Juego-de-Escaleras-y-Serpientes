package escalerasSerpientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GUIEscaleraSerpientes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dibujo dibujo;
	private JPanel tableroJuego;
	private ArrayList<JLabel> tablero = new ArrayList<JLabel>(100);

	private TableroJuego t1;

	public GUIEscaleraSerpientes() {
		initGUI();
		this.setTitle("Esacaleras y Serpientes");
		this.setVisible(true);
		this.setSize(450, 450);
		this.setLocationRelativeTo(null);
//		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void initGUI() {
//		getContentPane().setLayout(new FlowLayout());

		JLayeredPane capas = new JLayeredPane();
		capas.setSize(400, 400);

//		JPanel contenedor = new JPanel(new BorderLayout());
//		contenedor.setSize(400, 400);
//		contenedor.setPreferredSize(new Dimension(400, 400));

		dibujo = new Dibujo();
		dibujo.setSize(400, 400);

		tableroJuego = new JPanel(new GridLayout(10, 10));
		tableroJuego.setSize(400, 400);
//		tableroJuego.setPreferredSize(new Dimension(380, 400));
		tableroJuego.setOpaque(false);

		t1 = new TableroJuego();

		ArrayList<ArrayList<Integer>> t2 = t1.getTablero();

		int k = 0, m = 0;

		for (int i = 9; i >= 0; i--) {

			for (int j = 9; j >= 0; j--) {

				JLabel temp = new JLabel();
				temp.setText(String.valueOf(t2.get(i).get(j)));
				temp.setPreferredSize(new Dimension(20, 20));
				temp.setVerticalAlignment(JLabel.CENTER);
				temp.setHorizontalAlignment(JLabel.CENTER);
				temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

				tablero.add(temp);

			}
		}

		// AÑADIRLOS EN EL ORDEN NECESARIO
		for (int i = 9; i >= 0; i--) {
			for (int j = 9; j >= 0 && ((i + 1) % 2) != 0; j--) {
				tableroJuego.add(tablero.get(j + k));

//				System.out.println("Añadiendo 0 - " + tablero.get(j + k).getText());
			}

			k += 10;

			for (int n = 0; n < 10 && ((i + 1) % 2) == 0; n++) {
				tableroJuego.add(tablero.get(n + m));

//				System.out.println("Añadiendo 1 - " + tablero.get(n + m).getText());
			}
			m += 10;

		}

		// -----

		tableroJuego.setBounds(18, 6, 400, 400);

		capas.add(tableroJuego, new Integer(1));
		// capas.add(dibujo, new Integer(2));

		add(capas, BorderLayout.CENTER);

	}

}
