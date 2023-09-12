import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
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
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import interfaces.I01_PokemonNormal;
import interfaces.I02_PokemonFuego;
import interfaces.I03_PokemonAgua;
import interfaces.I04_PokemonPlanta;
import interfaces.I05_PokemonDragon;
import interfaces.I06_PokemonBicho;

public class C06_TipoEquipo extends JPanel implements ActionListener, MouseListener{

	//Variables de TipoEquipo
	private JButton jbPokemonNormal;
	private JButton jbPokemonFuego;
	private JButton jbPokemonAgua;
	private JButton jbPokemonPlanta;
	private JButton jbPokemonDragon;
	private JButton jbPokemonBicho;
	private JButton jbPokemonRandom;
	private JLabel jlFondo3;
	private JTextArea jtTexto;
	private Font sizeFont;
	private File myFont;
	private Font fuente;

	//Variables estaticas
	static Clip seSelect2;
	static Clip seSelect1;

	public static int numEquipos;

	public C06_TipoEquipo(){

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

		//Pokeball Tipo Normal
		jbPokemonNormal = new JButton();
		jbPokemonNormal.setIcon(new ImageIcon("src/resources/balls/pb1_1.png"));
		jbPokemonNormal.setRolloverIcon(new ImageIcon("src/resources/balls/pb2_1.png"));
		jbPokemonNormal.setPressedIcon(new ImageIcon("src/resources/balls/pb3_1.png"));
		jbPokemonNormal.setBounds(212, 112, 84, 140);
		jbPokemonNormal.setBorderPainted(false); 
		jbPokemonNormal.setContentAreaFilled(false);
		add(jbPokemonNormal);
		jbPokemonNormal.addActionListener(this);
		jbPokemonNormal.addMouseListener(this);

		//Pokeball Tipo Fuego
		jbPokemonFuego = new JButton();
		jbPokemonFuego.setIcon(new ImageIcon("src/resources/balls/pb1_2.png"));
		jbPokemonFuego.setRolloverIcon(new ImageIcon("src/resources/balls/pb2_2.png"));
		jbPokemonFuego.setPressedIcon(new ImageIcon("src/resources/balls/pb3_2.png"));
		jbPokemonFuego.setBounds(292, 180, 84, 140);
		jbPokemonFuego.setBorderPainted(false); 
		jbPokemonFuego.setContentAreaFilled(false);
		add(jbPokemonFuego);
		jbPokemonFuego.addActionListener(this);
		jbPokemonFuego.addMouseListener(this);

		//Pokeball Tipo Agua
		jbPokemonAgua = new JButton();
		jbPokemonAgua.setIcon(new ImageIcon("src/resources/balls/pb1_3.png"));
		jbPokemonAgua.setRolloverIcon(new ImageIcon("src/resources/balls/pb2_3.png"));
		jbPokemonAgua.setPressedIcon(new ImageIcon("src/resources/balls/pb3_3.png"));
		jbPokemonAgua.setBounds(404, 236, 84, 140);
		jbPokemonAgua.setBorderPainted(false); 
		jbPokemonAgua.setContentAreaFilled(false);
		add(jbPokemonAgua);
		jbPokemonAgua.addActionListener(this);
		jbPokemonAgua.addMouseListener(this);

		//Pokeball Tipo Planta
		jbPokemonPlanta = new JButton();
		jbPokemonPlanta.setIcon(new ImageIcon("src/resources/balls/pb1_4.png"));
		jbPokemonPlanta.setRolloverIcon(new ImageIcon("src/resources/balls/pb2_4.png"));
		jbPokemonPlanta.setPressedIcon(new ImageIcon("src/resources/balls/pb3_4.png"));
		jbPokemonPlanta.setBounds(524, 224, 84, 140);
		jbPokemonPlanta.setBorderPainted(false); 
		jbPokemonPlanta.setContentAreaFilled(false);
		add(jbPokemonPlanta);
		jbPokemonPlanta.addActionListener(this);
		jbPokemonPlanta.addMouseListener(this);

		//Pokeball Tipo Dragon
		jbPokemonDragon = new JButton();
		jbPokemonDragon.setIcon(new ImageIcon("src/resources/balls/pb1_5.png"));
		jbPokemonDragon.setRolloverIcon(new ImageIcon("src/resources/balls/pb2_5.png"));
		jbPokemonDragon.setPressedIcon(new ImageIcon("src/resources/balls/pb3_5.png"));
		jbPokemonDragon.setBounds(640, 200, 84, 140);
		jbPokemonDragon.setBorderPainted(false); 
		jbPokemonDragon.setContentAreaFilled(false);
		add(jbPokemonDragon);
		jbPokemonDragon.addActionListener(this);
		jbPokemonDragon.addMouseListener(this);

		//Pokeball Tipo Bicho
		jbPokemonBicho = new JButton();
		jbPokemonBicho.setIcon(new ImageIcon("src/resources/balls/pb1_6.png"));
		jbPokemonBicho.setRolloverIcon(new ImageIcon("src/resources/balls/pb2_6.png"));
		jbPokemonBicho.setPressedIcon(new ImageIcon("src/resources/balls/pb3_6.png"));
		jbPokemonBicho.setBounds(716, 124, 84, 140);
		jbPokemonBicho.setBorderPainted(false); 
		jbPokemonBicho.setContentAreaFilled(false);
		add(jbPokemonBicho);
		jbPokemonBicho.addActionListener(this);
		jbPokemonBicho.addMouseListener(this);

		//Pokeball Aleatorio
		jbPokemonRandom = new JButton();
		jbPokemonRandom.setIcon(new ImageIcon("src/resources/balls/pb1_7.png"));
		jbPokemonRandom.setRolloverIcon(new ImageIcon("src/resources/balls/pb2_7.png"));
		jbPokemonRandom.setPressedIcon(new ImageIcon("src/resources/balls/pb3_7.png"));
		jbPokemonRandom.setBounds(412, 12, 84, 140);
		jbPokemonRandom.setBorderPainted(false); 
		jbPokemonRandom.setContentAreaFilled(false);
		add(jbPokemonRandom);
		jbPokemonRandom.addActionListener(this);
		jbPokemonRandom.addMouseListener(this);

		//Texto de elegir pokeballl
		jtTexto = new JTextArea("Elige el tipo de tu equipo:");
		jtTexto.setBounds(65,593,800,124);
		jtTexto.setFont(sizeFont);
		jtTexto.setOpaque(false);
		jtTexto.setEditable(false);
		add(jtTexto);

		//Fondo  de Ventana Equipo
		jlFondo3 = new JLabel();
		jlFondo3.setBounds(0,0,1024,768);
		jlFondo3.setIcon(new ImageIcon("src/resources/fondos/fondo_tipo.png"));
		add(jlFondo3); 
	}

