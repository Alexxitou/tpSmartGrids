package ar.edu.ungs.prog2.smartgrids;


public class PlantaSolar extends PlantaRenovable {

    private double superficieCubierta;
    private double cantidadPaneles;
    private double horasRadiacionDiaria;
        
	public PlantaSolar(String nombre, String direccion, double capacidadMaxima,
				String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
				double superficieCubierta, int cantidadPaneles, double horasRadiacionDiaria) {
		
		super(nombre,direccion,capacidadMaxima,fechaHoraPuestaEnFuncionamiento,fuentePrimaria,eficienciaPromedio);
        if(superficieCubierta<0) {
        	throw new RuntimeException("superfieCubierta no puede ser menor a cero");
        }
        if(cantidadPaneles<0) {
        	throw new RuntimeException("CantidadPaneles no puede ser menor a cero");
        }
        if(horasRadiacionDiaria<0||horasRadiacionDiaria>24) {
        	throw new RuntimeException("las horas de radiación deben ser entre 0 y 23 incluidos.");
        }
		this.superficieCubierta = superficieCubierta;
        this.cantidadPaneles = cantidadPaneles;
        this.horasRadiacionDiaria = horasRadiacionDiaria;
	}

	@Override
	public double capacidadMinimaCuota() {
		return capacidadMaxima * (eficienciaPromedio/100) * horasRadiacionDiaria;
	}

	@Override
	public boolean cumpleCuota(Monitoreo monitoreo) {
		 return monitoreo.getEnergiaGenerada()>= capacidadMinimaCuota();
	}
	
	@Override
	public String toString() {
		StringBuilder st= new StringBuilder();
		
		st.append(super.toString()).append("\n");
		st.append("Superficie Cubierta: ").append(superficieCubierta).append("\n");
		st.append("Cantidad de Paneles: ").append(cantidadPaneles).append("\n");
		st.append("Horas de radiación diaria: ").append(horasRadiacionDiaria).append("\n");
		
		return st.toString();
	}

	@Override
	public boolean esRenovable() {

		return true;
	}

	@Override
	public String getFuentePrimaria() {
		return fuentePrimaria;
	}
	
}