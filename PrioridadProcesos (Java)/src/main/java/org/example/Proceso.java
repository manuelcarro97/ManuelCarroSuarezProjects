package org.example;

import javax.swing.event.ChangeListener;

public class Proceso extends Thread {

                private int tiempoSueño;
                private int prioridad;
                private ChangeListener progressListener;

                public Proceso(int tiempoSueño, ChangeListener progressListener) {
                    this.tiempoSueño = tiempoSueño;

                    this.progressListener = progressListener;
                }

                @Override
                public void run() {



                    try {
                        for (int i = 0; i <= 100; i++) {
                            // Realiza alguna tarea
                            System.out.println("Proceso " + getId() + " en ejecución.");

                            // Calcula el progreso
                            int progress = i;

                            // Notifica el progreso a través del listener
                            if (progressListener != null) {
                                progressListener.stateChanged(null);
                            }

                            // Espera durante tiempoSueño milisegundos
                            Thread.sleep(tiempoSueño / 100);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }






