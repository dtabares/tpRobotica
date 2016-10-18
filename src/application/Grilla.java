package application;

import java.util.Collection;
import java.util.LinkedList;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Grilla {
	
	private Collection <Shape> coleccionDeRectangulos;
	private GridPane gridPane;
	private float posicionX;
	private float posicionY;
	
	//Constructor de la clase Grilla, crea una grilla especificando el tamaño de cada unidad, require conocer el recinto para posicionarse
	public Grilla(Recinto recinto, int tamanio){
		
		coleccionDeRectangulos = new LinkedList<Shape>();	
		this.posicionX = recinto.getPosicionX();
		this.posicionY = recinto.getPosicionY();
		
		for (int fila=0;fila<(recinto.getAlto()/tamanio);fila++){
		
			for (int columna=0;columna<(recinto.getAncho()/tamanio);columna++){
				
				Rectangle rectangulo = new Rectangle((posicionX+(int)fila*tamanio),(posicionY+(int)columna*tamanio),tamanio,tamanio);
				rectangulo.setStroke(Color.BLACK);
				rectangulo.setFill(Color.WHITE);
				rectangulo.setStrokeWidth(1);	
				coleccionDeRectangulos.add(rectangulo);
				
			}
		}
		
	}
	
	public void mostrar(Mapa mapa){
	
		//mapa.agregarCollectionDeFormas(coleccionDeRectangulos);
		
	}

	public Collection<Shape> getColleccionDeRectangulos() {
		return coleccionDeRectangulos;
	}
	
}
