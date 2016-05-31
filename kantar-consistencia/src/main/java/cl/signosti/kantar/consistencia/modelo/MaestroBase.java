package cl.signosti.kantar.consistencia.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



public class MaestroBase implements Serializable{
	private static final long serialVersionUID = 1L;
	private java.lang.Integer id;
	private java.lang.String glosa;   public static final int GLOSA_DATALENGTH = 45;
	private java.lang.String categoria;   public static final int CATEGORIA_DATALENGTH = 125;
	private java.lang.Integer idcliente;
	private java.lang.Integer idencargado;
	private java.lang.Integer idejecutivo;
	private java.lang.Integer idpais;
	private java.sql.Timestamp createdAt;
	private java.sql.Timestamp updatedAt;

	private List<Basesm> lBases = new ArrayList<Basesm>();

	private Paisesm pais;

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.Integer getId() {
		return id;
	}
	public void setGlosa(java.lang.String glosa) throws Exception {
		if(glosa.length() > this.getGlosaDataLength()){
			throw new Exception("Largo del atributo 'glosa' ("+glosa.length()+") no puede superar "+this.getGlosaDataLength()+" caracteres");
		}
		this.glosa = glosa;
	}
	public java.lang.String getGlosa() {
		return glosa;
	}
	public int getGlosaDataLength(){
		return GLOSA_DATALENGTH;
	}
	public void setCategoria(java.lang.String categoria) throws Exception {
		if(categoria.length() > this.getCategoriaDataLength()){
			throw new Exception("Largo del atributo 'categoria' ("+categoria.length()+") no puede superar "+this.getCategoriaDataLength()+" caracteres");
		}
		this.categoria = categoria;
	}
	public java.lang.String getCategoria() {
		return categoria;
	}
	public int getCategoriaDataLength(){
		return CATEGORIA_DATALENGTH;
	}
	public void setIdcliente(java.lang.Integer idcliente) {
		this.idcliente = idcliente;
	}
	public java.lang.Integer getIdcliente() {
		return idcliente;
	}
	public void setIdencargado(java.lang.Integer idencargado) {
		this.idencargado = idencargado;
	}
	public java.lang.Integer getIdencargado() {
		return idencargado;
	}
	public void setIdejecutivo(java.lang.Integer idejecutivo) {
		this.idejecutivo = idejecutivo;
	}
	public java.lang.Integer getIdejecutivo() {
		return idejecutivo;
	}
	public void setIdpais(java.lang.Integer idpais) {
		this.idpais = idpais;
	}
	public java.lang.Integer getIdpais() {
		return idpais;
	}
	public void setCreatedAt(java.sql.Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public void setCreatedAt(java.lang.String createdAt) throws ParseException {
		this.createdAt = new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(createdAt).getTime());
	}
	public void setCreatedAt(java.util.Date createdAt) {
		this.createdAt = new java.sql.Timestamp(createdAt.getTime());
	}
	public java.sql.Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setUpdatedAt(java.sql.Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setUpdatedAt(java.lang.String updatedAt) throws ParseException {
		this.updatedAt = new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(updatedAt).getTime());
	}
	public void setUpdatedAt(java.util.Date updatedAt) {
		this.updatedAt = new java.sql.Timestamp(updatedAt.getTime());
	}
	public java.sql.Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void addLBases(Basesm bases) {
		this.lBases.add(bases);
	}
	public void setLBases(List<Basesm> bases) {
		this.lBases = bases;
	}
	public List<Basesm> getLBases() {
		return lBases;
	}

	public void setPais(Paisesm pais){
		this.pais = pais;
	}
	public Paisesm getPais() {
		return pais;
	}
}
