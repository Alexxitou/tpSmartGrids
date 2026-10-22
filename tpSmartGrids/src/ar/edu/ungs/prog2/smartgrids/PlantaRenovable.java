package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;

public abstract class PlantaRenovable extends Planta {
	
	private String fuentePrimaria;
	private double eficienciaPromedio;
	
	
	public PlantaRenovable(String nombre, String direccion, double capMaxGeneracion, LocalDate fechaFuncionamiento, String fuentePrimaria,double eficienciaPromedio) {
		super(nombre,direccion,capMaxGeneracion,fechaFuncionamiento);
		this.fuentePrimaria=fuentePrimaria;
		this.eficienciaPromedio=eficienciaPromedio;
	}
	public boolean esRenovable() {
		return false;
	}
	public String getFuentePrimaria() {
		return "";
	}
	@Override
	public abstract boolean cumpleCuota() ;
	
	/*@Override
	public String toString() {

		if (estadoPlanta) {
		return "Nombre: "+nombre+"/n"+
				"direccion: "+direccion+"/n"+
				"capacidad Máxima de Generación: "+capMaxGeneracion+"/n"+
				"Estado de la planta: "+"ACTIVA"+"/n"+
				"Fecha puesta en funcionamiento: "+fechaFuncionamiento+"/n"+
				"Fuente Primaria: "+fuentePrimaria+"/n"+
				"Eficiencia Promedio"+
		} else {
			return "Nombre: "+nombre+"/n"+
					"direccion: "+direccion+"/n"+
					"capacidad Máxima de Generación: "+capMaxGeneracion+"/n"+
					"Estado de la planta: "+"DESACTIVADA"+"/n"+
					"Fecha puesta en funcionamiento: "+fechaFuncionamiento+"/n";
		}
	
	}*/
	
}
