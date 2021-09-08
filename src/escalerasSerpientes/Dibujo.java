package escalerasSerpientes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Stroke;

public class Dibujo extends JPanel {

	private Image serpiente, escalera, snake;

	public Dibujo() {

//		this.setBackground(Color.BLUE.brighter().brighter().brighter());
		this.serpiente = new ImageIcon("src/imagenes/serpiente.png").getImage();
		this.escalera = new ImageIcon("src/imagenes/escalera.png").getImage();
		this.snake = new ImageIcon("src/imagenes/snake.png").getImage();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// g.drawImage(escalera,10,20, 80, 100, this);
		// g.drawImage(serpiente, 80, 50, 50, 300, this);
//		g.drawImage(serpiente, 0, 0, 200, 200, this);

		CubicCurve2D.Double cubic = new CubicCurve2D.Double();
		Point2D.Double start, end, uno, dos;
		start = new Point2D.Double();

		start.x = 10;
		start.y = 10;

		uno = new Point2D.Double();

		uno.x = 100;
		uno.y = 10;

		dos = new Point2D.Double();

		dos.x = 10;
		dos.y = 100;

		end = new Point2D.Double();

		end.x = 100;
		end.y = 100;

		cubic.setCurve(start, uno, dos, end);

		g.setColor(Color.GREEN.darker());

		g.fillOval(0, 0, 25, 25);

		((Graphics2D) g).setStroke(new BasicStroke(10));
		((Graphics2D) g).draw(cubic);
	}

}
