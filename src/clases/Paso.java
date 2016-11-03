package clases;

public class Paso {
	private double direccionEnGrados;
	private int numeroDeBaldozas;
	
	public Paso(double direccion, int baldozas){
		this.direccionEnGrados = direccion;
		this.numeroDeBaldozas = baldozas;
	}
	
	
	public double getDireccionEnGrados() {
		return direccionEnGrados;
	}
	public int getNumeroDeBaldozas() {
		return numeroDeBaldozas;
	}
	
	
}
