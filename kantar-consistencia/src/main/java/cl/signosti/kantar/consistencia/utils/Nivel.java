package cl.signosti.kantar.consistencia.utils;

public class Nivel {
	private int id; // id de la BD
	private int nroNivel; // nivel indicado en archivo
	private int total; // total de sus hermanos
	private int valor; // valor obtenido del archivo
	private int sumaHijos;

	public int getSumaHijos() {
		return sumaHijos;
	}

	public void setSumaHijos(int sumaHijos) {
		this.sumaHijos = sumaHijos;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	private int estado;

	public int getNroNivel() {
		return nroNivel;
	}

	public void setNroNivel(int nroNivel) {
		this.nroNivel = nroNivel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
