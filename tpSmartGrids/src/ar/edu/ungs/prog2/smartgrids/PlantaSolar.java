package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;

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
	public boolean cumpleCuota() {
		// TODO Auto-generated method stub
		return false;
	}
}