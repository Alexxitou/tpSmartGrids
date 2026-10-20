package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;

public class PlantaHidraulica extends PlantaRenovable{

	private int cantTurbinas;
	private double capacidadEmbalse;
	
	public PlantaHidraulica(String nombre, String direccion, double capMaxGeneracion, boolean estadoPlanta, LocalDate fechaFuncionamiento, String fuentePrimaria,double eficienciaPromedio,int cantTurbinas, double capacidadEmbalse) {
		super(nombre,direccion,capMaxGeneracion,estadoPlanta,fechaFuncionamiento,fuentePrimaria,eficienciaPromedio);
		this.cantTurbinas=cantTurbinas;
		this.capacidadEmbalse=capacidadEmbalse;
	}

	@Override
	public boolean cumpleCuota() {
		// TODO Auto-generated method stub
		return false;
	}

}
