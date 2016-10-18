package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Recinto {
	
	private Coordenada bordeSuperiorIzquierdo;
	private Coordenada bordeSuperiorDerecho;
	private Coordenada bordeInferiorIzquierdo;
	private Coordenada bordeInferiorDerecho;
	private float posicionX;
	private float posicionY;
	private float ancho;
	private float alto;
	public Shape recinto;
	private Grilla grilla;
	
	public Recinto(int x, int y, int ancho, int alto){
		recinto = new Rectangle(x,y,ancho,alto);
		recinto.setStroke(Color.BLACK);
		recinto.setFill(Color.WHITESMOKE);
		recinto.setStrokeWidth(2);
		this.posicionX = x;
		this.posicionY = y;
		this.ancho = ancho;
		this.alto = alto;
		this.calcularPosicionBordes();
	}
	
	public Shape crearRecinto(int x, int y, int ancho, int alto){
		
		Rectangle rectangle = new Rectangle(x,y,ancho,alto);
		rectangle.setStroke(Color.BLACK);
		rectangle.setFill(Color.WHITESMOKE);
		rectangle.setStrokeWidth(2);
		return rectangle;
	
	}
	
	private void calcularPosicionBordes(){
		this.bordeSuperiorIzquierdo = new Coordenada(this.posicionX,this.posicionY);
		this.bordeSuperiorDerecho = new Coordenada(this.posicionX + this.ancho, this.posicionY);
		this.bordeInferiorIzquierdo = new Coordenada(this.posicionX, this.posicionY + this.alto);
		this.bordeInferiorDerecho = new Coordenada(this.posicionX + this.ancho, this.posicionY + this.alto);
	}

	public Coordenada getBordeSuperiorIzquierdo() {
		return bordeSuperiorIzquierdo;
	}

	public Coordenada getBordeSuperiorDerecho() {
		return bordeSuperiorDerecho;
	}

	public Coordenada getBordeInferiorIzquierdo() {
		return bordeInferiorIzquierdo;
	}

	public Coordenada getBordeInferiorDerecho() {
		return bordeInferiorDerecho;
	}

	public float getAncho() {
		return ancho;
	}

	public float getAlto() {
		return alto;
	}

	public Grilla getGrilla() {
		return grilla;
	}
	
	public void setGrilla(Grilla grilla) {
		this.grilla = grilla;
	}

	public float getPosicionX() {
		return posicionX;
	}

	public float getPosicionY() {
		return posicionY;
	}	
	
	
	

}
