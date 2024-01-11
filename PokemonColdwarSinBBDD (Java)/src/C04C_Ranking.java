import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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


public class C04C_Ranking extends JPanel implements ActionListener {

	//Variables estaticas
    static Clip bgmRanking;
    static Clip seSelect;
    
    //Variables del reglas
    private JButton jbBack;
    private JLabel jlFondo;
    private JTextArea jtRanking;
    private JScrollPane jsRanking;
    private Font sizeFont;
    private File myFont;
    private Font fuente;
    private Font sizeFont2;
    private File myFont2;
    private Font fuente2;

    public C04C_Ranking() {

    	//Creamos variables con la ruta del fichero
        String ganadores = "src/fichero/ganador.txt";

    	//Cerramos menu principal
        C04_MenuPrincipal.bgmMenu.close();

        //Música
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/BGM/rank.wav").getAbsoluteFile());
            bgmRanking = AudioSystem.getClip();
            bgmRanking.open(audioInputStream);
            bgmRanking.loop(9999);


        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }

        //Fuente de letra
        String fontName = "src/resources/Fonts/FontRank3.ttf" ;
        myFont = new File(fontName);

        try{

            fuente = Font.createFont(Font.TRUETYPE_FONT,myFont);
            sizeFont = fuente.deriveFont(24f);

        }catch(FontFormatException ex){

            System.err.println("Error estableciendo fuente tipográfica");

        }catch (IOException ex) {

            System.err.println("Error I/O");
        }
        
      //Fuente de letra
        String fontName2 = "src/resources/Fonts/FontRank3.ttf" ;
        myFont2 = new File(fontName2);

        try{

            fuente2 = Font.createFont(Font.TRUETYPE_FONT,myFont);
            sizeFont2 = fuente2.deriveFont(30f);

        }catch(FontFormatException ex){

            System.err.println("Error estableciendo fuente tipográfica");

        }catch (IOException ex) {

            System.err.println("Error I/O");
        }


        setLayout(null);
        
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
        
        //Variables donde guardamos el txt
        String s = "";
        //Función para mostrar ganadores
        s = mostrarGanadores(ganadores, s);

        //TextArea "Ranking Equipos"
        jtRanking = new JTextArea();
        jtRanking.setBounds(150, 120, 440, 50);
        jtRanking.setFont(sizeFont2);
        jtRanking.setEditable(false);
        jtRanking.setForeground(Color.WHITE);
        jtRanking.setOpaque(false);
        jtRanking.setText("      RANKING DE EQUIPOS");
        add(jtRanking);    
        
        //TextArea Lista ganadores
        jtRanking = new JTextArea();
        jtRanking.setBounds(150, 170, 395, 494);
        jtRanking.setFont(sizeFont);
        jtRanking.setEditable(false);
        jtRanking.setForeground(Color.WHITE);
        jtRanking.setOpaque(false);
        jtRanking.setText(s);
        jtRanking.setCaretPosition(0);
        add(jtRanking);  
        
        //ScrollPane para lista ganadores
        jsRanking = new JScrollPane(jtRanking);
        jsRanking.setBounds(150, 170, 395, 494);
        jsRanking.setOpaque(false);
        jsRanking.getViewport().setOpaque(false);
        jsRanking.setBorder(null);
        jsRanking.setFont(sizeFont);
        Dimension tamanhoTextArea = jtRanking.getSize();
        Point p = new Point( 0, tamanhoTextArea.height);
        jsRanking.getViewport().setViewPosition(p);
        add(jsRanking);

        //Fondo del menu ranking
        jlFondo = new JLabel();
        jlFondo.setBounds(0,0,1024,768);
        jlFondo.setIcon(new ImageIcon("src/resources/fondos/fondo_ranking.png"));
        add(jlFondo);





    }

    //Muestra fichero TXT ganadores
    public String mostrarGanadores(String ganadores, String mostrarFichero) {

        String s= "";

        try {
            FileReader fr = new FileReader(ganadores);
            BufferedReader br = new BufferedReader(fr);

            //IMPRIMIMOS EL TXT SIEMPRE QUE CONTENGA ALGO DENTRO
            do {
                mostrarFichero = br.readLine();

                if (mostrarFichero != null) {

                    s += mostrarFichero + "\n";
                }

            } while(mostrarFichero != null);

            fr.close();
            br.close();

        }catch(IOException e){

            System.out.println("No existe ningún fichero.");
        }

        return s;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        sonidoBoton();
        //Boton vuelve al MenuPrincipal
        if (e.getSource().equals(jbBack)) {
            JFrame evento1 = (JFrame)SwingUtilities.getWindowAncestor(this);
            evento1.remove(this);
            evento1.getContentPane().add(new C04_MenuPrincipal());
            evento1.setVisible(true);
            bgmRanking.close();
        }
    }
    
    //Funcion de sonido para botones
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