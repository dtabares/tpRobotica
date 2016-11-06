import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clases.Contador;
import clases.Grilla;
import clases.Mapa;
import clases.Obstaculo;
import clases.Obstaculos;
import clases.Orientacion;
import clases.Puerta;
import clases.Recinto;
import clases.Validador;

public class ValidadorTest {
	
    @Before public void initialize() {
        Contador.getContador().setProximoNumeroDeCuadrante(0);
     }

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
		Obstaculo obstaculo = new Obstaculo((float)40, (float)50,Obstaculos.Armario);
		
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
		Obstaculo obstaculo = new Obstaculo((float)-50, (float)-30, Obstaculos.Armario);
		
		Assert.assertFalse(Validador.validarSiElObstaculoEntraEnElRecinto(recinto, obstaculo));
	}
	
	@Test
	public void creoUnObstaculoDentroDelRecintoYDelMapa() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recinto = new Recinto(30, 10, 100, 100,"Recinto 1");
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un obstaculo y lo posiciono dentro del recinto
		Obstaculo obstaculo = new Obstaculo((float)35, (float)10, Obstaculos.Mesa);
		
		Assert.assertTrue(Validador.validarSiElObstaculoEntraEnElRecinto(recinto, obstaculo));
	}
	
	@Test
	public void creoDosObstaculosQueSePisan() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recinto = new Recinto(30, 10, 100, 100,"Recinto 1");
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un obstaculo y lo posiciono dentro del recinto
		Obstaculo obstaculo1 = new Obstaculo((float)35, (float)10,Obstaculos.Mesa);
		
		Assert.assertTrue(Validador.validarSiElObstaculoEntraEnElRecinto(recinto, obstaculo1));
		
		recinto.agregarObstaculo(obstaculo1);
		
		Assert.assertFalse(recinto.getObstaculos().isEmpty());
		
		//Creo un segundo obstaculo y lo posiciono dentro del recinto pisando al primero
		Obstaculo obstaculo2 = new Obstaculo((float)35, (float)10, Obstaculos.Armario);
		
		Assert.assertFalse(Validador.validarSiElObstaculoEntraEnElRecinto(recinto, obstaculo2));
		
		
	}
	
	@Test
	public void creoDosObstaculosQueNoSePisan() {
		
		//Creo un mapa de 200 por 200
		Mapa mapa = new Mapa(200,200);
		
		//Creo un recinto de 10 por 10 y lo ubico en la posicion (30,10)
		Recinto recinto = new Recinto(30, 10, 100, 100,"Recinto 1");
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		Assert.assertFalse(mapa.getRecintos().isEmpty());
		
		//Creo un obstaculo y lo posiciono dentro del recinto
		Obstaculo obstaculo1 = new Obstaculo((float)35, (float)10, Obstaculos.Mesa);
		
		Assert.assertTrue(Validador.validarSiElObstaculoEntraEnElRecinto(recinto, obstaculo1));
		
		recinto.agregarObstaculo(obstaculo1);
		
		Assert.assertFalse(recinto.getObstaculos().isEmpty());
		
		//Creo un segundo obstaculo y lo posiciono dentro del recinto pisando al primero
		Obstaculo obstaculo2 = new Obstaculo((float)70, (float)10, Obstaculos.Silla);
		
		Assert.assertTrue(Validador.validarSiElObstaculoEntraEnElRecinto(recinto, obstaculo2));
		
		
	}
	
	@Test
	public void pongoUnaPuertaFueraDelMapa(){
		//Creo un mapa de 800 por 600
		Mapa mapa = new Mapa(800,600);
		
		//Creo un recinto de 100 por 100 y lo ubico en la posicion (0,0)
		Recinto recinto = new Recinto(0, 0, 100, 100,"Recinto 1");
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		//Creo una puerta y la posicionoFueraDelMapa()
		Puerta puerta = new Puerta((float)900,(float)600,Orientacion.Horizontal,(float)20);
		
		
		Assert.assertFalse(Validador.validarSiLaPuertaEstaEnUnaParedValida(puerta, mapa, recinto));
	}
	
	@Test
	public void pongoUnaPuertaFueraDelRecintoPeroDentroDelMapa(){
		//Creo un mapa de 800 por 600
		Mapa mapa = new Mapa(800,600);
		
		//Creo un recinto de 100 por 100 y lo ubico en la posicion (0,0)
		Recinto recinto = new Recinto(0, 0, 100, 100,"Recinto 1");
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		//Creo una puerta y la posiciono Fuera Del recinto
		Puerta puerta = new Puerta((float)500,(float)40,Orientacion.Horizontal,(float)20);
		
		
		Assert.assertFalse(Validador.validarSiLaPuertaEstaEnUnaParedValida(puerta, mapa, recinto));
	}
	
	@Test
	public void pongoUnaPuertaQueToqueAlRecintoPeroNoEstePosicionadoEnUnaPared(){
		//Creo un mapa de 800 por 600
		Mapa mapa = new Mapa(800,600);
		
		//Creo un recinto de 100 por 100 y lo ubico en la posicion (0,0)
		Recinto recinto = new Recinto(0, 0, 100, 100,"Recinto 1");
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		//Creo una puerta
		Puerta puerta = new Puerta((float)0,(float)40,Orientacion.Horizontal,(float)20);
		
		
		Assert.assertFalse(Validador.validarSiLaPuertaEstaEnUnaParedValida(puerta, mapa, recinto));
	}
	
	@Test
	public void pongoUnaPuertaEnUnaParedLinderaConElLimiteDelMapa(){
		//Creo un mapa de 800 por 600
		Mapa mapa = new Mapa(800,600);
		
		//Creo un recinto de 100 por 100 y lo ubico en la posicion (0,0)
		Recinto recinto = new Recinto(0, 0, 100, 100,"Recinto 1");
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		Puerta puerta = new Puerta((float)10,(float)0,Orientacion.Horizontal,(float)20);
		
		
		Assert.assertFalse(Validador.validarSiLaPuertaEstaEnUnaParedValida(puerta, mapa, recinto));
	}
	
	@Test
	public void pongoUnaPuertaEnUnaPosicionValidaEnCadaParedDelRecinto(){
		//Creo un mapa de 800 por 600
		Mapa mapa = new Mapa(800,600);

		Recinto recinto = new Recinto(10, 10, 100, 100,"Recinto 1");
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		Puerta puerta1 = new Puerta((float)10,(float)10,Orientacion.Horizontal,(float)20);
		Puerta puerta2 = new Puerta((float)10,(float)110,Orientacion.Horizontal,(float)20);
		Puerta puerta3 = new Puerta((float)10,(float)10,Orientacion.Vertical,(float)20);
		Puerta puerta4 = new Puerta((float)110,(float)10,Orientacion.Vertical,(float)20);
		
		
		Assert.assertTrue(Validador.validarSiLaPuertaEstaEnUnaParedValida(puerta1, mapa, recinto));
		Assert.assertTrue(Validador.validarSiLaPuertaEstaEnUnaParedValida(puerta2, mapa, recinto));
		Assert.assertTrue(Validador.validarSiLaPuertaEstaEnUnaParedValida(puerta3, mapa, recinto));
		Assert.assertTrue(Validador.validarSiLaPuertaEstaEnUnaParedValida(puerta4, mapa, recinto));
	}

}
