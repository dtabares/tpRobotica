package clases;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Trayectoria implements Serializable{
	
	private List<Paso> pasos;
	private List<Cuadrante> listaDeCuadrantesDeOrigenADestino;
	private double posicionEnGradosRespectoDelNorteMagnetico;
	
	public Trayectoria (List<Cuadrante> lista, double grados){
		this.pasos = new LinkedList<Paso>();
		this.listaDeCuadrantesDeOrigenADestino = lista;
		this.posicionEnGradosRespectoDelNorteMagnetico = grados;
	}
	
//	private void agregarPaso(double direccion, int cantidadDeBaldozas){
//		this.pasos.add(new Paso(direccion, cantidadDeBaldozas));
//	}
//	
	public List<Paso> getListaDePasos(){
		return this.pasos;
	}
	
	public void calcularTrayectoria(){
		Iterator<Cuadrante> iterador = this.listaDeCuadrantesDeOrigenADestino.iterator();
		Cuadrante cuadranteActual;
		Cuadrante cuadranteSiguiente = null;
		double dir;
		while(iterador.hasNext()){
			if (cuadranteSiguiente == null){
				cuadranteActual = iterador.next();
				//inicio
				cuadranteActual.getRectangle().setFill(Color.BLUE);
			}
			else{
				cuadranteActual = cuadranteSiguiente;
				//trayecto
				cuadranteActual.getRectangle().setFill(Color.BLUEVIOLET);
			}
			if(iterador.hasNext()){
				cuadranteSiguiente = iterador.next();
				dir = this.calcularDireccionEnGrados(cuadranteActual, cuadranteSiguiente);
				dir = dir + this.posicionEnGradosRespectoDelNorteMagnetico;
				if(dir > 360){
					dir = dir - 360;
				}
				Paso paso = new Paso(dir,1);
				this.pasos.add(paso);
			}
			//final
			cuadranteSiguiente.getRectangle().setFill(Color.GREEN);
		}
	}
	
	public void borrarTrayectoria(){
		
		Iterator<Cuadrante> iterador = this.listaDeCuadrantesDeOrigenADestino.iterator();
		Cuadrante cuadranteActual;
		while (iterador.hasNext()){
			cuadranteActual=iterador.next();
			cuadranteActual.getRectangle().setFill(Color.WHITE);
		}
		
	}
	
	private double calcularDireccionEnGrados(Cuadrante actual, Cuadrante siguiente){
		
		double direccion = 0;
		float x = actual.getPosicionX() - siguiente.getPosicionX();
		float y = actual.getPosicionY() - siguiente.getPosicionY();
		//Me muevo a derecha en x
		if(x<0){
			//Me muevo hacia abajo en y
			if(y<0){
				direccion = 135;
			}
			//Me quedo quieto en y
			else if(y==0){
				direccion = 90;
			}
			//Me muevo hacia arriba en y
			else{
				direccion = 45;
			}
		}
		//Me quedo quieto en x
		else if(x==0){
			//Me muevo hacia abajo en y
			if(y<0){
				direccion = 180;
			}
			//Me quedo quieto en y
			else if(y==0){
				//No hay movimiento
			}
			//Me muevo hacia arriba en y
			else{
				direccion = 0;
			}
		}
		
		//Me muevo a izquierda en x
		else{
			//Me muevo hacia abajo en y
			if(y<0){
				direccion = 225;
			}
			//Me quedo quieto en y
			else if(y==0){
				direccion = 270;
			}
			//Me muevo hacia arriba en y
			else{
				direccion = 315;
			}
		}
		return direccion;
		
	}
	
	public List<String> obtenerSecuenciaDePasosComoString(){
		List<String> secuencia = new LinkedList<String>();
		Iterator<Paso> iterador = this.pasos.iterator();
		Paso paso;
		String s;
		String textoBaldosa = "baldosa";
		while(iterador.hasNext()){
			paso = iterador.next();
			double dir = paso.getDireccionEnGrados() + posicionEnGradosRespectoDelNorteMagnetico;
			int baldosas = paso.getNumeroDeBaldosas();
			if(baldosas > 1){
				textoBaldosa = "baldosas";
			}
			s = "Moverse " + baldosas + " " + textoBaldosa + " en direccion: " + dir ;
			System.out.println(s);
			secuencia.add(s);
		}
		return secuencia;
	}
	
	public void regenerarFormasTrayectoria(){
		
			if(this.listaDeCuadrantesDeOrigenADestino != null){
				for (Cuadrante c : this.listaDeCuadrantesDeOrigenADestino) {
						c.recrearRectangulo();
				}
			}
		
	}

}
