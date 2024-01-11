import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class C02_Ventana extends JFrame {

	//Ventana principal del juego
	public C02_Ventana() {

		//Nombre de ventana
		setTitle("Pokémon Coldwar");

		//Tamaño de ventana
		setBounds(100, 100, 1040, 807);

		//No puedes modifcar tamaño ventana
		setResizable(false);

		//Cerramos ventana y se detienen todos los procesos
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Icono de la aplicación
		setIconImage(new ImageIcon("src/resources/icon2.png").getImage());

		//Creamos pantalla inicio
		C03_PantallaInicio e = new C03_PantallaInicio() ;
		add(e);

		setVisible(true);

	}

}
