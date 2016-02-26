package cl.signosti.kantar.consistencia.modelo;

public class StoreProcedure {

	public int id;
	public String nombre;
	public int kpiParametro;

	public StoreProcedure() {
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

	public int getKpiParametro() {
		return kpiParametro;
	}

	public void setKpiParametro(int kpiParametro) {
		this.kpiParametro = kpiParametro;
	}
}
