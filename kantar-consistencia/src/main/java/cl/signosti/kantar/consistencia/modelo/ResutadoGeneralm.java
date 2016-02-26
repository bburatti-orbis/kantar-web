package cl.signosti.kantar.consistencia.modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResutadoGeneralm {

	String nombre;
	String idBases;
	String glosa;
	String descripcion;
	String cliente;
	String measure;
	String periodo;
	String CI;
	String CH;
	String origen;
	String fecha_ini;
	String fecha_ter;
	String estado;
	String usuario;
	String link;
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getCI() {
		return CI;
	}

	public void setCI(String cI) {
		CI = cI;
	}

	public String getCH() {
		return CH;
	}

	public void setCH(String cH) {
		CH = cH;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getIdBases() {
		return idBases;
	}

	public void setIdBases(String idBases) {
		this.idBases = idBases;
	}

	public String getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_ini(String fecha_ini) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		SimpleDateFormat dfS = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			Date d = df.parse(fecha_ini);
			this.fecha_ini = dfS.format(d);
		} catch (ParseException e) {
			this.fecha_ini = fecha_ini;
		}
	}

	public String getFecha_ter() {
		return fecha_ter;
	}

	public void setFecha_ter(String fecha_ter) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		SimpleDateFormat dfS = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			Date d = df.parse(fecha_ter);
			this.fecha_ter = dfS.format(d);
		} catch (ParseException e) {
			this.fecha_ter = fecha_ter;
		}
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
	

	
}
