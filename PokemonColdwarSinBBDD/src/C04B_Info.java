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

public class C04B_Info extends JPanel implements ActionListener {
	
	//Variables estaticas
	static Clip bgmInfo;
	
	//Variables de Información
	private JButton jbBack;
	private JLabel jlFondo;
	private JTextArea jtFuente;
	private Font sizeFont;
	private File myFont;
	private Font fuente;
	static Clip seSelect;

	public C04B_Info() {
		
		//Cerramos MenuPrincipal
		C04_MenuPrincipal.bgmMenu.close();
		
		//Musica
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/BGM/info.wav").getAbsoluteFile());
			bgmInfo = AudioSystem.getClip();
			bgmInfo.open(audioInputStream);
			bgmInfo.loop(9999);

		} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error al reproducir el sonido.");
		}

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

		//Botón back
		jbBack = new JButton();
		jbBack.setIcon(new ImageIcon("src/resources/botones/flecha.png"));
		jbBack.setPressedIcon(new ImageIcon("src/resources/botones/flecha2.png"));
		jbBack.setBounds(890, 646, 104, 92);
		jbBack.setBorderPainted(false); 
		jbBack.setContentAreaFilled(false);
		add(jbBack);
		jbBack.addActionListener(this);

		//Texto de infromación
		jtFuente = new JTextArea("\t    INFORMACIÓN"
				+ "\n"
				+ "\nVersión:\n"
				+ "· 1.2.0 "
				+ "\n"
				+ "\nAutores:\n"
				+ "· Manuel Carro\n"
				+ "· Simón Souto\n"
				+ "· Carlos Iglesias\n"
				+ "\n"
				+ "Contacto:\n"
				+ "· Manuel: manuelcarro1997@gmail.com\n"
				+ "· Simón: simonsoutodopazo@gmail.com\n"
				+ "· Carlos: cacoiglesias11@gmail.com\n");
		jtFuente.setBounds(119, 119, 411, 530);
		jtFuente.setFont(sizeFont);
		jtFuente.setEditable(false);
		jtFuente.setOpaque(false);
		add(jtFuente);	

		//Fondo del menu información
		jlFondo = new JLabel();
		jlFondo.setBounds(0,0,1024,768);
		jlFondo.setIcon(new ImageIcon("src/resources/fondos/fondo_info.png"));
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
			bgmInfo.close();
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
