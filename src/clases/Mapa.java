package clases;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Mapa extends AnchorPane implements Serializable {

	private static final long serialVersionUID = 1L;
	private float tamanioX;
	private float tamanioY;
	private double posicionEnGradosRespectoDelNorteMagnetico;
	private Grilla grilla;
	private Recinto recintoMapa;
	private Collection <Recinto> recintos = new LinkedList<Recinto>();
	private MatrizDeAdyacencia matrizDeAdyacenciaGlobal;
	private List<Trayectoria> trayectorias;
		
	public Mapa(float tamanioX, float tamanioY) {
		this.setPrefSize(tamanioX, tamanioY);
		this.tamanioX = tamanioX;
		this.tamanioY = tamanioY;
		this.trayectorias = new LinkedList<Trayectoria>();
	}
		
	public void dibujarMapa(){
		
		Collection<Shape> formasPosicionablesEnMapa = new LinkedList<Shape>();
		Collection<Shape> recintosParaSerAgregados = new LinkedList<Shape>();
		Collection<Shape> grillasParaSerAgregadas = new LinkedList<Shape>();
		Collection<Shape> obstaculosParaSerAgregados = new LinkedList<Shape>();
		Collection<Shape> puertasParaSerAgregadas = new LinkedList<Shape>();
		Rectangle background = new Rectangle (this.tamanioX,this.tamanioY);
		background.setStroke(Color.BLACK);
		background.setFill(Color.LIGHTGREY);
		
		for (Recinto recinto : this.recintos) {
			recintosParaSerAgregados.add(recinto.getFormaRecinto());
			if(recinto.getGrilla() != null){
				recinto.verificarDisponibilidadDeLaGrilla();
				grillasParaSerAgregadas.addAll(recinto.getGrilla().getColeccionDeRectangulos());
			}
			if(recinto.getPuertas() != null){
				for(Puerta puerta : recinto.getPuertas()){
					puertasParaSerAgregadas.add(puerta.getLinea());
				}
			}
			if(recinto.getObstaculos()!=null){
				for (Obstaculo obstaculo : recinto.getObstaculos()) {
					obstaculosParaSerAgregados.add(obstaculo.getObstaculo());
				}
			}
		}
		
		//Primero se agrega el fondo
		formasPosicionablesEnMapa.add(background);
		//Si existe una grilla se agrega
		if(grilla!=null){
			formasPosicionablesEnMapa.addAll(grilla.getColeccionDeRectangulos());
		}
		//Luego se agregan los recintos en orden
		if(recintosParaSerAgregados!=null){
			formasPosicionablesEnMapa.addAll(recintosParaSerAgregados);
		}
		if(grillasParaSerAgregadas!=null){
		//Luego se agregan las grillas en orden
			formasPosicionablesEnMapa.addAll(grillasParaSerAgregadas);
		}
		//Luego se agregan las puertas en orden
		if(puertasParaSerAgregadas!=null){
			formasPosicionablesEnMapa.addAll(puertasParaSerAgregadas);
		}
		//Finalmente los obstaculos en orden
		if(obstaculosParaSerAgregados!=null){
			formasPosicionablesEnMapa.addAll(obstaculosParaSerAgregados);
		}
		
		if(this.getChildren()!=null){
			this.getChildren().clear();
		}
		this.getChildren().addAll(formasPosicionablesEnMapa);

	}
	
	public boolean agregarRecinto(Recinto recinto){
			
		boolean posicionValida = Validador.validarSiElRecintoEntraEnElMapa(this, recinto);
		if(posicionValida){
			this.recintos.add(recinto);
		}

		return posicionValida;
	}
	
	public Trayectoria buscarTrayectoriaPorNombre(String nombre){
		Trayectoria t;
		Trayectoria resultado = null;
		boolean encontro = false;
		Iterator<Trayectoria> iterador = trayectorias.iterator();
		
		while(iterador.hasNext() && !encontro){
			t = iterador.next();
			if(t.getNombre().equals(nombre)){
				encontro = true;
				resultado = t;
			}
		}
		
		return resultado;
	}
	
	public void borrarTrayectoria(String nombre){
		Trayectoria t;
		boolean encontro = false;
		int i = 0;
		Iterator<Trayectoria> iterador = trayectorias.iterator();
		
		while(iterador.hasNext() && !encontro){
			t = iterador.next();
			if(t.getNombre().equals(nombre)){
				encontro = true;
				trayectorias.remove(i);
			}
			i++;
		}
		
	}
	
	public void agregarTrayectoria(Trayectoria t){
		trayectorias.add(t);
	}
	
	public Recinto buscarRecintoPorNombre(String nombreBuscado) {
		boolean encontro = false;
		Recinto recintoBuscado = null;
		if(nombreBuscado.equals("Mapa")){
			recintoBuscado = this.recintoMapa;
		}
		else{
			for (Recinto recinto : recintos) {
				if (encontro == true){
					break;
				}
				else{
					if(recinto.getNombre().equals(nombreBuscado)){
						recintoBuscado = recinto;
					}
				}
			}
		}
		return recintoBuscado;
	}
			
	//Hay que forzar que cada Recinto (inclusive el del mapa) tenga una grilla, si es opcional revienta todo y no encontramos camino
	public Cuadrante buscarCuadrantePorId(int idCuadrante){
		Cuadrante cuadranteEncontrado = null;
		boolean encontrado = false;
		
		//Primero reviso si esta en la grilla local del recinto del mapa, el cual corresponde al espacio global
		Cuadrante[][] cuadrantesDelMapa = this.grilla.getMatrizDeCuadrantes();
		for (Cuadrante[] filaCuadrantes : cuadrantesDelMapa) {
			for (Cuadrante cuadrante : filaCuadrantes) {
				if(cuadrante.getId() == idCuadrante){
					cuadranteEncontrado = cuadrante;
					encontrado = true;
					break;
				}
			}
			if(encontrado){
				break;
			}
		}
		
		//Si no lo encontro en el espacio global lo busco iterando por las grillas de cada recinto
		if(!encontrado){
			Iterator<Recinto> iteradorDeRecintos = this.recintos.iterator();
			while(iteradorDeRecintos.hasNext() && !encontrado){
				Cuadrante[][] cuadrantes = iteradorDeRecintos.next().getGrilla().getMatrizDeCuadrantes();
				for (Cuadrante[] filaCuadrantes : cuadrantes) {
					for (Cuadrante cuadrante : filaCuadrantes) {
						if(cuadrante.getId() == idCuadrante){
							cuadranteEncontrado = cuadrante;
							encontrado = true;
							break;
						}
					}
					if(encontrado){
						break;
					}
				}
			}
		}
		return cuadranteEncontrado;
	}
	
	public void regenerarIdsCuadrantesDeTodoElMapa(){
		//reseteo el contador
		Contador.getContador().resetearContador();
		//primero regenero los de la grilla del mapa
		if(this.grilla!=null){
			this.grilla.regenerarIdsCuadrantes();
		}
		//luego regenero iterando por cada recinto
		Iterator<Recinto> iteradorDeRecintos = this.recintos.iterator();
		while(iteradorDeRecintos.hasNext()){
			Recinto r = iteradorDeRecintos.next();
			if(r.getGrilla()!=null){
				r.getGrilla().regenerarIdsCuadrantes();
			}
		}
	}
	
	public void calcularMatrizDeAdyacenciaGlobal(){
		int cantidadTotalDeCuadrantes = this.grilla.getContador().getProximoNumeroDeCuadrante();
		this.matrizDeAdyacenciaGlobal = new MatrizDeAdyacencia(cantidadTotalDeCuadrantes);
		//Primero calculo la de adyacencia de la grilla del mapa y la inserto en la global
		//this.grilla.prepararGrillaParaDibujo();
		this.recintoMapa.setGrilla(this.grilla);
		this.recintoMapa.verificarDisponibilidadDeLaGrilla();
		this.grilla = this.recintoMapa.getGrilla();
		MatrizDeAdyacencia ma = this.grilla.getMatrizDeAdyacencia();
		System.out.println("Matriz de adyacencia del mapa:");
		//ma.imprimirMatriz();
		boolean[][] matrizDeAdyacenciaDelMapa = ma.getMatrizDeAdyacenciaEnBooleanos();
		this.insertarMatrizLocalEnGlobal(matrizDeAdyacenciaDelMapa, this.grilla.getIdInicial());
		System.out.println("Insertando cuadrantes del mapa");

		//Luego itero por cada recinto, y genero la de adyacencia de cada recinto, y la inserto en la global
		Iterator<Recinto> iteradorDeRecintos = this.recintos.iterator();
		while(iteradorDeRecintos.hasNext()){
			Recinto recinto = iteradorDeRecintos.next();
			recinto.verificarDisponibilidadDeLaGrilla();
			Grilla grillaDelRecinto = recinto.getGrilla();
			MatrizDeAdyacencia mar = grillaDelRecinto.getMatrizDeAdyacencia();
			System.out.println("Matriz de adyacencia del recinto: " + recinto.getNombre());
			//mar.imprimirMatriz();
			boolean[][] matrizDeAdyacenciaDelRecinto = mar.getMatrizDeAdyacenciaEnBooleanos();
			this.insertarMatrizLocalEnGlobal(matrizDeAdyacenciaDelRecinto, grillaDelRecinto.getIdInicial());
		}

		//Puertas --> Conectar los grafos
		iteradorDeRecintos = this.recintos.iterator();
		Iterator<Recinto> iteradorDeRecintosEnBusquedaDeCoordenada;
		Recinto recintoEnBusquedaDeCoordenada = null;
		Coordenada coordenadaDelCuadranteExteriorALaPuerta;
		Cuadrante cuadranteInterior;
		Cuadrante cuadranteExterior;
		Queue<Cuadrante> colaCuadranteInterior = new LinkedList<Cuadrante>();
		Queue<Cuadrante> colaCuadranteExterior = new LinkedList<Cuadrante>();
		int idCuadranteInterior;
		int idCuadranteExterior;
		boolean encontroElRecinto = false;
		
		//Itero por cada recinto
		while(iteradorDeRecintos.hasNext()){
			encontroElRecinto = false;
			Recinto recinto = iteradorDeRecintos.next();
			Iterator<Puerta> iteradorDePuertas = recinto.getPuertas().iterator();
			
			//Itero por las puertas de cada recinto
			while(iteradorDePuertas.hasNext()){
				Puerta puerta = iteradorDePuertas.next();
				cuadranteInterior = recinto.obtenerCuadranteCercanoALaPuerta(puerta);
				//cuadranteInterior.setLinderoAPuerta(true);
				//Verifico si el cuadrante dentro del recinto (interior) pegado a la puerta esta disponible
				//Si no lo esta, ya ni busco en el exterior
				if(cuadranteInterior.estaDisponible()){
					idCuadranteInterior = cuadranteInterior.getId();
					coordenadaDelCuadranteExteriorALaPuerta = recinto.obtenerCoordenadaExteriorDeUnaPuerta(puerta);
					//Tengo que encontrar si esa coordenada pertenece al espacio global o a algun recinto
					//Empiezo iterando por cada recinto, para ver si esa coordenada pertenece a alguno de ellos
					iteradorDeRecintosEnBusquedaDeCoordenada = this.recintos.iterator();
					while(iteradorDeRecintosEnBusquedaDeCoordenada.hasNext() && !encontroElRecinto){
						recintoEnBusquedaDeCoordenada = iteradorDeRecintosEnBusquedaDeCoordenada.next();
						if(recintoEnBusquedaDeCoordenada.coordenadaPerteneceAlRecinto(coordenadaDelCuadranteExteriorALaPuerta)){
							encontroElRecinto = true;
						}
					}
					//pertenecia a un recinto
					if(encontroElRecinto){
						cuadranteExterior = recintoEnBusquedaDeCoordenada.getGrilla().buscarCuadrantePorCoordenada(coordenadaDelCuadranteExteriorALaPuerta);
						colaCuadranteInterior.add(cuadranteInterior);
						colaCuadranteExterior.add(cuadranteExterior);
					}
					//pertenece al espacio global
					else{
						cuadranteExterior = this.grilla.buscarCuadrantePorCoordenada(coordenadaDelCuadranteExteriorALaPuerta);
						cuadranteExterior.setLinderoAPuerta(true);
						colaCuadranteInterior.add(cuadranteInterior);
						colaCuadranteExterior.add(cuadranteExterior);
					}
				}
				
			}
		}
		
		//ACA
		System.out.println("Vuelvo a verificar la grilla del mapa");
		this.recintoMapa.verificarDisponibilidadDeLaGrilla();
		while(!colaCuadranteInterior.isEmpty()){
			cuadranteExterior = colaCuadranteExterior.poll();
			cuadranteInterior = colaCuadranteInterior.poll();
			//Si ambos estan disponibles los marco como conexos en la matriz global
			if(cuadranteExterior.estaDisponible()){
				idCuadranteInterior = cuadranteInterior.getId();
				idCuadranteExterior = cuadranteExterior.getId();		
				this.matrizDeAdyacenciaGlobal.marcarDosNodosComoConexos(idCuadranteInterior, idCuadranteExterior);
			}
		}

		System.out.println("Matriz de Adyacencia Global:");
		//matrizDeAdyacenciaGlobal.imprimirMatriz();
	}
	
	public MatrizDeAdyacencia getMatrizDeAdyacenciaGlobal(){
		return this.matrizDeAdyacenciaGlobal;
	}
	
	private void insertarMatrizLocalEnGlobal(boolean[][] matrizLocal, int idInicial){
		int posicionFila = idInicial;
		int posicionColumna = idInicial;
		
		for (boolean[] bs : matrizLocal) {
			for (boolean b : bs) {
				//System.out.println("Inserto el valor: " + b + " en la fila: " + posicionFila  + " columna: " +posicionColumna);
				this.matrizDeAdyacenciaGlobal.getMatrizDeAdyacenciaEnBooleanos()[posicionFila][posicionColumna] = b;
				posicionColumna++;
			}
			posicionColumna = idInicial;
			posicionFila++;
		}
		
	}
	
	public Cuadrante buscarCuadrantePorCoordenada(Coordenada coordenada){
		Cuadrante cuadrante = null;
		
		//primero busco en cada recinto
		Iterator<Recinto> iteradorDeRecintos = this.recintos.iterator();
		Recinto r;
		while (iteradorDeRecintos.hasNext() && cuadrante == null){
			r = iteradorDeRecintos.next();
			cuadrante = r.getGrilla().buscarCuadrantePorCoordenada(coordenada);
		}
		//finalmente en el mapa libre
		if (cuadrante == null){
			cuadrante = this.grilla.buscarCuadrantePorCoordenada(coordenada);
		}
		return cuadrante;
	}
	
	//Getters & Setters
	public float getTamanioX() {
		return tamanioX;
	}

	public void setTamanioX(float tamanioX) {
		this.tamanioX = tamanioX;
	}

	public float getTamanioY() {
		return tamanioY;
	}

	public void setTamanioY(float tamanioY) {
		this.tamanioY = tamanioY;
	}
	
	public Collection<Recinto> getRecintos() {
		return recintos;
	}

	public Grilla getGrilla() {
		return grilla;
	}

	public void setGrilla(Grilla grilla) {
		this.grilla = grilla;
	}

	public Recinto getRecintoMapa() {
		return recintoMapa;
	}

	public void setRecintoMapa(Recinto recintoMapa) {
		this.recintoMapa = recintoMapa;
	}
	
	public double getPosicionEnGradosRespectoDelNorteMagnetico() {
		return posicionEnGradosRespectoDelNorteMagnetico;
	}
	
	public void regenerarFiguras(){
		//Primero regenero la grilla del mapa y su recinto
		if(recintoMapa!=null){
			this.recintoMapa.regenerarFormaRecintoMapa();
			if(this.grilla!=null){
				this.grilla.prepararGrillaParaDibujoDespuesDeCargarMapa();
			}
		}
		//Despues itero por cada recinto
		//Regenerando :
		//El recinto, la grilla, los obstaculos, las puertas
		if (this.recintos!=null){
			for (Recinto recinto : this.recintos) {
				recinto.regenerarFormaRecinto();
				if(recinto.getGrilla()!=null){
					recinto.getGrilla().prepararGrillaParaDibujoDespuesDeCargarMapa();
				}
				if(recinto.getObstaculos()!=null){	
					for (Obstaculo obstaculo : recinto.getObstaculos()) {
							obstaculo.regenerarObstaculo();
					}
				}
				if(recinto.getPuertas()!=null){	
					for (Puerta puerta : recinto.getPuertas()) {
						puerta.regenerarLinea();
					}
				}
				if(recinto.getGrilla()!=null){
					recinto.getGrilla().prepararGrillaParaDibujoDespuesDeCargarMapa();
				}
			}
		}
	}
	
	public List<Trayectoria> getTrayectorias() {
		return trayectorias;
	}

}
