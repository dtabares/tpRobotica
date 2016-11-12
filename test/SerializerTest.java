import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Assert;
import org.junit.Test;

import clases.Orientacion;
import clases.Puerta;
import javafx.geometry.Orientation;

public class SerializerTest {

	@Test
	public void serializePuerta() throws IOException{
		Puerta p = new Puerta((float)0, (float)0, Orientacion.Horizontal, (float)20);
		FileOutputStream fout = new FileOutputStream("/tmp/linea.obj");
    	ObjectOutputStream oos = new ObjectOutputStream(fout);
    	oos.writeObject(p);
    	oos.close();
	}
	
	@Test
	public void deserializePuerta() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream("/tmp/linea.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Puerta p = (Puerta) ois.readObject();
		ois.close();
		p.regenerarFormaObstaculo();
		
		Assert.assertEquals(20, p.getAncho(),0);
	}
}
