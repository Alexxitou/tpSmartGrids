package ar.edu.ungs.prog2.smartgrids;

import java.util.Map;

import javax.management.RuntimeErrorException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SmartGrids implements ISmartGrids {
	
	private HashMap<String,Planta> conjuntoPlantas;
	private HashMap<String,HashMap<LocalDateTime,Monitoreo>> conjuntoMonitoreos;

	public SmartGrids() {
		conjuntoPlantas = new HashMap<>();
		conjuntoMonitoreos= new HashMap<>();	
	}
	

	
    /*
     * 1.a) Registran una planta con sus datos y caracteristicas  ✅
     * 
     * Por defecto la planta se crea en estado activa.  
     * 
     * @throws RuntimeException si ya existe ese nombre o falta o es 
     *         inaceptable algun dato.
     */
	
	

	@Override
	public void registrarPlantaSolar(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			double superficieCubierta, int cantidadPaneles, double horasRadiacionDiaria) {
		
		if (conjuntoPlantas.containsKey(nombre)) { //
			 throw new IllegalArgumentException("Ya existe una planta con ese nombre");
		}
		//crear la planta
		PlantaSolar Planta= new PlantaSolar(nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio, superficieCubierta, cantidadPaneles, horasRadiacionDiaria);
		
		conjuntoPlantas.put(nombre, Planta); //agrega la planta al diccionario de plantas
		
		conjuntoMonitoreos.put(nombre, new HashMap<>());//agrega al diccionario de Monitoreos el nombre de la planta y un diccionario vacío para meter sus monitoreos.
			
	}

	@Override
	public void registrarPlantaEolica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			int cantidadAerogeneradores, double alturaTorres, double velocidadMinimaViento) {

		if (conjuntoPlantas.containsKey(nombre)) { //
			 throw new IllegalArgumentException("Ya existe una planta con ese nombre");
		}
		
		PlantaEolica Planta= new PlantaEolica(nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio, cantidadAerogeneradores, alturaTorres, velocidadMinimaViento);
		
		conjuntoPlantas.put(nombre, Planta);
		
		conjuntoMonitoreos.put(nombre, new HashMap<>());
		

	}

	@Override
	public void registrarPlantaHidraulica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			int cantidadTurbinas, double capacidadEmbalse) {

		PlantaHidraulica Planta = new PlantaHidraulica(nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio, cantidadTurbinas, capacidadEmbalse);

		conjuntoPlantas.put(nombre,Planta);
		
		conjuntoMonitoreos.put(nombre, new HashMap<>());
	}

	@Override
	public void registrarPlantaTermica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String tipoCombustible, double objetivoEmisionesCo2PorHora,
			double capacidadAlmacenamientoCombustible) {

		PlantaTermica Planta = new PlantaTermica(nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, tipoCombustible, objetivoEmisionesCo2PorHora, capacidadAlmacenamientoCombustible);
		

		conjuntoPlantas.put(nombre,Planta);
		
		conjuntoMonitoreos.put(nombre, new HashMap<>());

	}

	 /*
     * 1.b) Consultar plantas exstentes.
     * 
     * Que devuelve un lista con el detalle de cada planta:
     *
     *  cada item debe respetar el formato: "{NOMBRE} ({ACTIVA|INACTIVA}): {FECHA_PUESTA_EN_FUNCIONAMIENTO}"
     * Por ejemplo:
     *     "Atucha II (ACTIVA): 2014-06-27T09:00:00"
     */
	
	@Override
	public List<String> consultarPlantas() {
		
		List<String> resultado = new ArrayList<>(); // crea la lista
		
		
	    for (String nombre : conjuntoPlantas.keySet()) { // Recorre en el diccionario las plantas por nombre

	        Planta planta = conjuntoPlantas.get(nombre); //obtiene la planta

	        String estado; //esto es para guardar el estado
	        
	        if (planta.estaActiva()) {
	            estado = "ACTIVA";
	        } else {
	            estado = "INACTIVA";
	        }
	        
	     
	        StringBuilder texto = new StringBuilder(); //Construye el texto usando StringBuilder

	        texto.append(planta.getNombre());
	        texto.append(" (");
	        texto.append(estado);
	        texto.append("): ");
	        texto.append(planta.getFechaHoraPuestaEnFuncionamiento());


	        resultado.add(texto.toString());// Convierte el StringBuilder a String y lo agrega a la lista.
	    }
	    return resultado; // Devuelve la lista		
	}
	/*
     * 2) Consulta cuanta energia debe generar la planta en una hora para
     * cumplir la cuota. En las solares se tiene en cuenta la radiacion;
     * en las eolicas e hidraulicas, la eficiencia. Las termicas deben
     * generar la mitad de su capacidad y emitir menos CO2 que el objetivo
     * por hora.
     *
     * @throws RuntimeException si la planta no existe
     */
	
	
	@Override
	public double capacidadMinimaCuota(String nombre) {
		if(!conjuntoPlantas.containsKey(nombre)) { //verifica si la planta no existe en el diccionario
			throw new RuntimeException("La planta no existe");
		}
		Planta planta = conjuntoPlantas.get(nombre);
		
		return planta.capacidadMinimaCuota();
	}

	/*
     * 3) Activar, desactivar una planta y consultar si está activa. 
     * 
     * La actualizacion y consulta se debe resolver en O(1).
     * 
     * @throws RuntimeException si la planta no existe o el formato de 
     *         fechaHora es inválido.
     */
	
	@Override
	public void activarPlanta(String nombre, String fechaHora) {
		
		if(!conjuntoPlantas.containsKey(nombre)) { //verifica si la planta no existe en el diccionario
			throw new RuntimeException("Planta no registrada");
		}
		
		LocalDateTime fecha= LocalDateTime.parse(fechaHora); //convierte el string en LocalDateTime
		
		Planta planta= conjuntoPlantas.get(nombre); //obtiene la planta del diccionario
		
		planta.activar(fecha); //activa la planta
		
		
	}

	@Override
	public void desactivarPlanta(String nombre, String fechaHora) {
		
		if(!conjuntoPlantas.containsKey(nombre)) { //verifica si la planta no existe en el diccionario
			throw new RuntimeException("Planta no registrada");
		}
		
		LocalDateTime fecha= LocalDateTime.parse(fechaHora); //convierte el string en LocalDateTime

		Planta planta= conjuntoPlantas.get(nombre); //obtiene la planta del diccionario
		
		planta.desactivar(fecha); //desactiva la planta
		
		


	}

	@Override
	public boolean estaActiva(String nombre) {
		Planta planta= conjuntoPlantas.get(nombre); //obtiene la planta del diciconario

		return planta.estaActiva(); //devuelve el estado
	}

	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias) {
		
		if (fechaHora == null) {
			throw new RuntimeException("La hora no puede ser nula");
		}
		LocalDateTime fecha = LocalDateTime.parse(fechaHora);

		if (!conjuntoPlantas.containsKey(nombre)) {
			throw new RuntimeException("No existe esa planta");
		}

		Planta planta = conjuntoPlantas.get(nombre);

		if (!planta.estaActiva()) {
			throw new RuntimeException("La planta esta inactiva.");
		}


		// La planta guarda su puesta en marcha como String entonces la paso a LocalDateTime para poder compararla.
		if (fecha.isBefore(LocalDateTime.parse(planta.getFechaHoraPuestaEnFuncionamiento()))) {
			throw new RuntimeException("La hora es anterior a la puesta en marcha de la planta.");
		}


		/*
		comentado porque esto ya lo valida la clase con su irep
		if (energiaGenerada < 0) {
			throw new RuntimeException("La energia generada no debe ser menor que 0.");
		}

		if (incidencias < 0)
		 {
			throw new RuntimeException("Las incidencias no deben ser menor que 0.");
		 }
		*/
		
		HashMap<LocalDateTime, Monitoreo> monitoreosDePlanta = conjuntoMonitoreos.get(nombre);

		Monitoreo monitoreo = new Monitoreo(planta, fecha, energiaGenerada, incidencias);

		monitoreosDePlanta.put(fecha, monitoreo); // IMPORTANTE!! CON ESTO ANADO AL DICCIONARIO INTERIOR
	}
		//armando el codigo, pienso que no haria falta que tenga como atributo el objeto planta, si ya lo busca por diccionario, podria sacarse o ponerle algun nombre al monitoreo	


	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
			double velocidadViento) {

		if (fechaHora == null) {
			throw new RuntimeException("La hora no puede ser nula");
		}
		LocalDateTime fecha = LocalDateTime.parse(fechaHora);

		if (!conjuntoPlantas.containsKey(nombre)) {
			throw new RuntimeException("No existe esa planta");
		}

		Planta planta = conjuntoPlantas.get(nombre);

		if (!(planta instanceof PlantaEolica)) {
			throw new RuntimeException("La planta no es eolica.");
		}

		if (!planta.estaActiva()) {
			throw new RuntimeException("La planta esta inactiva.");
		}

		if (fecha.isBefore(LocalDateTime.parse(planta.getFechaHoraPuestaEnFuncionamiento()))) {
			throw new RuntimeException("La hora es anterior a la puesta en marcha de la planta.");
		}

		HashMap<LocalDateTime, Monitoreo> monitoreosDePlanta = conjuntoMonitoreos.get(nombre);

		Monitoreo monitoreo = new MonitoreoEolica(planta, fecha, energiaGenerada, incidencias, velocidadViento);

		monitoreosDePlanta.put(fecha, monitoreo);
	}

	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
			double co2Emitido, double combustibleUtilizado) {

		if (fechaHora == null) {
			throw new RuntimeException("La hora no puede ser nula");
		}
		LocalDateTime fecha = LocalDateTime.parse(fechaHora);

		if (!conjuntoPlantas.containsKey(nombre)) {
			throw new RuntimeException("No existe esa planta");
		}

		Planta planta = conjuntoPlantas.get(nombre);

		if (!(planta instanceof PlantaTermica)) {
			throw new RuntimeException("La planta no es termica.");
		}

		if (!planta.estaActiva()) {
			throw new RuntimeException("La planta esta inactiva.");
		}

		if (fecha.isBefore(LocalDateTime.parse(planta.getFechaHoraPuestaEnFuncionamiento()))) {
			throw new RuntimeException("La hora es anterior a la puesta en marcha de la planta.");
		}

		HashMap<LocalDateTime, Monitoreo> monitoreosDePlanta = conjuntoMonitoreos.get(nombre);

		Monitoreo monitoreo = new MonitoreoTermica(planta, fecha, energiaGenerada, incidencias, co2Emitido, combustibleUtilizado);

		monitoreosDePlanta.put(fecha, monitoreo);
	}



	@Override
	public double totalGenerado(String nombre) {

		if (!conjuntoPlantas.containsKey(nombre)) {
			throw new RuntimeException("No existe esa planta");
		}

		double sumaEnergiaGenerada = 0;

		for (Monitoreo monitoreo: conjuntoMonitoreos.get(nombre).values()) {
			sumaEnergiaGenerada += monitoreo.getEnergiaGenerada();
		}
		
		return sumaEnergiaGenerada; 
		//diseño descartado, poner un if para ver si la lista esta vacia, pero ya con el for si no hay simplemente sale y returna 0

	}


	/*
     * 6) Devuelve un arreglo con 24 posiciones con las incidencias acumuladas 
     *         para cada hora del dia.
     * 
     * @throws RuntimeException si la planta no existe
     */
	@Override
	public int[] incidenciasPorHora(String nombre) {
		return null;
	}

	/*
     * 7) Cuenta, para cada hora del dia, en cuantos dias se cumplio la cuota
     * entre las fechas indicadas, incluidas ambas.
     *
     * Devuelve un arreglo con 24 posiciones, una por cada hora del dia
     * 
     * @throws RuntimeException si la planta no existe o si desde es
     *         posterior a hasta.
     */
	
	@Override
	public int[] histogramaCumplimiento(String nombre, String fechaDesde, String fechaHasta) {
		
		if(nombre==null) { 
			throw new RuntimeException("el nombre no puede ser null");
		}
		
		if(fechaDesde==null || fechaHasta==null) {
			throw new RuntimeException("Las fechas no pueden ser null");
		}

		LocalDate desde = LocalDate.parse(fechaDesde); //convierte el string en LocalDate
	    
	    LocalDate hasta = LocalDate.parse(fechaHasta); //convierte el string en LocalDate

	    int[] histograma = new int[24]; // crea la lista con 24 espacios vacios
		
		if(!conjuntoPlantas.containsKey(nombre)) { //verifica si la planta no existe en el diccionario
			throw new RuntimeException("Planta no registrada");
		}
		if(desde.isAfter(hasta)) { 
			throw new RuntimeException("La fechaDesde no puede ser posterior a la fechaHasta");
		}
		
		
		HashMap<LocalDateTime, Monitoreo> diccionarioInterior = conjuntoMonitoreos.get(nombre); //toma el diccionario de Monitoreos de la planta correspondiente
		
		if(diccionarioInterior==null) {//si el diccionario está vacío, simplemente devuelve el arreglo con los 24 espacios vacios.
			return histograma;
		}
		
		Planta planta= conjuntoPlantas.get(nombre);
		
		for (LocalDate dia=desde; !dia.isAfter(hasta); dia= dia.plusDays(1)) { //recorre los días desde el comienzo hasta el fin
			
			for(int hora=0; hora<24; hora++) { //recorre las 24 horas del día
				
				LocalDateTime diaYHora= dia.atTime(hora,0); //convierte el día(localDate) a LocalDateTime
					
					Monitoreo m= diccionarioInterior.get(diaYHora); //...toma el monitoreo del diccionario...
					
					if(m!=null && planta.cumpleCuota(m)){ //... comprueba si no es null y si el monitoreo cumple la cuota...
						histograma[hora]++; //... y suma un 1 si ese día se cumplió la cuota en esa hora. 
					}
			}
		}	
		return histograma; 
	}

	@Override
	public Map<String, Integer> plantasRenovablesPorFuente() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
     * 9) Devuelve nombres de las plantas que nunca cumplieron la cuota, 
     * incluso si no tienen monitoreos.
     */
	
	@Override
	public List<String> plantasQueNuncaCumplieronCuota() {
		
		List<String> lista= new ArrayList<>();
		
		for (String nombre:conjuntoPlantas.keySet()) { //recorre las plantas
			
			boolean cumpleAlgunaVez=false; //Acumulador Booleano
			
			HashMap<LocalDateTime,Monitoreo> diccionarioInterior= conjuntoMonitoreos.get(nombre); //toma el diccionario interior de conjuntoMonitoreos

			if (diccionarioInterior!=null){ //verifica que no esté vacio
			
				for (LocalDateTime fechaHora: diccionarioInterior.keySet()) {//recorre el diccionario interior
					
					Monitoreo m= diccionarioInterior.get(fechaHora);//toma el monitoreo del diccionario interior
					
					cumpleAlgunaVez= cumpleAlgunaVez || (m!=null || conjuntoPlantas.get(nombre).cumpleCuota(m));//verifica si la cuota se cumple alguna vez. en ese caso, cumpleAlgunaVez se vuelve true 	
					}
				}
			
			if(!cumpleAlgunaVez) { //si la cuota no se cumple, se añade el nombre de la planta a la lista.
				lista.add(nombre);
			}
		}
		return lista;
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

	
	/*
     * 13. Dado un fecha y hora, se debe devolver los nombres de todas las plantas que estuvieron inactivas en ese momento.
     * 
     */
	
	@Override
	public List<String> plantasInactivasEn(String fechaHora) {
		
		List<String> lista = new ArrayList<>();
		LocalDateTime fecha= LocalDateTime.parse(fechaHora);
		
		for(String nombre: conjuntoPlantas.keySet()) { //recorre las plantas
			
			Planta planta= conjuntoPlantas.get(nombre);
				
			HashMap<LocalDateTime,LocalDateTime> registroInactividad= planta.getRegistroInactividad(); //toma el registro de inactividad de la planta
			
			for(LocalDateTime inicio: registroInactividad.keySet()) { //recorre el registro
				LocalDateTime fin= registroInactividad.get(inicio);
				
				if(!fecha.isAfter(inicio)&& (fin==null || fecha.isBefore(fin))){ //verifica que la fecha esté después del inicioInactividad, y antes del finInactividad 
					lista.add(nombre);  //si fecha está entre ese periodo, agrega el nombre de la planta a la lista, deja de recorrer el registro, y salta a la siguiente planta.
					break;
				}
			}
		}
		return lista;
	}
}
