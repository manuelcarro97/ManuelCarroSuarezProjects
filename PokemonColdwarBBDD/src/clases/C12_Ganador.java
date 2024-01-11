package clases;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

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
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import base_datos.PokemonDAO;
public class C12_Ganador extends JPanel implements ActionListener {


	private JTextArea jtGanador;
	private JButton jbOk;
	private JLabel jlFondo;
	private JLabel jlTexto;
	private Font sizeFont;
	private File myFont;
	private Font fuente;

	static Clip seSelect;


	public C12_Ganador() {

		setLayout(null);

		//Fuente de letra
		String fontName = "src/resources/Fonts/Font3.ttf" ;
		myFont = new File(fontName);

		try{

			fuente = Font.createFont(Font.TRUETYPE_FONT,myFont);
			sizeFont = fuente.deriveFont(26f);

		}catch(FontFormatException ex){

			System.err.println("Error estableciendo fuente tipográfica");

		}catch (IOException ex) {

			System.err.println("Error I/O");
		}

		//Cursor 
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("src/resources/cursor.png");
		Cursor c = toolkit.createCustomCursor(image , new Point(this.getX(),this.getY()), "img");
		this.setCursor (c);

		//		textos 

		jtGanador = new JTextArea();
		jtGanador.setBounds(10, 10, 120, 120);
		jtGanador.setOpaque(false);
		jtGanador.setBorder(null);
		add(jtGanador);
		//boton

		jbOk = new JButton();
		jbOk.setBounds(890, 646, 104, 92);
		jbOk.setBorderPainted(false); 
		jbOk.setContentAreaFilled(false);
		jbOk.setIcon(new ImageIcon("src/resources/botones/flecha_g.png"));
		jbOk.setPressedIcon(new ImageIcon("src/resources/botones/flecha_g2.png"));
		add(jbOk);
		jbOk.addActionListener(this);

		//		texto en funcoin de ganador o empate

		if (C01_Principal.pokemon.size()==1) {

			jlTexto = new JLabel("¡ El ganador es ....");

		}
		else {

			jlTexto = new JLabel("EMPATE , no ha habido ningun ganador");

		}
		
		PokemonDAO.ganadores(C01_Principal.pokemon);


		jlTexto.setBounds(230,660,700,60);
		jlTexto.setFont(sizeFont);
		jlTexto.setOpaque(false);
		add(jlTexto);

		jlFondo = new JLabel();
		jlFondo.setBounds(0,0,1024,768);
		jlFondo.setIcon(new ImageIcon("src/resources/fondos/fondo_empate.png"));
		add(jlFondo);
	}





	public void actionPerformed(ActionEvent e) {

		sonidoBoton();

		if (C01_Principal.pokemon.size()==1) {

			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C12_Ganador2());
			evento1.setVisible(true);


		}
		else if (C01_Principal.pokemon.size()<1) {

			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C13_Final());
			evento1.setVisible(true);

		}


	}

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





}
