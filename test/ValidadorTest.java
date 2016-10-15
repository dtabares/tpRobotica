

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import application.Mapa;
import application.Recinto;
import application.Validador;

public class ValidadorTest {

	@Test
	public void CreoUnRecintoValidoYLoPosicionoFueraDelMapa() {
		
		//Creo un mapa de 20 por 20
		Mapa mapa = new Mapa(20,20);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		//O sea, fuera del mapa
		Recinto nuevoRecinto = new Recinto(30, 10, 10, 10);
		
		Assert.assertFalse(Validador.validarSiElRecintoEntraEnElMapa(mapa, nuevoRecinto));
	}
	
	@Test
	public void CreoUnRecintoValidoYLoPosicionoDentroDelMapa() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		//O sea, dentro del mapa
		Recinto nuevoRecinto = new Recinto(30, 10, 10, 10);
		
		Assert.assertTrue(Validador.validarSiElRecintoEntraEnElMapa(mapa, nuevoRecinto));
	}
	
	@Test
	public void AgregoDosRecintosIgualesALaMismaPosicion() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recintoUno = new Recinto(30, 10, 10, 10);
		
		//Agrego el recinto 1 al mapa
		mapa.agregarRecinto(recintoUno);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un recinto igual al primero y lo posiciono en exactamente la misma pos
		Recinto recintoDos = new Recinto(30, 10, 10, 10);
		
		Assert.assertFalse(Validador.validarSiElRecintoEntraEnElMapa(mapa, recintoDos));
	}
	
	@Test
	public void dosRecintosQueSePisanUnoDentroDelOtro() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recintoUno = new Recinto(10, 10, 10, 10);
		
		//Agrego el recinto 1 al mapa
		mapa.agregarRecinto(recintoUno);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un nuevo recinto y lo posiciono dentro del primero
		Recinto recintoDos = new Recinto(12, 12, 5, 5);
		
		Assert.assertFalse(Validador.validarSiElRecintoEntraEnElMapa(mapa, recintoDos));
	}
	
	@Test
	public void dosRecintosQueNoSePisan() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recintoUno = new Recinto(30, 10, 10, 10);
		
		//Agrego el recinto 1 al mapa
		mapa.agregarRecinto(recintoUno);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un nuevo recinto y lo posiciono dentro del primero
		Recinto recintoDos = new Recinto(12, 12, 5, 5);
		
		Assert.assertTrue(Validador.validarSiElRecintoEntraEnElMapa(mapa, recintoDos));
	}

}
