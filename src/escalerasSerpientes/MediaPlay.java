package escalerasSerpientes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// Clase en construccion para el reproductor de audio mp3
public class MediaPlay {
	private Media audio;
	private MediaPlayer musica;
	private String son;
	private ReadOnlyIntegerProperty siclos;
	private int x;

	public MediaPlay(String nombre) {
		this.son = nombre;
		String ruta = "src/sonidos/" + son + ".mp3";
		audio = new Media(new File(ruta).toURI().toString());
		musica = new MediaPlayer(audio);
	}
	
	public void reproducir() {

		musica.play();
       
	}

	
	
	/*public int algunMetodo() {
        File file= new File(son).getAbsoluteFile();
	    AudioFileFormat audioFileFormat = null;
		try {
			audioFileFormat = AudioSystem.getAudioFileFormat(file);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Map<String, Object>  properties = ((AudioFileFormat) audioFileFormat).properties();


	    Long micro = (Long) properties.get("duration");
	    int a      = (int) (micro / 1000);
	    int r      = (a / 1000) % 60;
 System.out.println("soy " + r);
	    return r;
	}*/
	
	
	public void pausar() {
		musica.pause();
	}

	// DURACCION DEL AUDIO
	public int getDuration() {
		audio.durationProperty();
		return 0;

	}

	/*public final javafx.util.Duration getTotalDuration() {
		javafx.util.Duration time = musica.getTotalDuration();

		return null;

	}*/
}
