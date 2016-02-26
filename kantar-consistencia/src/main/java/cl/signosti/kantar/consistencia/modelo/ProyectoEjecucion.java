package cl.signosti.kantar.consistencia.modelo;

import java.util.Date;

public class ProyectoEjecucion {	
	
	private int idProyecto;
	private String mes;
	private int ano;
	private String fechaInicioProduccionS;
	private String fechaFinProduccionS;
	private Date fechaInicioProduccion;
	private Date fechaFinProduccion;
	public int getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getFechaInicioProduccionS() {
		return fechaInicioProduccionS;
	}
	public void setFechaInicioProduccionS(String fechaInicioProduccionS) {
		this.fechaInicioProduccionS = fechaInicioProduccionS;
	}
	public String getFechaFinProduccionS() {
		return fechaFinProduccionS;
	}
	public void setFechaFinProduccionS(String fechaFinProduccionS) {
		this.fechaFinProduccionS = fechaFinProduccionS;
	}
	public Date getFechaInicioProduccion() {
		return fechaInicioProduccion;
	}
	public void setFechaInicioProduccion(Date fechaInicioProduccion) {
		this.fechaInicioProduccion = fechaInicioProduccion;
	}
	public Date getFechaFinProduccion() {
		return fechaFinProduccion;
	}
	public void setFechaFinProduccion(Date fechaFinProduccion) {
		this.fechaFinProduccion = fechaFinProduccion;
	}
	
	
}
