package cl.signosti.kantar.consistencia.modelo;

public class Basesm {
	int id;
	String glosa;
	String categoria;
	String fechaultimoproceso;
	int clienteid;
	int paisid;
	String nombreCliente;
	int estado;
	String fechamod;
	int coduser;
	String correo;
	
	private MaestroBase maestroBase = null;

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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getFechaultimoproceso() {
		return fechaultimoproceso;
	}

	public void setFechaultimoproceso(String fechaultimoproceso) {
		this.fechaultimoproceso = fechaultimoproceso;
	}

	public int getClienteid() {
		return clienteid;
	}

	public void setClienteid(int clienteid) {
		this.clienteid = clienteid;
	}

	public int getPaisid() {
		return paisid;
	}

	public void setPaisid(int paisid) {
		this.paisid = paisid;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getFechamod() {
		return fechamod;
	}

	public void setFechamod(String fechamod) {
		this.fechamod = fechamod;
	}

	public int getCoduser() {
		return coduser;
	}

	public void setCoduser(int coduser) {
		this.coduser = coduser;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public MaestroBase getMaestroBase() {
		return maestroBase;
	}

	public void setMaestroBase(MaestroBase maestroBase) {
		this.maestroBase = maestroBase;
	}

}
