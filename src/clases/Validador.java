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
		//System.out.println("entro a validarSiElObstaculoEntraEnElRecinto");
		if(obstaculo.getBordeSuperiorIzquierdo().getX() >= recinto.getBordeSuperiorIzquierdo().getX() && 
			obstaculo.getBordeSuperiorDerecho().getX() <= recinto.getBordeSuperiorDerecho().getX() &&
			obstaculo.getBordeSuperiorIzquierdo().getY() >= recinto.getBordeSuperiorIzquierdo().getY() &&
			obstaculo.getBordeInferiorDerecho().getY() <= recinto.getBordeInferiorDerecho().getY()){
			entra = true;
		}
//		System.out.println("valor de entra: " + entra);
//		System.out.println("valor de iterador has next: " + iterador.hasNext());
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
		//Veo el caso del recinto como un especial forzado
		if(b.getClass() == Obstaculo.class){
			Obstaculo obs = (Obstaculo) b;
			if(a.getClass() == Cuadrante.class){
				Cuadrante c = (Cuadrante) a;
				if(obs.getTipo().equals(Obstaculos.Recinto)){
					if(c.esLinderoAPuerta()){
						//seSuperponen = validarCuadranteLinderoAPuertaYrecinto(c,obs);
						seSuperponen = false;
						c.marcarComoDisponible();
						System.out.println("entre");
					}
					else{
						seSuperponen = validarCuadranteYrecinto(c,obs);
					}
					
				}
				else{
					if (!sonIgualesYTienenLaMismaPosicion(a, b)){
						seSuperponen = (sePisanEnX(a, b) && sePisanEnY(a, b));
					}
				}
			}

			else{
				if (!sonIgualesYTienenLaMismaPosicion(a, b)){
					seSuperponen = (sePisanEnX(a, b) && sePisanEnY(a, b));
				}
			}
		}
		else{
			if (!sonIgualesYTienenLaMismaPosicion(a, b)){
				seSuperponen = (sePisanEnX(a, b) && sePisanEnY(a, b));
			}
		}

		
		//System.out.println("entro  a seSuperponenLasFormas");
		
		
		return seSuperponen;
	}
	
	private static boolean validarCuadranteYrecinto(FormaPosicionableEnMapa a, Obstaculo obs) {
		float inicialXCuadrante = a.getPosicionX();
		float finalXCuadrante = inicialXCuadrante + a.getAncho();
		float inicialYCuadrante = a.getPosicionY();
		float finalYCuadrante = inicialYCuadrante + a.getAlto();
		float inicialXObstaculo = obs.getPosicionX();
		float finalXObstaculo = inicialXObstaculo + obs.getAncho();
		float inicialYObstaculo = obs.getPosicionY();
		float finalYObstaculo = inicialYObstaculo + obs.getAlto();
		boolean sePisanEnX;
		boolean sePisanEnY;
		if(inicialXCuadrante <= inicialXObstaculo && finalXCuadrante > inicialXObstaculo ||
				inicialXCuadrante < finalXObstaculo && finalXCuadrante >= finalXObstaculo ||
				inicialXCuadrante > inicialXObstaculo && finalXCuadrante < finalXObstaculo){
			sePisanEnX = true;
		}
		else{
			sePisanEnX = false;
		}
		
		if(inicialYCuadrante <= inicialYObstaculo && finalYCuadrante > inicialYObstaculo ||
				inicialYCuadrante < finalYObstaculo && finalYCuadrante >= finalYObstaculo ||
				inicialYCuadrante > inicialYObstaculo && finalYCuadrante < finalYObstaculo){
			sePisanEnY = true;
		}
		else{
			sePisanEnY = false;
		}
		return (sePisanEnX && sePisanEnY);
	}

	private static boolean validarCuadranteLinderoAPuertaYrecinto(FormaPosicionableEnMapa a, Obstaculo obs) {
		
		if((sePisanEnX(a, obs) && sePisanEnY(a, obs))){
			return false;
		}
		else{
			return true;
//			float inicialXCuadrante = a.getPosicionX();
//			float finalXCuadrante = inicialXCuadrante + a.getAncho();
//			float inicialYCuadrante = a.getPosicionY();
//			float finalYCuadrante = inicialYCuadrante + a.getAlto();
//			float inicialXObstaculo = obs.getPosicionX();
//			float finalXObstaculo = inicialXObstaculo + obs.getAncho();
//			float inicialYObstaculo = obs.getPosicionY();
//			float finalYObstaculo = inicialYObstaculo + obs.getAlto();
//			boolean sePisanEnX;
//			boolean sePisanEnY;
//			if(inicialXCuadrante >= inicialXObstaculo && finalXCuadrante < finalXObstaculo ||
//					inicialXCuadrante > inicialXObstaculo && finalXCuadrante <= finalXObstaculo ||
//					inicialXCuadrante == inicialXObstaculo && finalXCuadrante == finalXObstaculo){
//				sePisanEnX = true;
//			}
//			else{
//				sePisanEnX = false;
//			}
//			
//			if(inicialYCuadrante >= inicialYObstaculo && finalYCuadrante < finalYObstaculo ||
//					inicialYCuadrante > inicialYObstaculo && finalYCuadrante <= finalYObstaculo ||
//					inicialYCuadrante == inicialYObstaculo && finalYCuadrante == finalYObstaculo){
//				sePisanEnY = true;
//			}
//			else{
//				sePisanEnY = false;
//			}
//			return (sePisanEnX && sePisanEnY);
		}

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
		
		//A empieza a la izquierda de B y termina dentro de B
		//A empieza a la izquierda de B y termina fuera de B
		//En resumen, A empieza a la izquierda del lateral izquierdo de B y termina a la derecha del lateral izquierdo de B
		if(a.getPosicionX() <= b.getPosicionX() && (a.getPosicionX() + a.getAncho() > b.getPosicionX())){
			sePisan = true;
		}
		
		
		//A empieza a la derecha de B y termina dentro de B
		//En resumen, A empieza a la derecha del lateral izquierdo de B y termina a la izquierda del lateral derecho de B
		if(!sePisan && a.getPosicionX() >= b.getPosicionX() && (a.getPosicionX() + a.getAncho()) < (b.getPosicionX() + b.getAncho())){
			sePisan = true;
		}
		
		if(!sePisan && a.getPosicionX() < (b.getPosicionX() + b.getAncho()) && (a.getPosicionX() + a.getAncho()) >= (b.getPosicionX() + b.getAncho())){
			sePisan = true;
		}
		
		//para las puertas
		if(!sePisan  && a.getPosicionX() == b.getPosicionX() && (a.getPosicionX() + a.getAncho()) == (b.getPosicionX() + b.getAncho())){
			sePisan = true;
		}

		return sePisan;
	}
	
	private static boolean sePisanEnY(FormaPosicionableEnMapa a, FormaPosicionableEnMapa b){
		boolean sePisan = false;

		//A empieza arriba de B y termina abajo de la parte superior de B
		if(a.getPosicionY() <= b.getPosicionY() && (a.getPosicionY() + a.getAlto() > b.getPosicionY())){
			sePisan = true;
		}
		
		// A empieza abajo de B y termina arriba de la parte inferior de B
		if(!sePisan && a.getPosicionY() >= b.getPosicionY() && (a.getPosicionY() +a.getAlto() < (b.getPosicionY() + b.getAlto()))){
			sePisan = true;
		}
		
		// A empieza arriba de la parte inferior de B y termina abajo de la parte inferior de B
		if(!sePisan && a.getPosicionY() <  (b.getPosicionY() + b.getAlto()) && (a.getPosicionY() + a.getAlto()) >= (b.getPosicionY() + b.getAlto())){
			sePisan = true;
		}
		
		if(!sePisan && a.getPosicionY() == b.getPosicionY() && (a.getPosicionY() + a.getAlto()) == (b.getPosicionY() + b.getAlto())){
			sePisan = true;
		}
		

		return sePisan;
	}
	
	
	public static boolean validarSiLaPuertaEstaEnUnaParedValida(Puerta puerta, Mapa mapa, Recinto recinto){
		boolean posicionValida = false;
		if(validarSiLaPuertaEntraEnElMapa(mapa, puerta) && validarSiLaPuertaEstaEnUnaParedDelRecinto(recinto,puerta)
				&& !sePisaConOtrasPuertasDelRecinto(recinto,puerta)){
			posicionValida = true;
		}
		return posicionValida;
	}
	
	private static boolean validarSiLaPuertaEstaEnUnaParedDelRecinto(Recinto recinto, Puerta puerta) {
		boolean xOK = false;
		boolean yOK = false;
		
		if(puerta.getOrientacion().equals(Orientacion.Horizontal)){
			if(puerta.posicionX >= recinto.getBordeSuperiorIzquierdo().getX() && puerta.getPosicionFinalX() <= recinto.getBordeSuperiorDerecho().getX()){
				xOK = true;
			}
			if(puerta.getPosicionY() == recinto.getBordeSuperiorDerecho().getY() || puerta.getPosicionY() == recinto.getBordeInferiorDerecho().getY()){
				yOK = true;
			}
		}
		else{
			if(puerta.getPosicionY() >= recinto.getBordeSuperiorDerecho().getY() && puerta.getPosicionFinalY() <= recinto.getBordeInferiorDerecho().getY()){
				yOK = true;
			}
			if(puerta.getPosicionX() == recinto.getBordeSuperiorIzquierdo().getX() || puerta.getPosicionX() == recinto.getBordeSuperiorDerecho().getX()){
				xOK = true;
			}
		}
		
		return (xOK && yOK);
	}

	private static boolean validarSiLaPuertaEntraEnElMapa(Mapa mapa, Puerta puerta){
		boolean entraEnX = false;
		boolean entraEnY = false;
		if (puerta.getPosicionX() > 0 && puerta.getPosicionFinalX() < mapa.getTamanioX()){
			entraEnX = true;
		}
		
		if(puerta.getPosicionY() > 0 && puerta.getPosicionFinalY() < mapa.getTamanioY()){
			entraEnY = true;
		}
		
		return (entraEnX && entraEnY);
	}
	
	private static boolean sePisaConOtrasPuertasDelRecinto(Recinto recinto, Puerta puerta){
		Collection <Puerta> puertasDelRecinto = recinto.getPuertas();
		Iterator <Puerta> iterador = puertasDelRecinto.iterator();
		boolean sePisa  = false;
		while(iterador.hasNext() && sePisa == false){
			sePisa = seSuperponenLasFormas(puerta,iterador.next());
		}
		
		return sePisa;
	}
	

}
