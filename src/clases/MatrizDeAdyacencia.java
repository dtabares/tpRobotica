package clases;

import java.io.Serializable;

public class MatrizDeAdyacencia implements Serializable{
	
	private boolean[][] matrizDeAdyacencia;
	private Grilla grilla;
	
	
	public MatrizDeAdyacencia(Grilla grilla){
		this.grilla = grilla;
		int tamanio = grilla.getFilas()*grilla.getColumnas();
		this.matrizDeAdyacencia = new boolean[tamanio][tamanio];
	}
	
	public MatrizDeAdyacencia(int tamanio){
		this.matrizDeAdyacencia = new boolean[tamanio][tamanio];
		this.inicializarMatrizDeAdyacencia();
	}

	public boolean[][] getMatrizDeAdyacenciaEnBooleanos() {
		return this.matrizDeAdyacencia;
	}
	
	private void inicializarMatrizDeAdyacencia(){
		for (int i = 0; i < this.matrizDeAdyacencia.length; i++) {
			for (int j = 0; j < this.matrizDeAdyacencia[i].length; j++) {
				this.matrizDeAdyacencia[i][j] = false;
			}
		}
	}
	
	public void calcularMatrizDeAdyacencia(){
		this.inicializarMatrizDeAdyacencia();
		int numeroDelCuadranteARevisar;
		int numeroCuadranteAMarcarComoDisp;
		int[] posCuadranteARevisar;
		Cuadrante[][] matrizDeCuadrantes = this.grilla.getMatrizDeCuadrantes();
		for (int fila = 0; fila < matrizDeAdyacencia.length; fila++) {
			numeroDelCuadranteARevisar = fila;
			posCuadranteARevisar = this.grilla.obtenerPosicionDeUnCuadrante(numeroDelCuadranteARevisar);
			
			if (this.grilla.verificarDisponibilidadCuadranteActual(posCuadranteARevisar[0], posCuadranteARevisar[1])){
				if(this.grilla.verificarDisponibilidadCuadranteSuperiorIzquierdo(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0] -1][posCuadranteARevisar[1] -1].getIdLocal();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				if(this.grilla.verificarDisponibilidadCuadranteSuperior(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0] -1 ][posCuadranteARevisar[1]].getIdLocal();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				if(this.grilla.verificarDisponibilidadCuadranteSuperiorDerecho(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0] -1][posCuadranteARevisar[1] +1].getIdLocal();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				if(this.grilla.verificarDisponibilidadCuadranteInferiorIzquierdo(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0] +1][posCuadranteARevisar[1] -1].getIdLocal();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				if(this.grilla.verificarDisponibilidadCuadranteInferior(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0] +1 ][posCuadranteARevisar[1]].getIdLocal();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				if(this.grilla.verificarDisponibilidadCuadranteInferiorDerecho(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0] +1][posCuadranteARevisar[1] +1].getIdLocal();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				if(this.grilla.verificarDisponibilidadCuadranteIzquierdo(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0]][posCuadranteARevisar[1] -1].getIdLocal();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				
				if(this.grilla.verificarDisponibilidadCuadranteDerecho(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0]][posCuadranteARevisar[1] +1].getIdLocal();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
			}
		}
	}
	
	public void marcarDosNodosComoConexos(int idNodo1, int idNodo2){
		this.matrizDeAdyacencia[idNodo1][idNodo2] = true;
		this.matrizDeAdyacencia[idNodo2][idNodo1] = true;
	}
		
	public void imprimirMatriz(){
		
		for(int i=0;i<matrizDeAdyacencia.length;i++){
			for (int j=0;j<matrizDeAdyacencia[0].length;j++){
				System.out.print(matrizDeAdyacencia[i][j]);
				System.out.print(",");
				
			}
			System.out.println("");
		}
		
	}
	
}
