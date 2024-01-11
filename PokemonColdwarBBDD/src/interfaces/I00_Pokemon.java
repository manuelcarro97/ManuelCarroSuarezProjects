package interfaces;

import java.util.ArrayList;

public abstract class I00_Pokemon {

	// ATRIBUTOS
	protected int numEquipos;
	protected int misilesRonda;
	protected int vidas;
	protected String nombreEquipo;
	protected int tipoPlaneta;
	protected int victorias;

	// CONTRUCTORES

	// Constructor Combate
	public I00_Pokemon(String nombreEquipo, int vidas, int misilesRonda, int tipoPlaneta) {

		this.misilesRonda = misilesRonda;
		this.vidas = vidas;
		this.nombreEquipo = nombreEquipo;
		this.tipoPlaneta = tipoPlaneta;

	}

	// METODO GET

	public int getVidas() {
		return vidas;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public int getMisilesRonda() {
		return misilesRonda;
	}

	public int getNumEquipos() {
		return numEquipos;
	}

	public int getTipoPlaneta() {
		return tipoPlaneta;

	}

	// METODO SET

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public void setMisilesRonda(int misilesRonda) {
		this.misilesRonda = misilesRonda;
	}

	public void setNumEquipos(int numEquipos) {
		this.numEquipos = numEquipos;
	}

	public void setTipoPlaneta(int tipoPlaneta) {
		this.tipoPlaneta = tipoPlaneta;

	}

	// METODO DONDE SE REALIZARÁN TODOS LOS CALUCLOS EN LOS COMBATES
	public abstract int combate (ArrayList<I00_Pokemon> planets , int i , int numMisiles , boolean accion, int turno);

}
