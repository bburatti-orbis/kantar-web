package cl.signosti.kantar.consistencia.modelo;

public class ConexionesM {

	public String pais;
	public String ip;
	public String puerto;
	public String nombreDb;
	public String user;
	public String pass;
	
	public ConexionesM() {
	}
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPuerto() {
		return puerto;
	}
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}
	public String getNombreDb() {
		return nombreDb;
	}
	public void setNombreDb(String nombreDb) {
		this.nombreDb = nombreDb;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
