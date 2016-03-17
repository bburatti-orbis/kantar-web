package cl.signosti.kantar.consistencia.modelo;

public class Resultado {
	private String _rslt;
	private Integer _codError = 0;
	private String _mensaje;
	
	public Resultado(String _rslt, String _mensaje){
		this._rslt = _rslt;
		this._mensaje = _mensaje;
		if("error".equalsIgnoreCase(_rslt)){
			this._codError = 100;
		}
	}
	
	public Resultado(int _codError){
		this._codError = _codError;
		if (_codError != 0){
			this._rslt = "ERROR";
			this._mensaje = "Error interno";
		} else {
			this._rslt = "OK";
			this._mensaje = "Proceso Valido";
		}
	}
	
	public Resultado(int _codError, String _mensaje){
		this._codError = _codError;
		if (_codError != 0){
			this._rslt = "ERROR";
		} else {
			this._rslt = "OK";
		}
		this._mensaje = _mensaje;
	}
	
	public Resultado(String _mensaje){
		this._codError = 0;
		this._rslt = "OK";
		this._mensaje = _mensaje;
	}
	
	public Resultado(){
		this._rslt = "OK";
		this._mensaje = "Proceso Valido";
		this._codError = 0;
	}
	
	public String get_rslt() {
		return _rslt;
	}
	public void set_rslt(String _rslt) {
		this._rslt = _rslt;
	}
	public String get_mensaje() {
		return _mensaje;
	}
	public void set_mensaje(String _mensaje) {
		this._mensaje = _mensaje;
	}
	public Integer get_codError() {
		return _codError;
	}
	public void set_codError(Integer _codError) {
		this._codError = _codError;
	}
	
}
