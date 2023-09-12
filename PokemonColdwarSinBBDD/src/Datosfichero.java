

public class Datosfichero {
	
//	atributos
	
	private String nombre;
	private Integer vict;
	
//	contructor 
	
	public Datosfichero(String nombre, Integer vict) {
	
		this.nombre = nombre;
		this.vict = vict;
	}

//	get 

	public String getNombre() {
		return nombre;
	}

	public Integer getVict() {
		return vict;
	}

//	set

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setVict(Integer vict) {
		this.vict = vict;
	}
}


