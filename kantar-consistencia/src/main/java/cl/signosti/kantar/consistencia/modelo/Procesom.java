package cl.signosti.kantar.consistencia.modelo;

public class Procesom {

	String fecha_inicio;
	String messureset;
	int estado;

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getMessureset() {
		return messureset;
	}

	public void setMessureset(String messureset) {
		this.messureset = messureset;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
