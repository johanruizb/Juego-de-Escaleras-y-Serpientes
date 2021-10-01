package escalerasSerpientes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class DibujoEscalerasSerpientes.
 */
public class DibujoEscalerasSerpientes extends JPanel {

	/**
	 * clase especializada en el dibujo de los graficos escaleras en el panel.
	 */
	private static final long serialVersionUID = 7794229655855274126L;

	/**
	 * The serpientes coleccion donde se lamacena los datos del dibujo de la
	 * serpiente.
	 */
	private ArrayList<ArrayList<Integer>> serpientes = new ArrayList<ArrayList<Integer>>();

	/**
	 * The escaleras coleccion donde se almacena los datos del dibujo de las
	 * escaleras.
	 */
	private ArrayList<ArrayList<Integer>> escaleras = new ArrayList<ArrayList<Integer>>();

	/**
	 * The colores coleccion donde se lamacena los colores utilizados en el
	 * graficados de de los dibujos generados por la clase y metodos.
	 */
	private ArrayList<Color> colores = new ArrayList<>(
			Arrays.asList(Color.ORANGE, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.GREEN));

	/** The colores ran. */
	private ArrayList<Color> coloresRan = new ArrayList<>();

	/**
	 * Instantiates a new dibujo escaleras serpientes.
	 *
	 * @param auxPoint  the aux point
	 * @param auxPoint2 the aux point 2
	 */
	public DibujoEscalerasSerpientes(ArrayList<ArrayList<Integer>> auxPoint, ArrayList<ArrayList<Integer>> auxPoint2) {
		serpientes.addAll(auxPoint);
		escaleras.addAll(auxPoint2);
		this.setOpaque(false);

		this.setBounds(18, 6, 400, 400);

	}

	/**
	 * Sets the list.
	 *
	 * @param auxPoint  the aux point
	 * @param auxPoint2 the aux point 2
	 */
	public void setList(ArrayList<ArrayList<Integer>> auxPoint, ArrayList<ArrayList<Integer>> auxPoint2) {
		serpientes.clear();
		escaleras.clear();

		serpientes.addAll(auxPoint);
		escaleras.addAll(auxPoint2);
	}

	/**
	 * Paint component.
	 *
	 * @param g the g
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		dibujarEscaleras(g);
		dibujarSerpientes(g);
	}

	/**
	 * Dibujar escaleras.
	 *Este metodo se encarga del graficado de las escaleras
	 * @param g the g
	 */
	private void dibujarEscaleras(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; escaleras.size() > 0 && (i < escaleras.size()); i++) {

			// BASES

			// POSICIONES INCIALES DE LAS BASES
			int x1Inicio = escaleras.get(i).get(2) + 6, y1Inicio = escaleras.get(i).get(3) + 20,
					x1Fin = escaleras.get(i).get(0), y1Fin = escaleras.get(i).get(1) + 8;
			int x2Inicio = x1Inicio + 20, y2Inicio = y1Inicio, x2Fin = x1Fin + 20, y2Fin = y1Fin;
			int y1Copy = y1Inicio, y2Copy = y2Inicio;

			// DISTANCIA
			double d1 = Math.sqrt(Math.pow((x1Inicio - x1Fin), 2) + Math.pow((y1Inicio - y1Fin), 2));

			// ESPACIO ENTRE ESCALONES
			int espacioEscalones;
			espacioEscalones = (int) Math.ceil(d1 / 11) - 2;

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

				y1Copy -= 8;
				y2Copy -= 8;

				double isX1 = (((y1Copy) - n1) / m1);
				double isX2 = (((y2Copy) - n2) / m2);

				y1Inicio += 8;
				y2Inicio += 8;

				x1Inicio = (int) Math.round(isX1);
				x2Inicio = (int) Math.floor(isX2);

				// Correccion de posicion;
				x2Inicio -= 1;
				// ---

				g.drawLine(x1Inicio, y1Inicio, x2Inicio, y2Inicio);
			}
		}

	}

	/**
	 * Dibujar serpientes.
	 *Metodo encargado del dibujo de las serpientes
	 * @param g the g
	 */
	private void dibujarSerpientes(Graphics g) {
		// TODO Auto-generated method stub
		Random random1 = new Random();

		if (coloresRan.isEmpty()) {
			coloresRan.addAll(Arrays.asList(colores.get(random1.nextInt(colores.size())),
					colores.get(random1.nextInt(colores.size())), colores.get(random1.nextInt(colores.size())),
					colores.get(random1.nextInt(colores.size())), colores.get(random1.nextInt(colores.size()))));
		}


		for (int i = serpientes.size() - 1; serpientes.size() > 0 && (i >= 0); i--) {

			// COORDENADAS
			int xInicio = serpientes.get(i).get(0), yInicio = serpientes.get(i).get(1) + 20;
			int xFin = serpientes.get(i).get(2) + 8, yFin = serpientes.get(i).get(3) + 20;

			boolean derecha = (xInicio > xFin);

			// SERPIENTE

			// CUERPO
			g.setColor(colores.get(i).darker());
			CubicCurve2D.Double cubic = new CubicCurve2D.Double();
			Point2D.Double start, end, uno, dos;

			// INICIO - CUERPO
			start = new Point2D.Double();
			if (derecha) {
				start.x = xInicio;
				start.y = yInicio;
			} else {
				start.x = xInicio + 10;
				start.y = yInicio;
			}
			// CURVA 1
			uno = new Point2D.Double();
			if (derecha) {
				uno.x = xFin;
				uno.y = yInicio;
			} else {
				uno.x = xFin + 10;
				uno.y = yInicio + 10;
			}
			// CURVA 2
			dos = new Point2D.Double();
			dos.x = xInicio;
			dos.y = yFin;

			// FIN - CUERPO
			end = new Point2D.Double();
			if (derecha) {
				end.x = xFin;
				end.y = yFin;
			} else {
				end.x = xFin;
				end.y = yFin;
			}
			cubic.setCurve(start, uno, dos, end);

			// PINTANDO CUERPO
			((Graphics2D) g).setStroke(new BasicStroke(10));
			((Graphics2D) g).draw(cubic);

			// BOCA
			g.setColor(Color.RED);
			if (derecha) {
				g.fillRect(xInicio + 20, yInicio, 5, 2);
			} else {
				g.fillRect(xInicio - 14, yInicio, 5, 2);
			}

			// CABEZA
			g.setColor(colores.get(i).darker());
			if (derecha) {
				g.fillOval(xInicio - 10, yInicio - 6, 30, 15);
			} else {
				g.fillOval(xInicio - 5, yInicio - 6, 30, 15);
			}
			// COLA

			if (derecha) {
				g.fillOval(xFin - 15, yFin - 6, 30, 10);
			} else {
				g.fillOval(xFin - 10, yFin - 6, 30, 10);
			}
			// OJOS
			g.setColor(Color.WHITE);
			if (derecha) {
				g.fillOval(xInicio + 4, yInicio - 5, 5, 5);
				g.fillOval(xInicio + 4, yInicio + 2, 5, 5);
			} else {
				g.fillOval(xInicio - 4, yInicio - 5, 5, 5);
				g.fillOval(xInicio - 4, yInicio + 2, 5, 5);
			}

		}
	}

}
