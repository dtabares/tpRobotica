import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clases.Contador;
import clases.Coordenada;
import clases.Grilla;
import clases.Mapa;
import clases.Obstaculo;
import clases.Obstaculos;
import clases.Orientacion;
import clases.Puerta;
import clases.Recinto;
import clases.Validador;

public class RecintoTest {
	
    @Before public void initialize() {
        Contador.getContador().setProximoNumeroDeCuadrante(0);
     }
	
	@Test
	public void pongoUnaPuertaQueSePiseConOtra(){
		//Creo un mapa de 800 por 600
		Mapa mapa = new Mapa(800,600);

		Recinto recinto = new Recinto(10, 10, 100, 100,"Recinto 1");
		
		Puerta puerta1 = new Puerta((float)10,(float)10,Orientacion.Horizontal,(float)20);
		Puerta puerta2 = new Puerta((float)15,(float)10,Orientacion.Horizontal,(float)20);
		
		
		recinto.agregarPuerta(puerta1, mapa);
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		
		Assert.assertFalse(Validador.validarSiLaPuertaEstaEnUnaParedValida(puerta2, mapa, recinto));

	}
	
	@Test
	public void obtenerCuadranteAPartirDeUnaPuerta(){
		//Creo un mapa de 800 por 600
		Mapa mapa = new Mapa(800,600);

		Recinto recinto = new Recinto(0, 0, 100, 100,"Recinto 1");
		Grilla grilla = new Grilla(recinto,50,1);
		recinto.setGrilla(grilla);
		Puerta puerta1 = new Puerta((float)60,(float)0,Orientacion.Horizontal,(float)20);
		recinto.agregarPuerta(puerta1, mapa);
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		//El numero 1, vendria ser el cuadrante superior derecho
		
		//	-------__--
		//  |    |     |
		//  |  0 |   1 |
		//  ------------
		//  |    |     |
		//  |  2 |   3 |
		//  ------------
		Assert.assertEquals(1, recinto.obtenerCuadranteCercanoALaPuerta(puerta1).getId());
	}
	
	//En este caso, debe tomar el primero de los cuadrantes
	@Test
	public void obtenerCuadranteAPartirDeUnaPuertaCuandoJustoEstaEntreMedioDeDosCuadrantes(){
		//Creo un mapa de 800 por 600
		Mapa mapa = new Mapa(800,600);

		Recinto recinto = new Recinto(0, 0, 100, 100,"Recinto 1");
		Grilla grilla = new Grilla(recinto,50,1);
		recinto.setGrilla(grilla);
		Puerta puerta = new Puerta((float)40,(float)0,Orientacion.Horizontal,(float)20);
		recinto.agregarPuerta(puerta, mapa);
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		//El numero 1, vendria ser el cuadrante superior derecho
		
		//	----___----
		//  |    |     |
		//  |  0 |   1 |
		//  ------------
		//  |    |     |
		//  |  2 |   3 |
		//  ------------
		Assert.assertEquals(0, recinto.obtenerCuadranteCercanoALaPuerta(puerta).getId());
	}
	
	@Test
	public void obtenerCoordenadaExteriorDeUnaPuertaVertical(){
		//Creo un mapa de 800 por 600
		Mapa mapa = new Mapa(800,600);
		Recinto recintoMapa = new Recinto(0,0,800,600,"rMapa");
		Grilla grillaMapa = new Grilla(recintoMapa,20,1);
		recintoMapa.setGrilla(grillaMapa);
		recintoMapa.agregarObstaculo(new Obstaculo((float)0,(float)10,(float)800,(float)600,Obstaculos.Recinto));
		recintoMapa.getGrilla().prepararGrillaParaDibujo();
		Recinto recinto = new Recinto(0, 10, 100, 100,"Recinto 1");
		Grilla grilla = new Grilla(recinto,50,1);
		recinto.setGrilla(grilla);
		Puerta puerta = new Puerta((float)100,(float)10,Orientacion.Vertical,(float)20);
		recinto.agregarPuerta(puerta, mapa);
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		Coordenada c = recinto.obtenerCoordenadaExteriorDeUnaPuerta(puerta);
		
		Assert.assertEquals(100.5, c.getX(),0);
		Assert.assertEquals(20, c.getY(),0);
	}
	
	@Test
	public void obtenerCoordenadaExteriorDeUnaPuertaHorizontal(){
		//Creo un mapa de 800 por 600
		Mapa mapa = new Mapa(800,600);
		Recinto recintoMapa = new Recinto(0,0,800,600,"rMapa");
		Grilla grillaMapa = new Grilla(recintoMapa,20,1);
		recintoMapa.setGrilla(grillaMapa);
		recintoMapa.agregarObstaculo(new Obstaculo((float)0,(float)10,(float)800,(float)600,Obstaculos.Recinto));
		recintoMapa.getGrilla().prepararGrillaParaDibujo();
		Recinto recinto = new Recinto(0, 10, 100, 100,"Recinto 1");
		Grilla grilla = new Grilla(recinto,50,1);
		recinto.setGrilla(grilla);
		Puerta puerta = new Puerta((float)40,(float)10,Orientacion.Horizontal,(float)20);
		recinto.agregarPuerta(puerta, mapa);
		
		//Agrego el recinto al mapa
		mapa.agregarRecinto(recinto);
		
		Coordenada c = recinto.obtenerCoordenadaExteriorDeUnaPuerta(puerta);
		
		//System.out.println("X: "+ c.getX() + " Y: " + c.getY());
		Assert.assertEquals(50, c.getX(),0);
		Assert.assertEquals(9.5, c.getY(),0);
	}

}
