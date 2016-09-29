package cl.signosti.kantar.consistencia.modelo;

import java.util.Date;

public class Tareasm {
	int id;
	String nombre;
	int idresponsable;
	int plazo;
	java.lang.Integer tiempo;
	java.lang.Integer[] idantesesora;
	java.lang.Integer  idconjunto;
	int tipo_calendario;
	int proyecto_id;
	int delay;
	int frecuencia;
	java.lang.Integer tiempodelay;
	String tiempofrecuencia;
	private boolean automatica;
	
	private String nombreProyecto;
	private int estadosId;
	private String estadoS;
	private String responsable;
	private String acciones;
	private String area;
	private double horasDedicadas;
	private String inicioEstimado;
	private String inicioReal;
	private String finEstimado;
	private String finReal;
	private double desviacion;
	private double desviacionInicio;
	private double desviacionFin;
	
	private Date inicioEstimadoD;
	private Date finEstimadoD;
	
//	t.setTiempoInt(rs.getInt("tiempo"));
	private int tiempoInt;
//	t.setTiposTareasId(rs.getInt("TiposTareas_id"));
	private int tiposTareasId;
	
	
	
	
	public int getTiempoInt() {
		return tiempoInt;
	}
	public void setTiempoInt(int tiempoInt) {
		this.tiempoInt = tiempoInt;
	}
	public int getTiposTareasId() {
		return tiposTareasId;
	}
	public void setTiposTareasId(int tiposTareasId) {
		this.tiposTareasId = tiposTareasId;
	}
	public Date getInicioEstimadoD() {
		return inicioEstimadoD;
	}
	public void setInicioEstimadoD(Date inicioEstimadoD) {
		this.inicioEstimadoD = inicioEstimadoD;
	}
	public Date getFinEstimadoD() {
		return finEstimadoD;
	}
	public void setFinEstimadoD(Date finEstimadoD) {
		this.finEstimadoD = finEstimadoD;
	}
	public double getDesviacionInicio() {
		return desviacionInicio;
	}
	public void setDesviacionInicio(double desviacionInicio) {
		this.desviacionInicio = desviacionInicio;
	}
	public double getDesviacionFin() {
		return desviacionFin;
	}
	public void setDesviacionFin(double desviacionFin) {
		this.desviacionFin = desviacionFin;
	}
	public double getDesviacion() {
		return desviacion;
	}
	public void setDesviacion(double desviacion) {
		this.desviacion = desviacion;
	}
	public String getFinEstimado() {
		return finEstimado;
	}
	public void setFinEstimado(String finEstimado) {
		this.finEstimado = finEstimado;
	}
	public String getFinReal() {
		return finReal;
	}
	public void setFinReal(String finReal) {
		this.finReal = finReal;
	}
	public String getInicioReal() {
		return inicioReal;
	}
	public void setInicioReal(String inicioReal) {
		this.inicioReal = inicioReal;
	}
	public String getInicioEstimado() {
		return inicioEstimado;
	}
	public void setInicioEstimado(String inicioEstimado) {
		this.inicioEstimado = inicioEstimado;
	}
	public double getHorasDedicadas() {
		return horasDedicadas;
	}
	public void setHorasDedicadas(double horasDedicadas) {
		this.horasDedicadas = horasDedicadas;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAcciones() {
		return acciones;
	}
	public void setAcciones(String acciones) {
		this.acciones = acciones;
	}
	public String getEstadoS() {
		return estadoS;
	}
	public void setEstadoS(String estadoS) {
		this.estadoS = estadoS;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	public int getEstadosId() {
		return estadosId;
	}
	public void setEstadosId(int estadosId) {
		this.estadosId = estadosId;
	}
	String nomencargado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdresponsable() {
		return idresponsable;
	}
	public void setIdresponsable(int idresponsable) {
		this.idresponsable = idresponsable;
	}
	public int getPlazo() {
		return plazo;
	}
	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}
	public java.lang.Integer getTiempo() {
		return tiempo;
	}
	public void setTiempo(java.lang.Integer tiempo) {
		this.tiempo = tiempo;
	}
	public Integer[] getIdantesesora() {
		return idantesesora;
	}
	public void setIdantesesora(Integer[] idantesesora) {
		this.idantesesora = idantesesora;
	}

	public int getTipo_calendario() {
		return tipo_calendario;
	}
	public void setTipo_calendario(int tipo_calendario) {
		this.tipo_calendario = tipo_calendario;
	}
	public int getProyecto_id() {
		return proyecto_id;
	}
	public void setProyecto_id(int proyecto_id) {
		this.proyecto_id = proyecto_id;
	}
	public String getNomencargado() {
		return nomencargado;
	}
	public void setNomencargado(String nomencargado) {
		this.nomencargado = nomencargado;
	}
	public java.lang.Integer getIdconjunto() {
		return idconjunto;
	}
	public void setIdconjunto(java.lang.Integer idconjunto) {
		this.idconjunto = idconjunto;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public int getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}
	public java.lang.Integer getTiempodelay() {
		return tiempodelay;
	}
	public void setTiempodelay(java.lang.Integer tiempodelay) {
		this.tiempodelay = tiempodelay;
	}
	public String getTiempofrecuencia() {
		return tiempofrecuencia;
	}
	public void setTiempofrecuencia(String tiempofrecuencia) {
		this.tiempofrecuencia = tiempofrecuencia;
	}
	public boolean isAutomatica() {
		return automatica;
	}
	public void setAutomatica(boolean automatica) {
		this.automatica = automatica;
	}
	
	
	

}

