package cl.signosti.kantar.consistencia.modelo;

import java.util.List;

public class Paisesm {

	int id;
	String nombre;
	String estado;
	String ruta;
	Integer idSupervisor;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Integer getIdSupervisor() {
		return idSupervisor;
	}

	public void setIdSupervisor(Integer idSupervisor) {
		this.idSupervisor = idSupervisor;
	}

}
