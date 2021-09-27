package escalerasSerpientes;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// Clase en construccion para el reproductor de audio mp3
public class MediaPlay {
	private Media audio;
	private MediaPlayer musica;
	private String son;
	private javafx.util.Duration time, time2;

	public MediaPlay(String nombre) {
		this.son = nombre;
		String ruta = "src/sonidos/" + son + ".mp3";
		audio = new Media(new File(ruta).toURI().toString());
		musica = new MediaPlayer(audio);

	}

	public void reproducir() {

		musica.setCycleCount(MediaPlayer.INDEFINITE);
	
		musica.play();

	}

	public void pausar() {
		musica.pause();
	}

	public void stop() {
		musica.stop();
	}

	// DURACCION DEL AUDIO

	public double duracion() {

		time = musica.getTotalDuration();

		System.out.println("Soy duracion = " + time);
		return time.toSeconds();

	}

	public final MediaPlayer.Status getStatus() {
		System.out.println("Soy el estado actual " + musica.getStatus());
		return musica.getStatus();

	}

	
}
