package ar.edu.ungs.prog2.smartgrids;



public class PlantaEolica extends PlantaRenovable {

    private double cantidadAerogeneradores;
    private double alturaTorres;
    private double velocidadMinimaViento;

    public PlantaEolica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria,double eficienciaPromedio, double cantidadAerogeneradores, double alturaTorres, double velocidadMinimaViento) {
        super(nombre,direccion,capacidadMaxima,fechaHoraPuestaEnFuncionamiento,fuentePrimaria,eficienciaPromedio);
        if(cantidadAerogeneradores<0) {
        	throw new RuntimeException("CantidadAerogeneradores no puede ser menor a cero");
        }
        if(alturaTorres<0) {
        	throw new RuntimeException("AlturaTorre no puede ser menor a cero");
        }
        if(velocidadMinimaViento<0) {
        	throw new RuntimeException("velocidadMinimaViento no puede ser menor a cero");
        }
        
        this.cantidadAerogeneradores = cantidadAerogeneradores;
        this.alturaTorres = alturaTorres;
        this.velocidadMinimaViento = velocidadMinimaViento;
    }

	
	@Override
	public boolean cumpleCuota(Monitoreo monitoreo) {
		 return monitoreo.getEnergiaGenerada()>= capacidadMinimaCuota();
		
	}


	@Override
	public double capacidadMinimaCuota() {
		return capacidadMaxima * (eficienciaPromedio/100);
	}

	@Override
	public String toString() {
		StringBuilder st= new StringBuilder();
		
		st.append(super.toString()).append("\n");
		st.append("Cantidad de Aerogeneradores: ").append(cantidadAerogeneradores).append("\n");
		st.append("Altura de las torres: ").append(alturaTorres).append("\n");
		st.append("Velocidad minima del viento: ").append(velocidadMinimaViento).append("\n");
		
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