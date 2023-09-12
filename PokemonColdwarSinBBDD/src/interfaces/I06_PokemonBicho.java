package interfaces;

import java.util.ArrayList;

public class I06_PokemonBicho extends I00_Pokemon {

	// contructor que hereda de la clase Planeta

	public I06_PokemonBicho(String nombreEquipo, int tipoPlaneta) {

		super(nombreEquipo, 100, 50, tipoPlaneta);

	}

	public int combate (ArrayList<I00_Pokemon> pokemon , int i , int numMisiles , boolean accion, int turno) {

		int esquivados=0;


		//SI EL EQUIPO SELECCIONA DEFENDER A OTRO EQUIPO
		if (!accion) {

			if (pokemon.get(i).getTipoPlaneta()==6) {

				for ( int j = 0 ; j <numMisiles ; j++) {

					int random = (int)Math.floor(Math.random() * (1 - 0 + 1) + 0);

					if (random == 1) {

						esquivados++;
					}	   
				}


				//AUTODEFENDERSE SE SUMA LA MITAD DE MISILES USADOS QUE NO SE ESQUIVARON
				pokemon.get(i).setVidas(pokemon.get(i).getVidas()+((numMisiles-esquivados)/2));

			}
			
			else {
				
				pokemon.get(i).setVidas(pokemon.get(i).getVidas()+(numMisiles/2));
				
			}
			
			pokemon.get(turno).setMisilesRonda(pokemon.get(turno).getMisilesRonda()-numMisiles);
			
			
		}
		
		//SI EL EQUIPO SELECCIONA ATACAR
		else if  (accion) {


			if (pokemon.get(i).getTipoPlaneta()==6) {

				for ( int j = 0 ; j <numMisiles ; j++) {

					int random = (int)Math.floor(Math.random() * (1 - 0 + 1) + 0);

					if (random == 1) {

						esquivados++;
					}	

					pokemon.get(i).setVidas(pokemon.get(i).getVidas()-(numMisiles-esquivados));
				}
			}

			else {

				pokemon.get(i).setVidas(pokemon.get(i).getVidas()-numMisiles);
			}


			pokemon.get(turno).setMisilesRonda(pokemon.get(turno).getMisilesRonda()-numMisiles);

		}




		return esquivados;

	}

}
