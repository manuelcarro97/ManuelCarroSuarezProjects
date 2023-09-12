import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class C07B_ResumenFuego extends JPanel implements ActionListener{

	//Variables de ResumenFuego
	private JButton jbStart;
	private JButton jbAdd;
	private JLabel jlFondoFuego;
	private Font sizeFont;
	private Font sizeFont2;
	private File myFont;
	private Font fuente;
	private JLabel jlTipo;
	private JLabel jlEntrenador;
	private JTextArea jlDescripcion;
	private JButton jbAddApagado;
	private JButton jbStartApagado;
	private JLabel jlCharmander;

	//Variables estaticas
	static Clip seSelect;


	public C07B_ResumenFuego() {

		setLayout(null);

		//Fuente de letra
		String fontName = "src/resources/Fonts/Font3.ttf" ;
		myFont = new File(fontName);

		try{

			fuente = Font.createFont(Font.TRUETYPE_FONT,myFont);
			sizeFont = fuente.deriveFont(30f);
			sizeFont2 = fuente.deriveFont(24f);

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

		//Botón empezar partida si hay mas de 3 equipos
		if (C01_Principal.pokemon.size()<3) {

			jbStartApagado = new JButton();
			jbStartApagado.setIcon(new ImageIcon("src/resources/botones/start0.png"));
			jbStartApagado.setBounds(8, 608, 448, 88);
			jbStartApagado.setBorderPainted(false); 
			jbStartApagado.setContentAreaFilled(false);
			add(jbStartApagado);
			jbStartApagado.addActionListener(this);
		}
		//Botón empezar partida inhabilitado
		else {

			jbStart = new JButton();
			jbStart.setIcon(new ImageIcon("src/resources/botones/start.png"));
			jbStart.setPressedIcon(new ImageIcon("src/resources/botones/start2.png"));
			jbStart.setBounds(8, 608, 448, 88);
			jbStart.setBorderPainted(false); 
			jbStart.setContentAreaFilled(false);
			add(jbStart);
			jbStart.addActionListener(this);

		}




		//Botón añadir equipo si son menos de 8 equipos
		if (C01_Principal.pokemon.size()<8) {

			jbAdd = new JButton();
			jbAdd.setIcon(new ImageIcon("src/resources/botones/add.png"));
			jbAdd.setPressedIcon(new ImageIcon("src/resources/botones/add2.png"));
			jbAdd.setBounds(8, 512, 448, 88);
			jbAdd.setBorderPainted(false); 
			jbAdd.setContentAreaFilled(false);
			add(jbAdd);
			jbAdd.addActionListener(this);
		}
		//Botón añadir equipo inhabilitado
		else {

			jbAddApagado = new JButton();
			jbAddApagado.setIcon(new ImageIcon("src/resources/botones/add0.png"));

			jbAddApagado.setBounds(8, 512, 448, 88);
			jbAddApagado.setBorderPainted(false); 
			jbAddApagado.setContentAreaFilled(false);
			add(jbAddApagado);
			jbAddApagado.addActionListener(this);

		}

		//Color de letra
		Color colorLetra=new Color(202, 60, 1);

		//		animacion y grito del pokemon
		jlCharmander = new JLabel();
		jlCharmander.setBounds(130, 256, 148, 188);
		jlCharmander.setIcon(new ImageIcon("src/resources/pokegifs/charmander.gif"));			
		add(jlCharmander);
		grito();

		//		resetear el gif para que vuelva a reproducirse desde el principio
		ImageIcon icon = (ImageIcon) jlCharmander.getIcon();
		Image img = icon.getImage();
		ImageIcon newIcon = new ImageIcon(img);
		img.flush();
		jlCharmander.setIcon(newIcon);

		//Texto tipo Pokemon
		jlTipo = new JLabel("Tipo FUEGO");
		jlTipo.setBounds(170,75,700,60);
		jlTipo.setForeground(colorLetra);
		jlTipo.setFont(sizeFont);
		jlTipo.setOpaque(false);
		add(jlTipo);

		//Texto tipo Nombre entrenador
		jlEntrenador = new JLabel(C05_NombreEquipo.nombres.get(C05_NombreEquipo.numEquipo - 1));
		jlEntrenador.setBounds(630,75,700,60);
		jlEntrenador.setFont(sizeFont);
		jlEntrenador.setOpaque(false);
		add(jlEntrenador);

		//Texto descripción tipo
		jlDescripcion = new JTextArea("\t\tDATOS"
				+ "\n"
				+ "\nPS: 200"
				+ "\nPP: 50\n"
				+ "\nVENTAJA:"
				+ "\nHace el doble de daño al tipo PLANTA.\n"
				+ "\nDESVENTAJA:"
				+ "\nRecibe el doble de daño del tipo AGUA.\n"
				+ "\nDESCRIPCIÓN:"
				+ "\nLa llama de su cola indica su fuerza vital."
				+ "\nSi está débil, la llama arderá más tenue.");
		jlDescripcion.setBounds(520, 180, 450, 550);
		jlDescripcion.setFont(sizeFont2);
		jlDescripcion.setEditable(false);
		jlDescripcion.setOpaque(false);
		add(jlDescripcion);

		//Imagen Fondo
		jlFondoFuego = new JLabel();
		jlFondoFuego.setBounds(0,0,1024,768);
		jlFondoFuego.setIcon(new ImageIcon("src/resources/fondos/FondosPokedex/tipofuego.png"));
		add(jlFondoFuego);


	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//Boton AñadirEquipo: vuelve a NombreEquipo
		if (e.getSource().equals(jbAdd)) {
			sonidoBoton();
			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C05_NombreEquipo());
			evento1.setVisible(true);

		}

		//Boton Start: pasa a ListaEquipos
		if (e.getSource().equals(jbStart)) {
			sonidoBoton();
			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C08_ListaEquipos());
			evento1.setVisible(true);
			;
		}

		//Sonido del boton cuando está deshabilitado
		if (e.getSource().equals(jbAddApagado)){

			sonidoBotonError();
		}
		if(e.getSource().equals(jbStartApagado)) {

			sonidoBotonError();
		}

	}

	//Funcion sonido de boton
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

	//Funcion sonido de boton de error
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

	//Funcion grito pokemon
	public void grito() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/SE/charmander.wav").getAbsoluteFile());
			seSelect = AudioSystem.getClip();
			seSelect.open(audioInputStream);
			seSelect.loop(0);
		} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error al reproducir el sonido.");
		}
	}


}