	public void actionPerformed(ActionEvent e) {

		sonidoBotonClick();
		
		//Segun selecciones la pokeboll te enviará a otra ventana
		if (e.getSource().equals(jbPokemonAgua)) {
			C01_Principal.pokemon.add(new I03_PokemonAgua(C05_NombreEquipo.nombres.get(C05_NombreEquipo.numEquipo -1),3));
			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C07C_ResumenAgua());
			evento1.setVisible(true);

		}else if (e.getSource().equals(jbPokemonFuego)) {
			C01_Principal.pokemon.add(new I02_PokemonFuego(C05_NombreEquipo.nombres.get(C05_NombreEquipo.numEquipo -1),2));
			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C07B_ResumenFuego());
			evento1.setVisible(true);

		}else if (e.getSource().equals(jbPokemonNormal)) {
			C01_Principal.pokemon.add(new I01_PokemonNormal(C05_NombreEquipo.nombres.get(C05_NombreEquipo.numEquipo -1),1));
			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C07A_ResumenNormal());
			evento1.setVisible(true);

		}else if (e.getSource().equals(jbPokemonPlanta)) {
			C01_Principal.pokemon.add(new I04_PokemonPlanta(C05_NombreEquipo.nombres.get(C05_NombreEquipo.numEquipo -1),4));
			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C07D_ResumenPlanta());
			evento1.setVisible(true);

		}else if (e.getSource().equals(jbPokemonDragon)) {
			C01_Principal.pokemon.add(new I05_PokemonDragon(C05_NombreEquipo.nombres.get(C05_NombreEquipo.numEquipo -1),5));
			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C07E_ResumenDragon());
			evento1.setVisible(true);

		}else if (e.getSource().equals(jbPokemonBicho)) {
			C01_Principal.pokemon.add(new I06_PokemonBicho(C05_NombreEquipo.nombres.get(C05_NombreEquipo.numEquipo -1),6));
			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.getContentPane().add(new C07F_ResumenBicho());
			evento1.remove(this);
			evento1.setVisible(true);
		
		//Pokebol Random
		}else if (e.getSource().equals(jbPokemonRandom)) {
			int numero = (int)(Math.random()*(6-1+1)+1);

			if(numero == 1) {

				C01_Principal.pokemon.add(new I01_PokemonNormal(C05_NombreEquipo.nombres.get(C05_NombreEquipo.numEquipo -1),1));

				JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
				evento1.remove(this);
				evento1.getContentPane().add(new C07A_ResumenNormal());
				evento1.setVisible(true);

			}else if (numero == 2) {

				C01_Principal.pokemon.add(new I02_PokemonFuego(C05_NombreEquipo.nombres.get(C05_NombreEquipo.numEquipo -1),2));


				JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
				evento1.remove(this);
				evento1.getContentPane().add(new C07C_ResumenAgua());
				evento1.setVisible(true);

			}else if (numero == 3) {

				C01_Principal.pokemon.add(new I03_PokemonAgua(C05_NombreEquipo.nombres.get(C05_NombreEquipo.numEquipo -1),3));

				JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
				evento1.remove(this);
				evento1.getContentPane().add(new C07B_ResumenFuego());
				evento1.setVisible(true);

			}else if (numero == 4) {

				C01_Principal.pokemon.add(new I04_PokemonPlanta(C05_NombreEquipo.nombres.get(C05_NombreEquipo.numEquipo -1),4));

				JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
				evento1.remove(this);
				evento1.getContentPane().add(new C07D_ResumenPlanta());
				evento1.setVisible(true);

			}else if (numero == 5) {

				C01_Principal.pokemon.add(new I05_PokemonDragon(C05_NombreEquipo.nombres.get(C05_NombreEquipo.numEquipo -1),5));

				JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
				evento1.remove(this);
				evento1.getContentPane().add(new C07E_ResumenDragon());
				evento1.setVisible(true);

			}else if (numero == 6) {

				C01_Principal.pokemon.add(new I06_PokemonBicho(C05_NombreEquipo.nombres.get(C05_NombreEquipo.numEquipo -1),6));

				JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
				evento1.remove(this);
				evento1.getContentPane().add(new C07F_ResumenBicho());
				evento1.setVisible(true);
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
	
	//Entrada: al pasar el cursor por encima de una pokeball moestramos texto
	@Override
	public void mouseEntered(MouseEvent e) {
		
		sonidoBotonPasar();

		if(e.getSource().equals(jbPokemonRandom)) {

			jtTexto.setText("Pokémon ALEATORIO"
					+ "\n\nSe selecciona aleatoriamente uno de los 6 tipos posibles.");

		}else if(e.getSource().equals(jbPokemonAgua)) {

			jtTexto.setText("Pokémon Tipo AGUA"
					+ "\n\nPS: 200  |  PP: 50  |  ATK: Tipo FUEGO x2  |  ATK: Tipo PLANTA x1/2");

		}else if (e.getSource().equals(jbPokemonFuego)) {

			jtTexto.setText("Pokémon Tipo FUEGO"
					+ "\n\nPS: 200  |  PP: 50  |  ATK: Tipo PLANTA x2  |  ATK: Tipo AGUA x1/2");

		}else if (e.getSource().equals(jbPokemonPlanta)) {

			jtTexto.setText("Pokémon Tipo PLANTA"
					+ "\n\nPS: 200  |  PP: 50  |  ATK: Tipo AGUA x2  |  ATK: Tipo FUEGO x1/2");

		}else if (e.getSource().equals(jbPokemonNormal)) {

			jtTexto.setText("Pokémon Tipo NORMAL"
					+ "\n\nPS: 200  |  PP: 50 ");

		}else if (e.getSource().equals(jbPokemonDragon)) {

			jtTexto.setText("Pokémon Tipo DRAGÓN"
					+ "\n\nPS: 400  |  PP: 10  |  +10 PP por cada ronda");

		}else if (e.getSource().equals(jbPokemonBicho)) {

			jtTexto.setText("Pokémon Tipo BICHO"
					+ "\n\nPS: 100  |  PP: 50  |  50% de probabilidad de esquivar cada PP");
		}
	}

	//Salida: al dejar de pasar por encima de la pokeball vuelve al texto original
	@Override
	public void mouseExited(MouseEvent e) {

		e.getSource().equals(jbPokemonRandom);
		jtTexto.setText("Elige el tipo de tu equipo");
	}
	
	//Sonido boton al pasar por encima
	public void sonidoBotonPasar() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/SE/pasar.wav").getAbsoluteFile());
			seSelect2 = AudioSystem.getClip();
			seSelect2.open(audioInputStream);
			seSelect2.loop(0);
		} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error al reproducir el sonido.");
		}
	}
	
	//Sonido boton al clicar
	public void sonidoBotonClick() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/SE/abrir.wav").getAbsoluteFile());
			seSelect1 = AudioSystem.getClip();
			seSelect1.open(audioInputStream);
			seSelect1.loop(0);
		} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error al reproducir el sonido.");
		}
	}
}
