package cl.signosti.kantar.consistencia.modelo;

import java.util.Date;

public class Bitacora {

	private int idTarea;
	private int idBitacora;
	private int idAccion;
	private String fechaEstimadaFinTareaS;
	private Date fechaEstimadaFinTareaD;
	private int horas;
	private int minutos;
	private double horasDedicadas;
	private String comentarios;
	private Date fechaRegistro;
	private String fechaRegistroS;
	private int idUsuario;
	private String actor;
	private String accion;
	private int cl;
	private int avance;
	
	public int getCl() {
		return cl;
	}
	public void setCl(int cl) {
		this.cl = cl;
	}
	public int getAvance() {
		return avance;
	}
	public void setAvance(int avance) {
		this.avance = avance;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getFechaRegistroS() {
		return fechaRegistroS;
	}
	public void setFechaRegistroS(String fechaRegistroS) {
		this.fechaRegistroS = fechaRegistroS;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public int getIdAccion() {
		return idAccion;
	}
	public void setIdAccion(int idAccion) {
		this.idAccion = idAccion;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public double getHorasDedicadas() {
		return horasDedicadas;
	}
	public void setHorasDedicadas(double horasDedicadas) {
		this.horasDedicadas = horasDedicadas;
	}
	public int getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}
	public int getIdBitacora() {
		return idBitacora;
	}
	public void setIdBitacora(int idBitacora) {
		this.idBitacora = idBitacora;
	}
	public String getFechaEstimadaFinTareaS() {
		return fechaEstimadaFinTareaS;
	}
	public void setFechaEstimadaFinTareaS(String fechaEstimadaFinTareaS) {
		this.fechaEstimadaFinTareaS = fechaEstimadaFinTareaS;
	}
	public Date getFechaEstimadaFinTareaD() {
		return fechaEstimadaFinTareaD;
	}
	public void setFechaEstimadaFinTareaD(Date fechaEstimadaFinTareaD) {
		this.fechaEstimadaFinTareaD = fechaEstimadaFinTareaD;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public int getMinutos() {
		return minutos;
	}
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}