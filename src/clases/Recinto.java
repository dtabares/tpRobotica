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
	
	public Recinto(float x, float y, float ancho, float alto){
		setFormaRecinto(new Rectangle(x,y,ancho,alto));
		getFormaRecinto().setStroke(Color.BLACK);
		getFormaRecinto().setFill(Color.WHITESMOKE);
		getFormaRecinto().setStrokeWidth(2);
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
		for (Cuadrante cuadrante : this.grilla.getColeccionDeCuadrantes()) {
			for (Obstaculo obstaculo : obstaculos) {
				if (Validador.validarSiElCuadranteEsOcupadoPorUnObstaculo(cuadrante,obstaculo)){
					cuadrante.marcarComoNoDisponible();
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

}
