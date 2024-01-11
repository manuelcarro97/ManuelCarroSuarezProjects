package clases;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import base_datos.PokemonDAO;

public class C04D_ElegirPartida extends JPanel implements ActionListener, MouseListener{

	//Variables estaticas
	static Clip seSelect;

	//Variables de MenuPrincipal
	private JButton jbNuevaPartida;
	private JButton jbCargarPartida;
	private JLabel jlFondo;
	private JButton jbBack;

	static boolean partidaguardada;

	public C04D_ElegirPartida() {

		partidaguardada = base_datos.PokemonDAO.comprobarBBDD();

		setLayout(null);

		//Cambio de cursor
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("src/resources/cursor.png");
		Cursor c = toolkit.createCustomCursor(image , new Point(this.getX(),this.getY()), "img");
		this.setCursor (c);

		//Botón de nueva partida 
		jbNuevaPartida = new JButton();
		jbNuevaPartida.setIcon(new ImageIcon("src/resources/botones/nueva.png"));
		jbNuevaPartida.setPressedIcon(new ImageIcon("src/resources/botones/nueva2.png"));
		jbNuevaPartida.setBounds(240, 285, 544, 92);
		jbNuevaPartida.setBorderPainted(false); 
		jbNuevaPartida.setContentAreaFilled(false);
		add(jbNuevaPartida);
		jbNuevaPartida.addActionListener(this);
		jbNuevaPartida.addMouseListener(this);	

		//Botón de cargar partida
		jbCargarPartida = new JButton();

		if (partidaguardada) {

			jbCargarPartida.setIcon(new ImageIcon("src/resources/botones/guardada.png"));
			jbCargarPartida.setPressedIcon(new ImageIcon("src/resources/botones/guardada2.png"));
		}
		else {

			jbCargarPartida.setIcon(new ImageIcon("src/resources/botones/guardada0.png"));
		}

		jbCargarPartida.setBounds(240, 425, 544, 92);
		jbCargarPartida.setBorderPainted(false); 
		jbCargarPartida.setContentAreaFilled(false);
		add(jbCargarPartida);
		jbCargarPartida.addActionListener(this);
		jbCargarPartida.addMouseListener(this);	


		//Boton de flecha
		jbBack = new JButton();
		jbBack.setIcon(new ImageIcon("src/resources/botones/flecha.png"));
		jbBack.setPressedIcon(new ImageIcon("src/resources/botones/flecha2.png"));
		jbBack.setBounds(890, 646, 104, 92);
		jbBack.setBorderPainted(false); 
		jbBack.setContentAreaFilled(false);
		add(jbBack);
		jbBack.addActionListener(this);

		//Fondo Menu
		jlFondo = new JLabel();
		jlFondo.setBounds(0,0,1024,768);
		jlFondo.setIcon(new ImageIcon("src/resources/fondos/fondo_menu.gif"));
		add(jlFondo); 

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//Función de botón "NuevaPartida"
		if (e.getSource().equals(jbNuevaPartida)) {
			sonidoBoton();

			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C05_NombreEquipo());
			evento1.setVisible(true);
		}

		//Función de botón "CargarPartida"
		else if (e.getSource().equals(jbCargarPartida) && partidaguardada) {
			sonidoBoton();

			C04_MenuPrincipal.bgmMenu.close();
			C01_Principal.pokemon = PokemonDAO.cargarPartida(C01_Principal.pokemon);
			C09_Combate.ronda = PokemonDAO.borrarPartida();
			C08_ListaEquipos.contRonda = C09_Combate.ronda + 2;

			if (C01_Principal.pokemon.size()<2) {

				JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
				evento1.remove(this);
				evento1.getContentPane().add(new C12_Ganador());
				evento1.setVisible(true);
			}
			else {

				JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
				evento1.remove(this);
				evento1.getContentPane().add(new C09_Combate());
				evento1.setVisible(true);
			}




			//Función de botón "Back"	
		}
		else if (e.getSource().equals(jbCargarPartida) && !partidaguardada) {

			sonidoBotonError();
		}

		else if (e.getSource().equals(jbBack)) {
			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C04_MenuPrincipal());
			evento1.setVisible(true);
			C04A_Reglas.bgmReglas.close();

		}
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	//Función de sonido de botones
	public void sonidoBoton() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/SE/select.wav").getAbsoluteFile());
			seSelect = AudioSystem.getClip();
			seSelect.open(audioInputStream);
			seSelect.loop(0);
		} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error al reproducir el sonido.");
		}
	}
	public void sonidoBotonError() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/SE/buzzer.wav").getAbsoluteFile());
			seSelect = AudioSystem.getClip();
			seSelect.open(audioInputStream);
			seSelect.loop(0);
		} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error al reproducir el sonido.");
		}
	}


}
