package cl.signosti.kantar.consistencia.modelo;

public class PerformanceAreas {
	
	private int areaId;
	private String areaGlosa;
	private int estadoId;
	private String estadoGlosa;
	private int total;
	
	public PerformanceAreas() {
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAreaGlosa() {
		return areaGlosa;
	}
	public void setAreaGlosa(String areaGlosa) {
		this.areaGlosa = areaGlosa;
	}
	public int getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(int estadoId) {
		this.estadoId = estadoId;
	}
	public String getEstadoGlosa() {
		return estadoGlosa;
	}
	public void setEstadoGlosa(String estadoGlosa) {
		this.estadoGlosa = estadoGlosa;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
