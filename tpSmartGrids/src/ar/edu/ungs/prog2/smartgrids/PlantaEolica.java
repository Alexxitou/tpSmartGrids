package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;

public class PlantaEolica extends PlantaRenovable {

    private double cantAerogeneradores;
    private double alturaTorre;
    private double velMinViento;

    public PlantaEolica(String nombre, String direccion, double capMaxGeneracion, boolean estadoPlanta, LocalDate fechaFuncionamiento, String fuentePrimaria,double eficienciaPromedio, double cantAerogeneradores, double velVientoPromedio) {
        super(nombre,direccion,capMaxGeneracion,estadoPlanta,fechaFuncionamiento,fuentePrimaria,eficienciaPromedio);
        this.cantAerogeneradores = cantAerogeneradores;
        this.alturaTorre = alturaTorre;
        this.velMinViento = velVientoPromedio;
    }

	@Override
	public boolean cumpleCuota() {
		// TODO Auto-generated method stub
		return false;
	}



}