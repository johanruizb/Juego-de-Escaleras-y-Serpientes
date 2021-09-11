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
	private ArrayList<ArrayList<Integer>> escaleras = new ArrayList<ArrayList<Integer>>();

	private ArrayList<Color> colores = new ArrayList<>(
			Arrays.asList(Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN));

	public Dibujo(ArrayList<ArrayList<Integer>> auxPoint, ArrayList<ArrayList<Integer>> auxPoint2) {
		serpientes.addAll(auxPoint);
		escaleras.addAll(auxPoint2);
		this.setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		dibujarEscaleras(g);
		dibujarSerpientes(g);
	}

	private void dibujarEscaleras(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; escaleras.size() > 0 && (i < escaleras.size()); i++) {

			// BASES

			// POSICIONES INCIALES DE LAS BASES
			int x1Inicio = escaleras.get(i).get(2), y1Inicio = escaleras.get(i).get(3), x1Fin = escaleras.get(i).get(0),
					y1Fin = escaleras.get(i).get(1);
			int x2Inicio = x1Inicio + 20, y2Inicio = y1Inicio, x2Fin = x1Fin + 20, y2Fin = y1Fin;
			int y1Copy = y1Inicio, y2Copy = y2Inicio;

			// DISTANCIA
			double d1 = Math.sqrt(Math.pow((x1Inicio - x1Fin), 2) + Math.pow((y1Inicio - y1Fin), 2));

			// ESPACIO ENTRE ESCALONES
			int espacioEscalones;
			espacioEscalones = (int) Math.ceil(d1 / 15) - 1;

			// DIBUJAR BASES
			g.setColor(Color.BLACK);
			((Graphics2D) g).setStroke(new BasicStroke(3));
			g.drawLine(x1Inicio, y1Inicio, x1Fin, y1Fin);
			g.drawLine(x2Inicio, y2Inicio, x2Fin, y2Fin);

			// ESCALONES

			// CALCULOS PARA LOS ESCALONES
			double a1 = (y1Inicio - y1Fin), a2 = (x1Fin - x1Inicio), m1 = a1 / a2;
			double b1 = (y2Inicio - y2Fin), b2 = (x2Fin - x2Inicio), m2 = b1 / b2;
			double n1 = y1Inicio - (m1 * x1Inicio);
			double n2 = y2Inicio - (m2 * x2Inicio);

			// PINTAR ESCALONES
			for (int j = 0; j < (espacioEscalones); j++) {

				y1Copy -= 10;
				y2Copy -= 10;

				double isX1 = (((y1Copy) - n1) / m1);
				double isX2 = (((y2Copy) - n2) / m2);

				y1Inicio += 10;
				y2Inicio += 10;

				x1Inicio = (int) Math.round(isX1);
				x2Inicio = (int) Math.floor(isX2);

				// Correccion de posicion;
				x2Inicio -= 1;
				// ---

				g.drawLine(x1Inicio, y1Inicio, x2Inicio, y2Inicio);
			}
		}

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
