package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;

public class PlantaSolar extends PlantaRenovable {

    private double superficieCubierta;
    private double cantPaneles;
    private double horasRadiacion;

    
    
        public PlantaSolar(String nombre, String direccion, double capMaxGeneracion, boolean estadoPlanta, LocalDate fechaFuncionamiento, String fuentePrimaria,double eficienciaPromedio,double superficieCubierta, double cantPaneles, double horasRadiacion) {
    	super(nombre,direccion,capMaxGeneracion,estadoPlanta,fechaFuncionamiento,fuentePrimaria,eficienciaPromedio);
    	
    	
        this.superficieCubierta = superficieCubierta;
        this.cantPaneles = cantPaneles;
        this.horasRadiacion = horasRadiacion;
        
    }
	@Override
	public boolean cumpleCuota() {
		// TODO Auto-generated method stub
		return false;
	}
}