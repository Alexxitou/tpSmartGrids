package ar.edu.ungs.prog2.smartgrids;



public class PlantaEolica extends PlantaRenovable {

    private double cantidadAerogeneradores;
    private double alturaTorres;
    private double velocidadMinimaViento;

    public PlantaEolica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria,double eficienciaPromedio, double cantidadAerogeneradores, double alturaTorres, double velocidadMinimaViento) {
        super(nombre,direccion,capacidadMaxima,fechaHoraPuestaEnFuncionamiento,fuentePrimaria,eficienciaPromedio);
        this.cantidadAerogeneradores = cantidadAerogeneradores;
        this.alturaTorres = alturaTorres;
        this.velocidadMinimaViento = velocidadMinimaViento;
    }

	
	/*@Override
	public boolean cumpleCuota() {
		// TODO Auto-generated method stub
		return false;
	}*/


	@Override
	public double capacidadMinimaCuota() {
		return capacidadMaxima * eficienciaPromedio;
	}



}