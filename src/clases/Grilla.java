package clases;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import javafx.scene.shape.Shape;


public class Grilla implements Serializable{
	
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
		this.filas = (int) Math.ceil( this.recinto.getAlto() / this.longitudNormal);// System.out.println(filas);
		this.columnas = (int) Math.ceil(this.recinto.getAncho() / this.longitudNormal); //System.out.println(columnas);
		matrizDeAdyacencia = new MatrizDeAdyacencia(this);
		this.matrizDeCuadrantes = new Cuadrante[this.filas][this.columnas];
		this.crearGrilla(vertice);
	}
	
	private void crearGrilla(int vertice){
		this.prepararValoresGrilla();
		int numero = this.contador.getProximoNumeroDeCuadrante();
		int idLocal = 0;
		float tamanioX;
		float tamanioY;
		System.out.println("Creando Grilla del recinto: " + this.recinto.getNombre());
		System.out.println("Num Filas: "  + filas);
		System.out.println("Num Col: " + columnas);
		System.out.println("Id Primer Cuadrante: " + numero);
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
						cuadrante.setIdLocal(idLocal);
						matrizDeCuadrantes[fila][columna] = cuadrante;
						numero++;
						idLocal++;
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
						cuadrante.setIdLocal(idLocal);
						matrizDeCuadrantes[fila][columna-1] = cuadrante;
						numero++;
						idLocal++;
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
						cuadrante.setIdLocal(idLocal);
						matrizDeCuadrantes[fila-1][columna] = cuadrante;
						numero++;
						idLocal++;
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
						cuadrante.setIdLocal(idLocal);
						matrizDeCuadrantes[fila-1][columna-1] = cuadrante;
						numero++;
						idLocal++;
					}
				}
			break;
			default: System.out.println("El vertice elegido es incorrecto");
			break;
		}
		//imprimirMatriz(matrizDeCuadrantes);
		this.contador.setProximoNumeroDeCuadrante(numero);
		System.out.println("Ultimo Id: " + numero);
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
				if(this.matrizDeCuadrantes[fila][columna].getIdLocal() == numeroDeCuadrante){
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
	public Contador getContador() {
		return contador;
	}
	
	public void regenerarIdsCuadrantes(){
		int id = this.contador.getProximoNumeroDeCuadrante();;
		for (Cuadrante[] filaCuadrantes : this.getMatrizDeCuadrantes()) {
			for (Cuadrante cuadrante : filaCuadrantes) {
				cuadrante.setId(id);
				id++;
				
			}
		}
		this.contador.setProximoNumeroDeCuadrante(id);
	}
	
	/**
	 * 
	 * @param coordenada
	 * Le pasas una coordenada y te devuelve el Cuadrante que esta en esa coordenada
	 * @return
	 */
	public Cuadrante obtenerCuadrante(Coordenada coordenada){
		Cuadrante cuadranteADevolver = null;
		Cuadrante cuadranteAEvaluar;
		Cuadrante[][] cuadrantes = this.getMatrizDeCuadrantes();
		boolean encontro = false;
		for(int i=0; i<cuadrantes.length; i++){
			if (encontro){
				break;
			}
			else{
				for (int j = 0; j < cuadrantes[i].length; j++) {
					cuadranteAEvaluar = cuadrantes[i][j];
					if(cuadranteAEvaluar.getPosicionX()<= coordenada.getX()
						&& cuadranteAEvaluar.getBordeSuperiorDerecho().getX() >= coordenada.getX()
						&& cuadranteAEvaluar.getBordeSuperiorDerecho().getY() <= coordenada.getY()
						&& cuadranteAEvaluar.getBordeInferiorDerecho().getY() >= coordenada.getY()){
						
						encontro = true;
						cuadranteADevolver = cuadranteAEvaluar;
						break;
						
					}
				}
			}
		}
		
		return cuadranteADevolver;
	}
	
	public int getIdInicial(){
		return this.matrizDeCuadrantes[0][0].getId();
	}
	
	public Cuadrante buscarCuadrantePorCoordenada(Coordenada coordenada){
		Cuadrante[][] matriz = this.getMatrizDeCuadrantes();
		Cuadrante cuadranteADevolver = null;
		boolean pertenece = false;
		for (Cuadrante[] cuadrantes : matriz) {
			for (Cuadrante cuadrante : cuadrantes) {
				if(coordenada.getX() >= cuadrante.getPosicionX() && coordenada.getX() <= (cuadrante.getPosicionX() + cuadrante.getAncho())
					&& coordenada.getY() >= cuadrante.getPosicionY() && coordenada.getY() <= (cuadrante.getPosicionY() + cuadrante.getAlto())){
					pertenece = true;
					cuadranteADevolver = cuadrante;
					break;
				}
			}
			if(pertenece){
				break;
			}
		}
		
		return cuadranteADevolver;
	}
	
	public void imprimirMatriz(Cuadrante[][] matrizDeCuadrantes){
		
		for(int i=0;i<matrizDeCuadrantes.length;i++){
			for (int j=0;j<matrizDeCuadrantes[0].length;j++){
				System.out.print(matrizDeCuadrantes[i][j].getId());
				System.out.print(",");
				
			}
			System.out.println("");
		}
		
	}
	
}
