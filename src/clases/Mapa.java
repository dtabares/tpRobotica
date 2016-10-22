package clases;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;

public class Mapa extends AnchorPane{

	private float tamanioX;
	private float tamanioY;
	private float escalaX;
	private float escalaY;
	Collection <Recinto> recintos = new LinkedList<Recinto>();
		
	public Mapa(float tamanioX, float tamanioY) {
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
	
	//Este metodo no deberia validar sino agregar todo al momento de dibujar
	public void agregarColleccionDeRecintos(Collection <Recinto> recintos){
		
		Iterator <Recinto> iterador = recintos.iterator();
		
		while (iterador.hasNext()){
			Recinto r = iterador.next(); 
			agregarForma(r.getFormaRecinto());
			}
	
	}

	
	public boolean agregarRecinto(Recinto recinto){
				
		boolean posicionValida = Validador.validarSiElRecintoEntraEnElMapa(this, recinto);
		if(posicionValida){
			this.agregarForma(recinto.getFormaRecinto());
		}

		return posicionValida;
	}
	
	//Getters & Setters
	public float getEscalaX() {
		return escalaX;
	}

	public float getEscalaY() {
		return escalaY;
	}

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

}
