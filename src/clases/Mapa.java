package clases;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Mapa extends AnchorPane implements Serializable {

	private float tamanioX;
	private float tamanioY;
	private double posicionEnGradosRespectoDelNorteMagnetico;
	private Grilla grilla;
	private Recinto recintoMapa;
	private Collection <Recinto> recintos = new LinkedList<Recinto>();
	private MatrizDeAdyacencia matrizDeAdyacenciaGlobal;
		
	public Mapa(float tamanioX, float tamanioY) {
		this.setPrefSize(tamanioX, tamanioY);
		this.tamanioX = tamanioX;
		this.tamanioY = tamanioY;
	}
	
	public void agregarForma(Shape forma){
		
		System.out.println(this.getChildren().size());
		if (this.getChildren().contains(forma)){
			System.out.println("La forma: " + forma + "ya existe");
		}
		else{
			this.getChildren().add(forma);
		}
		System.out.println(this.getChildren().size());
	}
		
	public void dibujarMapa(){
		
		Collection<Shape> formasPosicionablesEnMapa = new LinkedList<Shape>();
		Collection<Shape> recintosParaSerAgregados = new LinkedList<Shape>();
		Collection<Shape> grillasParaSerAgregadas = new LinkedList<Shape>();
		Collection<Shape> obstaculosParaSerAgregados = new LinkedList<Shape>();
		Collection<Shape> puertasParaSerAgregadas = new LinkedList<Shape>();
		Rectangle background = new Rectangle (tamanioX,tamanioY);
		background.setStroke(Color.BLACK);
		background.setFill(Color.WHITE);
		
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
			for (Obstaculo obstaculo : recinto.getObstaculos()) {
				obstaculosParaSerAgregados.add(obstaculo.getObstaculo());
			}
		}
		
		//Primero se agrega el fondo
		formasPosicionablesEnMapa.add(background);
		//Si existe una grilla se agrega
		if(grilla!=null){formasPosicionablesEnMapa.addAll(grilla.getColeccionDeRectangulos());}
		//Luego se agregan los recintos en orden
		formasPosicionablesEnMapa.addAll(recintosParaSerAgregados);
		//Luego se agregan las grillas en orden
		formasPosicionablesEnMapa.addAll(grillasParaSerAgregadas);
		//Luego se agregan las puertas en orden
		formasPosicionablesEnMapa.addAll(puertasParaSerAgregadas);
		//Finalmente los obstaculos en orden
		formasPosicionablesEnMapa.addAll(obstaculosParaSerAgregados);
		
		this.getChildren().clear();
		this.getChildren().addAll(formasPosicionablesEnMapa);

	}
	
	public boolean agregarRecinto(Recinto recinto){
			
		boolean posicionValida = Validador.validarSiElRecintoEntraEnElMapa(this, recinto);
		if(posicionValida){
			this.recintos.add(recinto);
		}

		return posicionValida;
	}
	
	public Recinto buscarRecintoPorNombre(String nombreBuscado) {
		boolean encontro = false;
		Recinto recintoBuscado = null;
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
		return recintoBuscado;
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
		this.grilla.getContador().resetearContador();
		//primero regenero los de la grilla del mapa
		this.grilla.regenerarIdsCuadrantes();
		//luego regenero iterando por cada recinto
		Iterator<Recinto> iteradorDeRecintos = this.recintos.iterator();
		while(iteradorDeRecintos.hasNext()){
			iteradorDeRecintos.next().getGrilla().regenerarIdsCuadrantes();
		}
	}

}
