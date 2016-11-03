package clases;

public class Contador {
	
	private static Contador instance = null;
	private int proximoNumeroDeCuadrante;
	protected Contador() {
		proximoNumeroDeCuadrante = 0;
	}
	public static Contador getContador() {
		if (instance == null){
			instance = new Contador();
		}
		return instance;
	}
	public int getProximoNumeroDeCuadrante() {
		return proximoNumeroDeCuadrante;
	}
	public void setProximoNumeroDeCuadrante(int numeroDeCuadrante) {
		this.proximoNumeroDeCuadrante = numeroDeCuadrante;
	}
	
}