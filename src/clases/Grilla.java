package clases;

import java.util.Collection;
import java.util.LinkedList;
import javafx.scene.shape.Shape;


public class Grilla {
	
	private Cuadrante[][] matrizDeCuadrantes;
	private Collection <Shape> coleccionDeRectangulos;
	private boolean[][] matrizDeAdyacencia;
	private float posicionX;
	private float posicionY;
	private float longitudNormal;
	private float longitudXUltimaColumna;
	private float longitudYUltimaFila;
	private Recinto recinto;
	private int filas;
	private int columnas;
	
	//Constructor de la clase Grilla, crea una grilla especificando el tamaño de cada unidad, require conocer el recinto para posicionarse
	public Grilla(Recinto recinto, float tamanio){
		this.coleccionDeRectangulos = new LinkedList<Shape>();
		//this.matrizDeAdyacencia = new LinkedList<boolean>();
		this.posicionX = recinto.getPosicionX();
		this.posicionY = recinto.getPosicionY();
		this.longitudNormal = tamanio;
		this.recinto = recinto;
		this.filas = (int) Math.ceil( this.recinto.getAlto() / this.longitudNormal);
		this.columnas = (int) Math.ceil(this.recinto.getAncho() / this.longitudNormal);
		matrizDeAdyacencia = new boolean[this.filas*this.columnas][this.filas*this.columnas];
		this.matrizDeCuadrantes = new Cuadrante[this.filas][this.columnas];
		
		this.crearGrilla();
	}
	
	private void crearGrilla(){
		this.prepararValoresGrilla();

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
				Cuadrante cuadrante = new Cuadrante((posicionX+(int)columna*longitudNormal),(posicionY+(int)fila*longitudNormal),tamanioX,tamanioY);
				matrizDeCuadrantes[fila][columna] = cuadrante;
				
			}
		}
	}
	
	public Cuadrante[][] getMatrizDeCuadrantes(){
		return this.matrizDeCuadrantes;
	}
	
	public Collection<Shape> getColeccionDeRectangulos() {
		return coleccionDeRectangulos;
	}
	
	public void prepararGrillaParaDibujo(){
		this.coleccionDeRectangulos.clear();
		for (Cuadrante[] filacuadrante : this.matrizDeCuadrantes) {
			for (Cuadrante cuadrante : filacuadrante) {
				this.coleccionDeRectangulos.add(cuadrante.getRectangle());
			}	
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
	
	public boolean[][] calcularMatrizDeAdyacencia(){

		return this.matrizDeAdyacencia;
	}
	
	private boolean verificarDisponibilidadCuadranteSuperiorIzquierdo(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion superior izquierda, tengo que restar 1 en x (columna) y 1 en y (fila)
		if (posicionValida(filaActual -1 , columnaActual -1)){
			if(this.matrizDeCuadrantes[filaActual -1 ][columnaActual -1].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	private boolean verificarDisponibilidadCuadranteSuperior(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion superior, tengo que restar 1 en y (fila)
		if (posicionValida(filaActual -1 , columnaActual)){
			if(this.matrizDeCuadrantes[filaActual -1 ][columnaActual].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	private boolean verificarDisponibilidadCuadranteSuperiorDerecho(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion superior derecha, tengo que sumar 1 en x (columna) y restar 1 en y (fila)
		if (posicionValida(filaActual -1 , columnaActual +1)){
			if(this.matrizDeCuadrantes[filaActual -1 ][columnaActual + 1].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	private boolean verificarDisponibilidadCuadranteIzquierdo(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion izquierda, tengo que restar 1 en x (columna)
		if (posicionValida(filaActual, columnaActual -1)){
			if(this.matrizDeCuadrantes[filaActual][columnaActual -1].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	private boolean verificarDisponibilidadCuadranteDerecho(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion derecha, tengo que sumar 1 en x (columna)
		if (posicionValida(filaActual , columnaActual +1)){
			if(this.matrizDeCuadrantes[filaActual][columnaActual + 1].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	private boolean verificarDisponibilidadCuadranteInferiorIzquierdo(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion inferior izquierda, tengo que restar 1 en x (columna) y sumar 1 en y (fila)
		if (posicionValida(filaActual +1 , columnaActual -1)){
			if(this.matrizDeCuadrantes[filaActual +1 ][columnaActual -1].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	private boolean verificarDisponibilidadCuadranteInferior(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion inferior, sumar 1 en y (fila)
		if (posicionValida(filaActual +1 , columnaActual)){
			if(this.matrizDeCuadrantes[filaActual +1 ][columnaActual].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	private boolean verificarDisponibilidadCuadranteInferiorDerecho(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion inferior derecha, tengo que sumar 1 en x (columna) y 1 en y (fila)
		if (posicionValida(filaActual +1 , columnaActual +1)){
			if(this.matrizDeCuadrantes[filaActual +1 ][columnaActual +1].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	private boolean posicionValida(int fila, int columna){
		boolean valida = false;
		
		if(fila < this.filas && columna < this.columnas){
			valida = true;
		}
		
		return valida;
	}
	

	
}
