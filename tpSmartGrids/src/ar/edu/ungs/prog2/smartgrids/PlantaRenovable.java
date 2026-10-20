package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;

public abstract class PlantaRenovable extends Planta {
	
	private String fuentePrimaria;
	private double eficienciaPromedio;
	
	
	public PlantaRenovable(String nombre, String direccion, double capMaxGeneracion, boolean estadoPlanta, LocalDate fechaFuncionamiento, String fuentePrimaria,double eficienciaPromedio) {
		super(nombre,direccion,capMaxGeneracion,estadoPlanta,fechaFuncionamiento);
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
	
	
}
