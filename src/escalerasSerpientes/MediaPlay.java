/*
 * 
 */
package escalerasSerpientes;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// TODO: Auto-generated Javadoc
/**
 * The Class MediaPlay es una clase para ra reproduccion de musica durante el
 * juego se encarga de leer archivos .pm3.
 */
// Clase en construccion para el reproductor de audio mp3
public class MediaPlay {

	/** The audio. */
	private Media audio;

	/** The musica. */
	private MediaPlayer musica;

	/** The son. */
	private String son;

	/**
	 * Instantiates a new media play. Constructor de la clase encargado de
	 * inicializar los objectos y cargar los recursos para la reproduccion
	 * 
	 * @param nombre the nombre
	 */
	public MediaPlay(String nombre) {
		this.son = nombre;
		String ruta = "src/sonidos/" + son + ".mp3";
		audio = new Media(new File(ruta).toURI().toString());
		musica = new MediaPlayer(audio);

	}

	/**
	 * Reproducir metodo encargado de la reproduccion infinita de la musica.
	 */
	public void reproducir() {

		musica.setCycleCount(MediaPlayer.INDEFINITE);
		musica.play();

	}

	/**
	 * Pausar metodo encargado de pausar la reproduccion.
	 */
	public void pausar() {
		musica.pause();
	}

}
