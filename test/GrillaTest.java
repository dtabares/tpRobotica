import org.junit.Test;

import clases.Contador;
import clases.Grilla;
import clases.Mapa;
import clases.Recinto;
import junit.framework.Assert;

public class GrillaTest {
	
	@SuppressWarnings("deprecation")
	@Test
	public void creoUnaGrilla(){

		Mapa mapa = new Mapa(800,600);
		Contador contador = Contador.getContador();
		contador.setProximoNumeroDeCuadrante(0);
		

		Recinto nuevoRecinto = new Recinto(30, 10, 100, 100, "Nuevo Recinto");
		
		Grilla grilla = new Grilla(nuevoRecinto,50,1);
		
		
		Assert.assertEquals(4, contador.getProximoNumeroDeCuadrante());
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void creoDosGrillas(){
		Contador contador = Contador.getContador();
		contador.setProximoNumeroDeCuadrante(0);
		Mapa mapa = new Mapa(800,600);
		

		Recinto nuevoRecinto = new Recinto(30, 10, 100, 100, "Nuevo Recinto");
		Recinto nuevoRecinto2 = new Recinto(150, 20, 200, 100, "Nuevo Recinto");
		
		@SuppressWarnings("unused")
		Grilla grilla = new Grilla(nuevoRecinto,50,1);
		Grilla grilla2 = new Grilla(nuevoRecinto2,100,1);
		
		
		Assert.assertEquals(6, contador.getProximoNumeroDeCuadrante());
		
	}

}
