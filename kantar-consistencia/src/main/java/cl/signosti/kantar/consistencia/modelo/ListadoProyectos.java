package cl.signosti.kantar.consistencia.modelo;

public class ListadoProyectos {
	
	private String nombre;
	private String fechaInicio;
	private int id;
	
	public ListadoProyectos() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
