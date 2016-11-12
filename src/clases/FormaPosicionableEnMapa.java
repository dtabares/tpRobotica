package clases;

import java.io.Serializable;

public abstract class FormaPosicionableEnMapa implements Serializable{
	
	protected Coordenada bordeSuperiorIzquierdo;
	protected Coordenada bordeSuperiorDerecho;
	protected Coordenada bordeInferiorIzquierdo;
	protected Coordenada bordeInferiorDerecho;
	protected float posicionX;
	protected float posicionY;
	protected float ancho;
	protected float alto;
	
	
	protected void calcularPosicionBordes(){
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
	
	public float getPosicionX() {
		return posicionX;
	}

	public float getPosicionY() {
		return posicionY;
	}	

}
