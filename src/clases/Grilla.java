package clases;

import java.util.Collection;
import java.util.LinkedList;

import javafx.scene.shape.Shape;

public class Grilla {
	
	private Collection <Cuadrante> coleccionDeCuadrantes;
	private Collection <Shape> coleccionDeRectangulos;
	private float posicionX;
	private float posicionY;
	private float longitudNormal;
	private float longitudXUltimaColumna;
	private float longitudYUltimaFila;
	private Recinto recinto;
	
	//Constructor de la clase Grilla, crea una grilla especificando el tamaño de cada unidad, require conocer el recinto para posicionarse
	public Grilla(Recinto recinto, float tamanio){	
		this.coleccionDeCuadrantes = new LinkedList<Cuadrante>();
		this.coleccionDeRectangulos = new LinkedList<Shape>();
		this.posicionX = recinto.getPosicionX();
		this.posicionY = recinto.getPosicionY();
		this.longitudNormal = tamanio;
		this.recinto = recinto;
		
		this.crearGrilla();
	}
	
	private void crearGrilla(){
		this.prepararValoresGrilla();
		int filas = (int) Math.ceil( this.recinto.getAlto() / this.longitudNormal);
		int columnas = (int) Math.ceil(this.recinto.getAncho() / this.longitudNormal);
		float tamanioX;
		float tamanioY;
		
		for (int fila=0; fila < filas ;fila++){
			//System.out.println("Fila Numero: " + fila);
			for (int columna=0;columna < columnas;columna++){
				//System.out.println("Columna Numero: " + columna);
				
				if(fila +1 == filas){
					tamanioY = this.longitudYUltimaFila;
				}
				else{
					tamanioY = this.longitudNormal;
				}
				
				if(columna +1 == columnas){
					tamanioX = this.longitudXUltimaColumna;
				}
				else{
					tamanioX = this.longitudNormal;
				}
				//System.out.println("TamanioX: " + tamanioX);
				//System.out.println("TamanioY: " + tamanioY);
				//To Fix ancho/alto
				Cuadrante cuadrante = new Cuadrante((posicionX+(int)fila*longitudNormal),(posicionY+(int)columna*longitudNormal),tamanioY,tamanioX);	
				coleccionDeCuadrantes.add(cuadrante);
				
			}
		}
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
	
	private void prepararValoresGrilla(){
		float ratioX = this.recinto.getAncho()/this.longitudNormal;
		float ratioY = this.recinto.getAlto()/this.longitudNormal;
		
		if(ratioX % 1 == 0){
			this.longitudXUltimaColumna = this.longitudNormal;
		}
		else {
			this.longitudXUltimaColumna = this.recinto.getAncho() - (this.longitudNormal * (int) ratioX);
		}
		
		if(ratioY % 1 == 0){
			this.longitudYUltimaFila = this.longitudNormal;
		}
		else {
			this.longitudYUltimaFila = this.recinto.getAlto() - (this.longitudNormal * (int) ratioY);
		}
	}
	
}
