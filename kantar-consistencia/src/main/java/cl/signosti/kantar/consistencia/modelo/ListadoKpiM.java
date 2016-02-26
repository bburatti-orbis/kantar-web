package cl.signosti.kantar.consistencia.modelo;

public class ListadoKpiM {
	
	public String nombreKpi;
	public String tipoKpi;
	public String pais;
	public String alcance;
	public String area;
	public String categoria;
	public int id;
	
	public ListadoKpiM() {
	}
	public String getNombreKpi() {
		return nombreKpi;
	}
	public void setNombreKpi(String nombreKpi) {
		this.nombreKpi = nombreKpi;
	}
	public String getTipoKpi() {
		return tipoKpi;
	}
	public void setTipoKpi(String tipoKpi) {
		this.tipoKpi = tipoKpi;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getAlcance() {
		return alcance;
	}
	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
