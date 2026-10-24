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
		if(nombre== null|| nombre =="") {
			throw new RuntimeException("Nombre no válido");
		}
		if (direccion == null || direccion=="") {
	        throw new RuntimeException("Dirección no válida");
	    }
		if (capacidadMaxima <= 0) {
	        throw new RuntimeException("Capacidad máxima no válida");
	    }
		if (fechaHoraPuestaEnFuncionamiento == null) {
			throw new RuntimeException("Fecha no válida");
		}
		
		this.nombre=nombre;
		this.direccion=direccion;
		this.capacidadMaxima=capacidadMaxima;
		this.estadoPlanta= true;
		this.fechaHoraPuestaEnFuncionamiento=fechaHoraPuestaEnFuncionamiento;
		this.registroInactividad = new HashMap<>();
	}
	public void desactivar(LocalDateTime inicioDeInactividad) {
		if(estadoPlanta) {
			registroInactividad.put(inicioDeInactividad, null); //El inicio de la inactividad es la fecha dada, y no hay fin de inactividad de momento.
			inicioInactividad=inicioDeInactividad;
			this.estadoPlanta=false;}
			
		}
	
	
	
	
	public void activar(LocalDateTime finDeInactividad) {
		if(!estadoPlanta) {
			
			if(inicioInactividad!=null) {
				
				registroInactividad.put(inicioInactividad,finDeInactividad); //registra el inicio de inactividad(si ya estaba inactiva antes), y la fecha en la que se activa
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
	
	@Override
	public String toString() {
		StringBuilder st = new StringBuilder();
		
		st.append("Nombre: ").append(nombre).append("\n");
		st.append("Dirección: ").append(direccion).append("\n");
		st.append("Capacidad máxima de generación: ").append(capacidadMaxima).append("\n");
		String estado; //esto es para guardar el estado
        
        if (estaActiva()) {
            estado = "ACTIVA";
        } else {
            estado = "INACTIVA";
        }
		st.append("Estado: ").append(estado).append("\n");
		st.append("Puesta en funcionamiento: ").append(fechaHoraPuestaEnFuncionamiento).append("\n");
		
		return st.toString();
	}
	
}
