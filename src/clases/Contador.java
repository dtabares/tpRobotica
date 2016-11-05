package clases;

import java.io.Serializable;

public class Contador implements Serializable{
	
	private static final long serialVersionUID = 2688427677898794473L;
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
	
	public void resetearContador(){
		this.proximoNumeroDeCuadrante = 0;
	}
	
}