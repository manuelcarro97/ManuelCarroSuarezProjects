package interfaces;

import java.util.ArrayList;

public class I04_PokemonPlanta extends I00_Pokemon{

	//contructor planeta verde que hereda de Planeta 

	public I04_PokemonPlanta(String nombreEquipo, int tipoPlaneta) {

		super(nombreEquipo, 200, 50, tipoPlaneta);

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

				}else {

					pokemon.get(i).setVidas(pokemon.get(i).getVidas() + numMisiles/2);

				}
				
				pokemon.get(turno).setMisilesRonda(pokemon.get(turno).getMisilesRonda()-numMisiles);

				//SI EL EQUIPO SELECCIONA ATACAR
			}else if  (accion) {

				if (pokemon.get(i).getTipoPlaneta()==2) {
					pokemon.get(i).setVidas(pokemon.get(i).getVidas()-numMisiles/2);

				}else if (pokemon.get(i).getTipoPlaneta()==3) {
					pokemon.get(i).setVidas(pokemon.get(i).getVidas()-numMisiles*2);

				}else if (pokemon.get(i).getTipoPlaneta()==6) {

					for ( int j = 0 ; j < numMisiles ; j++) {
						int random = (int)Math.floor(Math.random() * (1 - 0 + 1) + 0);
						if (random==1) {
							pokemon.get(i).setVidas(pokemon.get(i).getVidas()-1);
						} else {
							esquivados++;
						}
					}

				}else {
					pokemon.get(i).setVidas(pokemon.get(i).getVidas()-numMisiles);
				}
				
				pokemon.get(turno).setMisilesRonda(pokemon.get(turno).getMisilesRonda()-numMisiles);
			}

			return esquivados;
		}
	}
