package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Cuadro {

	private int xEnTablero, yEnTablero;
	private int origenCoordenadaX, origenCoordenadaY;
	private int alto, ancho;
	private int jugadorPropietario = 0;

	/**
	 * @param xEnTablero
	 * @param yEnTablero
	 */
	public Cuadro(int xEnTablero, int yEnTablero) {
		super();
		this.xEnTablero = xEnTablero;
		this.yEnTablero = yEnTablero;
	}

	public void paint(Graphics g) {
		ancho = TresEnRaya.getInstance().getWidth() / 3;
		alto = TresEnRaya.getInstance().getHeight() / 3;
		origenCoordenadaX = ancho * xEnTablero;
		origenCoordenadaY = alto * yEnTablero;

		g.setColor(Color.BLACK);
		g.drawRect(origenCoordenadaX, origenCoordenadaY, ancho, alto);

		pintaImagenBitMap(g);
	}

	public boolean clickSobreMi(int xClick, int yClick) {
		if (xClick > origenCoordenadaX && xClick < (origenCoordenadaX + ancho) && yClick > origenCoordenadaY
				&& yClick < (origenCoordenadaY + alto)) {
			return true;
		}
		return false;
	}

	public void click(int jugador) {
		if (this.jugadorPropietario == 0) {
			this.jugadorPropietario = jugador;
		}
		
		TresEnRaya.getInstance().getMatrizJugadas()[this.yEnTablero][this.xEnTablero] = jugador;

		TresEnRaya.getInstance().repaint();
		TresEnRaya.getInstance().revalidate();
		
		System.out.println("Estado del juego");
		for (int i = 0; i < TresEnRaya.getInstance().getMatrizJugadas().length; i++) {
			for (int j = 0; j < TresEnRaya.getInstance().getMatrizJugadas()[i].length; j++) {
				System.out.print(TresEnRaya.getInstance().getMatrizJugadas()[i][j] + "\t");
			}
			System.out.println();
		}
		
		//Comprobar ganador
		TresEnRaya.getInstance().hayGanador(jugadorPropietario);
	}


	private void pintaImagenBitMap (Graphics g) {
		BufferedImage imagenAPintar = null;
		//Choose the player's image
		if (jugadorPropietario == TresEnRaya.JUGADOR_1) {
			imagenAPintar = SpritesRepository.getInstance().getSprite("X.png");
		}

		if (jugadorPropietario == TresEnRaya.JUGADOR_2) {
			imagenAPintar = SpritesRepository.getInstance().getSprite("O.png");
		}
		
		//once chosen, paint
		
		if (imagenAPintar != null) {
			int x = this.origenCoordenadaX + this.ancho / 2 - imagenAPintar.getWidth()	/ 2;
			int y = this.origenCoordenadaY + this.alto / 2 - imagenAPintar.getHeight()	/ 2;
			g.drawImage(imagenAPintar, x, y, null);
		}
	}

	@Override
	public String toString() {
		return "Cuadro [xEnTablero=" + xEnTablero + ", yEnTablero=" + yEnTablero + "]";
	}

	/**
	 * @return the xEnTablero
	 */
	public int getxEnTablero() {
		return xEnTablero;
	}

	/**
	 * @param xEnTablero the xEnTablero to set
	 */
	public void setxEnTablero(int xEnTablero) {
		this.xEnTablero = xEnTablero;
	}

	/**
	 * @return the yEnTablero
	 */
	public int getyEnTablero() {
		return yEnTablero;
	}

	/**
	 * @param yEnTablero the yEnTablero to set
	 */
	public void setyEnTablero(int yEnTablero) {
		this.yEnTablero = yEnTablero;
	}

}
