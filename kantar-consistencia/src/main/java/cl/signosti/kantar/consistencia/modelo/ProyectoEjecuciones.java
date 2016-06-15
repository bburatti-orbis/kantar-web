package cl.signosti.kantar.consistencia.modelo;

import java.util.Date;

public class ProyectoEjecuciones {

//	p.setId(rs.getInt("id"));
	private int id;
//	p.setProyecto_id(rs.getInt("Proyecto_id"));
	private int proyecto_id;
//	p.setMes(rs.getString("mes"));
	private String mes;
//	p.setAno(rs.getInt("ano"));
	private int ano;
//	p.setFecha_inicio_produccion(rs.getDate("fecha_inicio_produccion"));
	private Date fecha_inicio_produccion;
//	p.setFecha_fin_produccion(rs.getDate("fecha_fin_produccion"));
	private Date fecha_fin_produccion;
//	p.setEstados_ejecucion_id(rs.getInt("estados_ejecucion_id"));
	private int estados_ejecucion_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProyecto_id() {
		return proyecto_id;
	}
	public void setProyecto_id(int proyecto_id) {
		this.proyecto_id = proyecto_id;
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
	public Date getFecha_inicio_produccion() {
		return fecha_inicio_produccion;
	}
	public void setFecha_inicio_produccion(Date fecha_inicio_produccion) {
		this.fecha_inicio_produccion = fecha_inicio_produccion;
	}
	public Date getFecha_fin_produccion() {
		return fecha_fin_produccion;
	}
	public void setFecha_fin_produccion(Date fecha_fin_produccion) {
		this.fecha_fin_produccion = fecha_fin_produccion;
	}
	public int getEstados_ejecucion_id() {
		return estados_ejecucion_id;
	}
	public void setEstados_ejecucion_id(int estados_ejecucion_id) {
		this.estados_ejecucion_id = estados_ejecucion_id;
	}
	
}
