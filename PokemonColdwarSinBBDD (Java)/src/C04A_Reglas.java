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

public class C04A_Reglas extends JPanel implements ActionListener{

	//Variables de Reglas
	private JButton jbBack;
	private JLabel jlFondo;
	private JTextArea jtFuente;
	private Font sizeFont;
	private File myFont;
	private Font fuente;
	
	//Variables estaticas
	static Clip bgmReglas;
	static Clip seSelect;

	public C04A_Reglas () {
		
		//Cerramos menu principal
		C04_MenuPrincipal.bgmMenu.close();

		setLayout(null);
		
		//Musica
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/BGM/reglas.wav").getAbsoluteFile());
			bgmReglas = AudioSystem.getClip();
			bgmReglas.open(audioInputStream);
			bgmReglas.loop(9999);

		} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error al reproducir el sonido.");
		}

		//Fuente de letra
		String fontName = "src/resources/Fonts/Font3.ttf" ;
		myFont = new File(fontName);

		try{

			fuente = Font.createFont(Font.TRUETYPE_FONT,myFont);
			sizeFont = fuente.deriveFont(22f);

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

		//Boton de flecha
		jbBack = new JButton();
		jbBack.setIcon(new ImageIcon("src/resources/botones/flecha.png"));
		jbBack.setPressedIcon(new ImageIcon("src/resources/botones/flecha2.png"));
		jbBack.setBounds(890, 646, 104, 92);
		jbBack.setBorderPainted(false); 
		jbBack.setContentAreaFilled(false);
		add(jbBack);
		jbBack.addActionListener(this);

		//Texto de infromación
		jtFuente = new JTextArea("\t\tREGLAS DEL JUEGO"
				+ "\n"
				+ "\n 1 - Debe haber entre 3 y 10 equipos."
				+ "\n"
				+ "\n 2 - Los PP y PS  de cada equipo dependerán del"
				+ "\n      tipo elegido por el entrenador"
				+ "\n"
				+ "\n 3 - Los equipos deben utilizar todos sus PP en"
				+ "\n      cada ronda."
				+ "\n"
				+ "\n 4 - Los PP se pueden utilizar para:"
				+ "  \n      · Atacar a uno o varios equipos."
				+ "	 \n      · Defenderse a uno mismo."
				+ "  \n      · Defender a otro equipo."
				+ "\n"
				+ "\n 5 - En la autodefensa y la defensa a otro equipo"
				+ "\n      se sumarán como PS la mitad de los PP"
				+ "\n      utilizados."
				+ "\n"
				+ "\n 6 - Cuando un equipo se queda sin PS, es eliminado."
				+ "\n"
				+ "\n 7 - La partida finaliza cuando sólo quede un"
				+ "\n      equipo con PS."
				+ "\n");
		jtFuente.setBounds(52, 55, 500, 658);
		jtFuente.setFont(sizeFont);
		jtFuente.setEditable(false);
		jtFuente.setOpaque(false);
		add(jtFuente);	

		//Fondo del menu reglas		
		jlFondo = new JLabel();
		jlFondo.setBounds(0,0,1024,768);
		jlFondo.setIcon(new ImageIcon("src/resources/fondos/fondo_reglas.png"));
		add(jlFondo); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Boton para volver al MenuPrincipal
		sonidoBoton();

		if (e.getSource().equals(jbBack)) {
			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C04_MenuPrincipal());
			evento1.setVisible(true);
			C04A_Reglas.bgmReglas.close();

		}
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
}
