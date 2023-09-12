import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

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
public class C12_Ganador extends JPanel implements ActionListener {


	private JTextArea jtGanador;
	private JButton jbOk;
	private JLabel jlFondo;
	private JLabel jlTexto;
	private Font sizeFont;
	private File myFont;
	private Font fuente;

	static Clip seSelect;


	public C12_Ganador() {

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

		//		textos 

		jtGanador = new JTextArea();
		jtGanador.setBounds(10, 10, 120, 120);
		jtGanador.setOpaque(false);
		jtGanador.setBorder(null);
		add(jtGanador);
		//boton

		jbOk = new JButton();
		jbOk.setBounds(890, 646, 104, 92);
		jbOk.setBorderPainted(false); 
		jbOk.setContentAreaFilled(false);
		jbOk.setIcon(new ImageIcon("src/resources/botones/ok.png"));
		jbOk.setPressedIcon(new ImageIcon("src/resources/botones/ok2.png"));
		add(jbOk);
		jbOk.addActionListener(this);

		//		texto en funcoin de ganador o empate

		if (C01_Principal.pokemon.size()==1) {

			jlTexto = new JLabel("¡ El ganador es ....");

		}
		else {

			jlTexto = new JLabel("EMPATE , no ha habido ningun ganador");

		}


		jlTexto.setBounds(230,660,700,60);
		jlTexto.setFont(sizeFont);
		jlTexto.setOpaque(false);
		add(jlTexto);

		jlFondo = new JLabel();
		jlFondo.setBounds(0,0,1024,768);
		jlFondo.setIcon(new ImageIcon("src/resources/fondos/fondo_empate.png"));
		add(jlFondo);



		//CREAMOS LA RUTA DONDE ESTARÁ EL FICHERO Y LLAMAMOS A LA FUNCIÓN PARA ESCRIBIR EN EL

		if (C01_Principal.pokemon.size()>0) {

			String ruta = "src/fichero/ganador.txt";
			String docGanador =  C01_Principal.pokemon.get(0).getNombreEquipo();
			ArrayList<Datosfichero> listaDatos = new ArrayList<Datosfichero>();


			listaDatos = leerFichero(listaDatos, ruta, docGanador);
			listaDatos = comprobarDatos(listaDatos, docGanador);
			escribirFichero(listaDatos, ruta);
		}



	}

	public ArrayList<Datosfichero> leerFichero(ArrayList<Datosfichero> listaDatos, String ruta, String docGanador) {

		boolean existeGan = false;

		try {

			FileReader fr = new FileReader(ruta);
			BufferedReader br = new BufferedReader(new FileReader(ruta));

			String lineaLeida = "";

			//leemos el fichero liena a linea si no esta vacia

			while((lineaLeida = br.readLine()) != null ) {

				//separamos entre el nombre y el numero de victorias y lo añadimos al array como un objeto

				String[] partes = lineaLeida.split(" --> ");
				listaDatos.add(new Datosfichero(partes[0], Integer.parseInt(partes[1])));

			}

			br.close();

			listaDatos.sort(new Comparator<Datosfichero>() {
				@Override
				public int compare(Datosfichero pers1, Datosfichero pers2) {
					return pers2.getVict().compareTo(pers1.getVict());
				}
			});

			//EN CASO DE QUE LA RUTA NO SEA VÁLIDA EN EL EQUIPO EN EL QUE SE REALIZA SALE ERROR.

		}catch (IOException e) {

		}

		return listaDatos;
	}

	//Añade el número de vistorias

	public ArrayList<Datosfichero> comprobarDatos(ArrayList<Datosfichero> listaDatos, String docGanador) {

		boolean existeGan = false;

		//    por cada elemento del array si el nombre se repite le añadimos uno a las victorias

		for(Datosfichero datos: listaDatos) {

			if(datos.getNombre().equals(docGanador)) {
				datos.setVict(datos.getVict()+1);
				existeGan = true;
			}
		}

		//     sino se repite le ponemos 1 victoria

		if(existeGan == false) {

			listaDatos.add(new Datosfichero(docGanador, 1));
		}

		return listaDatos;
	}


	public void escribirFichero(ArrayList<Datosfichero> listaDatos, String ruta) {

		try {

			FileWriter fw = new FileWriter(ruta, false);
			BufferedWriter bw = new BufferedWriter(fw);

			for(int i = 0; i < listaDatos.size(); i++ ) {

				//        escribimos el contenido del array

				bw.write(listaDatos.get(i).getNombre() + " --> " + listaDatos.get(i).getVict() );
				bw.newLine();
			}


			bw.close();
			fw.close();

		}catch(IOException e) {

			System.out.println("ERROR. Ruta no válida.");
		}
	}





	public void actionPerformed(ActionEvent e) {

		sonidoBoton();

		if (C01_Principal.pokemon.size()==1) {

			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C12_Ganador2());
			evento1.setVisible(true);


		}
		else if (C01_Principal.pokemon.size()<1) {

			JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
			evento1.remove(this);
			evento1.getContentPane().add(new C13_Final());
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





}
