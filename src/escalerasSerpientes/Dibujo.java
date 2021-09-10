package escalerasSerpientes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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

		int x1Inicio = 20, y1Inicio = 60, x1Fin = 150, y1Fin = 150;

		int x2Inicio = x1Inicio + 20, y2Inicio = y1Inicio, x2Fin = x1Fin + 20, y2Fin = y1Fin;

		double d = Math.sqrt(Math.pow((x1Inicio - x1Fin), 2) + Math.pow((y1Inicio - y1Fin), 2));

		System.out.println("Distancia " + (d / 6 - 2));

		int divisiones = (int) d;

		((Graphics2D) g).setStroke(new BasicStroke(3));
		g.drawLine(x1Inicio, y1Inicio, x1Fin + 6, y1Fin + 6);
		g.drawLine(x2Inicio - 6, y2Inicio - 6, x2Fin, y2Fin);

		double a1 = (y1Inicio + y1Fin), a2 = (x1Fin + x1Inicio), a = a1 / a2;

		int aux = (int) Math.ceil((a - 1));

		System.out.println("Pendiente " + a + " aux " + aux);

		for (int i = 0; i < (divisiones / 8) - (2 * aux); i++) {
			g.drawLine((x1Inicio + 6) + 5, y1Inicio + 6, x1Inicio + 20, y1Inicio);
			x1Inicio += 6 + (1 * aux);
			y1Inicio += 6;

//			System.out.println(xInicio);
		}

//		dibujarSerpientes(g);

	}

	private void dibujarSerpientes(Graphics g) {
		// TODO Auto-generated method stub
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
