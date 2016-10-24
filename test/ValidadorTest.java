import org.junit.Assert;
import org.junit.Test;

import clases.Mapa;
import clases.Obstaculo;
import clases.Recinto;
import clases.Validador;

public class ValidadorTest {

	@Test
	public void CreoUnRecintoValidoYLoPosicionoFueraDelMapa() {
		
		//Creo un mapa de 20 por 20
		Mapa mapa = new Mapa(20,20);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		//O sea, fuera del mapa
		Recinto nuevoRecinto = new Recinto(30, 10, 10, 10, "Nuevo Recinto");
		
		Assert.assertFalse(Validador.validarSiElRecintoEntraEnElMapa(mapa, nuevoRecinto));
	}
	
	@Test
	public void CreoUnRecintoValidoYLoPosicionoDentroDelMapa() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		//O sea, dentro del mapa
		Recinto nuevoRecinto = new Recinto(30, 10, 10, 10, "Nuevo Recinto");
		
		Assert.assertTrue(Validador.validarSiElRecintoEntraEnElMapa(mapa, nuevoRecinto));
	}
	
	@Test
	public void AgregoDosRecintosIgualesALaMismaPosicion() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recintoUno = new Recinto(30, 10, 10, 10, "Recinto 1");
		
		//Agrego el recinto 1 al mapa
		mapa.agregarRecinto(recintoUno);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un recinto igual al primero y lo posiciono en exactamente la misma pos
		Recinto recintoDos = new Recinto(30, 10, 10, 10, "Recinto 2");
		
		Assert.assertFalse(Validador.validarSiElRecintoEntraEnElMapa(mapa, recintoDos));
	}
	
	@Test
	public void dosRecintosQueSePisanUnoDentroDelOtro() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recintoUno = new Recinto(10, 10, 10, 10,"Recinto 1");
		
		//Agrego el recinto 1 al mapa
		mapa.agregarRecinto(recintoUno);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un nuevo recinto y lo posiciono dentro del primero
		Recinto recintoDos = new Recinto(12, 12, 5, 5,"Recinto 2");
		
		Assert.assertFalse(Validador.validarSiElRecintoEntraEnElMapa(mapa, recintoDos));
	}
	
	@Test
	public void dosRecintosQueNoSePisan() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recintoUno = new Recinto(30, 10, 10, 10,"Recinto 1");
		
		//Agrego el recinto 1 al mapa
		mapa.agregarRecinto(recintoUno);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un nuevo recinto y lo posiciono dentro del primero
		Recinto recintoDos = new Recinto(12, 12, 5, 5,"Recinto 2");
		
		Assert.assertTrue(Validador.validarSiElRecintoEntraEnElMapa(mapa, recintoDos));
	}
	
	@Test
	public void creoUnObstaculoFueraDelRecinto() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recinto = new Recinto(30, 10, 10, 10,"Recinto 1");
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un obstaculo y lo posiciono fuera del recinto
		Obstaculo obstaculo = new Obstaculo(40, 50, 10, 10);
		
		Assert.assertFalse(Validador.validarSiElObstaculoEntraEnElRecinto(recinto, obstaculo));
	}
	
	@Test
	public void creoUnObstaculoFueraDelRecintoYDelMapa() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recinto = new Recinto(30, 10, 10, 10,"Recinto 1");
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un obstaculo y lo posiciono fuera del recinto
		Obstaculo obstaculo = new Obstaculo(-50, -30, 10, 10);
		
		Assert.assertFalse(Validador.validarSiElObstaculoEntraEnElRecinto(recinto, obstaculo));
	}
	
	@Test
	public void creoUnObstaculoDentroDelRecintoYDelMapa() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recinto = new Recinto(30, 10, 10, 10,"Recinto 1");
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un obstaculo y lo posiciono dentro del recinto
		Obstaculo obstaculo = new Obstaculo(35, 10, 2, 1);
		
		Assert.assertTrue(Validador.validarSiElObstaculoEntraEnElRecinto(recinto, obstaculo));
	}
	
	@Test
	public void creoDosObstaculosQueSePisan() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recinto = new Recinto(30, 10, 10, 10,"Recinto 1");
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un obstaculo y lo posiciono dentro del recinto
		Obstaculo obstaculo1 = new Obstaculo(35, 10, 2, 1);
		
		Assert.assertTrue(Validador.validarSiElObstaculoEntraEnElRecinto(recinto, obstaculo1));
		
		recinto.agregarObstaculo(obstaculo1);
		
		Assert.assertFalse(recinto.getObstaculos().isEmpty());
		
		//Creo un segundo obstaculo y lo posiciono dentro del recinto pisando al primero
		Obstaculo obstaculo2 = new Obstaculo(36, 10, 2, 1);
		
		Assert.assertFalse(Validador.validarSiElObstaculoEntraEnElRecinto(recinto, obstaculo2));
		
		
	}
	
	@Test
	public void creoDosObstaculosQueNoSePisan() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recinto = new Recinto(30, 10, 10, 10,"Recinto 1");
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un obstaculo y lo posiciono dentro del recinto
		Obstaculo obstaculo1 = new Obstaculo(35, 10, 2, 1);
		
		Assert.assertTrue(Validador.validarSiElObstaculoEntraEnElRecinto(recinto, obstaculo1));
		
		recinto.agregarObstaculo(obstaculo1);
		
		Assert.assertFalse(recinto.getObstaculos().isEmpty());
		
		//Creo un segundo obstaculo y lo posiciono dentro del recinto pisando al primero
		Obstaculo obstaculo2 = new Obstaculo(31, 10, 1, 1);
		
		Assert.assertTrue(Validador.validarSiElObstaculoEntraEnElRecinto(recinto, obstaculo2));
		
		
	}

}
