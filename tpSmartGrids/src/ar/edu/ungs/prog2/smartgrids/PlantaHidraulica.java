package ar.edu.ungs.prog2.smartgrids;



public class PlantaHidraulica extends PlantaRenovable{

	private int cantidadTurbinas;
	private double capacidadEmbalse;
	
	public PlantaHidraulica(String nombre, String direccion, double capMaxGeneracion, String fechaFuncionamiento, String fuentePrimaria,double eficienciaPromedio,int cantidadTurbinas, double capacidadEmbalse) {
		super(nombre,direccion,capMaxGeneracion,fechaFuncionamiento,fuentePrimaria,eficienciaPromedio);
		this.cantidadTurbinas=cantidadTurbinas;
		this.capacidadEmbalse=capacidadEmbalse;
	}

	@Override
	public boolean cumpleCuota() {
		// TODO Auto-generated method stub
		return false;
	}

}
