package escalerasSerpientes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JPanel;

public class Dibujo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7794229655855274126L;
	private ArrayList<ArrayList<Integer>> serpientes = new ArrayList<ArrayList<Integer>>();

	private ArrayList<Color> colores = new ArrayList<>(
			Arrays.asList(Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN));

	public Dibujo(ArrayList<ArrayList<Integer>> auxPoint) {

		serpientes.addAll(auxPoint);

//		this.setPreferredSize(new Dimension(400, 400));
		this.setOpaque(false);
//		this.setBackground(new Color(0, 0, 0));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Random random1 = new Random();

		for (int i = serpientes.size() - 1; serpientes.size() > 0 && (i >= 0); i--) {

			int color1 = random1.nextInt(colores.size());

			int xInicio = serpientes.get(i).get(0), yInicio = serpientes.get(i).get(1);
			int xFin = serpientes.get(i).get(2), yFin = serpientes.get(i).get(3);

			// SERPIENTE

			// CUERPO
			g.setColor(colores.get(color1).darker());
			CubicCurve2D.Double cubic = new CubicCurve2D.Double();
			Point2D.Double start, end, uno, dos;
			// INICIO - CUERPO
			start = new Point2D.Double();
			start.x = xInicio;
			start.y = yInicio;

			// CURVA 1
			uno = new Point2D.Double();
			uno.x = xFin;
			uno.y = yInicio;

			// CURVA 2
			dos = new Point2D.Double();
			dos.x = xInicio;
			dos.y = yFin;

			// FIN - CUERPO
			end = new Point2D.Double();
			end.x = xFin;
			end.y = yFin;

			cubic.setCurve(start, uno, dos, end);

			// PINTANDO CUERPO
			((Graphics2D) g).setStroke(new BasicStroke(10));
			((Graphics2D) g).draw(cubic);

			// BOCA
			g.setColor(Color.RED);
			if (xInicio > xFin) {
				g.fillRect(xInicio + 20, yInicio, 15, 2);
			} else {
				g.fillRect(xInicio - 24, yInicio, 15, 2);
			}

			// CABEZA
			g.setColor(colores.get(color1).darker());
			g.fillOval(xInicio - 10, yInicio - 6, 30, 15);

			// COLA
			g.fillOval(xFin - 10, yFin - 6, 30, 10);

			// OJOS
			g.setColor(Color.WHITE);
			if (xInicio > xFin) {
				g.fillOval(xInicio + 4, yInicio - 5, 5, 5);
				g.fillOval(xInicio + 4, yInicio + 2, 5, 5);
			} else {
				g.fillOval(xInicio - 4, yInicio - 5, 5, 5);
				g.fillOval(xInicio - 4, yInicio + 2, 5, 5);
			}

		}
	}

}
