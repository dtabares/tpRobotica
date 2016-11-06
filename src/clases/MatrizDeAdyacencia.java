package clases;

public class MatrizDeAdyacencia {
	
	private boolean[][] matrizDeAdyacencia;
	private Grilla grilla;
	
	
	public MatrizDeAdyacencia(Grilla grilla){
		this.grilla = grilla;
		int tamanio = grilla.getFilas()*grilla.getColumnas();
		this.matrizDeAdyacencia = new boolean[tamanio][tamanio];
	}
	
	public MatrizDeAdyacencia(int tamanio){
		this.matrizDeAdyacencia = new boolean[tamanio][tamanio];
	}

	public boolean[][] getMatrizDeAdyacencia() {
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
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0] -1][posCuadranteARevisar[1] -1].getId();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				if(this.grilla.verificarDisponibilidadCuadranteSuperior(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0] -1 ][posCuadranteARevisar[1]].getId();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				if(this.grilla.verificarDisponibilidadCuadranteSuperiorDerecho(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0] -1][posCuadranteARevisar[1] +1].getId();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				if(this.grilla.verificarDisponibilidadCuadranteInferiorIzquierdo(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0] +1][posCuadranteARevisar[1] -1].getId();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				if(this.grilla.verificarDisponibilidadCuadranteInferior(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0] +1 ][posCuadranteARevisar[1]].getId();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				if(this.grilla.verificarDisponibilidadCuadranteInferiorDerecho(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0] +1][posCuadranteARevisar[1] +1].getId();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				if(this.grilla.verificarDisponibilidadCuadranteIzquierdo(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0]][posCuadranteARevisar[1] -1].getId();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
				
				
				if(this.grilla.verificarDisponibilidadCuadranteDerecho(posCuadranteARevisar[0], posCuadranteARevisar[1])){
					numeroCuadranteAMarcarComoDisp = matrizDeCuadrantes[posCuadranteARevisar[0]][posCuadranteARevisar[1] +1].getId();
					this.matrizDeAdyacencia[fila][numeroCuadranteAMarcarComoDisp] = true;
				}
			}
			

			
		}

		//return this.matrizDeAdyacencia;
	}
}
