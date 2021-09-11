package escalerasSerpientes;

import java.io.File;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.SourceDataLine;
// Clase en construccion para el reproductor de audio mp3
public class MediaPlay {
	private AudioInputStream audio;
	//private AudioFormat formato;

	public void MediaPlay(String ruta) {
		File archivo = new File(ruta);
		audio = getAudioInputStream(archivo);
	//final	AudioFormat formato =  getOutFormat(audio.getFormat());
		//final Info info = new Info(SourceDataLine.class, formato);

	}

	private AudioFormat getOutFormat(AudioFormat format) {
		// TODO Auto-generated method stub
		return null;
	}

	private AudioInputStream getAudioInputStream(File archivo) {
		// TODO Auto-generated method stub
		return null;
	}
}