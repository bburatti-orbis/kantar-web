package cl.signosti.kantar.consistencia.modelo;

public class Proyectom {
	
	String id;
	String nombre;
	String calendario;
	String id_paises;
	int id_ciclo;
	
	ciclom ciclo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCalendario() {
		return calendario;
	}
	public void setCalendario(String calendario) {
		this.calendario = calendario;
	}
	public String getId_paises() {
		return id_paises;
	}
	public void setId_paises(String id_paises) {
		this.id_paises = id_paises;
	}
	public int getId_ciclo() {
		return id_ciclo;
	}
	public void setId_ciclo(int id_ciclo) {
		this.id_ciclo = id_ciclo;
	}
	public ciclom getCiclo() {
		return ciclo;
	}
	public void setCiclo(ciclom ciclo) {
		this.ciclo = ciclo;
	}
	
	
	
}
