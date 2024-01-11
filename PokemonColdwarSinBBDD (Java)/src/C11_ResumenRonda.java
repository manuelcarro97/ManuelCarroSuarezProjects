import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class C11_ResumenRonda extends JPanel implements ActionListener {

	private JLabel jlEnfermera;
	private JLabel jlReportera;
	private JTextArea jtResumenRonda;
	private JScrollPane jsResumenRonda;
	private JLabel jlFondo;
	private JButton jbOk;
	private Font sizeFont;
	private File myFont;
	private Font fuente;

	private Font sizeFont2;
	private File myFont2;
	private Font fuente2;

	static Clip seSelect;
	static Clip bgmResumen;
	private String resumen="";

	private JLabel jlPokeIcon;
	private JTextArea jlTeam;
	private JLabel jlVida;
	private JTextArea jlPS;



	public C11_ResumenRonda()  {


		C09_Combate.bgmCombate.close();

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

		//Fuente de letra 2
		String fontName2 = "src/resources/Fonts/Font3.ttf" ;
		myFont2 = new File(fontName2);

		try{

			fuente2 = Font.createFont(Font.TRUETYPE_FONT,myFont);
			sizeFont2 = fuente2.deriveFont(20f);

		}catch(FontFormatException ex){

			System.err.println("Error estableciendo fuente tipográfica");

		}catch (IOException ex) {

			System.err.println("Error I/O");
		}

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("src/resources/cursor.png");
		Cursor c = toolkit.createCustomCursor(image , new Point(this.getX(),this.getY()), "img");
		this.setCursor (c);

		//musica
		if(bgmResumen == null || !bgmResumen.isActive() ) {

			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/BGM/PokeCenter.wav").getAbsoluteFile());
				bgmResumen = AudioSystem.getClip();
				bgmResumen.open(audioInputStream);
				bgmResumen.loop(9999);


			} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
				System.out.println("Error al reproducir el sonido.");
			}
		}


		C09_Combate.ronda ++;

		//		boton

		jbOk = new JButton();
		jbOk.setBounds(890, 646, 104, 92);
		jbOk.setBorderPainted(false); 
		jbOk.setContentAreaFilled(false);
		jbOk.setIcon(new ImageIcon("src/resources/botones/ok.png"));
		jbOk.setPressedIcon(new ImageIcon("src/resources/botones/ok2.png"));
		add(jbOk);
		jbOk.addActionListener(this);

		//EQUIPOS y su posicion
		if (C01_Principal.pokemon.size() == 2) {


			equipo(122,115,0,152);
			equipo(197,190,1,226);

		}
		else if (C01_Principal.pokemon.size() == 3) {


			equipo(122,115,0,152);
			equipo(197,190,1,226);
			equipo(272,265,2,301);

		}else if (C01_Principal.pokemon.size() == 4) {

			equipo(122,115,0,152);
			equipo(197,190,1,226);
			equipo(272,265,2,301);
			equipo(347,340,3,376);

		}else if (C01_Principal.pokemon.size() == 5) {

			equipo(122,115,0,152);
			equipo(197,190,1,226);
			equipo(272,265,2,301);
			equipo(347,340,3,376);
			equipo(422,415,4,452);

		}else if (C01_Principal.pokemon.size() == 6) {


			equipo(122,115,0,152);
			equipo(197,190,1,226);
			equipo(272,265,2,301);
			equipo(347,340,3,376);
			equipo(422,415,4,452);
			equipo(497,490,5,527);

		}else if (C01_Principal.pokemon.size() == 7) {

			equipo(122,115,0,152);
			equipo(197,190,1,226);
			equipo(272,265,2,301);
			equipo(347,340,3,376);
			equipo(422,415,4,452);
			equipo(497,490,5,527);
			equipo(572,565,6,603);

		}else if (C01_Principal.pokemon.size() == 8) {

			equipo(122,115,0,152);
			equipo(197,190,1,226);
			equipo(272,265,2,301);
			equipo(347,340,3,376);
			equipo(422,415,4,452);
			equipo(497,490,5,527);
			equipo(572,565,6,603);
			equipo(647,640,7,678);
		}


		// textos

		jtResumenRonda = new JTextArea();
		jtResumenRonda.setBounds(495,290,490,330);
		jtResumenRonda.setOpaque(false);
		jtResumenRonda.setEditable(false);
		jtResumenRonda.setBorder(null);
		jtResumenRonda.setFont(sizeFont2);
		jtResumenRonda.setText('"' + "¡Y este es el resumen de la RONDA " + C09_Combate.ronda + "!" + '"' + "\n\n" + C09_Combate.resumen);
		jtResumenRonda.setCaretPosition(0);
		add(jtResumenRonda);

		jsResumenRonda = new JScrollPane(jtResumenRonda);
		jsResumenRonda.setBounds(495,290,490,302);
		jsResumenRonda.setOpaque(false);
		jsResumenRonda.getViewport().setOpaque(false);
		jsResumenRonda.setBorder(null);
		jsResumenRonda.setFont(sizeFont2);

		Dimension tamanhoTextArea = jtResumenRonda.getSize();
		Point p = new Point( 0, tamanhoTextArea.height);
		jsResumenRonda.getViewport().setViewPosition(p);

		add(jsResumenRonda);

		jlEnfermera = new JLabel('"' + "¡Así quedan los equipos!" + '"');
		jlEnfermera.setBounds(50,55,700,60);
		jlEnfermera.setFont(sizeFont);
		jlEnfermera.setOpaque(false);
		add(jlEnfermera);

		//fondo en funcion del numero de equipos

		if (C01_Principal.pokemon.size()==8) {

			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,1024,768);
			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosResumen/fondo_resumen8.png"));
			add(jlFondo);
		}
		else if (C01_Principal.pokemon.size()==7) {

			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,1024,768);
			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosResumen/fondo_resumen7.png"));
			add(jlFondo);
		}
		else if (C01_Principal.pokemon.size()==6) {

			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,1024,768);
			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosResumen/fondo_resumen6.png"));
			add(jlFondo);
		}

		else if (C01_Principal.pokemon.size()==5) {

			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,1024,768);
			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosResumen/fondo_resumen5.png"));
			add(jlFondo);
		}

		else if (C01_Principal.pokemon.size()==4) {

			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,1024,768);
			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosResumen/fondo_resumen4.png"));
			add(jlFondo);
		}

		else if (C01_Principal.pokemon.size()==3) {

			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,1024,768);
			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosResumen/fondo_resumen3.png"));
			add(jlFondo);
		}

		else if (C01_Principal.pokemon.size()==2) {

			jlFondo = new JLabel();
			jlFondo.setBounds(0,0,1024,768);
			jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosResumen/fondo_resumen2.png"));
			add(jlFondo);
		}




		setLayout(null);


	}




	public void actionPerformed(ActionEvent e) {

		sonidoBoton();

		bgmResumen.close();

		C09_Combate.resumen="";
		C09_Combate.contRonda++;

		//		comporobar si hay un pokemon muert y borrarlo

		for (int i = 0; i < C01_Principal.pokemon.size(); i ++) {


			if (C01_Principal.pokemon.get(i).getVidas() <= 0) {

				C01_Principal.pokemon.remove(i);
				i--;

			}
			if (C01_Principal.pokemon.size()<1) {

				break;
			}
		}

		if (C01_Principal.pokemon.size()<2) {

			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C12_Ganador());
			evento1.setVisible(true);

		}else {

			C09_Combate.turno = 0;
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

	public void equipo(int x, int y, int z , int w) {

		//		EQUIPO
		if (C01_Principal.pokemon.get(z).getTipoPlaneta()==1) {

			jlPokeIcon = new JLabel();
			jlPokeIcon.setBounds(45,x,60,60);
			jlPokeIcon.setIcon(new ImageIcon("src/resources/pokeicons/normal.png"));
			add(jlPokeIcon);

			if (C01_Principal.pokemon.get(z).getVidas() < 0) {
				jlTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "  " + "0 / 200");

			}else {
				jlTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "  " + (C01_Principal.pokemon.get(z).getVidas() + " / 200"));
			}

			jlTeam.setBounds(123,y,700,75);
			jlTeam.setFont(sizeFont);
			jlTeam.setEditable(false);
			jlTeam.setOpaque(false);
			add(jlTeam);

			if (C01_Principal.pokemon.get(z).getVidas()>=200) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida4.png"));
				add(jlVida);

			}

			else if (C01_Principal.pokemon.get(z).getVidas()<200 && C01_Principal.pokemon.get(z).getVidas()>100  ) {


				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida3.png"));
				add(jlVida);
			}

			else if (C01_Principal.pokemon.get(z).getVidas()<=100 && C01_Principal.pokemon.get(z).getVidas()>50  ) {


				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida2.png"));
				add(jlVida);
			}
			else if (C01_Principal.pokemon.get(z).getVidas()<=50 && C01_Principal.pokemon.get(z).getVidas()>0) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida1.png"));
				add(jlVida);

			}
			else if (C01_Principal.pokemon.get(z).getVidas()<=0) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 20);
				jlVida.setText("HA SIDO ELIMINADO");
				jlVida.setFont(sizeFont2);
				add(jlVida);

			}

		}else if (C01_Principal.pokemon.get(z).getTipoPlaneta()==2) {

			jlPokeIcon = new JLabel();
			jlPokeIcon.setBounds(45,x,60,60);
			jlPokeIcon.setIcon(new ImageIcon("src/resources/pokeicons/fuego.png"));
			add(jlPokeIcon);

			if (C01_Principal.pokemon.get(z).getVidas() < 0) {
				jlTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "  " + "0 / 200");

			}else {
				jlTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "  " + (C01_Principal.pokemon.get(z).getVidas() + " / 200"));
			}

			jlTeam.setBounds(123,y,700,75);
			jlTeam.setFont(sizeFont);
			jlTeam.setOpaque(false);
			jlTeam.setEditable(false);
			add(jlTeam);

			if (C01_Principal.pokemon.get(z).getVidas()>=200) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida4.png"));
				add(jlVida);

			}

			else if (C01_Principal.pokemon.get(z).getVidas()<200 && C01_Principal.pokemon.get(z).getVidas()>100  ) {


				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida3.png"));
				add(jlVida);
			}

			else if (C01_Principal.pokemon.get(z).getVidas()<=100 && C01_Principal.pokemon.get(z).getVidas()>50  ) {


				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida2.png"));
				add(jlVida);
			}
			else if (C01_Principal.pokemon.get(z).getVidas()<=50 && C01_Principal.pokemon.get(z).getVidas()>0) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida1.png"));
				add(jlVida);

			}
			else if (C01_Principal.pokemon.get(z).getVidas()<=0) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 20);
				jlVida.setText("HA SIDO ELIMINADO");
				jlVida.setFont(sizeFont2);
				add(jlVida);

			}

		}else if (C01_Principal.pokemon.get(z).getTipoPlaneta()==3) {

			jlPokeIcon = new JLabel();
			jlPokeIcon.setBounds(45,x,60,60);
			jlPokeIcon.setIcon(new ImageIcon("src/resources/pokeicons/agua.png"));
			add(jlPokeIcon);

			if (C01_Principal.pokemon.get(z).getVidas() < 0) {
				jlTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "  " + "0 / 200");

			}else {
				jlTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "  " + (C01_Principal.pokemon.get(z).getVidas() + " / 200"));
			}

			jlTeam.setBounds(123,y,700,75);
			jlTeam.setFont(sizeFont);
			jlTeam.setOpaque(false);
			jlTeam.setEditable(false);
			add(jlTeam);


			if (C01_Principal.pokemon.get(z).getVidas()>100) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida3.png"));
				add(jlVida);

			}
			else if (C01_Principal.pokemon.get(z).getVidas()<=100 && C01_Principal.pokemon.get(z).getVidas()>50  ) {


				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida2.png"));
				add(jlVida);
			}
			else if (C01_Principal.pokemon.get(z).getVidas()<=50 && C01_Principal.pokemon.get(z).getVidas()>0) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida1.png"));
				add(jlVida);

			}
			else if (C01_Principal.pokemon.get(z).getVidas()<=0) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 20);
				jlVida.setText("HA SIDO ELIMINADO");
				jlVida.setFont(sizeFont2);
				add(jlVida);

			}
		}else if (C01_Principal.pokemon.get(z).getTipoPlaneta()==4) {

			jlPokeIcon = new JLabel();
			jlPokeIcon.setBounds(45,x,60,60);
			jlPokeIcon.setIcon(new ImageIcon("src/resources/pokeicons/planta.png"));
			add(jlPokeIcon);

			if (C01_Principal.pokemon.get(z).getVidas() < 0) {
				jlTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "  " + "0 / 200");

			}else {
				jlTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "  " + (C01_Principal.pokemon.get(z).getVidas() + " / 200"));
			}

			jlTeam.setBounds(123,y,700,75);
			jlTeam.setFont(sizeFont);
			jlTeam.setEditable(false);
			jlTeam.setOpaque(false);
			add(jlTeam);

			if (C01_Principal.pokemon.get(z).getVidas()>=200) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida4.png"));
				add(jlVida);

			}

			else if (C01_Principal.pokemon.get(z).getVidas()<200 && C01_Principal.pokemon.get(z).getVidas()>100  ) {


				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida3.png"));
				add(jlVida);
			}

			else if (C01_Principal.pokemon.get(z).getVidas()<=100 && C01_Principal.pokemon.get(z).getVidas()>50  ) {


				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida2.png"));
				add(jlVida);
			}
			else if (C01_Principal.pokemon.get(z).getVidas()<=50 && C01_Principal.pokemon.get(z).getVidas()>0) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida1.png"));
				add(jlVida);

			}
			else if (C01_Principal.pokemon.get(z).getVidas()<=0) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 20);
				jlVida.setText("HA SIDO ELIMINADO");
				jlVida.setFont(sizeFont2);
				add(jlVida);

			}

		}else if (C01_Principal.pokemon.get(z).getTipoPlaneta()==5) {

			jlPokeIcon = new JLabel();
			jlPokeIcon.setBounds(45,x,60,60);
			jlPokeIcon.setIcon(new ImageIcon("src/resources/pokeicons/dragon.png"));
			add(jlPokeIcon);

			if (C01_Principal.pokemon.get(z).getVidas() < 0) {
				jlTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "  " + "0 / 400");

			}else {
				jlTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "  " + (C01_Principal.pokemon.get(z).getVidas() + " / 400"));
			}

			jlTeam.setBounds(123,y,700,75);
			jlTeam.setFont(sizeFont);
			jlTeam.setOpaque(false);
			jlTeam.setEditable(false);
			add(jlTeam);


			if (C01_Principal.pokemon.get(z).getVidas()>=400) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida4.png"));
				add(jlVida);

			}

			else if (C01_Principal.pokemon.get(z).getVidas()<400 && C01_Principal.pokemon.get(z).getVidas()>200  ) {


				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida3.png"));
				add(jlVida);
			}

			else if (C01_Principal.pokemon.get(z).getVidas()<=200 && C01_Principal.pokemon.get(z).getVidas()>100  ) {


				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida2.png"));
				add(jlVida);
			}
			else if (C01_Principal.pokemon.get(z).getVidas()<=100 && C01_Principal.pokemon.get(z).getVidas()>0) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida1.png"));
				add(jlVida);

			}
			else if (C01_Principal.pokemon.get(z).getVidas()<=0) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 20);
				jlVida.setText("HA SIDO ELIMINADO");
				jlVida.setFont(sizeFont2);
				add(jlVida);

			}

		}else if (C01_Principal.pokemon.get(z).getTipoPlaneta()==6) {

			jlPokeIcon = new JLabel();
			jlPokeIcon.setBounds(45,x,60,60);
			jlPokeIcon.setIcon(new ImageIcon("src/resources/pokeicons/bicho.png"));
			add(jlPokeIcon);

			if (C01_Principal.pokemon.get(z).getVidas() < 0) {
				jlTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "  " + "0 / 100");

			}else {
				jlTeam = new JTextArea(C01_Principal.pokemon.get(z).getNombreEquipo() + "  " + (C01_Principal.pokemon.get(z).getVidas() + " / 100"));
			}

			jlTeam.setBounds(123,y,700,75);
			jlTeam.setFont(sizeFont);
			jlTeam.setOpaque(false);
			jlTeam.setEditable(false);
			add(jlTeam);

			if (C01_Principal.pokemon.get(z).getVidas()>=100) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida4.png"));
				add(jlVida);

			}

			else if (C01_Principal.pokemon.get(z).getVidas()<100 && C01_Principal.pokemon.get(z).getVidas()>50  ) {


				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida3.png"));
				add(jlVida);
			}

			else if (C01_Principal.pokemon.get(z).getVidas()<=50 && C01_Principal.pokemon.get(z).getVidas()>25  ) {


				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida2.png"));
				add(jlVida);
			}
			else if (C01_Principal.pokemon.get(z).getVidas()<=25 && C01_Principal.pokemon.get(z).getVidas()>0) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 16);
				jlVida.setIcon(new ImageIcon("src/resources/vida1.png"));
				add(jlVida);

			}
			else if (C01_Principal.pokemon.get(z).getVidas()<=0) {

				jlVida = new JLabel();
				jlVida.setBounds(123, w, 179, 20);
				jlVida.setText("HA SIDO ELIMINADO");
				jlVida.setFont(sizeFont2);
				add(jlVida);

			}



		}

	}



}
