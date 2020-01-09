package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TresEnRaya extends Canvas {

	JFrame ventana = new JFrame();

	private static final int JFRAME_WIDTH = 700;
	private static final int JFRAME_HEIGHT = 700;
	private static TresEnRaya instance = null;
	public static int JUGADOR_1 = 1;
	public static int JUGADOR_2 = 2;
	private int turnoActual = JUGADOR_1;

	private int matrizJugadas[][] = new int[][]{{ 0, 0, 0 },
												{ 0, 0, 0 },
												{ 0, 0, 0 } };

	List<Cuadro> cuadros = new ArrayList<Cuadro>();

	/**
	 * 
	 */
	public TresEnRaya() {

		SoundsRepository.getInstance();
		SpritesRepository.getInstance();

		JPanel panel = (JPanel) ventana.getContentPane();

		panel.setLayout(new BorderLayout());

		panel.add(this, BorderLayout.CENTER);

		ventana.setBounds(100, 100, JFRAME_WIDTH, JFRAME_HEIGHT);

		crearCuadros();


		SoundsRepository.getInstance().loopSound("BSO.wav");

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {

					for (Cuadro cuadro : cuadros) {
						if (cuadro.clickSobreMi(e.getX(), e.getY())) {
							cuadro.click(turnoActual);

							if (turnoActual == JUGADOR_1) {
								turnoActual = JUGADOR_2;
								SoundsRepository.getInstance().playSound("missile.wav");
							} else {
								turnoActual = JUGADOR_1;
								SoundsRepository.getInstance().playSound("photon.wav");
							}
						}
					}
				}
			}

		});

		ventana.setVisible(true);

		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				cerrarAplicacion();
			}
		});
	}

	public void hayGanador(int jugador) {
		if (comprobar_ganador(jugador)) {
			JOptionPane.showMessageDialog(null, "Ha ganado el jugador " + jugador);
			System.exit(0);
		}
	}
	
	public boolean comprobar_ganador(int turnoActual) {

		if ((matrizJugadas[0][0] == turnoActual) && (matrizJugadas[0][1] == turnoActual)
				&& (matrizJugadas[0][2] == turnoActual)) {
			return true;
		}
		if ((matrizJugadas[1][0] == turnoActual) && (matrizJugadas[1][1] == turnoActual)
				&& (matrizJugadas[1][2] == turnoActual)) {
			return true;
		}
		if ((matrizJugadas[2][0] == turnoActual) && (matrizJugadas[2][1] == turnoActual)
				&& (matrizJugadas[2][2] == turnoActual)) {
			return true;
		}
		if ((matrizJugadas[0][0] == turnoActual) && (matrizJugadas[1][0] == turnoActual)
				&& (matrizJugadas[2][0] == turnoActual)) {
			return true;
		}
		if ((matrizJugadas[0][1] == turnoActual) && (matrizJugadas[1][1] == turnoActual)
				&& (matrizJugadas[2][1] == turnoActual)) {
			return true;
		}
		if ((matrizJugadas[0][2] == turnoActual) && (matrizJugadas[1][2] == turnoActual)
				&& (matrizJugadas[2][2] == turnoActual)) {
			return true;
		}
		if ((matrizJugadas[0][0] == turnoActual) && (matrizJugadas[1][1] == turnoActual)
				&& (matrizJugadas[2][2] == turnoActual)) {
			return true;
		}
		if ((matrizJugadas[0][2] == turnoActual) && (matrizJugadas[1][1] == turnoActual)
				&& (matrizJugadas[2][0] == turnoActual)) {
			return true;
		}
		return false;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);

		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// PintarCuadros
		for (Cuadro cuadro : cuadros) {
			cuadro.paint(g);
		}
	}

	/**
	 * 
	 */
	public void cerrarAplicacion() {
		String[] opciones = { "Aceptar", "Cancelar" };
		int eleccion = JOptionPane.showOptionDialog(ventana, "¿Desea cerrar el juego?", "Cerrando la aplicacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void crearCuadros() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.cuadros.add(new Cuadro(i, j));
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public static TresEnRaya getInstance() {
		if (instance == null) {
			instance = new TresEnRaya();
		}
		return instance;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TresEnRaya.getInstance();

	}

	/**
	 * @return the matrizJugadas
	 */
	public int[][] getMatrizJugadas() {
		return matrizJugadas;
	}

	/**
	 * @param matrizJugadas the matrizJugadas to set
	 */
	public void setMatrizJugadas(int[][] matrizJugadas) {
		this.matrizJugadas = matrizJugadas;
	}

}
