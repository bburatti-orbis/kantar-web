package cl.signosti.kantar.consistencia.modelo;

import java.util.Map;

public class ResultadoGeneral {
	private Map<Integer, ResutadoGeneralm> lista;
	public Map<Integer, ResutadoGeneralm> getLista() {
		return lista;
	}
	public void setLista(Map<Integer, ResutadoGeneralm> lista) {
		this.lista = lista;
	}
	public int getInc() {
		return inc;
	}
	public void setInc(int inc) {
		this.inc = inc;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	private int inc;
	private int limit;

}
