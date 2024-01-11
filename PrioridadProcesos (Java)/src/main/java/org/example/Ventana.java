package org.example;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

        public Ventana() {
            //Nombre de ventana
             setTitle("Carrera de Hilos");
            // Tamaño de ventana
             setBounds(600, 550, 600, 550);
            // No puedes modifcar tamaño ventana
             setResizable(false);
            // Cerramos ventana y se detienen todos los procesos
             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Creamos ventana inicio
             App e = new App() ;
             e.setLayout(null);
            // Establecer layout nulo para App // Agregamos el panel App al centro del marco
             add(e, BorderLayout.CENTER);
             setVisible(true);

        }

    }
