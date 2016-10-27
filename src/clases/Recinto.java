package clases;

import java.util.Collection;
import java.util.HashSet;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Recinto extends FormaPosicionableEnMapa{
	
	private Shape formaRecinto;
	private Grilla grilla;
	Collection <Obstaculo> obstaculos = new HashSet<Obstaculo>();
	private String nombre;
	
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
		for (Cuadrante cuadrante : this.grilla.getColeccionDeCuadrantes()) {
			for (Obstaculo obstaculo : obstaculos) {
				if (Validador.validarSiElCuadranteEsOcupadoPorUnObstaculo(cuadrante,obstaculo)){
					cuadrante.marcarComoNoDisponible();
				}
			}
		}
	}
	
	public void ajustarEscala(double escala){
		float nuevaPosicionX=posicionX; 
		float nuevaPosicionY=posicionY;
		if (posicionX!=0){ nuevaPosicionX = (float) (this.getPosicionX()-(this.getAncho()*escala)); System.out.println("Posicion X ajustada:" + nuevaPosicionX );}
		if (posicionY!=0){ nuevaPosicionY = (float) (this.getPosicionY()-(this.getAlto()*escala)); System.out.println("Posicion Y ajustada:" + nuevaPosicionY );}
		 		
		float nuevoAncho = (float) (this.getAncho()*escala);
		float nuevoAlto = (float) (this.getAlto()*escala);
		
		this.setFormaRecinto(new Rectangle(nuevaPosicionX,nuevaPosicionY,nuevoAncho,nuevoAlto));
		this.getFormaRecinto().setStroke(Color.BLACK);
		this.getFormaRecinto().setFill(Color.WHITE);
		this.getFormaRecinto().setStrokeWidth(2);		
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

}
