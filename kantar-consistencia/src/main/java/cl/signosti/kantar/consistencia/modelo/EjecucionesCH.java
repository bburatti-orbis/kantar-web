package cl.signosti.kantar.consistencia.modelo;

import java.util.Map;

public class EjecucionesCH {
	String pais;
	String base;
	int ejecucion;
	String periodo;
	Map<Integer, Nomenclaturam> nomenclatura;
	String ruta;

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public int getEjecucion() {
		return ejecucion;
	}

	public void setEjecucion(int ejecucion) {
		this.ejecucion = ejecucion;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Map<Integer, Nomenclaturam> getNomenclatura() {
		return nomenclatura;
	}

	public void setNomenclatura(Map<Integer, Nomenclaturam> nomenclatura) {
		this.nomenclatura = nomenclatura;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	
}
