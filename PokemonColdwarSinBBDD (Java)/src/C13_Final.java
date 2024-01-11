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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class C13_Final extends JPanel implements ActionListener {

	private JLabel jlFondo;

	static Clip bgmFinal;

	public C13_Final() {

		

		if(bgmFinal == null || !bgmFinal.isActive() ) {

			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/BGM/final.wav").getAbsoluteFile());
				bgmFinal = AudioSystem.getClip();
				bgmFinal.open(audioInputStream);
				bgmFinal.loop(0);


			} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
				System.out.println("Error al reproducir el sonido.");
			}
		}

		setLayout(null);

		//		cursor
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("src/resources/cursor2.png");
		Cursor c = toolkit.createCustomCursor(image , new Point(this.getX(),this.getY()), "img");
		this.setCursor (c);

		//gif de créditos
		jlFondo = new JLabel();
		jlFondo.setBounds(0,0,1024,768);
		jlFondo.setIcon(new ImageIcon("src/resources/fondos/final.gif"));
		add(jlFondo);

		//pausa para reproducir el gif
		Timer clearTimer = new Timer(29500, this);
		clearTimer.start();
		clearTimer.setRepeats(false);

	}

	public void actionPerformed(ActionEvent e) {
		JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
		evento1.remove(this);
		evento1.getContentPane().add(new C03_PantallaInicio());
		evento1.setVisible(true);
		bgmFinal.close();



	}
}
