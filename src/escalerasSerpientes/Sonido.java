package escalerasSerpientes;

import java.awt.MediaTracker;
import java.io.File;
import java.io.IOException;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

// Clase qiue reproduce un archivo wav esta funcionando
public class Sonido {
	private AudioInputStream entrada;

	public void reproductorMusic(String name) {

		try {
			entrada = AudioSystem.getAudioInputStream(new File("src/sonidos/" + name + ".wav"));
		} catch (UnsupportedAudioFileException e) { // TODO Auto-generated
			e.printStackTrace();
		} catch (IOException e) { // TODO Auto-generated
			e.printStackTrace();
		}
	}

	try
	{
		rp = AudioSystem.getClip();
		try {
			rp.open(entrada);
		} catch (IOException e) { // TODO Auto-generated
			e.printStackTrace();
		}
	}rp.start();}catch(
	LineUnavailableException e)
	{ // TODO Auto-generated
	 e.printStackTrace(); }

}
