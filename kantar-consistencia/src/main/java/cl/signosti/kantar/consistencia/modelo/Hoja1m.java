package cl.signosti.kantar.consistencia.modelo;

public class Hoja1m {

	int id;
	String fecha_ini;
	String fecha_ter;
	int canbases;
	int basesOK;
	int basesNOK;

	public String getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_ini(String fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public String getFecha_ter() {
		return fecha_ter;
	}

	public void setFecha_ter(String fecha_ter) {
		this.fecha_ter = fecha_ter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCanbases() {
		return canbases;
	}

	public void setCanbases(int canbases) {
		this.canbases = canbases;
	}

	public int getBasesOK() {
		return basesOK;
	}

	public void setBasesOK(int basesOK) {
		this.basesOK = basesOK;
	}

	public int getBasesNOK() {
		return basesNOK;
	}

	public void setBasesNOK(int basesNOK) {
		this.basesNOK = basesNOK;
	}

}
