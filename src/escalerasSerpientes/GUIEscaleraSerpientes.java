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
		capas.setSize(500, 500);

//		JPanel contenedor = new JPanel(new BorderLayout());
//		contenedor.setSize(400, 400);
//		contenedor.setPreferredSize(new Dimension(400, 400));

		tableroJuego = new JPanel(new GridLayout(10, 10));
		tableroJuego.setSize(500, 500);
//		tableroJuego.setPreferredSize(new Dimension(380, 400));
		tableroJuego.setOpaque(false);

		t1 = new TableroJuego();

		ArrayList<ArrayList<Integer>> t2 = t1.getTablero();

		int k = 0;

		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < 10; j++) {

				JLabel temp = new JLabel();
				temp.setText(String.valueOf(t2.get(i).get(j)));
				temp.setPreferredSize(new Dimension(20, 20));
				temp.setVerticalAlignment(JLabel.CENTER);
				temp.setHorizontalAlignment(JLabel.CENTER);
				temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

				tablero.add(temp);
				tableroJuego.add(tablero.get(k));
				k++;
			}
		}

		// -----

		ArrayList<ArrayList<Integer>> serpientes = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> auxPoint = new ArrayList<>();
		serpientes.addAll(t1.getSerpientes());

		for (int i = 0; i < serpientes.size(); i++) {
			int fila = -1;
			int columna = -1;

			auxPoint.add(new ArrayList<>());

			for (int j = 0; j < 2; j++) {
				if (String.valueOf(serpientes.get(i).get(j)).length() > 1 && serpientes.get(i).get(j) % 10 != 0) {
					fila = 9 - Integer.parseInt(String.valueOf(serpientes.get(i).get(j)).substring(0, 1));
//					System.out.println("Index " + fila);

				} else if (String.valueOf(serpientes.get(i).get(j)).length() == 1 || serpientes.get(i).get(j) == 10) {
					fila = 9;
//					System.out.println("Index " + fila);

				} else if (serpientes.get(i).get(j) % 10 == 0) {
					fila = 10 - Integer.parseInt(String.valueOf(serpientes.get(i).get(j)).substring(0, 1));

//					System.out.println("Index " + fila);
				}

				columna = t2.get(fila).indexOf(serpientes.get(i).get(j));

//				System.out.println("encontrado en " + fila + ", " + columna);
//				System.out.println("posicion " + (fila * 20) + "-" + (columna * 20));
				auxPoint.get(i).add((columna * 40) + 30);
				auxPoint.get(i).add((fila * 40) + 30);
			}

		}

		dibujo = new Dibujo(auxPoint);

		// ---

		tableroJuego.setBounds(18, 6, 400, 400);
//		capas.add(tableroJuego, new Integer(2));

//		dibujo = new Dibujo();
		dibujo.setSize(500, 500);
		capas.add(dibujo, new Integer(1));

		add(capas, BorderLayout.CENTER);

	}

}
