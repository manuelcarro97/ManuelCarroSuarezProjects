package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JPanel implements ActionListener {

    private JButton comenzar;
    private JSlider sl1;
    private JSlider sl2;
    private JSlider sl3;

    private static JProgressBar pb1;
    private static JProgressBar pb2;
    private static JProgressBar pb3;

    private static JTextField caballo1;
    private static JTextField caballo2;
    private static JTextField caballo3;

    private static int primerHiloTerminado = 0;
    private static int hilosTerminados = 0;
    private static boolean mensajeMostrado = false;

    public App() {
        comenzar = new JButton();
        comenzar.setText("comenzar carrera");
        comenzar.setBounds(220, 30, 150, 40);
        add(comenzar);
        comenzar.addActionListener(this);

        sl1 = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        sl1.setBounds(80, 100, 400, 50);
        add(sl1);

        pb1 = new JProgressBar();
        pb1.setBounds(80, 150, 400, 30);
        add(pb1);

        sl2 = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        sl2.setBounds(80, 200, 400, 50);
        add(sl2);
        pb2 = new JProgressBar();
        pb2.setBounds(80, 250, 400, 30);
        add(pb2);

        sl3 = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        sl3.setBounds(80, 300, 400, 50);
        add(sl3);

        pb3 = new JProgressBar();
        pb3.setBounds(80, 350, 400, 30);
        add(pb3);

        caballo1 = new JTextField();
        caballo1.setBounds(100, 400, 50, 30);
        caballo1.setEditable(false);
        add(caballo1);

        caballo2 = new JTextField();
        caballo2.setBounds(250, 400, 50, 30);
        caballo2.setEditable(false);
        add(caballo2);

        caballo3 = new JTextField();
        caballo3.setBounds(400, 400, 50, 30);
        caballo3.setEditable(false);
        add(caballo3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        comenzar.setEnabled(false);



        JOptionPane.showMessageDialog(null,
                "Prioridad del hilo 1: " + sl1.getValue() +
                        "\nPrioridad del hilo 2: " + sl2.getValue() +
                        "\nPrioridad del hilo 3: " + sl3.getValue(),
                "Prioridad hilos", JOptionPane.INFORMATION_MESSAGE);

        hilosTerminados = 0; // Reinicia el contador de hilos terminados
        mensajeMostrado = false; // Reinicia la variable de mensaje mostrado
        primerHiloTerminado = 0; // Reinicia el número del primer hilo terminado
        Proceso hilo1 = new Proceso(10000, new ProgressChangeListener(pb1, comenzar, caballo1, 1));
        Proceso hilo2 = new Proceso(10000, new ProgressChangeListener(pb2, comenzar, caballo2, 2));
        Proceso hilo3 = new Proceso(10000, new ProgressChangeListener(pb3, comenzar, caballo3, 3));

        hilo1.setPriority(sl1.getValue());
        hilo2.setPriority(sl2.getValue());
        hilo3.setPriority(sl3.getValue());

        hilo1.start();
        hilo2.start();
        hilo3.start();


    }




    private static class ProgressChangeListener implements ChangeListener {
        private final JProgressBar progressBar;
        private final JButton comenzarButton;
        private final JTextField caballoTextField;
        private final int numeroHilo;

        public ProgressChangeListener(JProgressBar progressBar, JButton comenzarButton, JTextField caballoTextField, int numeroHilo) {
            this.progressBar = progressBar;
            this.comenzarButton = comenzarButton;
            this.caballoTextField = caballoTextField;
            this.numeroHilo = numeroHilo;
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            int progress = progressBar.getValue() + 1;
            if (progress <= progressBar.getMaximum()) {
                progressBar.setValue(progress);

                // Actualizar el JTextField con el progreso
                caballoTextField.setText(String.valueOf(progress));

                // Verificar si el hilo ha terminado
                if (progress == progressBar.getMaximum() && !mensajeMostrado) {
                    hilosTerminados++;

                    // Verificar si es el primer hilo en terminar
                    if (primerHiloTerminado == 0) {
                        primerHiloTerminado = numeroHilo;
                    }

                    // Verificar si todos los hilos han terminado
                    if (hilosTerminados == 3) {
                        mensajeMostrado = true;
                        // Mostrar el mensaje cuando todos los hilos han terminado
                        JOptionPane.showMessageDialog(null, "Hilo " + primerHiloTerminado + " ha terminado primero", "Resultado de la carrera", JOptionPane.INFORMATION_MESSAGE);

                        // Resetear la carrera después de mostrar el mensaje
                        resetearCarrera();
                    }
                }
            }
        }

        // Método para resetear todas las barras y campos de texto
        private void resetearCarrera() {
            pb1.setValue(0);
            pb2.setValue(0);
            pb3.setValue(0);

            caballo1.setText("");
            caballo2.setText("");
            caballo3.setText("");

            // Habilitar el botón "Comenzar" después de resetear la carrera
            comenzarButton.setEnabled(true);
        }
    }
}
