package clases;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Recinto extends FormaPosicionableEnMapa{
	
	private Shape formaRecinto;
	private Grilla grilla;
	Collection <Obstaculo> obstaculos = new HashSet<Obstaculo>();
	private String nombre;
	private Collection<Puerta> puertas = new LinkedList<>();

	public Recinto(float x, float y, float ancho, float alto, String nombre){
		this.setNombre(nombre);
		setFormaRecinto(new Rectangle(x,y,ancho,alto));
		getFormaRecinto().setStroke(Color.BLACK);
		getFormaRecinto().setFill(Color.WHITE);
		getFormaRecinto().setStrokeWidth(2);
		this.posicionX = x;
		this.posicionY = y;
		this.ancho = ancho;
		this.largo = alto;
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
		for (Cuadrante[] filacuadrante : this.grilla.getMatrizDeCuadrantes()) {
			for (Cuadrante cuadrante : filacuadrante) {
				for (Obstaculo obstaculo : obstaculos) {
					if (Validador.validarSiElCuadranteEsOcupadoPorUnObstaculo(cuadrante,obstaculo)){
						cuadrante.marcarComoNoDisponible();
					}
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
}
