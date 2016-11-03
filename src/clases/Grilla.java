package clases;

import java.util.Collection;
import java.util.LinkedList;
import javafx.scene.shape.Shape;


public class Grilla {
	
	private Cuadrante[][] matrizDeCuadrantes;
	private Collection <Shape> coleccionDeRectangulos;
	private MatrizDeAdyacencia matrizDeAdyacencia;
	private float posicionX;
	private float posicionY;
	private float longitudNormal;
	private float longitudXUltimaColumna;
	private float longitudYUltimaFila;
	private Recinto recinto;
	private int filas;
	private int columnas;
	private Contador contador;
	
	//Constructor de la clase Grilla, crea una grilla especificando el tamaño de cada unidad, require conocer el recinto para posicionarse
	public Grilla(Recinto recinto, float tamanio, int vertice){
		this.coleccionDeRectangulos = new LinkedList<Shape>();
		this.contador = Contador.getContador();
		this.posicionX = recinto.getPosicionX();
		this.posicionY = recinto.getPosicionY();
		this.longitudNormal = tamanio;
		this.recinto = recinto;
		this.filas = (int) Math.ceil( this.recinto.getAlto() / this.longitudNormal); System.out.println(filas);
		this.columnas = (int) Math.ceil(this.recinto.getAncho() / this.longitudNormal); System.out.println(columnas);
		matrizDeAdyacencia = new MatrizDeAdyacencia(this);
		this.matrizDeCuadrantes = new Cuadrante[this.filas][this.columnas];
		this.crearGrilla(vertice);
	}
	
