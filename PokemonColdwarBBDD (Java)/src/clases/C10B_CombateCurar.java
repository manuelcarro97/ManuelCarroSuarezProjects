package clases;
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
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class C10B_CombateCurar extends JPanel implements ActionListener {

	private JLabel jlFondo;
	private Font sizeFont;
	private File myFont;
	private Font fuente;
	private JTextArea jtEquipo;
	private JButton jbAtacar; 
	private JButton jbCurar; 

	private JCheckBox cb1;
	private JCheckBox cb2;
	private JCheckBox cb3;
	private JCheckBox cb4;
	private JCheckBox cb5;
	private JCheckBox cb6;
	private JCheckBox cb7;
	private JCheckBox cb8;

	private JLabel jlLinea1;
	private JLabel jlLinea2;
	private JLabel jlLinea3;
	private JLabel jlPregunta;
	private boolean checkbox = false;

	private JTextField jtPP;

	static Clip seSelect;



	public C10B_CombateCurar(){

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


		//Texto datos
		jtEquipo = new JTextArea(C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo());
		jtEquipo.setBounds(45, 85, 500, 400);
		jtEquipo.setFont(sizeFont);
		jtEquipo.setEditable(false);
		jtEquipo.setOpaque(false);
		add(jtEquipo);	

		//Botón atacar

		jbAtacar = new JButton();
		jbAtacar.setIcon(new ImageIcon("src/resources/botones/confirmar.png"));
		jbAtacar.setPressedIcon(new ImageIcon("src/resources/botones/confirmar2.png"));
		jbAtacar.setBounds(598, 638, 325, 76);
		jbAtacar.setBorderPainted(false); 
		jbAtacar.setContentAreaFilled(false);
		add(jbAtacar);
		jbAtacar.addActionListener(this);

		jlLinea1 = new JLabel("Introduce tus PP");	
		jlLinea1.setBounds(62, 627, 400, 40);
		jlLinea1.setFont(sizeFont);
		jlLinea1.setOpaque(false);
		add(jlLinea1);


		jlLinea2 = new JLabel("/ " + C01_Principal.pokemon.get(C09_Combate.turno).getMisilesRonda());	
		jlLinea2.setBounds(116, 682, 400, 40);
		jlLinea2.setFont(sizeFont);
		jlLinea2.setOpaque(false);
		add(jlLinea2);

		jtPP = new JTextField();	
		jtPP.setBounds(51, 682, 60, 40);
		jtPP.setFont(sizeFont);
		jtPP.setOpaque(false);
		jtPP.setBorder(null);
		jtPP.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jtPP);


		//		limites para la lines de escritura , solo 2 o 3 caracteres y numeros

		if (C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta() == 5 && C01_Principal.pokemon.get(C09_Combate.turno).getMisilesRonda() >= 100) {

			jtPP.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) { 

					char c = e.getKeyChar();

					if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) || jtPP.getText().length() == 3) {
						e.consume();
					}
				}
			});

		}else {

			jtPP.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) { 

					char c = e.getKeyChar();

					if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) || jtPP.getText().length() == 2) {
						e.consume();
					}
				}
			});
		}




		//Mostraremos la ventana combate dependiendo del pokemon seleccionado

		jlFondo = new JLabel();
		jlFondo.setBounds(0,0,1024,768);

		if (C01_Principal.pokemon.size()==8) {

			cb1 = checkBoxGeneral(87, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-8).getNombreEquipo()), cb1);

			cb2 = checkBoxGeneral(143, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-7).getNombreEquipo()), cb2);

			cb3 = checkBoxGeneral(199, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-6).getNombreEquipo()), cb3);

			cb4 = checkBoxGeneral(255, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-5).getNombreEquipo()), cb4);

			cb5 = checkBoxGeneral(311, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-4).getNombreEquipo()), cb5);

			cb6 = checkBoxGeneral(367, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-3).getNombreEquipo()), cb6);

			cb7 = checkBoxGeneral(423, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-2).getNombreEquipo()), cb7);

			cb8 = checkBoxGeneral(479, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-1).getNombreEquipo()), cb8);

			modificarCheckBox();
			tipoFondoPokemon(C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta(), jlFondo);

		}else if (C01_Principal.pokemon.size()==7) {

			cb2 = checkBoxGeneral(143, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-7).getNombreEquipo()), cb2);

			cb3 = checkBoxGeneral(199, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-6).getNombreEquipo()), cb3);

			cb4 = checkBoxGeneral(255, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-5).getNombreEquipo()), cb4);

			cb5 = checkBoxGeneral(311, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-4).getNombreEquipo()), cb5);

			cb6 = checkBoxGeneral(367, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-3).getNombreEquipo()), cb6);

			cb7 = checkBoxGeneral(423, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-2).getNombreEquipo()), cb7);

			cb8 = checkBoxGeneral(479, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-1).getNombreEquipo()), cb8);



			modificarCheckBox();

			tipoFondoPokemon(C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta(), jlFondo);

		}else if (C01_Principal.pokemon.size()==6) {

			cb3 = checkBoxGeneral(199, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-6).getNombreEquipo()), cb3);

			cb4 = checkBoxGeneral(255, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-5).getNombreEquipo()), cb4);

			cb5 = checkBoxGeneral(311, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-4).getNombreEquipo()), cb5);

			cb6 = checkBoxGeneral(367, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-3).getNombreEquipo()), cb6);

			cb7 = checkBoxGeneral(423, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-2).getNombreEquipo()), cb7);

			cb8 = checkBoxGeneral(479, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-1).getNombreEquipo()), cb8);



			modificarCheckBox();

			tipoFondoPokemon(C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta(), jlFondo);

		}else if (C01_Principal.pokemon.size()==5) {

			cb4 = checkBoxGeneral(255, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-5).getNombreEquipo()), cb4);

			cb5 = checkBoxGeneral(311, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-4).getNombreEquipo()), cb5);

			cb6 = checkBoxGeneral(367, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-3).getNombreEquipo()), cb6);

			cb7 = checkBoxGeneral(423, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-2).getNombreEquipo()), cb7);

			cb8 = checkBoxGeneral(479, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-1).getNombreEquipo()), cb8);



			modificarCheckBox();

			tipoFondoPokemon(C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta(), jlFondo);

		}else if (C01_Principal.pokemon.size()==4) {

			cb5 = checkBoxGeneral(311, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-4).getNombreEquipo()), cb5);

			cb6 = checkBoxGeneral(367, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-3).getNombreEquipo()), cb6);

			cb7 = checkBoxGeneral(423, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-2).getNombreEquipo()), cb7);

			cb8 = checkBoxGeneral(479, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-1).getNombreEquipo()), cb8);



			modificarCheckBox();

			tipoFondoPokemon(C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta(), jlFondo);

		}else if (C01_Principal.pokemon.size()==3) {

			cb6 = checkBoxGeneral(367, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-3).getNombreEquipo()), cb6);

			cb7 = checkBoxGeneral(423, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-2).getNombreEquipo()), cb7);

			cb8 = checkBoxGeneral(479, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-1).getNombreEquipo()), cb8);



			modificarCheckBox();

			tipoFondoPokemon(C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta(), jlFondo);

		}else if (C01_Principal.pokemon.size()==2) {

			cb7 = checkBoxGeneral(423, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-2).getNombreEquipo()), cb7);

			cb8 = checkBoxGeneral(479, (C01_Principal.pokemon.get(C01_Principal.pokemon.size()-1).getNombreEquipo()), cb8);

		}

		modificarCheckBox();

		tipoFondoPokemon(C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta(), jlFondo);

		add(jlFondo);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int esquivados=0;


		//		sonido si el texto esta vacio o el mayor que tu numero de pp

		if (jtPP.getText().isEmpty() || Integer.parseInt(jtPP.getText()) > C01_Principal.pokemon.get(C09_Combate.turno).getMisilesRonda()) {

			jtPP.setText("");
			sonidoBotonError();


		}else {

			//			comprobbar que checkbox esta seleccionada y hacer el combate y guardar el resumen 


			if (cb8 != null && cb8.isSelected()) {

				checkbox = true;

				for (int i=0; i<C01_Principal.pokemon.size(); i++) {

					if (cb8.getText().equals(C01_Principal.pokemon.get(i).getNombreEquipo())) {

						esquivados=C01_Principal.pokemon.get(C09_Combate.turno).combate(C01_Principal.pokemon, i, Integer.parseInt(jtPP.getText()), C09_Combate.accion, C09_Combate.turno);

						if (C01_Principal.pokemon.get(i).getTipoPlaneta()==6) {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n     y "+ C01_Principal.pokemon.get(i).getNombreEquipo() + " ha acertado " + (Integer.parseInt(jtPP.getText())-esquivados)+"\n\n";
						}
						else {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n\n";

						}
					}

				}

			}else if (cb7 != null && cb7.isSelected()) {

				checkbox = true;

				for (int i=0; i<C01_Principal.pokemon.size(); i++) {

					if (cb7.getText().equals(C01_Principal.pokemon.get(i).getNombreEquipo())) {

						esquivados=C01_Principal.pokemon.get(C09_Combate.turno).combate(C01_Principal.pokemon, i, Integer.parseInt(jtPP.getText()), C09_Combate.accion, C09_Combate.turno);
						if (C01_Principal.pokemon.get(i).getTipoPlaneta()==6) {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n     y "+ C01_Principal.pokemon.get(i).getNombreEquipo() + "  ha acertado " + (Integer.parseInt(jtPP.getText())-esquivados)+"\n\n";						}
						else {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n\n";

						}
					}

				}
			}else if (cb6 != null && cb6.isSelected()) {

				checkbox = true;

				for (int i=0; i<C01_Principal.pokemon.size(); i++) {

					if (cb6.getText().equals(C01_Principal.pokemon.get(i).getNombreEquipo())) {

						esquivados= C01_Principal.pokemon.get(C09_Combate.turno).combate(C01_Principal.pokemon, i, Integer.parseInt(jtPP.getText()), C09_Combate.accion, C09_Combate.turno);
						if (C01_Principal.pokemon.get(i).getTipoPlaneta()==6) {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n     y "+ C01_Principal.pokemon.get(i).getNombreEquipo() + " ha acertado " + (Integer.parseInt(jtPP.getText())-esquivados)+"\n\n";						}
						else {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n\n";

						}
					}

				}
			}else if (cb5 != null && cb5.isSelected()) {

				checkbox = true;

				for (int i=0; i<C01_Principal.pokemon.size(); i++) {

					if (cb5.getText().equals(C01_Principal.pokemon.get(i).getNombreEquipo())) {

						esquivados=C01_Principal.pokemon.get(C09_Combate.turno).combate(C01_Principal.pokemon, i, Integer.parseInt(jtPP.getText()), C09_Combate.accion, C09_Combate.turno);
						if (C01_Principal.pokemon.get(i).getTipoPlaneta()==6) {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n     y "+ C01_Principal.pokemon.get(i).getNombreEquipo() + " ha acertado " + (Integer.parseInt(jtPP.getText())-esquivados)+"\n\n";						}
						else {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n\n";

						}
					}

				}
			}else if (cb4 != null && cb4.isSelected()) {

				checkbox = true;

				for (int i=0; i<C01_Principal.pokemon.size(); i++) {

					if (cb4.getText().equals(C01_Principal.pokemon.get(i).getNombreEquipo())) {

						esquivados=C01_Principal.pokemon.get(C09_Combate.turno).combate(C01_Principal.pokemon, i, Integer.parseInt(jtPP.getText()), C09_Combate.accion, C09_Combate.turno);
						if (C01_Principal.pokemon.get(i).getTipoPlaneta()==6) {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n     y "+ C01_Principal.pokemon.get(i).getNombreEquipo() + " ha acertado " + (Integer.parseInt(jtPP.getText())-esquivados)+"\n\n";						}
						else {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n\n";

						}
					}

				}
			}else if (cb3 != null && cb3.isSelected()) {

				checkbox = true;

				for (int i=0; i<C01_Principal.pokemon.size(); i++) {

					if (cb3.getText().equals(C01_Principal.pokemon.get(i).getNombreEquipo())) {

						esquivados=C01_Principal.pokemon.get(C09_Combate.turno).combate(C01_Principal.pokemon, i, Integer.parseInt(jtPP.getText()), C09_Combate.accion, C09_Combate.turno);

						if (C01_Principal.pokemon.get(i).getTipoPlaneta()==6) {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n     y "+ C01_Principal.pokemon.get(i).getNombreEquipo() + " ha acertado " + (Integer.parseInt(jtPP.getText())-esquivados)+"\n\n";						}
						else {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n\n";

						}
					}

				}
			}else if (cb2 != null && cb2.isSelected()) {

				checkbox = true;

				for (int i=0; i<C01_Principal.pokemon.size(); i++) {

					if (cb2.getText().equals(C01_Principal.pokemon.get(i).getNombreEquipo())) {

						esquivados=C01_Principal.pokemon.get(C09_Combate.turno).combate(C01_Principal.pokemon, i, Integer.parseInt(jtPP.getText()), C09_Combate.accion, C09_Combate.turno);
						if (C01_Principal.pokemon.get(i).getTipoPlaneta()==6) {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n     y "+ C01_Principal.pokemon.get(i).getNombreEquipo() + " ha acertado " + (Integer.parseInt(jtPP.getText())-esquivados)+"\n\n";						}
						else {

							C09_Combate.resumen += C01_Principal.pokemon.get(C09_Combate.turno).getNombreEquipo()+" CURÓ a "+C01_Principal.pokemon.get(i).getNombreEquipo()+ " con "+jtPP.getText() + " PP\n\n";

						}
					}

				}
			}

			//			cambiar de ventana en funcion del equipos y del turno que sea en ese momento y resetear sus misiles cuando se le acabe le turno 



			if (C01_Principal.pokemon.get(C09_Combate.turno).getMisilesRonda()<=0 && C09_Combate.turno < (C01_Principal.pokemon.size()-1) && checkbox) {

				if (C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta()==5) {


					C01_Principal.pokemon.get(C09_Combate.turno).setMisilesRonda(10*C08_ListaEquipos.contRonda );

				}else {
					C01_Principal.pokemon.get(C09_Combate.turno).setMisilesRonda(50);
				}

				C09_Combate.turno ++;
				JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
				evento1.remove(this);
				evento1.getContentPane().add(new C09_Combate());
				evento1.setVisible(true);
				sonidoBoton();

			}else if (C09_Combate.turno < (C01_Principal.pokemon.size()-1) && checkbox) {

				JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
				evento1.remove(this);
				evento1.getContentPane().add(new C09_Combate());
				evento1.setVisible(true);
				sonidoBoton();
			}

			if (C09_Combate.turno == C01_Principal.pokemon.size()-1 && C01_Principal.pokemon.get(C09_Combate.turno).getMisilesRonda() > 0 && checkbox) {

				JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);

				try {
					evento1.remove(this);
					evento1.getContentPane().add(new C09_Combate());
					evento1.setVisible(true);

				}catch (Exception ex) {
				}
				sonidoBoton();
			}

			else if (C09_Combate.turno == C01_Principal.pokemon.size()-1 && C01_Principal.pokemon.get(C09_Combate.turno).getMisilesRonda() == 0 && checkbox) {

				C09_Combate.bgmCombate.close();

				if (C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta()==5) {


					C01_Principal.pokemon.get(C09_Combate.turno).setMisilesRonda(10*C08_ListaEquipos.contRonda );
				}else {
					C01_Principal.pokemon.get(C09_Combate.turno).setMisilesRonda(50);
				}

				JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
				evento1.remove(this);
				evento1.getContentPane().add(new C11_ResumenRonda());
				evento1.setVisible(true);
				sonidoBoton();
			}
			if (!checkbox) {
				sonidoBotonError();
			}



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

	//Función que muestra las checkbox 
	private JCheckBox checkBoxGeneral(int ejeY, String nombre, JCheckBox cb) {

		cb = new JCheckBox(nombre);
		cb.setBounds(561,ejeY,200,46);
		cb.setOpaque(false);
		cb.setFocusPainted(false);
		cb.setIcon(new ImageIcon("src/resources/balls/checkb.png"));
		cb.setSelectedIcon(new ImageIcon("src/resources/balls/check2b.png"));
		add(cb);

		for (int i=0; i<C01_Principal.pokemon.size();i++) {

			if (nombre.equals(C01_Principal.pokemon.get(i).getNombreEquipo())) {

				if (C01_Principal.pokemon.get(i).getTipoPlaneta()==1) {
					cb.setIcon(new ImageIcon("src/resources/balls/checkbN.png"));
					cb.setSelectedIcon(new ImageIcon("src/resources/balls/check2bN.png"));

				}else if (C01_Principal.pokemon.get(i).getTipoPlaneta()==2) {
					cb.setIcon(new ImageIcon("src/resources/balls/checkbF.png"));
					cb.setSelectedIcon(new ImageIcon("src/resources/balls/check2bF.png"));

				}else if (C01_Principal.pokemon.get(i).getTipoPlaneta()==3) {
					cb.setIcon(new ImageIcon("src/resources/balls/checkbA.png"));
					cb.setSelectedIcon(new ImageIcon("src/resources/balls/check2bA.png"));

				}else if (C01_Principal.pokemon.get(i).getTipoPlaneta()==4) {
					cb.setIcon(new ImageIcon("src/resources/balls/checkbP.png"));
					cb.setSelectedIcon(new ImageIcon("src/resources/balls/check2bP.png"));

				}else if (C01_Principal.pokemon.get(i).getTipoPlaneta()==5) {
					cb.setIcon(new ImageIcon("src/resources/balls/checkbD.png"));
					cb.setSelectedIcon(new ImageIcon("src/resources/balls/check2bD.png"));

				}else if (C01_Principal.pokemon.get(i).getTipoPlaneta()==6) {
					cb.setIcon(new ImageIcon("src/resources/balls/checkbB.png"));
					cb.setSelectedIcon(new ImageIcon("src/resources/balls/check2bB.png"));
				}

			}
		}

		return cb;

	}


	//Función que permite marcar solo una checbox
	private void modificarCheckBox() {

		if (C01_Principal.pokemon.size() == 8) {

			cb1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb1.isSelected()) {

						cb2.setSelected(false);
						cb3.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb2.isSelected()) {

						cb1.setSelected(false);
						cb3.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb3.isSelected()) {

						cb1.setSelected(false);
						cb2.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb4.isSelected()) {

						cb1.setSelected(false);
						cb2.setSelected(false);
						cb3.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb5.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb5.isSelected()) {

						cb1.setSelected(false);
						cb2.setSelected(false);
						cb3.setSelected(false);
						cb4.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb6.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb6.isSelected()) {

						cb1.setSelected(false);
						cb2.setSelected(false);
						cb3.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb7.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb7.isSelected()) {

						cb1.setSelected(false);
						cb2.setSelected(false);
						cb3.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb8.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb8.isSelected()) {

						cb1.setSelected(false);
						cb2.setSelected(false);
						cb3.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
					} 

				}
			});


		}else if (C01_Principal.pokemon.size() == 7) {

			cb2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb2.isSelected()) {

						cb3.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb3.isSelected()) {

						cb2.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb4.isSelected()) {

						cb2.setSelected(false);
						cb3.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb5.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb5.isSelected()) {

						cb2.setSelected(false);
						cb3.setSelected(false);
						cb4.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb6.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb6.isSelected()) {

						cb2.setSelected(false);
						cb3.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb7.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb7.isSelected()) {

						cb2.setSelected(false);
						cb3.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb8.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb8.isSelected()) {

						cb2.setSelected(false);
						cb3.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
					} 

				}
			});


		}else if (C01_Principal.pokemon.size() == 6) {

			cb3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb3.isSelected()) {

						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb4.isSelected()) {

						cb3.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb5.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb5.isSelected()) {

						cb3.setSelected(false);
						cb4.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb6.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb6.isSelected()) {

						cb3.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb7.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb7.isSelected()) {

						cb3.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb8.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb8.isSelected()) {

						cb3.setSelected(false);
						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
					} 

				}
			});



		}else if (C01_Principal.pokemon.size() == 5) {

			cb4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb4.isSelected()) {

						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb5.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb5.isSelected()) {

						cb4.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb6.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb6.isSelected()) {

						cb4.setSelected(false);
						cb5.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb7.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb7.isSelected()) {

						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb8.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb8.isSelected()) {

						cb4.setSelected(false);
						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
					} 

				}
			});


		}else if (C01_Principal.pokemon.size() == 4) {

			cb5.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb5.isSelected()) {

						cb6.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb6.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb6.isSelected()) {

						cb5.setSelected(false);
						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb7.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb7.isSelected()) {

						cb5.setSelected(false);
						cb6.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb8.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb8.isSelected()) {

						cb5.setSelected(false);
						cb6.setSelected(false);
						cb7.setSelected(false);
					} 

				}
			});


		}else if (C01_Principal.pokemon.size() == 3) {


			cb6.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb6.isSelected()) {

						cb7.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb7.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb7.isSelected()) {

						cb6.setSelected(false);
						cb8.setSelected(false);
					} 

				}
			});

			cb8.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb8.isSelected()) {

						cb6.setSelected(false);
						cb7.setSelected(false);
					} 

				}
			});


		}else if (C01_Principal.pokemon.size() == 2) {

			cb7.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb7.isSelected()) {

						cb8.setSelected(false);
					} 

				}
			});

			cb8.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (cb8.isSelected()) {

						cb7.setSelected(false);
					} 

				}
			});

		}

	}


	//Función que muestra el fondo correspondiente
	public static void tipoFondoPokemon (int tipo, JLabel jlFondo) {


		if (C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta() == 1) {

			if(C01_Principal.pokemon.size()== 8) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/normal/combate_8pb.png"));

			}else if(C01_Principal.pokemon.size()== 7) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/normal/combate_7pb.png"));

			}else if(C01_Principal.pokemon.size()== 6) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/normal/combate_6pb.png"));

			}else if(C01_Principal.pokemon.size()== 5) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/normal/combate_5pb.png"));

			}else if(C01_Principal.pokemon.size()== 4) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/normal/combate_4pb.png"));

			}else if(C01_Principal.pokemon.size()== 3) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/normal/combate_3pb.png"));

			}else if(C01_Principal.pokemon.size()== 2) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/normal/combate_2pb.png"));

			}


		}else if(C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta() == 2) {

			if(C01_Principal.pokemon.size()== 8) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/fuego/combate_8pb.png"));

			}else if(C01_Principal.pokemon.size()== 7) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/fuego/combate_7pb.png"));

			}else if(C01_Principal.pokemon.size()== 6) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/fuego/combate_6pb.png"));

			}else if(C01_Principal.pokemon.size()== 5) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/fuego/combate_5pb.png"));

			}else if(C01_Principal.pokemon.size()== 4) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/fuego/combate_4pb.png"));


			}else if(C01_Principal.pokemon.size()== 3) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/fuego/combate_3pb.png"));


			}else if(C01_Principal.pokemon.size()== 2) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/fuego/combate_2pb.png"));

			}
		}else if(C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta() == 3) {

			if(C01_Principal.pokemon.size()== 8) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/agua/combate_8pb.png"));	

			}else if(C01_Principal.pokemon.size()== 7) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/agua/combate_7pb.png"));

			}else if(C01_Principal.pokemon.size()== 6) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/agua/combate_6pb.png"));

			}else if(C01_Principal.pokemon.size()== 5) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/agua/combate_5pb.png"));

			}else if(C01_Principal.pokemon.size()== 4) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/agua/combate_4pb.png"));

			}else if(C01_Principal.pokemon.size()== 3) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/agua/combate_3pb.png"));

			}else if(C01_Principal.pokemon.size()== 2) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/agua/combate_2pb.png"));

			}

		}else if(C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta() == 4) {

			if(C01_Principal.pokemon.size()== 8) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/planta/combate_8pb.png"));

			}else if(C01_Principal.pokemon.size()== 7) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/planta/combate_7pb.png"));

			}else if(C01_Principal.pokemon.size()== 6) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/planta/combate_6pb.png"));

			}else if(C01_Principal.pokemon.size()== 5) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/planta/combate_5pb.png"));

			}else if(C01_Principal.pokemon.size()== 4) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/planta/combate_4pb.png"));

			}else if(C01_Principal.pokemon.size()== 3) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/planta/combate_3pb.png"));

			}else if(C01_Principal.pokemon.size()== 2) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/planta/combate_2pb.png"));

			}

		}else if(C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta() == 5) {

			if(C01_Principal.pokemon.size()== 8) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/dragon/combate_8pb.png"));

			}else if(C01_Principal.pokemon.size()== 7) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/dragon/combate_7pb.png"));

			}else if(C01_Principal.pokemon.size()== 6) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/dragon/combate_6pb.png"));

			}else if(C01_Principal.pokemon.size()== 5) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/dragon/combate_5pb.png"));

			}else if(C01_Principal.pokemon.size()== 4) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/dragon/combate_4pb.png"));

			}else if(C01_Principal.pokemon.size()== 3) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/dragon/combate_3pb.png"));

			}else if(C01_Principal.pokemon.size()== 2) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/dragon/combate_2pb.png"));

			}


		}else if(C01_Principal.pokemon.get(C09_Combate.turno).getTipoPlaneta() == 6) {

			if(C01_Principal.pokemon.size()== 8) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/bicho/combate_8pb.png"));

			}else if(C01_Principal.pokemon.size()== 7) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/bicho/combate_7pb.png"));

			}else if(C01_Principal.pokemon.size()== 6) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/bicho/combate_6pb.png"));

			}else if(C01_Principal.pokemon.size()== 5) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/bicho/combate_5pb.png"));

			}else if(C01_Principal.pokemon.size()== 4) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/bicho/combate_4pb.png"));

			}else if(C01_Principal.pokemon.size()== 3) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/bicho/combate_3pb.png"));

			}else if(C01_Principal.pokemon.size()== 2) {

				jlFondo.setIcon(new ImageIcon("src/resources/fondos/FondosCombate/bicho/combate_2pb.png"));


			}

		}

	}


}