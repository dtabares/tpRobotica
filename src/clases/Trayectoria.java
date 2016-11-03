package clases;

import java.util.LinkedList;
import java.util.List;

public class Trayectoria {
	
	private List<Paso> pasos;
	
	public Trayectoria (){
		this.pasos = new LinkedList<Paso>();
	}
	
	public void agregarPaso(double direccion, int cantidadDeBaldozas){
		this.pasos.add(new Paso(direccion, cantidadDeBaldozas));
	}
	
	public List<Paso> getListaDePasos(){
		return this.pasos;
	}

}
