package clases;

import java.util.Collection;
import java.util.Iterator;


public class Validador {
	
	public static boolean validarSiElRecintoEntraEnElMapa(Mapa mapa, Recinto recinto){
		boolean entra = false;
		Collection<Recinto> recintos = mapa.getRecintos();
		Iterator<Recinto> iterador = recintos.iterator();
		
		if(recinto.getBordeSuperiorIzquierdo().getX() >= 0 && 
			recinto.getBordeSuperiorDerecho().getX() <= mapa.getTamanioX() &&
			recinto.getBordeSuperiorIzquierdo().getY() >= 0 &&
			recinto.getBordeInferiorDerecho().getY() <= mapa.getTamanioY()){
			entra = true;
		}
		
		while(iterador.hasNext() && entra == true){
			if(seSuperponenLasFormas(iterador.next(), recinto)){
				entra = false;
			}
		}
		return entra;
	}
	
	public static boolean validarSiElObstaculoEntraEnElRecinto(Recinto recinto, Obstaculo obstaculo){
		boolean entra = false;
		Collection<Obstaculo> obstaculos = recinto.getObstaculos();
		Iterator<Obstaculo> iterador = obstaculos.iterator();
		
		if(obstaculo.getBordeSuperiorIzquierdo().getX() >= recinto.getBordeSuperiorIzquierdo().getX() && 
			obstaculo.getBordeSuperiorDerecho().getX() <= recinto.getBordeSuperiorDerecho().getX() &&
			obstaculo.getBordeSuperiorIzquierdo().getY() >= recinto.getBordeSuperiorIzquierdo().getY() &&
			obstaculo.getBordeSuperiorDerecho().getY() <= recinto.getBordeInferiorDerecho().getY()){
			entra = true;
		}
		
		while(iterador.hasNext() && entra == true){
			if(seSuperponenLasFormas(iterador.next(), obstaculo)){
				entra = false;
			}
		}
		return entra;
	}
	
	public static boolean validarSiElCuadranteEsOcupadoPorUnObstaculo(Cuadrante cuadrante, Obstaculo obstaculo){
		boolean ocupado = false;
		if(seSuperponenLasFormas(cuadrante, obstaculo)){
			ocupado = true;
		}
		return ocupado;
	}
	
	//Devuelve true si se superponen
	private static boolean seSuperponenLasFormas(FormaPosicionableEnMapa a, FormaPosicionableEnMapa b){
		boolean seSuperponen = true;
		if (!sonIgualesYTienenLaMismaPosicion(a, b)){
			seSuperponen = (sePisanEnX(a, b) && sePisanEnY(a, b));
		}
		
		
		return seSuperponen;
	}
	
	private static boolean sonIgualesYTienenLaMismaPosicion(FormaPosicionableEnMapa a, FormaPosicionableEnMapa b){
		boolean iguales = false;
		
		if(a.getBordeInferiorDerecho().equals(b.getBordeInferiorDerecho())  &&
				a.getBordeInferiorIzquierdo().equals(b.getBordeInferiorIzquierdo()) &&
				a.getBordeSuperiorDerecho().equals(b.getBordeSuperiorDerecho()) &&
				a.getBordeSuperiorIzquierdo().equals(b.getBordeSuperiorIzquierdo())){
			iguales = true;
		}
		
		return iguales;
	}
	
	private static boolean sePisanEnX(FormaPosicionableEnMapa a, FormaPosicionableEnMapa b){
		boolean sePisan = false;
		
		if((b.getBordeSuperiorDerecho().getX() >= a.getBordeSuperiorDerecho().getX() && 
				b.getBordeSuperiorIzquierdo().getX() <= a.getBordeSuperiorDerecho().getX()) 
				|| (b.getBordeInferiorDerecho().getX() >= a.getBordeInferiorDerecho().getX() && 
					b.getBordeInferiorIzquierdo().getX() <= a.getBordeInferiorDerecho().getX())){
			sePisan = true;
		}
		else{
			if((b.getBordeSuperiorIzquierdo().getX() <= a.getBordeSuperiorIzquierdo().getX() && 
				b.getBordeSuperiorDerecho().getX() >= a.getBordeSuperiorIzquierdo().getX()) ||
				(b.getBordeInferiorIzquierdo().getX() <= a.getBordeInferiorIzquierdo().getX() && 
				b.getBordeInferiorDerecho().getX() >= a.getBordeInferiorIzquierdo().getX())){
				sePisan = true;
			}
			else {
				if((b.getBordeSuperiorIzquierdo().getX() >= a.getBordeSuperiorIzquierdo().getX() && 
					b.getBordeSuperiorDerecho().getX() <= a.getBordeSuperiorDerecho().getX()) ||
					(b.getBordeInferiorIzquierdo().getX() >= a.getBordeInferiorIzquierdo().getX() && 
					b.getBordeInferiorDerecho().getX() <= a.getBordeInferiorDerecho().getX())){
					sePisan = true;
				}
			}
		}
		return sePisan;
	}
	
	private static boolean sePisanEnY(FormaPosicionableEnMapa a, FormaPosicionableEnMapa b){
		boolean sePisan = false;
		
		if((b.getBordeSuperiorDerecho().getY() <= a.getBordeSuperiorDerecho().getY() && 
			b.getBordeInferiorDerecho().getY() >= a.getBordeSuperiorDerecho().getY()) ||
			(b.getBordeSuperiorIzquierdo().getY() <= a.getBordeSuperiorIzquierdo().getY() && 
			b.getBordeInferiorIzquierdo().getY() >= a.getBordeSuperiorIzquierdo().getY())){
			sePisan = true;
		}
		else{
			if((b.getBordeSuperiorIzquierdo().getY() >= a.getBordeSuperiorIzquierdo().getY() && 
				b.getBordeInferiorIzquierdo().getY() <= a.getBordeInferiorIzquierdo().getY()) ||
				(b.getBordeSuperiorDerecho().getY() >= a.getBordeSuperiorDerecho().getY() && 
				b.getBordeInferiorDerecho().getY() <= a.getBordeInferiorDerecho().getY())){
				sePisan = true;
			}
			else {
				if((b.getBordeSuperiorIzquierdo().getY() >= a.getBordeSuperiorIzquierdo().getY() && 
					b.getBordeSuperiorIzquierdo().getY() <= a.getBordeInferiorIzquierdo().getY()) ||
					(b.getBordeSuperiorDerecho().getY() >= a.getBordeSuperiorDerecho().getY() && 
					b.getBordeSuperiorDerecho().getY() <= a.getBordeInferiorDerecho().getY())){
					sePisan = true;
				}
			}
		}
		//System.out.println("Se pisan en Y: " + sePisan);
		return sePisan;
	}

}
