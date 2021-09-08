package escalerasSerpientes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Dibujo extends JPanel{
	
	private Image serpiente,escalera,snake;
	public Dibujo() {
		
		this.setBackground(Color.BLUE.brighter().brighter().brighter());
		this.serpiente = new ImageIcon("src/imagenes/serpiente.png").getImage();
		this.escalera = new ImageIcon("src/imagenes/escalera.png").getImage();
		this.snake = new ImageIcon("src/imagenes/snake.png").getImage();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//g.drawImage(escalera,10,20, 80, 100, this);
		//g.drawImage(serpiente, 80, 50, 50, 300, this);
		g.drawImage(snake, 0, 0, 100, 200, this);
		
		
		
	}

}
