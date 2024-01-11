import java.awt.Cursor;
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
import javax.swing.SwingUtilities;

public class C03_PantallaInicio extends JPanel implements ActionListener {
	
	//Variables de Pantalla Inicio
	private JButton jbLogo;
	private JLabel jlFondo0;

	//Variables estaticas
	static Clip bgmInicio;
	
	//Pantalla Inicio
	public C03_PantallaInicio() {
		
		//Música
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/BGM/inicio.wav").getAbsoluteFile());
			bgmInicio = AudioSystem.getClip();			
			bgmInicio.open(audioInputStream);
			bgmInicio.loop(9999);
	
		} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error al reproducir el sonido.");
		}

		setLayout(null);
		
		//Modificación del cursor
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("src/resources/cursor.png");
		Cursor c = toolkit.createCustomCursor(image , new Point(this.getX(),this.getY()), "img");
		this.setCursor (c);
		
		//Botón de inicio de juego
		jbLogo = new JButton();
		jbLogo.setIcon(new ImageIcon("src/resources/logo.png"));
		jbLogo.setBounds(0, 0, 1024, 768);
		jbLogo.setBorderPainted(false); 
		jbLogo.setContentAreaFilled(false);
		add(jbLogo);
		jbLogo.addActionListener(this);
		
		//Fondo de pantalla de inicio
		jlFondo0 = new JLabel();
		jlFondo0.setBounds(0,0,1024,768);
		jlFondo0.setIcon(new ImageIcon("src/resources/fondos/fondo_inicio.gif"));
		add(jlFondo0);

	}
	
	public void actionPerformed(ActionEvent e) {
		
		//Cuando pulsamos el Botón(logo) nos lleva a Meu Principal
		if (e.getSource().equals(jbLogo)) {
			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C04_MenuPrincipal());
			evento1.setVisible(true);
			bgmInicio.close();
		}
	}
}
