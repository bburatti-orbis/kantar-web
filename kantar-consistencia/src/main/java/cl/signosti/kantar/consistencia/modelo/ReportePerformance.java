package cl.signosti.kantar.consistencia.modelo;

public class ReportePerformance {
	
	private String nombreProyecto;
	private	String pais;
	private String area;
	private String nombreEjecutivo;
	private int tareasRealizadas;
	private int tareasTotales;
	private long porcParticipacion;

	public ReportePerformance() {
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getNombreEjecutivo() {
		return nombreEjecutivo;
	}

	public void setNombreEjecutivo(String nombreEjecutivo) {
		this.nombreEjecutivo = nombreEjecutivo;
	}

	public int getTareasRealizadas() {
		return tareasRealizadas;
	}

	public void setTareasRealizadas(int tareasRealizadas) {
		this.tareasRealizadas = tareasRealizadas;
	}

	public int getTareasTotales() {
		return tareasTotales;
	}

	public void setTareasTotales(int tareasTotales) {
		this.tareasTotales = tareasTotales;
	}

	public long getPorcParticipacion() {
		return porcParticipacion;
	}

	public void setPorcParticipacion(long porcParticipacion) {
		this.porcParticipacion = porcParticipacion;
	}
}
