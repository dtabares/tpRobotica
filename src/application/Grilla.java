package application;

import java.util.Collection;
import java.util.LinkedList;

import javafx.scene.shape.Shape;

public class Grilla {
	
	private Collection <Cuadrante> coleccionDeCuadrantes;
	private Collection <Shape> coleccionDeRectangulos;
	private float posicionX;
	private float posicionY;
	
	//Constructor de la clase Grilla, crea una grilla especificando el tamaño de cada unidad, require conocer el recinto para posicionarse
	public Grilla(Recinto recinto, int tamanio){
		
		this.coleccionDeCuadrantes = new LinkedList<Cuadrante>();
		this.coleccionDeRectangulos = new LinkedList<Shape>();
		this.posicionX = recinto.getPosicionX();
		this.posicionY = recinto.getPosicionY();
		
		for (int fila=0;fila<(recinto.getAlto()/tamanio);fila++){
		
			for (int columna=0;columna<(recinto.getAncho()/tamanio);columna++){
				
				Cuadrante cuadrante = new Cuadrante((posicionX+(int)fila*tamanio),(posicionY+(int)columna*tamanio),tamanio,tamanio);	
				coleccionDeCuadrantes.add(cuadrante);
				
			}
		}
		
	}
	
	public void mostrar(Mapa mapa){
	
		//mapa.agregarCollectionDeFormas(coleccionDeRectangulos);
		
	}

	public Collection<Cuadrante> getColeccionDeCuadrantes() {
		return coleccionDeCuadrantes;
	}
	
	public Collection<Shape> getColeccionDeRectangulos() {
		return coleccionDeRectangulos;
	}
	
	public void prepararGrillaParaDibujo(){
		this.coleccionDeRectangulos.clear();
		for (Cuadrante cuadrante : this.coleccionDeCuadrantes) {
			this.coleccionDeRectangulos.add(cuadrante.getRectangle());
		}
	}
	
}
