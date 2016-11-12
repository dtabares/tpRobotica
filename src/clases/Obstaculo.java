package clases;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Obstaculo extends FormaPosicionableEnMapa{
	
	private Shape obstaculo;
	private Obstaculos tipo;
	
	public Obstaculo(Float x, Float y, Obstaculos tipo){
		setearCaracteristicasDelObstaculo(tipo);
		obstaculo = new Rectangle(x,y,this.ancho,this.largo);
		obstaculo.setStroke(Color.BLACK);
		obstaculo.setFill(Color.WHITESMOKE);
		obstaculo.setStrokeWidth(2);
		this.posicionX = x;
		this.posicionY = y;
		this.calcularPosicionBordes();
		this.tipo = tipo;
	}
	
	public Obstaculo(Float x, Float y, Float ancho, Float alto, Obstaculos tipo){
		obstaculo = new Rectangle(x,y,ancho,alto);
		obstaculo.setStroke(Color.BLACK);
		obstaculo.setFill(Color.WHITESMOKE);
		obstaculo.setStrokeWidth(2);
		this.posicionX = x;
		this.posicionY = y;
		this.ancho = ancho;
		this.largo = alto;
		this.calcularPosicionBordes();
		this.tipo = tipo;
	}

	public Shape getObstaculo() {
		return obstaculo;
	}

	public void setObstaculo(Shape obstaculo) {
		this.obstaculo = obstaculo;
	}
	
	//Mesa, Silla, Pizarron, Armario
	private void setearCaracteristicasDelObstaculo(Obstaculos tipo){
		switch (tipo) {
		case Silla:
			this.ancho = (float) 10;
			this.largo = (float) 10;
			break;
		case Mesa:
			this.ancho = (float) 30;
			this.largo = (float) 20;
			break;
			
		case Pizarron:
			this.ancho = (float) 0.1;
			this.largo = (float) 2;
			break;
			
		case Armario:
			this.ancho = (float) 0.6;
			this.largo = (float) 10;
			break;

		default:
			break;
		}
	}
	
	public Obstaculos getTipo(){
		return this.tipo;
	}

}
