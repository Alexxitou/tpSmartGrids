package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;

public abstract class Planta {
	
	protected String nombre;
	protected String direccion;
	protected double capMaxGeneracion;
	protected boolean estadoPlanta;
	protected LocalDate  fechaFuncionamiento;
	
	
	public Planta(String nombre, String direccion, double capMaxGeneracion, LocalDate fechaFuncionamiento) {
		this.nombre=nombre;
		this.direccion=direccion;
		this.capMaxGeneracion=capMaxGeneracion;
		this.estadoPlanta= true;
		this.fechaFuncionamiento=fechaFuncionamiento;
		
	}
	public void activar() {
		this.estadoPlanta=true;
	}
	public void desactivar() {
		this.estadoPlanta=false;
	}
	
	public boolean estaActiva() {
		return estadoPlanta; 
	}
	public double cuotaMinima() {
		return 2; 
	}
	public abstract boolean cumpleCuota();
	
	
	
	
	
	public String mostrarInfo() {
		return toString();
		
	}
	@Override
	public  String toString() {
		if (estadoPlanta) {
		return "Nombre: "+nombre+"\n"+
				"direccion: "+direccion+"\n"+
				"capacidad Máxima de Generación: "+capMaxGeneracion+"\n"+
				"Estado de la planta: "+"ACTIVA"+"\n"+
				"Fecha puesta en funcionamiento: "+fechaFuncionamiento+"\n";
		} else {
			return "Nombre: "+nombre+"\n"+
					"direccion: "+direccion+"\n"+
					"capacidad Máxima de Generación: "+capMaxGeneracion+"\n"+
					"Estado de la planta: "+"DESACTIVADA"+"\n"+
					"Fecha puesta en funcionamiento: "+fechaFuncionamiento+"\n";
		}
	}
	public String getNombre() {
		return nombre;		
	}
}
