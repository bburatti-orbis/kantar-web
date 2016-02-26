package cl.signosti.kantar.consistencia.modelo;

import java.sql.Date;

public class ReportesRevisarProyecto {
	
	private String nombreProyecto;
	private String nombreTarea;
	private String nombreArea;
	private String nombreResponsable;
	private String estado;
	private String inicioEstimada;
	private String inicioReal;
	private String terminoEstimada;
	private int hrsEstimadas;
	private int hrsDedicadas;
	private String atrasada;
	private int hrsAtrasado;
	private String comentario;
	
	public ReportesRevisarProyecto() {
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getNombreTarea() {
		return nombreTarea;
	}

	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public String getNombreResponsable() {
		return nombreResponsable;
	}

	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getInicioEstimada() {
		return inicioEstimada;
	}

	public void setInicioEstimada(String inicioEstimada) {
		this.inicioEstimada = inicioEstimada;
	}

	public String getInicioReal() {
		return inicioReal;
	}

	public void setInicioReal(String inicioReal) {
		this.inicioReal = inicioReal;
	}

	public String getTerminoEstimada() {
		return terminoEstimada;
	}

	public void setTerminoEstimada(String terminoEstimada) {
		this.terminoEstimada = terminoEstimada;
	}

	public int getHrsEstimadas() {
		return hrsEstimadas;
	}

	public void setHrsEstimadas(int hrsEstimadas) {
		this.hrsEstimadas = hrsEstimadas;
	}

	public int getHrsDedicadas() {
		return hrsDedicadas;
	}

	public void setHrsDedicadas(int hrsDedicadas) {
		this.hrsDedicadas = hrsDedicadas;
	}

	public String getAtrasada() {
		return atrasada;
	}

	public void setAtrasada(String atrasada) {
		this.atrasada = atrasada;
	}

	public int getHrsAtrasado() {
		return hrsAtrasado;
	}

	public void setHrsAtrasado(int hrsAtrasado) {
		this.hrsAtrasado = hrsAtrasado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
