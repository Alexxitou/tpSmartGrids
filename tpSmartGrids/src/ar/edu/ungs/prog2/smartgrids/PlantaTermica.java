package ar.edu.ungs.prog2.smartgrids;


public class PlantaTermica extends Planta{

	private String tipoCombustible;
	private double objetivoEmisionesCo2PorHora;
	private double capacidadAlmacenamientoCombustible;
	
	public PlantaTermica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String tipoCombustible, double objetivoEmisionesCo2PorHora, double capacidadAlmacenamientoCombustible) {
		super(nombre,direccion,capacidadMaxima,fechaHoraPuestaEnFuncionamiento);
		if(tipoCombustible==null || tipoCombustible=="") {
        	throw new RuntimeException("tipoCombustible no válido");
        }
		if(objetivoEmisionesCo2PorHora<0) {
			throw new RuntimeException("objetivoEmisionesCo2PorHora no válido");
		}
		if(capacidadAlmacenamientoCombustible<0) {
			throw new RuntimeException("capacidadAlmacenamientoCombustible no válido");
		}
        
		
		
		this.tipoCombustible=tipoCombustible;
		this.objetivoEmisionesCo2PorHora=objetivoEmisionesCo2PorHora;
		this.capacidadAlmacenamientoCombustible=capacidadAlmacenamientoCombustible;
	
	}

	


	@Override
	public double capacidadMinimaCuota() {
	    	return capacidadMaxima/2;
		
	}


	@Override
	public boolean cumpleCuota(Monitoreo monitoreo) {
		if(!(monitoreo instanceof MonitoreoTermica)) { //Para descartar los monitoreos que no sean MonitoreoTermica que se pasen como parámetro
			throw new RuntimeException("Se requiere un monitoreoTermica");
			
		}
		MonitoreoTermica mt = (MonitoreoTermica) monitoreo; 
		
		double emisionesCO2PorHora= mt.getCO2generado();
		double energiaGenerada=mt.getEnergiaGenerada();
		
		if (emisionesCO2PorHora <objetivoEmisionesCo2PorHora && energiaGenerada>=capacidadMinimaCuota()){
			return true;
		}
		return false;
		
	}



}
