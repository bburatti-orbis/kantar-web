package cl.signosti.kantar.consistencia.modelo;

import java.util.Date;

public class TareasEjecuciones {
	
	private Integer id;
	
	//t.setProyecto_ejecucion_id(rs.getInt("proyecto_ejecucion_id"));
	private int proyecto_ejecucion_id;
	//t.setTareas_id(rs.getInt("Tareas_id"));
	private int tareas_id;
	//t.setTiempo(rs.getString("tiempo"));
	private String tiempo;
	//t.setIdconjunto(rs.getInt("idconjunto"));
	private int idconjunto;
	//t.setTipo_calendario(rs.getInt("tipo_calendario"));
	private int tipo_calendario;
	//t.setEstados_ejecucion_id(rs.getInt("estados_ejecucion_id"));
	private int estados_ejecucion_id;
	//t.setFrecuencia(rs.getInt("frecuencia"));
	private int frecuencia;
	//t.setInicio_estimado(rs.getDate("inicio_estimado"));
	private Date inicio_estimado;
	//t.setFin_estimado(rs.getDate("fin_estimado"));
	private Date fin_estimado;
	//t.setInicio_real(rs.getDate("inicio_real"));
	private Date inicio_real;
//	t.setFin_real(rs.getDate("fin_real"));
	private Date fin_real;
//	t.setVisible(rs.getInt("visible"));
	private int visible;
//	t.setDesviacion(rs.getDouble("desviacion"));
	private double desviacion;
//	t.setDesviacion_inicio(rs.getDouble("desviacion_inicio"));
	private double desviacion_inicio;
//	t.setDesviacion_fin(rs.getDouble("desviacion_fin"));
	private double desviacion_fin; 
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getProyecto_ejecucion_id() {
		return proyecto_ejecucion_id;
	}

	public void setProyecto_ejecucion_id(int proyecto_ejecucion_id) {
		this.proyecto_ejecucion_id = proyecto_ejecucion_id;
	}

	public int getTareas_id() {
		return tareas_id;
	}

	public void setTareas_id(int tareas_id) {
		this.tareas_id = tareas_id;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public int getIdconjunto() {
		return idconjunto;
	}

	public void setIdconjunto(int idconjunto) {
		this.idconjunto = idconjunto;
	}

	public int getTipo_calendario() {
		return tipo_calendario;
	}

	public void setTipo_calendario(int tipo_calendario) {
		this.tipo_calendario = tipo_calendario;
	}

	public int getEstados_ejecucion_id() {
		return estados_ejecucion_id;
	}

	public void setEstados_ejecucion_id(int estados_ejecucion_id) {
		this.estados_ejecucion_id = estados_ejecucion_id;
	}

	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}

	public Date getInicio_estimado() {
		return inicio_estimado;
	}

	public void setInicio_estimado(Date inicio_estimado) {
		this.inicio_estimado = inicio_estimado;
	}

	public Date getFin_estimado() {
		return fin_estimado;
	}

	public void setFin_estimado(Date fin_estimado) {
		this.fin_estimado = fin_estimado;
	}

	public Date getInicio_real() {
		return inicio_real;
	}

	public void setInicio_real(Date inicio_real) {
		this.inicio_real = inicio_real;
	}

	public Date getFin_real() {
		return fin_real;
	}

	public void setFin_real(Date fin_real) {
		this.fin_real = fin_real;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public double getDesviacion() {
		return desviacion;
	}

	public void setDesviacion(double desviacion) {
		this.desviacion = desviacion;
	}

	public double getDesviacion_inicio() {
		return desviacion_inicio;
	}

	public void setDesviacion_inicio(double desviacion_inicio) {
		this.desviacion_inicio = desviacion_inicio;
	}

	public double getDesviacion_fin() {
		return desviacion_fin;
	}

	public void setDesviacion_fin(double desviacion_fin) {
		this.desviacion_fin = desviacion_fin;
	}

}
