package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;
import java.util.HashMap;

public abstract class Planta {
	
	protected String nombre;
	protected String direccion;
	protected double capacidadMaxima;
	protected boolean estadoPlanta;
	protected String  fechaHoraPuestaEnFuncionamiento;
	protected HashMap<LocalDateTime, LocalDateTime> registroInactividad; //el primer LocalDateTime registra el inicio, y el segundo, el final.
	protected LocalDateTime inicioInactividad;
	
	
	public Planta(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento) {
		this.nombre=nombre;
		this.direccion=direccion;
		this.capacidadMaxima=capacidadMaxima;
		this.estadoPlanta= true;
		this.fechaHoraPuestaEnFuncionamiento=fechaHoraPuestaEnFuncionamiento;
		
		
	
		
	}
	public void desactivar(LocalDateTime fecha) {
		if(estadoPlanta) {
			registroInactividad.put(fecha, null); //El inicio de la inactividad es la fecha dada, y no hay fin de inactividad de momento.
			inicioInactividad=fecha;
			this.estadoPlanta=false;}
			
		}
	
	
	
	
	public void activar(LocalDateTime fecha) {
		if(!estadoPlanta) {
			
			if(inicioInactividad!=null) {
				
				registroInactividad.put(inicioInactividad,fecha); //registra el inicio de inactividad(si ya estaba inactiva antes), y la fecha en la que se activa
				inicioInactividad=null; //inicioInactividad se convierte en null porque ya no hay inactividad
			}
			this.estadoPlanta=true;
			
			
		}
	
	
		
		
		
		
	}
	
	public boolean estaActiva() {
		return estadoPlanta; 
	}
	
	public abstract boolean cumpleCuota(Monitoreo monitoreo);
	
	public abstract double capacidadMinimaCuota();

	
	
	public String getNombre() {
		return nombre;		
	}
	public String getFechaHoraPuestaEnFuncionamiento() {
		return fechaHoraPuestaEnFuncionamiento;
	}
	public HashMap<LocalDateTime, LocalDateTime> getRegistroInactividad() {
		return registroInactividad;
	}
	
	
	
}
