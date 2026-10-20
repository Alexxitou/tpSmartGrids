package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;

public abstract class Planta {
	
	private String nombre;
	private String direccion;
	private double capMaxGeneracion;
	private boolean estadoPlanta;
	private LocalDate  fechaFuncionamiento;
	
	
	public Planta(String nombre, String direccion, double capMaxGeneracion, boolean estadoPlanta, LocalDate fechaFuncionamiento) {
		this.nombre=nombre;
		this.direccion=direccion;
		this.capMaxGeneracion=capMaxGeneracion;
		this.estadoPlanta= true;
		this.fechaFuncionamiento=fechaFuncionamiento;
		
	}
	public void activar() {
		
	}
	public void desactivar() {
		
	}
	public double cuotaMinima() {
		return 2;
	}
	public abstract boolean cumpleCuota(Monitoreo);
	
}
