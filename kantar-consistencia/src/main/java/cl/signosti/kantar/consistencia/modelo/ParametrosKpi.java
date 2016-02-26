package cl.signosti.kantar.consistencia.modelo;

import java.sql.Date;

public class ParametrosKpi {
	
	public int idP;
	public String nombreP;
	public int kpiId;
	public int paramId;
	public int sizeTipo;
	public int orden;
	public String defaultS;
	public Double defaultD;
	public Date defaultT;
	public int defaultI;
	public String tipoDato;
	
	public ParametrosKpi() {
	}

	public int getIdP() {
		return idP;
	}

	public void setIdP(int idP) {
		this.idP = idP;
	}

	public String getNombreP() {
		return nombreP;
	}

	public void setNombreP(String nombreP) {
		this.nombreP = nombreP;
	}

	public int getKpiId() {
		return kpiId;
	}

	public void setKpiId(int kpiId) {
		this.kpiId = kpiId;
	}

	public int getParamId() {
		return paramId;
	}

	public void setParamId(int paramId) {
		this.paramId = paramId;
	}

	public int getSizeTipo() {
		return sizeTipo;
	}

	public void setSizeTipo(int sizeTipo) {
		this.sizeTipo = sizeTipo;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getDefaultS() {
		return defaultS;
	}

	public void setDefaultS(String defaultS) {
		this.defaultS = defaultS;
	}

	public Double getDefaultD() {
		return defaultD;
	}

	public void setDefaultD(Double defaultD) {
		this.defaultD = defaultD;
	}

	public Date getDefaultT() {
		return defaultT;
	}

	public void setDefaultT(Date defaultT) {
		this.defaultT = defaultT;
	}

	public int getDefaultI() {
		return defaultI;
	}

	public void setDefaultI(int defaultI) {
		this.defaultI = defaultI;
	}

	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	
	
}
