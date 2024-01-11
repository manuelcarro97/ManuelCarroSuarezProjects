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

public class C07F_ResumenBicho extends JPanel implements ActionListener {

	//Variables de ResumenBicho
	private JButton jbStart;
	private JButton jbAdd;
	private JLabel jlFondoBicho;
	private Font sizeFont;
	private Font sizeFont2;
	private File myFont;
	private Font fuente;
	private JLabel jlTipo;
	private JLabel jlEntrenador;
	private JTextArea jlDescripcion;
	private JButton jbStartApagado;
	private JButton jbAddApagado;
	private JLabel jlCaterpie;

	//Variables estaticas
	static Clip seSelect;


	public C07F_ResumenBicho() {

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
		Color colorLetra=new Color(28, 77, 4);

		//		animacion y grito del pokemon
		jlCaterpie = new JLabel();
		jlCaterpie.setBounds(135, 312, 192, 140);
		jlCaterpie.setIcon(new ImageIcon("src/resources/pokegifs/caterpie.gif"));			
		add(jlCaterpie);
		grito();

		//		resetear el gif para que vuelva a reproducirse desde el principio
		ImageIcon icon = (ImageIcon) jlCaterpie.getIcon();
		Image img = icon.getImage();
		ImageIcon newIcon = new ImageIcon(img);
		img.flush();
		jlCaterpie.setIcon(newIcon);

		//Texto tipo Pokemon
		jlTipo = new JLabel("\nTipo BICHO");
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
				+ "\nPS: 100"
				+ "\nPP: 50\n"
				+ "\nVENTAJA: "
				+ "\nTiene un 50% de probabilidad de esquivar"
				+ "\ncada PP de su rival.\n"
				+ "\nDESVENTAJA:"
				+ "\nTiene menos PS que el resto de tipos.\n"
				+ "\nDESCRIPCIÓN:"
				+ "\nPara protegerse, despide un hedor"
				+ "\nhorrible por las antenas con el"
				+ "\nque repele a sus enemigos.");
		jlDescripcion.setBounds(520, 180, 450, 550);
		jlDescripcion.setFont(sizeFont2);
		jlDescripcion.setEditable(false);
		jlDescripcion.setOpaque(false);
		add(jlDescripcion);	

		//Fondo de Ventana Equipo
		jlFondoBicho = new JLabel();
		jlFondoBicho.setBounds(0,0,1024,768);
		jlFondoBicho.setIcon(new ImageIcon("src/resources/fondos/FondosPokedex/tipobicho.png"));
		add(jlFondoBicho);


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
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/SE/caterpie.wav").getAbsoluteFile());
			seSelect = AudioSystem.getClip();
			seSelect.open(audioInputStream);
			seSelect.loop(0);
		} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error al reproducir el sonido.");
		}
	}


}
