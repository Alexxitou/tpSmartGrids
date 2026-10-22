package ar.edu.ungs.prog2.smartgrids;


public abstract class Planta {
	
	protected String nombre;
	protected String direccion;
	protected double capacidadMaxima;
	protected boolean estadoPlanta;
	protected String  fechaHoraPuestaEnFuncionamiento;
	
	
	public Planta(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento) {
		this.nombre=nombre;
		this.direccion=direccion;
		this.capacidadMaxima=capacidadMaxima;
		this.estadoPlanta= true;
		this.fechaHoraPuestaEnFuncionamiento=fechaHoraPuestaEnFuncionamiento;
		
		
	
		
	}
	public void activar() {
		this.estadoPlanta=true;
	}
	public void desactivar() {
		this.estadoPlanta=false;
	}
	
	public boolean estaActiva() {
		return estadoPlanta; 
	}
	
	public abstract boolean cumpleCuota(Monitoreo monitoreo);
	
	public abstract double capacidadMinimaCuota();

	
	
	public String getNombre() {
		return nombre;		
	}
	public String getFechaHoraPuestaEnFuncionamiento() {
		return fechaHoraPuestaEnFuncionamiento;
	}
	
	
	
}
