package cl.signosti.kantar.consistencia.modelo;

import java.sql.Date;

public class ParametrosKpiM {

	public int id;
	public String descripcion;
	public int kpi;
	public int parametro;
	public int size;
	public int orden;
	public String tipo;
	public String defecto;
	public String defectoS;
	public int defectoI;
	public Date defectoDt;
	public Double defectoD;
	
	public ParametrosKpiM(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getKpi() {
		return kpi;
	}

	public void setKpi(int kpi) {
		this.kpi = kpi;
	}

	public int getParametro() {
		return parametro;
	}

	public void setParametro(int parametro) {
		this.parametro = parametro;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getDefectoS() {
		return defectoS;
	}

	public void setDefectoS(String defectoS) {
		this.defectoS = defectoS;
	}

	public int getDefectoI() {
		return defectoI;
	}

	public void setDefectoI(int defectoI) {
		this.defectoI = defectoI;
	}

	public Date getDefectoDt() {
		return defectoDt;
	}

	public void setDefectoDt(Date defectoDt) {
		this.defectoDt = defectoDt;
	}

	public Double getDefectoD() {
		return defectoD;
	}

	public void setDefectoD(Double defectoD) {
		this.defectoD = defectoD;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDefecto() {
		return defecto;
	}

	public void setDefecto(String defecto) {
		this.defecto = defecto;
	}
}
