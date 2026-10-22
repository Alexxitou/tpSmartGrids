package ar.edu.ungs.prog2.smartgrids;


public class PlantaSolar extends PlantaRenovable {

    private double superficieCubierta;
    private double cantidadPaneles;
    private double horasRadiacionDiaria;
        
	public PlantaSolar(String nombre, String direccion, double capacidadMaxima,
				String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
				double superficieCubierta, int cantidadPaneles, double horasRadiacionDiaria) {
		
		super(nombre,direccion,capacidadMaxima,fechaHoraPuestaEnFuncionamiento,fuentePrimaria,eficienciaPromedio);
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
}