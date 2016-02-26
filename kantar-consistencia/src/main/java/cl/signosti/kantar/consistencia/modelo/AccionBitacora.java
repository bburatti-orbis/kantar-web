package cl.signosti.kantar.consistencia.modelo;

public class AccionBitacora {
	
	private int idAccion;
	private String nombreAccion;
	private int estadoInicial;
	private int estadoFinal;
	
	public int getIdAccion() {
		return idAccion;
	}
	public void setIdAccion(int idAccion) {
		this.idAccion = idAccion;
	}
	public String getNombreAccion() {
		return nombreAccion;
	}
	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}
	public int getEstadoInicial() {
		return estadoInicial;
	}
	public void setEstadoInicial(int estadoInicial) {
		this.estadoInicial = estadoInicial;
	}
	public int getEstadoFinal() {
		return estadoFinal;
	}
	public void setEstadoFinal(int estadoFinal) {
		this.estadoFinal = estadoFinal;
	}
	
}
