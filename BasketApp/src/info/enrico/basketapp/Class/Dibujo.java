package info.enrico.basketapp.Class;

public class Dibujo {

	private int idDibujo;
	private String nombreDibujo;
	
	public Dibujo(){}
	
	public Dibujo(int idDibujo, String nombreDibujo){
		this.idDibujo = idDibujo;
		this.nombreDibujo = nombreDibujo;
	}

	public int getIdDibujo() {
		return idDibujo;
	}

	public void setIdDibujo(int idDibujo) {
		this.idDibujo = idDibujo;
	}

	public String getNombreDibujo() {
		return nombreDibujo;
	}

	public void setNombreDibujo(String nombreDibujo) {
		this.nombreDibujo = nombreDibujo;
	}

	@Override
	public String toString() {
		return "Dibujo [idDibujo=" + idDibujo + " / nombreDibujo=" + nombreDibujo + "]";
	}	
}
