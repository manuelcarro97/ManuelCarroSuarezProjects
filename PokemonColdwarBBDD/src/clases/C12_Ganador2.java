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

public class C12_Ganador2 extends JPanel implements ActionListener {


	private JTextArea jtGanador;
	private JButton jbOk;
	private JLabel jlFondo;
	private JLabel jlTexto;
	private Font sizeFont;
	private File myFont;
	private Font fuente;
	private JLabel imglabel;
	private JLabel jlfundido;

	static Clip seSelect;
	static Clip bgmGanador;


	public C12_Ganador2() {

		setLayout(null);



		if(bgmGanador == null || !bgmGanador.isActive() ) {

			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/BGM/ganador.wav").getAbsoluteFile());
				bgmGanador = AudioSystem.getClip();
				bgmGanador.open(audioInputStream);
				bgmGanador.loop(9999);


			} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
				System.out.println("Error al reproducir el sonido.");
			}
		}

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

		jbOk = new JButton();
		jbOk.setBounds(890, 646, 104, 92);
		jbOk.setBorderPainted(false); 
		jbOk.setContentAreaFilled(false);
		jbOk.setIcon(new ImageIcon("src/resources/botones/ok.png"));
		jbOk.setPressedIcon(new ImageIcon("src/resources/botones/ok2.png"));
		add(jbOk);
		jbOk.addActionListener(this);

		jlTexto = new JLabel("¡ "+C01_Principal.pokemon.get(0).getNombreEquipo()+	" !");
		jlTexto.setBounds(230,660,700,60);
		jlTexto.setFont(sizeFont);
		jlTexto.setOpaque(false);
		add(jlTexto);

		//		gif para presentar el ganador
		jlFondo = new JLabel();
		jlFondo.setBounds(0,0,1024,768);
		jlFondo.setIcon(new ImageIcon("src/resources/fondos/ganador.gif"));
		add(jlFondo);

		//		resetear el gif para que cada vez que entremos en esta ventana, se reproduzca desde el principio
		ImageIcon icon = (ImageIcon) jlFondo.getIcon();
		Image img = icon.getImage();
		ImageIcon newIcon = new ImageIcon(img);
		img.flush();
		jlFondo.setIcon(newIcon);
	}

	public void actionPerformed(ActionEvent e) {

		sonidoBoton();
		bgmGanador.close();
		C12_Ganador2.bgmGanador.close();
		JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
		evento1.remove(this);
		evento1.getContentPane().add(new C13_Final());
		evento1.setVisible(true);
		

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