	private void crearGrilla(int vertice){
		this.prepararValoresGrilla();
		int numero = this.contador.getProximoNumeroDeCuadrante();
		float tamanioX;
		float tamanioY;
		
		switch(vertice){
			case 1:
				for (int fila=0; fila < filas ;fila++){
					for (int columna=0;columna < columnas;columna++){
						
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
						Cuadrante cuadrante = new Cuadrante((posicionX+(int)columna*longitudNormal),(posicionY+(int)fila*longitudNormal),tamanioX,tamanioY, numero);
						matrizDeCuadrantes[fila][columna] = cuadrante;
						numero++;
					}
				}
			break;
			case 2:
				for (int fila=0; fila < filas ;fila++){
					for (int columna=columnas;columna > 0;columna--){
						
						if(fila +1 == filas){
							tamanioY = this.longitudYUltimaFila;
						}
						else{
							tamanioY = this.longitudNormal;
						}
						
						if(columna -1 == 0){
							tamanioX = this.longitudXUltimaColumna;
						}
						else{
							tamanioX = this.longitudNormal;
						}
						Cuadrante cuadrante;
						if(columna-1!=0){
							cuadrante = new Cuadrante(((posicionX+recinto.getAncho())-((int)columna-1)*longitudNormal),(posicionY+(int)fila*longitudNormal),tamanioX,tamanioY, numero);
						}
						else{
							cuadrante = new Cuadrante((posicionX),(posicionY+(int)fila*longitudNormal),tamanioX,tamanioY, numero);	
						}
						matrizDeCuadrantes[fila][columna-1] = cuadrante;
						numero++;
					}
				}
			break;
			case 3:
				for (int fila=filas; fila > 0 ;fila--){
					for (int columna=0;columna < columnas;columna++){
						
						if(fila -1 == 0){
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
						Cuadrante cuadrante;
						if(fila-1!=0){
							cuadrante = new Cuadrante((posicionX+(int)columna*longitudNormal),((posicionY+recinto.getAlto())-((int)fila-1)*longitudNormal),tamanioX,tamanioY, numero);
						}
						else{
							cuadrante = new Cuadrante((posicionX+(int)columna*longitudNormal),(posicionY),tamanioX,tamanioY, numero);	
						}						
						matrizDeCuadrantes[fila-1][columna] = cuadrante;
						numero++;
					}
				}
			break;
			case 4:
				for (int fila=filas; fila > 0 ;fila--){
					for (int columna=columnas;columna > 0;columna--){
						if(fila -1 == 0){
							tamanioY = this.longitudYUltimaFila;
						}
						else{
							tamanioY = this.longitudNormal;
						}
						
						if(columna -1 == 0){
							tamanioX = this.longitudXUltimaColumna;
						}
						else{
							tamanioX = this.longitudNormal;
						}
						
						Cuadrante cuadrante;
						if(fila-1!=0){
							if(columna-1!=0){
								cuadrante = new Cuadrante(((posicionX+recinto.getAncho())-((int)columna-1)*longitudNormal),((posicionY+recinto.getAlto())-((int)fila-1)*longitudNormal),tamanioX,tamanioY, numero);
							}
							else{
								cuadrante = new Cuadrante((posicionX),((posicionY+recinto.getAlto())-((int)fila-1)*longitudNormal),tamanioX,tamanioY, numero);	
							}
						}
						else{
							
							if(columna-1!=0){
								cuadrante = new Cuadrante(((posicionX+recinto.getAncho())-((int)columna-1)*longitudNormal),(posicionY),tamanioX,tamanioY, numero);
							}
							else {						
								cuadrante = new Cuadrante((posicionX),(posicionY),tamanioX,tamanioY, numero);
							}
						}			
						matrizDeCuadrantes[fila-1][columna-1] = cuadrante;
						numero++;
					}
				}
			break;
			default: System.out.println("El vertice elegido es incorrecto");
			break;
		}
		this.contador.setProximoNumeroDeCuadrante(numero);
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
	
	public int[] obtenerPosicionDeUnCuadrante(int numeroDeCuadrante){
		int[] posicion = new int[2];
		boolean encontro = false;
		int fila = 0;
		int columna;
		
		while(!encontro && fila < this.filas){
			columna = 0;
			while(!encontro && columna < this.columnas){
				if(this.matrizDeCuadrantes[fila][columna].getNumero() == numeroDeCuadrante){
					encontro = true;
					posicion[0] = fila;
					posicion[1] = columna;
				}
				else{
					columna++;
				}
			}
			fila++;
		}
		return posicion;
	}
	
	public boolean verificarDisponibilidadCuadranteSuperiorIzquierdo(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion superior izquierda, tengo que restar 1 en x (columna) y 1 en y (fila)
		if (posicionValida(filaActual -1 , columnaActual -1)){
			if(this.matrizDeCuadrantes[filaActual -1 ][columnaActual -1].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	public boolean verificarDisponibilidadCuadranteSuperior(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion superior, tengo que restar 1 en y (fila)
		if (posicionValida(filaActual -1 , columnaActual)){
			if(this.matrizDeCuadrantes[filaActual -1 ][columnaActual].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	public boolean verificarDisponibilidadCuadranteSuperiorDerecho(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion superior derecha, tengo que sumar 1 en x (columna) y restar 1 en y (fila)
		if (posicionValida(filaActual -1 , columnaActual +1)){
			if(this.matrizDeCuadrantes[filaActual -1 ][columnaActual + 1].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	public boolean verificarDisponibilidadCuadranteIzquierdo(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion izquierda, tengo que restar 1 en x (columna)
		if (posicionValida(filaActual, columnaActual -1)){
			if(this.matrizDeCuadrantes[filaActual][columnaActual -1].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	public boolean verificarDisponibilidadCuadranteDerecho(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion derecha, tengo que sumar 1 en x (columna)
		if (posicionValida(filaActual , columnaActual +1)){
			if(this.matrizDeCuadrantes[filaActual][columnaActual + 1].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	public boolean verificarDisponibilidadCuadranteInferiorIzquierdo(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion inferior izquierda, tengo que restar 1 en x (columna) y sumar 1 en y (fila)
		if (posicionValida(filaActual +1 , columnaActual -1)){
			if(this.matrizDeCuadrantes[filaActual +1 ][columnaActual -1].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	public boolean verificarDisponibilidadCuadranteInferior(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion inferior, sumar 1 en y (fila)
		if (posicionValida(filaActual +1 , columnaActual)){
			if(this.matrizDeCuadrantes[filaActual +1 ][columnaActual].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	public boolean verificarDisponibilidadCuadranteInferiorDerecho(int filaActual, int columnaActual){
		boolean disponible = false;
		//Para sacar la posicion inferior derecha, tengo que sumar 1 en x (columna) y 1 en y (fila)
		if (posicionValida(filaActual +1 , columnaActual +1)){
			if(this.matrizDeCuadrantes[filaActual +1 ][columnaActual +1].estaDisponible()){
				disponible = true;
			}
		}

		return disponible;
	}
	
	public boolean verificarDisponibilidadCuadranteActual(int filaActual, int columnaActual){
		boolean disponible = false;
		if (posicionValida(filaActual , columnaActual)){
			if(this.matrizDeCuadrantes[filaActual][columnaActual].estaDisponible()){
				disponible = true;
			}
		}
		return disponible;
	}
	
	public boolean posicionValida(int fila, int columna){
		boolean valida = false;
		
		if(fila < this.filas && fila >=0 && columna >= 0 && columna < this.columnas){
			valida = true;
		}
		
		return valida;
	}

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}
	
	public MatrizDeAdyacencia getMatrizDeAdyacencia(){
		this.matrizDeAdyacencia.calcularMatrizDeAdyacencia();
		return this.matrizDeAdyacencia;
	}
	
}
