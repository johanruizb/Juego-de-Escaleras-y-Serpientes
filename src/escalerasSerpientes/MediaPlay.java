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
import javafx.beans.property.ReadOnlyObjectProperty;
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

		musica.setCycleCount(musica.INDEFINITE);
	
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
