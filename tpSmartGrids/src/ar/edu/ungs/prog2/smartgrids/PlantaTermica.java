package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;

public class PlantaTermica extends Planta{

	private String tipoCombustible;
	private double objetivoEmisionesCo2PorHora;
	private double capacidadAlmacenamientoCombustible;
	
	public PlantaTermica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String tipoCombustible, double objetivoEmisionesCo2PorHora, double capacidadAlmacenamientoCombustible) {
		super(nombre,direccion,capacidadMaxima,fechaHoraPuestaEnFuncionamiento);
		this.tipoCombustible=tipoCombustible;
		this.objetivoEmisionesCo2PorHora=objetivoEmisionesCo2PorHora;
		this.capacidadAlmacenamientoCombustible=capacidadAlmacenamientoCombustible;
	
	}

	
	@Override
	public boolean cumpleCuota() {
		// TODO Auto-generated method stub
		return false;
	}

}
