import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class C05_NombreEquipo extends JPanel implements ActionListener, MouseListener {
	
	//Variables estaticas
	static Clip bgmNombre;
	static Clip seSelect;
	static ArrayList<String> nombres = new ArrayList<String>();
	static int numEquipo = 0;

	//Variables de NombreEquipo
	private JButton jbOk;
	private JLabel jlFondo;
	private JLabel jlTexto;
	private JTextField jtNombre;
	private Font sizeFont;
	private File myFont;
	private Font fuente;

	public C05_NombreEquipo() {
		
		//Cerramos ventana MenuPrincpal
		C04_MenuPrincipal.bgmMenu.close();
		
		//Musica
		if(bgmNombre == null || !bgmNombre.isActive() ) {

			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/BGM/equipos.wav").getAbsoluteFile());
				bgmNombre = AudioSystem.getClip();
				bgmNombre.open(audioInputStream);
				bgmNombre.loop(9999);

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

		//Botón de OK
		jbOk = new JButton();
		jbOk.setIcon(new ImageIcon("src/resources/botones/ok.png"));
		jbOk.setPressedIcon(new ImageIcon("src/resources/botones/ok2.png"));
		jbOk.setBounds(890, 646, 104, 92);
		jbOk.setBorderPainted(false); 
		jbOk.setContentAreaFilled(false);
		add(jbOk);
		jbOk.addActionListener(this);

		//Texto nombre entrenador
		jlTexto = new JLabel("\nIntroduce el nombre de tu entrenador:");
		jlTexto.setBounds(65,580,700,60);
		jlTexto.setFont(sizeFont);
		jlTexto.setOpaque(false);
		add(jlTexto);

		//Introducir Nombre
		jtNombre = new JTextField();
		jtNombre.setOpaque(false);
		jtNombre.setBorder(null);
		jtNombre.setBounds(75,653,700,40);
		jtNombre.setFont(sizeFont);
		jtNombre.addMouseListener(this);
		add(jtNombre);
		
		//Comprobamos que el nombre no tenga mas de 15 carcateres
		jtNombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
				if (jtNombre.getText().length() >= 15 )
					e.consume();
			}
		});
	
		//Fondo de nombre equipo
		jlFondo = new JLabel();
		jlFondo.setBounds(0,0,1024,768);
		jlFondo.setIcon(new ImageIcon("src/resources/fondos/fondo_nombre.png"));
		add(jlFondo); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		boolean nombreCorrecto = false;
		
		//Comprobamos si el nombre esta repetido
		if(nombres.contains(jtNombre.getText()) || jtNombre.getText().equals("ERROR. Nombre repetido.")) {
			jtNombre.setText("ERROR. Nombre repetido.");
			
		//Comprobamos si has introducido algún nombre
		}else if (jtNombre.getText().isEmpty() || jtNombre.getText().equals("ERROR. Introduce un nombre.")) {
			jtNombre.setText("ERROR. Introduce un nombre.");

		}else {
			nombreCorrecto = true;
		}
		
		//Añadimos el nombre al ArrayList
		if (nombreCorrecto) {

			nombres.add(jtNombre.getText());

			sonidoBoton();
			
			//Pasamos a ventana TipoEquipo
			if (e.getSource().equals(jbOk)) {
				JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
				evento1.remove(this);
				evento1.getContentPane().add(new C06_TipoEquipo());
				evento1.setVisible(true);
				numEquipo ++;
			}

		}else  {
			sonidoBotonError();
		}

	}
	
	//Funcion sonido de Boton
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

	public static boolean comprobarNombre(String nombreEntrenador, ArrayList<String> nombres, boolean nombreCorrecto) {

		return nombreCorrecto;
	}
	
	@Override
	
	//Mostramos el error al mostrar un nombre 
	public void mouseClicked(MouseEvent e) {

		if (jtNombre.getText().equals("ERROR. Nombre repetido.") || jtNombre.getText().equals("ERROR. Introduce un nombre.")) {
			jtNombre.setText("");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	//Función sonido de cuando es un ERROR
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