package cl.signosti.kantar.consistencia.modelo;

import java.util.Map;

public class ResultadoGeneral {
	private String _rslt;
	private String _mensaje;
	private Map<Integer, ResutadoGeneralm> lista;
	private int inc;
	private int limit;
	private String desde;
	private String hasta;
	
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
	public String getDesde() {
		return desde;
	}
	public void setDesde(String desde) {
		this.desde = desde;
	}
	public String getHasta() {
		return hasta;
	}
	public void setHasta(String hasta) {
		this.hasta = hasta;
	}
	public String get_rslt() {
		return _rslt;
	}
	public void set_rslt(String _rslt) {
		this._rslt = _rslt;
	}
	public String get_mensaje() {
		return _mensaje;
	}
	public void set_mensaje(String _mensaje) {
		this._mensaje = _mensaje;
	}

}
