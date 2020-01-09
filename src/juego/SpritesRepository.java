package juego;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpritesRepository {

	private HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();
	
	private static SpritesRepository instance = null;
	
	private static String RESOURCES_FOLDER = "../resources/images/";
	
	public static String IMAGEN_JUGADOR_1 = "O.png";
	public static String IMAGEN_JUGADOR_2 = "X.png";
	
	
	
	
	/**
	 * 
	 */
	public SpritesRepository() {
		this.getSprite(IMAGEN_JUGADOR_1);
		this.getSprite(IMAGEN_JUGADOR_2);
	}
	
	private BufferedImage loadImage (String resourceName) {
		
		URL url = null;
		
		try {
			url = getClass().getResource(resourceName);
			return ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("No se pudo cargar la imagen " + resourceName + " de " + url);
			System.out.println("Error: " + e.getClass().getName()+ " " + e.getMessage());
			System.exit(0);
		}
		return null;
		
	}
	
	public BufferedImage getSprite (String resourceName) {
		BufferedImage img = sprites.get(resourceName);
		
		if (img == null) {
			img = loadImage(RESOURCES_FOLDER + resourceName);
			
			sprites.put(resourceName, img);
		}
		return img;
	}



	//Singleton pattern
	public static SpritesRepository getInstance() {
		if (instance == null) {
			instance = new SpritesRepository();
		}
		return instance;
	}
}
