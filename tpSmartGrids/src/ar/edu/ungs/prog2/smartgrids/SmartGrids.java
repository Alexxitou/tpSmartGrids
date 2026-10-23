package ar.edu.ungs.prog2.smartgrids;

import java.util.Map;
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
	
	/* IGNORAR
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
	
	
	
	public void cambiarEstadoPlanta(String nombrePlanta) {
		if(!conjuntoPlantas.containsKey(nombrePlanta)) { //verifica si la planta no está registrada
			throw new IllegalArgumentException("Planta no registrada");
		} 
		Planta planta = conjuntoPlantas.get(nombrePlanta); //toma la planta del conjuntoPlantas
		if (planta.estaActiva()){
			planta.desactivar();
		} else {
			planta.activar();
		}
		
	}
	
	
	

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

	
	*/
	
	
	
	
	
	
	
	
	
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
		
		planta.activar(); //activa la planta
		
		
		//Por completar
		
	}

	@Override
	public void desactivarPlanta(String nombre, String fechaHora) {
		
		if(!conjuntoPlantas.containsKey(nombre)) { //verifica si la planta no existe en el diccionario
			throw new RuntimeException("Planta no registrada");
		}
		

		Planta planta= conjuntoPlantas.get(nombre); //obtiene la planta del diccionario
		
		planta.desactivar(); //desactiva la planta
		
		
		//Por completar

	}

	@Override
	public boolean estaActiva(String nombre) {
		Planta planta= conjuntoPlantas.get(nombre); //obtiene la planta del diciconario

		return planta.estaActiva(); //devuelve el estado
	}

	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias) {

		if (!conjuntoPlantas.containsKey(nombre)) {
			throw new RuntimeException("No existe esa planta");
		}
		
		if (!conjuntoPlantas.get(nombre).estaActiva()) {
			throw new RuntimeException("La planta esta inactiva.");
		}
		//terminar las verificaciones que pide la consigna

		LocalDateTime fecha = LocalDateTime.parse(fechaHora);//vcr bien si en los test queda bien el parseo 

		Planta planta = conjuntoPlantas.get(nombre);// para usarlo dsp al crear el monitoreo

		HashMap<LocalDateTime, Monitoreo> monitoreosDePlanta = conjuntoMonitoreos.get(nombre);

		Monitoreo monitoreo = new Monitoreo(planta, fecha, energiaGenerada, incidencias);

		monitoreosDePlanta.put(fecha, monitoreo); // IMPORTANTE!! CON ESTO ANADO AL DICCIONARIO INTERIOR
	}
		//armando el codigo, pienso que no haria falta que tenga como atributo el objeto planta, si ya lo busca por diccionario, podria sacarse o ponerle algun nombre al monitoreo	


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
	public int[] histogramaCumplimiento(String nombre, String fechaDesde, String fechaHasta) { //POR COMPLETAR	

		LocalDate desde= LocalDate.parse(fechaDesde); //convierte el string en LocalDate
		LocalDate hasta= LocalDate.parse(fechaHasta); //convierte el string en LocalDate


		
		if(!conjuntoPlantas.containsKey(nombre)) { //verifica si la planta no existe en el diccionario
			throw new RuntimeException("Planta no registrada");
		}
		if(desde.isAfter(hasta)) { 
			throw new RuntimeException("La fechaDesde no puede ser posterior a la fechaHasta");
		}
		
		int[] histograma = new int[24]; // crea la lista con 24 espacios vacios
		
		HashMap<LocalDateTime, Monitoreo> diccionarioInterior = conjuntoMonitoreos.get(nombre); //toma el diccionario de Monitoreos de la planta correspondiente
		
		for (LocalDate dia=desde; !dia.isAfter(hasta); dia= dia.plusDays(1)) { //recorre los días desde el comienzo hasta el fin
			
			for(int hora=0; hora<24; hora++) { //recorre las 24 horas del día
				
				LocalDateTime fechaHora= dia.atTime(hora,0); // convierte el dia(un LocalDate) a LocalDateTime
				
				if(diccionarioInterior.containsKey(fechaHora)) { //si el diccionario tiene un monitoreo para esa fechaHora...
					
					Monitoreo m= diccionarioInterior.get(fechaHora); //...toma el monitoreo del diccionario...
					
					if(m!=null && conjuntoPlantas.get(nombre).cumpleCuota(m)){ //... comprueba si no es null y si el monitoreo cumple la cuota...
						histograma[hora]++; //... y suma un 1 si ese día se cumplió la cuota en esa hora. 
					}
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
		
		for (String nombre:conjuntoPlantas.keySet()) {
			
			boolean cumpleAlgunaVez=false; //Acumulador Booleano
			
			HashMap<LocalDateTime,Monitoreo> diccionarioInterior= conjuntoMonitoreos.get(nombre); 

			if (diccionarioInterior!=null){
			
				for (LocalDateTime fechaHora: diccionarioInterior.keySet()) {
					
					Monitoreo m= diccionarioInterior.get(fechaHora);
					
					cumpleAlgunaVez= cumpleAlgunaVez || (m!=null || conjuntoPlantas.get(nombre).cumpleCuota(m)); 	
					}
				}
			
			if(!cumpleAlgunaVez) {
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

	@Override
	public List<String> plantasInactivasEn(String fechaHora) {
		// TODO Auto-generated method stub
		return null;
	}


}
