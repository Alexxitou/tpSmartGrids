package ar.edu.ungs.prog2.smartgrids;


public abstract class PlantaRenovable extends Planta {
	
	private String fuentePrimaria;
	protected double eficienciaPromedio;
	
	
	public PlantaRenovable(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria,double eficienciaPromedio) {
		super(nombre,direccion,capacidadMaxima,fechaHoraPuestaEnFuncionamiento);
		if (fuentePrimaria==null || fuentePrimaria=="") {
			throw new RuntimeException("Fuente Primaria no válida");
		}
		if (eficienciaPromedio<0 || eficienciaPromedio>100) {
			throw new RuntimeException("Valor de eficiencia Promedio no válida");
		}
		this.fuentePrimaria=fuentePrimaria;
		this.eficienciaPromedio=eficienciaPromedio;
	}
	
	
	
	public boolean esRenovable() {
		return false;
	}
	public String getFuentePrimaria() {
		return "";
	}
	public abstract double capacidadMinimaCuota();
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
