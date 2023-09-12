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

public class C09_Combate extends JPanel implements ActionListener {

	private JLabel jlFondo;
	private Font sizeFont;
	private File myFont;
	private Font fuente;
	private JTextArea jtEquipo;
	private JButton jbAtacar; 
	private JButton jbCurar; 

	static Clip bgmCombate;
	static Clip seSelect;

	static boolean accion;
	static int turno = 0;
	static String resumen="" ;
	static int contRonda = 2;
	static int ronda = 0;


	private JLabel jlPregunta;
	public C09_Combate(){

		C05_NombreEquipo.bgmNombre.close();

		if(bgmCombate == null || !bgmCombate.isActive() ) {

			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/BGM/combate.wav").getAbsoluteFile());
				bgmCombate = AudioSystem.getClip();
				bgmCombate.open(audioInputStream);
				bgmCombate.loop(9999);


			} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
				System.out.println("Error al reproducir el sonido.");
			}
		}

		setLayout(null);

		//Fuente de letra
		String fontName = "src/resources/Fonts/Font3.ttf" ;
		myFont = new File(fontName);

		try{

			fuente = Font.createFont(Font.TRUETYPE_FONT,myFont);
			sizeFont = fuente.deriveFont(30f);

		}catch(FontFormatException ex){

			System.err.println("Error estableciendo fuente tipográfica");

		}catch (IOException ex) {

			System.err.println("Error I/O");
		}

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("src/resources/cursor.png");
		Cursor c = toolkit.createCustomCursor(image , new Point(this.getX(),this.getY()), "img");
		this.setCursor (c);



		//Texto tipo Nombre entrenador
		jlPregunta = new JLabel("¿Que quieres hacer?");
		jlPregunta.setBounds(50,640,700,60);
		jlPregunta.setFont(sizeFont);
		jlPregunta.setOpaque(false);
		add(jlPregunta);

		//Texto datos
		jtEquipo = new JTextArea(C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo());
		jtEquipo.setBounds(45, 85, 500, 400);
		jtEquipo.setFont(sizeFont);
		jtEquipo.setEditable(false);
		jtEquipo.setOpaque(false);
		add(jtEquipo);	

		//Botón atacar

		jbAtacar = new JButton();
		jbAtacar.setIcon(new ImageIcon("src/resources/botones/atacar.png"));
		jbAtacar.setPressedIcon(new ImageIcon("src/resources/botones/atacar2.png"));
		jbAtacar.setBounds(506, 638, 250, 76);
		jbAtacar.setBorderPainted(false); 
		jbAtacar.setContentAreaFilled(false);
		add(jbAtacar);
		jbAtacar.addActionListener(this);

		//Botón curar

		jbCurar = new JButton();
		jbCurar.setIcon(new ImageIcon("src/resources/botones/curar.png"));
		jbCurar.setPressedIcon(new ImageIcon("src/resources/botones/curar2.png"));
		jbCurar.setBounds(764, 638, 250, 76);
		jbCurar.setBorderPainted(false); 
		jbCurar.setContentAreaFilled(false);
		add(jbCurar);
		jbCurar.addActionListener(this);



		//Mostraremos la ventana combate dependiendo del pokemon seleccionado
		jlFondo = new JLabel();
		jlFondo.setBounds(0,0,1024,768);

		if (C01_Principal.pokemon.get(turno).getTipoPlaneta() == 1) {

			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/normal/combate_normal.png"));

		}else if(C01_Principal.pokemon.get(turno).getTipoPlaneta() == 2) {

			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/fuego/combate_fuego.png"));

		}else if(C01_Principal.pokemon.get(turno).getTipoPlaneta() == 3) {

			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/agua/combate_agua.png"));

		}else if(C01_Principal.pokemon.get(turno).getTipoPlaneta() == 4) {

			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/planta/combate_planta.png"));

		}else if(C01_Principal.pokemon.get(turno).getTipoPlaneta() == 5) {

			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/dragon/combate_dragon.png"));

		}else if(C01_Principal.pokemon.get(turno).getTipoPlaneta() == 6) {

			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/bicho/combate_bicho.png"));

		}

		add(jlFondo);

	}

	@Override
	public void actionPerformed(ActionEvent e) {


		//		cambia a una  ventana u otra  en funcion del boton pulsado

		sonidoBoton();


		if (e.getSource().equals(jbAtacar)) {
			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C10A_CombateAtacar());
			evento1.setVisible(true);
			accion = true;

		}

		if (e.getSource().equals(jbCurar)) {
			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C10B_CombateCurar());
			evento1.setVisible(true);
			accion = false;
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
