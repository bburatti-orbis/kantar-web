package cl.signosti.kantar.consistencia.modelo;

import java.util.Map;

public class Nomenclaturam {

	int id;
	String glosa;
	int tipo;
	int estadocinterna;
	int estadochistorica;
	int id_ejecuciones;
	Map<Integer, Marcam> detalle;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getEstadocinterna() {
		return estadocinterna;
	}

	public void setEstadocinterna(int estadocinterna) {
		this.estadocinterna = estadocinterna;
	}

	public int getEstadochistorica() {
		return estadochistorica;
	}

	public void setEstadochistorica(int estadochistorica) {
		this.estadochistorica = estadochistorica;
	}

	public int getId_ejecuciones() {
		return id_ejecuciones;
	}

	public void setId_ejecuciones(int id_ejecuciones) {
		this.id_ejecuciones = id_ejecuciones;
	}

	public Map<Integer, Marcam> getDetalle() {
		return detalle;
	}

	public void setDetalle(Map<Integer, Marcam> detalle) {
		this.detalle = detalle;
	}

}
