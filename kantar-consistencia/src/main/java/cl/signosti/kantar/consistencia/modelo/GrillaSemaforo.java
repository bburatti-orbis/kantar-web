package cl.signosti.kantar.consistencia.modelo;

public class GrillaSemaforo {
	
	public int idRango;
	public int idUmbral;
	public String color;
	public int idOper;
	public String oper;
	public int valor;
	
	public GrillaSemaforo(){
		
	}

	public int getIdRango() {
		return idRango;
	}

	public void setIdRango(int idRango) {
		this.idRango = idRango;
	}

	public int getIdUmbral() {
		return idUmbral;
	}

	public void setIdUmbral(int idUmbral) {
		this.idUmbral = idUmbral;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getIdOper() {
		return idOper;
	}

	public void setIdOper(int idOper) {
		this.idOper = idOper;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
}
