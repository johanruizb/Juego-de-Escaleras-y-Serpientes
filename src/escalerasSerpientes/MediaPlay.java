package escalerasSerpientes;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// Clase en construccion para el reproductor de audio mp3
public class MediaPlay {
	private Media audio;
	private MediaPlayer musica;
	private String son;

	public MediaPlay(String nombre) {
		this.son = nombre;
		String ruta = "src/sonidos/" + son + ".mp3";
		audio = new Media(new File(ruta).toURI().toString());
		musica = new MediaPlayer(audio);

	}

	public void reproducir() {
		musica.play();
	}
	public void stop() {
		musica.stop();
	}
	 public void pausar() {
		 musica.pause();
	 }
}
