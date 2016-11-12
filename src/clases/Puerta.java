package clases;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Puerta extends FormaPosicionableEnMapa {
	
	private transient Shape linea;
	private float posicionFinalX;
	private float posicionFinalY;
	private Orientacion orientacion;
	
//	protected float posicionX;
//	protected float posicionY;
//	protected float ancho;
//	protected float largo;
	
	public Puerta(float posicionX, float posicionY, Orientacion orientacion, float ancho){
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.orientacion = orientacion;
		if(orientacion == Orientacion.Horizontal){
			this.ancho = ancho;
			this.alto = 0;
			this.posicionFinalX = posicionX + ancho;
			this.posicionFinalY = posicionY;
		}
		else{
			this.ancho = 0;
			this.alto = ancho;
			this.posicionFinalX = posicionX;
			this.posicionFinalY = posicionY+ancho;
		}
		
		this.setearLinea(this.posicionFinalX, this.posicionFinalY);
		this.calcularPosicionBordes();
		
		
	}
	
	private void setearLinea(float posFinalX, float posFinalY){
		this.linea = new Line(this.posicionX,this.posicionY,posFinalX,posFinalY);
		this.linea.setFill(Color.NAVY);
		this.linea.setStroke(Color.NAVY);
		this.linea.setStrokeWidth(5);
	}
	
	public void regenerateLinea(){
		this.setearLinea(this.posicionFinalX, this.posicionFinalY);
	}

	public float getPosicionFinalX() {
		return posicionFinalX;
	}

	public float getPosicionFinalY() {
		return posicionFinalY;
	}
	
	public Orientacion getOrientacion() {
		return this.orientacion;
	}
	
	public Shape getLinea(){
		return this.linea;
	}

	
}
