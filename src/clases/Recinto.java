package clases;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Recinto extends FormaPosicionableEnMapa{
	
	private transient Shape formaRecinto;
	private Grilla grilla;
	Collection <Obstaculo> obstaculos = new HashSet<Obstaculo>();
	private String nombre;
	private Collection<Puerta> puertas = new LinkedList<>();

	public Recinto(float x, float y, float ancho, float alto, String nombre){
		this.setNombre(nombre);
		setFormaRecinto(new Rectangle(x,y,ancho,alto));
		getFormaRecinto().setStroke(Color.BLACK);
		getFormaRecinto().setFill(Color.WHITE);
		getFormaRecinto().setStrokeWidth(4);
		this.posicionX = x;
		this.posicionY = y;
		this.ancho = ancho;
		this.alto = alto;
		this.calcularPosicionBordes();
	}
	
	public boolean agregarObstaculo(Obstaculo obstaculo){
		boolean posicionValida = Validador.validarSiElObstaculoEntraEnElRecinto(this, obstaculo);
		if(posicionValida){
			this.obstaculos.add(obstaculo);
		}
		return posicionValida;
	}
	
	public void verificarDisponibilidadDeLaGrilla(){
		System.out.println("Imprimiendo Grilla del recinto: " + this.nombre);
		for (Cuadrante[] filacuadrante : this.grilla.getMatrizDeCuadrantes()) {
			for (Cuadrante cuadrante : filacuadrante) {
				for (Obstaculo obstaculo : obstaculos) {
					if (Validador.validarSiElCuadranteEsOcupadoPorUnObstaculo(cuadrante,obstaculo)){
						cuadrante.marcarComoNoDisponible();
					}
					System.out.println("Cuadrante: " + cuadrante.getId() + " disponible: " + cuadrante.estaDisponible());
				}
			}
		}
	}
		
	//Getters & Setters

	public Shape getFormaRecinto() {
		return formaRecinto;
	}

	public void setFormaRecinto(Shape formaRecinto) {
		this.formaRecinto = formaRecinto;
	}
	
	public Grilla getGrilla() {
		return grilla;
	}
	
	public void setGrilla(Grilla grilla) {
		this.grilla = grilla;
	}

	public Collection<Obstaculo> getObstaculos() {
		return obstaculos;
	}

	public void setObstaculos(Collection<Obstaculo> obstaculos) {
		this.obstaculos = obstaculos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Puerta> getPuertas() {
		return puertas;
	}

	public boolean agregarPuerta(Puerta puerta, Mapa mapa) {
		boolean puertaValida = Validador.validarSiLaPuertaEstaEnUnaParedValida(puerta,mapa , this);
		if (puertaValida){
			this.puertas.add(puerta);
		}
		return puertaValida;
	}
	
	public Cuadrante obtenerCuadranteCercanoALaPuerta(Puerta puerta){
		Cuadrante cuadranteADevolver = null;
		float inicio;
		float fin;
		float medio;
		Coordenada coordenada;
		
		if (puerta.getOrientacion().equals(Orientacion.Horizontal)){
			inicio = puerta.getPosicionX();
			fin = puerta.getPosicionFinalX();
			medio = (inicio + fin) /2;
			//Tengo que saber si la puerta esta en la parte inferior o superior del recinto
			if(puerta.bordeInferiorDerecho.getY() == this.bordeInferiorDerecho.getY()){
				//estoy abajo
				coordenada = new Coordenada(medio,puerta.getPosicionY() - (float)0.5);
			}
			else{
				//estoy en la parte de arriba
				coordenada = new Coordenada(medio,puerta.getPosicionY() + (float)0.5);
			}
			
		}
		else{
			inicio = puerta.getPosicionY();
			fin = puerta.getPosicionFinalY();
			medio = (inicio + fin) /2;
			if(puerta.bordeInferiorIzquierdo.getX() == this.bordeInferiorIzquierdo.getX()){
				//Estoy en el lateral izquierdo
				coordenada = new Coordenada(puerta.getPosicionX() + (float)0.5,medio);
			}
			else{
				//Estoy en el lateral derecho
				coordenada = new Coordenada(puerta.getPosicionX() - (float)0.5,medio);
			}
			
		}
		
		cuadranteADevolver = this.grilla.obtenerCuadrante(coordenada);
		return cuadranteADevolver;
	}
	
	public Coordenada obtenerCoordenadaExteriorDeUnaPuerta(Puerta puerta){
		float inicio;
		float fin;
		float medio;
		Coordenada coordenada;
		
		if (puerta.getOrientacion().equals(Orientacion.Horizontal)){
			inicio = puerta.getPosicionX();
			fin = puerta.getPosicionFinalX();
			medio = (inicio + fin) /2;
			//Tengo que saber si la puerta esta en la parte inferior o superior del recinto
			if(puerta.bordeInferiorDerecho.getY() == this.bordeInferiorDerecho.getY()){
				//estoy abajo
				coordenada = new Coordenada(medio,puerta.getPosicionY() + (float)0.5);
			}
			else{
				//estoy en la parte de arriba
				coordenada = new Coordenada(medio,puerta.getPosicionY() - (float)0.5);
			}
			
		}
		else{
			inicio = puerta.getPosicionY();
			fin = puerta.getPosicionFinalY();
			medio = (inicio + fin) /2;
			if(puerta.bordeInferiorIzquierdo.getX() == this.bordeInferiorIzquierdo.getX()){
				//Estoy en el lateral izquierdo
				coordenada = new Coordenada(puerta.getPosicionX() - (float)0.5,medio);
			}
			else{
				//Estoy en el lateral derecho
				coordenada = new Coordenada(puerta.getPosicionX() + (float)0.5,medio);
			}
			
		}
		return coordenada;
	}
	
	public boolean coordenadaPerteneceAlRecinto(Coordenada coordenada){
		boolean pertenece = false;
		
		if(coordenada.getX() >= this.getPosicionX() && coordenada.getX() <= (this.getPosicionX() + this.getAncho())
				&& coordenada.getY() >= this.getPosicionY() && coordenada.getY() <= (this.getPosicionY() + this.getAlto())){
			pertenece = true;
		}
		
		return pertenece;
	}
	
	public void regenerarFormaRecinto(){
		setFormaRecinto(new Rectangle(this.posicionX,this.posicionY,this.ancho,this.alto));
		getFormaRecinto().setStroke(Color.BLACK);
		getFormaRecinto().setFill(Color.WHITE);
		getFormaRecinto().setStrokeWidth(4);
	}
	public void regenerarFormaRecintoMapa(){
		setFormaRecinto(new Rectangle(this.posicionX,this.posicionY,this.ancho,this.alto));
		getFormaRecinto().setStroke(Color.BLACK);
		getFormaRecinto().setFill(Color.LIGHTGREY);
		getFormaRecinto().setStrokeWidth(4);
	}
}
