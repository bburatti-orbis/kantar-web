package cl.signosti.kantar.consistencia.modelo;

public class KpiM {

	public int id;
	public String glosa;
	public String tipo;
	public String nombreSp;
	public int valorTeoricoD;
	public int pais;
	public int estado;
	public int categoria;
	public int alcance;
	public int area;
	
	public KpiM(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombreSp() {
		return nombreSp;
	}

	public void setNombreSp(String nombreSp) {
		this.nombreSp = nombreSp;
	}

	public int getValorTeoricoD() {
		return valorTeoricoD;
	}

	public void setValorTeoricoD(int valorTeoricoD) {
		this.valorTeoricoD = valorTeoricoD;
	}

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getAlcance() {
		return alcance;
	}

	public void setAlcance(int alcance) {
		this.alcance = alcance;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}
	
	
}
