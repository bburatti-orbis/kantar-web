package cl.signosti.kantar.consistencia.modelo;

public class Marcam {
	int id;
	String glosa;
	int nomenclatura;
	String marcaanterior;
	int linea;
	int valor;
	int estadoCinterna;
	int sumaHijos;

	int nivel;

	public int getSumaHijos() {
		return sumaHijos;
	}

	public void setSumaHijos(int sumaHijos) {
		this.sumaHijos = sumaHijos;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
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

	public int getNomenclatura() {
		return nomenclatura;
	}

	public void setNomenclatura(int nomenclatura) {
		this.nomenclatura = nomenclatura;
	}

	public String getMarcaanterior() {
		return marcaanterior;
	}

	public void setMarcaanterior(String marcaanterior) {
		this.marcaanterior = marcaanterior;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getEstadoCinterna() {
		return estadoCinterna;
	}

	public void setEstadoCinterna(int estadoCinterna) {
		this.estadoCinterna = estadoCinterna;
	}

}
