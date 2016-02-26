package cl.signosti.kantar.consistencia.modelo;

import java.util.List;

public class Clientem {

	int id;
	String nombre;
	List<Basesm> bases;

	public List<Basesm> getBases() {
		return bases;
	}

	public void setBases(List<Basesm> bases) {
		this.bases = bases;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
