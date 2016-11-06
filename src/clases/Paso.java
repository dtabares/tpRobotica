package clases;

public class Paso {
	private double direccionEnGrados;
	private int numeroDeBaldosas;
	
	public Paso(double direccion, int baldosas){
		this.direccionEnGrados = direccion;
		this.numeroDeBaldosas = baldosas;
	}
	
	
	public double getDireccionEnGrados() {
		return direccionEnGrados;
	}
	public int getNumeroDeBaldosas() {
		return numeroDeBaldosas;
	}
	
	
}
