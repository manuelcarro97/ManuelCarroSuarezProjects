package clases;
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

public class C08_ListaEquipos extends JPanel implements ActionListener {

	static Clip bgmNombre;
	static Clip seSelect;

	//Variables de ListaEquipos
	private Font sizeFont;
	private File myFont;
	private Font fuente;
	private JLabel jlFondo;
	private JButton jbOk;
	private JLabel jlTexto;
	private JLabel jlPokeIcon;
	private JTextArea jtTeam;
	private JLabel jlVida;
	private JTextArea jlPS;
	static int contRonda = 2;




	public C08_ListaEquipos(){


		setLayout(null);

		//Fuente de letra
		String fontName = "src/resources/Fonts/Font3.ttf" ;
		myFont = new File(fontName);

		try{

			fuente = Font.createFont(Font.TRUETYPE_FONT,myFont);
			sizeFont = fuente.deriveFont(24f);

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

		//		boton

		jbOk = new JButton();
		jbOk.setBounds(890, 646, 104, 92);
		jbOk.setBorderPainted(false); 
		jbOk.setContentAreaFilled(false);
		jbOk.setIcon(new ImageIcon("src/resources/botones/ok.png"));
		jbOk.setPressedIcon(new ImageIcon("src/resources/botones/ok2.png"));
		add(jbOk);
		jbOk.addActionListener(this);

		//		texto

		jlTexto = new JLabel("¡Estos son los equipos participantes!");
		jlTexto.setBounds(57,55,700,60);
		jlTexto.setFont(sizeFont);
		jlTexto.setOpaque(false);
		add(jlTexto);

		//EQUIPOS
		if (C01_Principal.pokemon.size() == 3) {

			equipo(122,115,0);
			equipo(197,190,1);
			equipo(272,265,2);

			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,1024,768);
			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosLista/fondo_lista3.png"));
			add(jlFondo);

		}else if (C01_Principal.pokemon.size() == 4) {

			equipo(122,115,0);
			equipo(197,190,1);
			equipo(272,265,2);
			equipo(347,340,3);

			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,1024,768);
			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosLista/fondo_lista4.png"));
			add(jlFondo);

		}else if (C01_Principal.pokemon.size() == 5) {

			equipo(122,115,0);
			equipo(197,190,1);
			equipo(272,265,2);
			equipo(347,340,3);
			equipo(422,415,4);

			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,1024,768);
			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosLista/fondo_lista5.png"));
			add(jlFondo);

		}else if (C01_Principal.pokemon.size() == 6) {

			equipo(122,115,0);
			equipo(197,190,1);
			equipo(272,265,2);
			equipo(347,340,3);
			equipo(422,415,4);
			equipo(497,490,5);

			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,1024,768);
			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosLista/fondo_lista6.png"));
			add(jlFondo);

		}else if (C01_Principal.pokemon.size() == 7) {

			equipo(122,115,0);
			equipo(197,190,1);
			equipo(272,265,2);
			equipo(347,340,3);
			equipo(422,415,4);
			equipo(497,490,5);
			equipo(572,565,6);

			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,1024,768);
			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosLista/fondo_lista7.png"));
			add(jlFondo);

		}else if (C01_Principal.pokemon.size() == 8) {

			equipo(122,115,0);
			equipo(197,190,1);
			equipo(272,265,2);
			equipo(347,340,3);
			equipo(422,415,4);
			equipo(497,490,5);
			equipo(572,565,6);
			equipo(647,640,7);

			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,1024,768);
			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosLista/fondo_lista8.png"));
			add(jlFondo);

		}

		//Fondo lista pokemons
		jlFondo = new JLabel();
		jlFondo.setBounds(0,0,1024,768);
		jlFondo.setIcon(new ImageIcon("src/resources/fondos/fondo_lista.png"));
		add(jlFondo);

	}

	@Override
	public void actionPerformed(ActionEvent e) {


		//	boton que cambia de ventana	

		if (e.getSource().equals(jbOk)) {

			sonidoBoton();

			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C09_Combate());
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

	//	funcion para el posicionamiento de los iconos  textos

	public void equipo(int x, int y, int z) {

		//		EQUIPO
		if (C01_Principal.pokemon.get(z).getTipoPlaneta()==1) {

			jlPokeIcon = new JLabel();
			jlPokeIcon.setBounds(57,x,60,60);
			jlPokeIcon.setIcon(new ImageIcon("src/resources/pokeicons/normal.png"));
			add(jlPokeIcon);

			jtTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "\nTipo Normal");
			jtTeam.setBounds(135,y,700,75);
			jtTeam.setFont(sizeFont);
			jtTeam.setEditable(false);
			jtTeam.setOpaque(false);
			add(jtTeam);

		}else if (C01_Principal.pokemon.get(z).getTipoPlaneta()==2) {

			jlPokeIcon = new JLabel();
			jlPokeIcon.setBounds(57,x,60,60);
			jlPokeIcon.setIcon(new ImageIcon("src/resources/pokeicons/fuego.png"));
			add(jlPokeIcon);

			jtTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "\nTipo Fuego");
			jtTeam.setBounds(135,y,700,75);
			jtTeam.setFont(sizeFont);
			jtTeam.setEditable(false);
			jtTeam.setOpaque(false);
			add(jtTeam);

		}else if (C01_Principal.pokemon.get(z).getTipoPlaneta()==3) {

			jlPokeIcon = new JLabel();
			jlPokeIcon.setBounds(57,x,60,60);
			jlPokeIcon.setIcon(new ImageIcon("src/resources/pokeicons/agua.png"));
			add(jlPokeIcon);

			jtTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "\nTipo Agua");
			jtTeam.setBounds(135,y,700,75);
			jtTeam.setFont(sizeFont);
			jtTeam.setEditable(false);
			jtTeam.setOpaque(false);
			add(jtTeam);

		}else if (C01_Principal.pokemon.get(z).getTipoPlaneta()==4) {

			jlPokeIcon = new JLabel();
			jlPokeIcon.setBounds(57,x,60,60);
			jlPokeIcon.setIcon(new ImageIcon("src/resources/pokeicons/planta.png"));
			add(jlPokeIcon);

			jtTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "\nTipo Planta");
			jtTeam.setBounds(135,y,700,75);
			jtTeam.setFont(sizeFont);
			jtTeam.setEditable(false);
			jtTeam.setOpaque(false);
			add(jtTeam);

		}else if (C01_Principal.pokemon.get(z).getTipoPlaneta()==5) {

			jlPokeIcon = new JLabel();
			jlPokeIcon.setBounds(57,x,60,60);
			jlPokeIcon.setIcon(new ImageIcon("src/resources/pokeicons/dragon.png"));
			add(jlPokeIcon);

			jtTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "\nTipo Dragón");
			jtTeam.setBounds(135,y,700,75);
			jtTeam.setFont(sizeFont);
			jtTeam.setEditable(false);
			jtTeam.setOpaque(false);
			add(jtTeam);

		}else if (C01_Principal.pokemon.get(z).getTipoPlaneta()==6) {

			jlPokeIcon = new JLabel();
			jlPokeIcon.setBounds(57,x,60,60);
			jlPokeIcon.setIcon(new ImageIcon("src/resources/pokeicons/bicho.png"));
			add(jlPokeIcon);

			jtTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "\nTipo Bicho");
			jtTeam.setBounds(135,y,700,75);
			jtTeam.setFont(sizeFont);
			jtTeam.setEditable(false);
			jtTeam.setOpaque(false);
			add(jtTeam);
		}

	}

}