package ar.edu.ungs.prog2.smartgrids;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class SmartGrids implements ISmartGrids {
	
	private HashMap<String,Planta> conjuntoPlantas;
	private HashMap<String,HashMap<LocalDateTime,Monitoreo>> conjuntoMonitoreos;

	public SmartGrids() {
		conjuntoPlantas = new HashMap<>();
		conjuntoMonitoreos= new HashMap<>();
		
		
		
	}
	public void registrarPlanta(Planta planta) {
		String nombrePlanta= planta.getNombre(); //Toma el nombre de la planta
		
		
		if (conjuntoPlantas.containsKey(nombrePlanta)) { // para que no se registren nuevas plantas con el mismo nombre.
	        throw new IllegalArgumentException("Ya existe una planta con ese nombre");
	    }
	    
		conjuntoPlantas.put(nombrePlanta, planta); //agrega la planta al HashMap con el nombre de la planta como Clave.	
	    	
		
	    conjuntoMonitoreos.put(nombrePlanta, new HashMap<>());	//agrega al diccionario de Monitoreos el nombre de la planta y un diccionario vacío para meter sus monitoreos.
		
		
		
	}
	
	public void registrarMonitoreo(Monitoreo monitoreo) { 
		
		
		Planta planta= monitoreo.getPlanta(); //Toma la planta del monitoreo
		
		String nombrePlanta= planta.getNombre(); //toma el nombre de la planta obtenida del codigo de arriba
		
		if (!conjuntoPlantas.containsKey(nombrePlanta)) { //comprueba si la planta está registrada en el conjuntoPlantas
            throw new IllegalArgumentException("La planta no está registrada");
        }
		
		
		
		
		HashMap<LocalDateTime, Monitoreo> monitoreosDePlanta = //toma el diccionario vacío de la planta
		        conjuntoMonitoreos.get(nombrePlanta);
		
		LocalDateTime fechaHora= monitoreo.getFechaHora(); //toma la fecha y hora del monitoreo
		
		if(!planta.estaActiva()) { //comprueba si la planta no está activa, y en ese caso, se registra la fecha, pero no se registra un monitoreo.
			monitoreosDePlanta.put(fechaHora, null);
		} else {
			monitoreosDePlanta.put(fechaHora, monitoreo); //registra el monitoreo en el diccionario vacío junto a su Fecha y Hora
		}
		
		
		
		


	}
	public void consultarPlanta(Planta planta) {
		System.out.println(planta.mostrarInfo());
	}
	
	
	
	//esto es para testeos
	public int cantidadMonitoreos(String nombrePlanta) {
	    // Busca el diccionario de monitoreos de la planta.
	    HashMap<LocalDateTime, Monitoreo> monitoreos =
	        conjuntoMonitoreos.get(nombrePlanta);

	    // Si no existe esa planta, informa el error.
	    if (monitoreos == null) {
	        throw new IllegalArgumentException("Planta no registrada");
	    }

	    // Devuelve cuántos monitoreos tiene guardados.
	    return monitoreos.size();
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public void registrarPlantaSolar(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			double superficieCubierta, int cantidadPaneles, double horasRadiacionDiaria) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void registrarPlantaEolica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			int cantidadAerogeneradores, double alturaTorres, double velocidadMinimaViento) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void registrarPlantaHidraulica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			int cantidadTurbinas, double capacidadEmbalse) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void registrarPlantaTermica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String tipoCombustible, double objetivoEmisionesCo2PorHora,
			double capacidadAlmacenamientoCombustible) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<String> consultarPlantas() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double capacidadMinimaCuota(String nombre) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void activarPlanta(String nombre, String fechaHora) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void desactivarPlanta(String nombre, String fechaHora) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean estaActiva(String nombre) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
			double velocidadViento) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
			double co2Emitido, double combustibleUtilizado) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public double totalGenerado(String nombre) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int[] incidenciasPorHora(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int[] histogramaCumplimiento(String nombre, String fechaDesde, String fechaHasta) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, Integer> plantasRenovablesPorFuente() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> plantasQueNuncaCumplieronCuota() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String horasSinVelocidadMinimaPorPlantaEolica() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int limpiarMonitoreosSinDatos() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<String> plantasOrdenadasPorEnergiaGenerada() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> plantasInactivasEn(String fechaHora) {
		// TODO Auto-generated method stub
		return null;
	}
}
