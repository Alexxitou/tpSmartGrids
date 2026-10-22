package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;

public class PlantaTermica extends Planta{

	private String tipoCombustible;
	private double objetivoEmisionesCO2;
	private double capAlmacenamientoCombustible;
	
	public PlantaTermica(String nombre, String direccion, double capMaxGeneracion, LocalDate fechaFuncionamiento, String tipoCombustible, double objetivoEmisionesCO2, double capAlmacenamientoCombustible) {
		super(nombre,direccion,capMaxGeneracion,fechaFuncionamiento);
		this.tipoCombustible=tipoCombustible;
		this.objetivoEmisionesCO2=objetivoEmisionesCO2;
		this.capAlmacenamientoCombustible=capAlmacenamientoCombustible;
	
	}
	
	@Override
	public boolean cumpleCuota() {
		// TODO Auto-generated method stub
		return false;
	}

}
