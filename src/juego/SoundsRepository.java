package juego;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.HashMap;

public class SoundsRepository {
	
	private HashMap<String, AudioClip> sounds = new HashMap<String, AudioClip>();
	
	private static SoundsRepository instance = null;
	
	private static String RESOURCES_FOLDER = "../resources/sounds/";
	
	public static String MUSICA_DE_FONDO = "Evangelion-Opening.wav";
	public static String EFECTO_JUGADOR_1 = "missile.wav";
	public static String EFECTO_JUGADOR_2 = "photon.wav";
	

	/**
	 * 
	 */
	public SoundsRepository() {
		this.getAudioClip(MUSICA_DE_FONDO);
		this.getAudioClip(EFECTO_JUGADOR_1);
		this.getAudioClip(EFECTO_JUGADOR_2);
	}
	
	public static SoundsRepository getInstance() {
		if (instance == null) {
			instance = new SoundsRepository();
		}
		return instance;
	}
	
	private AudioClip loadResource(String name) {
		URL url=null;
		url = getClass().getResource(name);
		return Applet.newAudioClip(url);
	}
	
	private AudioClip getAudioClip(String resourceName) {
		AudioClip clip = sounds.get(resourceName);
		
		if (clip == null) {
			clip = loadResource(RESOURCES_FOLDER + resourceName);
			
			sounds.put(resourceName, clip);
		}
		
		return clip;
	}
	
	public void playSound(final String name) {
		getAudioClip(name).play();
	}
	
	public void loopSound(final String name) {
		getAudioClip(name).loop();
	}
	
	

}
