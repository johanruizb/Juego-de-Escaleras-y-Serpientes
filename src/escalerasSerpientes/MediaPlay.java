package escalerasSerpientes;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// Clase en construccion para el reproductor de audio mp3
public class MediaPlay {
	private Media audio;
	private MediaPlayer musica;

	public MediaPlay(String ruta) {
		audio = new Media(new File(ruta).toURI().toString());
		musica = new MediaPlayer(audio);
		musica.play();
	}
}

