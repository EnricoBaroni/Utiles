package info.enrico.basketapp.Class;

import java.util.ArrayList;

public class Entrenamiento {

	private int idEntrenamiento;
	private String nombreEntrenamiento;
	private ArrayList<Categoria> categoriasEntrenamiento;	
	private ArrayList<Dibujo> dibujosEntrenamiento;
	private String explicacionEntrenamiento;
	private String notasEntrenamiento; //Podran ser varios, pero los repartiremos con un split
	
	public Entrenamiento(){}
	
	public Entrenamiento(int idEntrenamiento, String nombreEntrenamiento, ArrayList<Categoria> categoriasEntrenamiento, 
			ArrayList<Dibujo> dibujosEntrenamiento, String explicacionEntrenamiento, String notasEntrenamiento){
		this.idEntrenamiento = idEntrenamiento;
		this.nombreEntrenamiento = nombreEntrenamiento;
		this.categoriasEntrenamiento = categoriasEntrenamiento;		
		this.dibujosEntrenamiento = dibujosEntrenamiento;
		this.explicacionEntrenamiento = explicacionEntrenamiento;
		this.notasEntrenamiento = notasEntrenamiento;
	}

	public int getIdEntrenamiento() {
		return idEntrenamiento;
	}

	public void setIdEntrenamiento(int idEntrenamiento) {
		this.idEntrenamiento = idEntrenamiento;
	}

	public String getNombreEntrenamiento() {
		return nombreEntrenamiento;
	}

	public void setNombreEntrenamiento(String nombreEntrenamiento) {
		this.nombreEntrenamiento = nombreEntrenamiento;
	}

	public ArrayList<Categoria> getCategoriasEntrenamiento() {
		return categoriasEntrenamiento;
	}

	public void setCategoriasEntrenamiento(
			ArrayList<Categoria> categoriasEntrenamiento) {
		this.categoriasEntrenamiento = categoriasEntrenamiento;
	}

	public ArrayList<Dibujo> getDibujosEntrenamiento() {
		return dibujosEntrenamiento;
	}

	public void setDibujosEntrenamiento(ArrayList<Dibujo> dibujosEntrenamiento) {
		this.dibujosEntrenamiento = dibujosEntrenamiento;
	}

	public String getExplicacionEntrenamiento() {
		return explicacionEntrenamiento;
	}

	public void setExplicacionEntrenamiento(String explicacionEntrenamiento) {
		this.explicacionEntrenamiento = explicacionEntrenamiento;
	}

	public String getNotasEntrenamiento() {
		return notasEntrenamiento;
	}

	public void setNotasEntrenamiento(String notasEntrenamiento) {
		this.notasEntrenamiento = notasEntrenamiento;
	}

	@Override
	public String toString() {
		return "Entrenamiento [idEntrenamiento=" + idEntrenamiento
				+ ", nombreEntrenamiento=" + nombreEntrenamiento
				+ ", categoriasEntrenamiento=" + categoriasEntrenamiento
				+ ", dibujosEntrenamiento=" + dibujosEntrenamiento
				+ ", explicacionEntrenamiento=" + explicacionEntrenamiento
				+ ", notasEntrenamiento=" + notasEntrenamiento + "]";
	}
}