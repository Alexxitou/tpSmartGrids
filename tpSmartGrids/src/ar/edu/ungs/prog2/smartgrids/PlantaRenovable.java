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
	
	@Override
	public String toString() {
		StringBuilder st = new StringBuilder();
		
		st.append(super.toString()).append("\n");
		st.append("Fuente Primaria: ").append(fuentePrimaria).append("\n");
		st.append("Eficiencia Promedio: ").append(eficienciaPromedio).append("%\n");
		return st.toString();
	}
}

