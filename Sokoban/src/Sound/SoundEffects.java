package Sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffects {

	Clip clip = null;
	
		public SoundEffects(String file) {
			 try {
				AudioInputStream sound = AudioSystem.getAudioInputStream(new File(file));
				clip = AudioSystem.getClip();
				clip.open(sound);
			} catch (Exception e) {
				System.out.println("file sound non trovato");
			}
		}
		
		public void playSound() {
			clip.setFramePosition(0);
			clip.start();
		}
}
