package ar.edu.ungs.prog2.smartgrids;

import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SmartGrids implements ISmartGrids {
	
	private HashMap<String,Planta> conjuntoPlantas; //<nombre,Planta>
	private HashMap<String,HashMap<LocalDateTime,Monitoreo>> conjuntoMonitoreos; //<nombre, <fechaHora, Monitoreo>>
	private ArrayList<String> ordenPlantas; //Recorrer las plantas en el orden en que se cargaron. consultarPlantas() 

	public SmartGrids() {
		conjuntoPlantas = new HashMap<>();
		conjuntoMonitoreos= new HashMap<>();
		ordenPlantas= new ArrayList<>();
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
		
		existePlanta(nombre);
		//crear la planta
		PlantaSolar Planta= new PlantaSolar(nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio, superficieCubierta, cantidadPaneles, horasRadiacionDiaria);
		
		guardarPlanta(nombre, Planta);
	
	}

	@Override
	public void registrarPlantaEolica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			int cantidadAerogeneradores, double alturaTorres, double velocidadMinimaViento) {

		existePlanta(nombre);
		
		PlantaEolica Planta= new PlantaEolica(nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio, cantidadAerogeneradores, alturaTorres, velocidadMinimaViento);
		
		guardarPlanta(nombre, Planta);


	}

	@Override
	public void registrarPlantaHidraulica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			int cantidadTurbinas, double capacidadEmbalse) {
		
		existePlanta(nombre);
		//faltaba el chequeo, por eso tiraba error el test, se pisaban si ya existia una planta con ese nombre

		PlantaHidraulica Planta = new PlantaHidraulica(nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio, cantidadTurbinas, capacidadEmbalse);

		guardarPlanta(nombre, Planta);

	}

	@Override
	public void registrarPlantaTermica(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String tipoCombustible, double objetivoEmisionesCo2PorHora,
			double capacidadAlmacenamientoCombustible) {
				
		existePlanta(nombre);
		//faltaba el chequeo, por eso tiraba error el test, se pisaban si ya existia una planta con ese nombre
		PlantaTermica Planta = new PlantaTermica(nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, tipoCombustible, objetivoEmisionesCo2PorHora, capacidadAlmacenamientoCombustible);
		

		guardarPlanta(nombre, Planta);

	}

	
	@Override
	public List<String> consultarPlantas() {
		
		List<String> resultado = new ArrayList<>(); // crea la lista
		
		
	    for (String nombre : ordenPlantas) { // Recorre los nombres en el orden en que se registraron

	        Planta planta = conjuntoPlantas.get(nombre); //obtiene la planta
	     
	        StringBuilder texto = new StringBuilder(); //Construye el texto usando StringBuilder

	        texto.append(planta.getNombre());
	        texto.append(" (");
	        texto.append(planta.getEstadoTexto());
	        texto.append("): ");
	        texto.append(planta.getFechaHoraPuestaEnFuncionamiento());


	        resultado.add(texto.toString());// Convierte el StringBuilder a String y lo agrega a la lista.
	    }
	    return resultado; // Devuelve la lista		
	}

	
	@Override
	public double capacidadMinimaCuota(String nombre) {
		Planta planta = obtenerPlanta(nombre);
		
		return planta.capacidadMinimaCuota();
	}


	@Override
	public void activarPlanta(String nombre, String fechaHora) {
		
		LocalDateTime fecha = normalizarFecha(fechaHora);
		
		Planta planta= obtenerPlanta(nombre);
		
		planta.activar(fecha); //activa la planta
		
		
	}

	@Override
	public void desactivarPlanta(String nombre, String fechaHora) {
		
		LocalDateTime fecha = normalizarFecha(fechaHora);

		Planta planta = obtenerPlanta(nombre);
		
		planta.desactivar(fecha); //desactiva la planta

	}

	@Override
	public boolean estaActiva(String nombre) {
		Planta planta = obtenerPlanta(nombre);
		return planta.estaActiva(); //devuelve el estado
	}

	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias) {
	    LocalDateTime fecha = normalizarFecha(fechaHora);
	    Planta planta = obtenerPlantaParaMonitoreo(nombre, fecha);

		//si son eolica o termina no las toma, estas tienen sus propios monitoreos
		if (planta instanceof PlantaEolica || planta instanceof PlantaTermica) {
        	throw new RuntimeException("Esta planta requiere un monitoreo especifico.");
    	}

	    guardarMonitoreo(nombre, fecha, new Monitoreo(planta, fecha, energiaGenerada, incidencias));
	}

	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
	        double velocidadViento) {
	    LocalDateTime fecha = normalizarFecha(fechaHora);
	    Planta planta = obtenerPlantaParaMonitoreo(nombre, fecha);

	    if (!(planta instanceof PlantaEolica)) {
	        throw new RuntimeException("La planta no es eolica.");
	    }
	    guardarMonitoreo(nombre, fecha, new MonitoreoEolica(planta, fecha, energiaGenerada, incidencias, velocidadViento));
	}

	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
	        double co2Emitido, double combustibleUtilizado) {
	    LocalDateTime fecha = normalizarFecha(fechaHora);
	    Planta planta = obtenerPlantaParaMonitoreo(nombre, fecha);

	    if (!(planta instanceof PlantaTermica)) {
	        throw new RuntimeException("La planta no es termica.");
	    }
	    guardarMonitoreo(nombre, fecha, new MonitoreoTermica(planta, fecha, energiaGenerada, incidencias, co2Emitido, combustibleUtilizado));
	}

	@Override
	public double totalGenerado(String nombre) {

		noExistePlanta(nombre);

		double sumaEnergiaGenerada = 0;

		for (Monitoreo monitoreo: conjuntoMonitoreos.get(nombre).values()) {
			sumaEnergiaGenerada += monitoreo.getEnergiaGenerada();
		}
		
		return sumaEnergiaGenerada; 
		//diseño descartado, poner un if para ver si la lista esta vacia, pero ya con el for si no hay simplemente sale y returna 0

	}


	@Override
	public int[] incidenciasPorHora(String nombre) {

		noExistePlanta(nombre);


	    int[] resultado = new int[24]; //crea un arreglo con 24 espacios vacíos.


	    HashMap<LocalDateTime, Monitoreo> diccionarioInterior= conjuntoMonitoreos.get(nombre); //Toma el diccionario Interior de conjuntoMonitoreos


	    if (diccionarioInterior == null) {// Si todavía no tiene monitoreos, devuelve los 24 ceros.
	        return resultado;
	    }

	    for (LocalDateTime fechaHora : diccionarioInterior.keySet()) {//recorre el diccionario interior.

	        Monitoreo monitoreo = diccionarioInterior.get(fechaHora); //toma el monitoreo del diccionario interior

	        if (monitoreo != null) {// Ignora las horas que no tienen monitoreo.

	            int hora = fechaHora.getHour();// Obtiene la hora del día

	            resultado[hora] += monitoreo.getCantidadIncidencias(); // Acumula las incidencias en la posición correspondiente.
	        }
	    }
	    return resultado;
	}

	@Override
	public int[] histogramaCumplimiento(String nombre, String fechaDesde, String fechaHasta) {
		
		LocalDate desde = LocalDate.parse(fechaDesde); //convierte el string en LocalDate
	    
	    LocalDate hasta = LocalDate.parse(fechaHasta); //convierte el string en LocalDate
		
		Planta planta= obtenerPlanta(nombre);//recordar que tmb chequea que no exista la planta
		
		if(desde.isAfter(hasta)) { 
			throw new RuntimeException("La fechaDesde no puede ser posterior a la fechaHasta");
		}
		
		int[] histograma = new int[24]; // crea la lista con 24 espacios vacios
		
		HashMap<LocalDateTime, Monitoreo> diccionarioInterior = conjuntoMonitoreos.get(nombre); //toma el diccionario de Monitoreos de la planta correspondiente
		
		if(diccionarioInterior==null) {//si el diccionario está vacío, simplemente devuelve el arreglo con los 24 espacios vacios.
			return histograma;
		}
		
		
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
		 
	    Map<String, Integer> resultado = new TreeMap<>(); // TreeMap mantiene las fuentes ordenadas alfabéticamente.


	    for (String nombre : conjuntoPlantas.keySet()) {//recorre el diccionario de Plantas

	        Planta planta = conjuntoPlantas.get(nombre);

	        if (planta.esRenovable()) { //verifica si la Planta es renovable

	            String fuente = planta.getFuentePrimaria(); //obtiene la fuente primaria de la planta

	            if (resultado.containsKey(fuente)) {// Si la fuente ya está registrada, sumamos uno.
	                resultado.put(fuente, resultado.get(fuente) + 1);
	            }
	            // Si es la primera planta de esa fuente, comenzamos en uno.
	            else {
	                resultado.put(fuente, 1);
	            }
	        }
	    }
	    return resultado;
	}
	/*
     * 9) Devuelve nombres de las plantas que nunca cumplieron la cuota, 
     * incluso si no tienen monitoreos.
     */
	
	@Override
	public List<String> plantasQueNuncaCumplieronCuota() {
		
		List<String> lista= new ArrayList<>();
		
		for (String nombre : ordenPlantas) { //recorre las plantas en orden de registro			
			boolean cumpleAlgunaVez=false; //Acumulador Booleano
			
			HashMap<LocalDateTime,Monitoreo> diccionarioInterior= conjuntoMonitoreos.get(nombre); //toma el diccionario interior de conjuntoMonitoreos

			if (diccionarioInterior!=null){ //verifica que no esté vacio
			
				for (LocalDateTime fechaHora: diccionarioInterior.keySet()) {//recorre el diccionario interior
					
					Monitoreo m= diccionarioInterior.get(fechaHora);//toma el monitoreo del diccionario interior
					
						cumpleAlgunaVez = cumpleAlgunaVez || (m != null && conjuntoPlantas.get(nombre).cumpleCuota(m)); //verifica si la cuota se cumple alguna vez. en ese caso, cumpleAlgunaVez se vuelve true 	
					}
				}
			
			if(!cumpleAlgunaVez) { //si la cuota no se cumple, se añade el nombre de la planta a la lista.
				lista.add(nombre);
			}
		}
		return lista;
	}




	/*
     * 10) Devuelve para cada planta eolica, las horas (de 0 a 23) con 
     * registros en las que nunca se alcanzo la velocidad minima del viento.
     * Se debe respetar el siguiente formato:
     * """
     * Plantas Eolicas: Horas con poco viento
     * - Planta 1:  5 m/s
     *        07: 2 m/s
     *        11: 3 m/s
     *        12: 1 m/s
     *        13: 0 m/s  
     * - Mi Super Planta:  4 m/s
     *        07: 2 m/s
     *        11: 3 m/s
     * """
     */
	@Override
	public String horasSinVelocidadMinimaPorPlantaEolica() {

		StringBuilder texto = new StringBuilder("Plantas Eolicas: Horas con poco viento\n");

		for (Planta planta : conjuntoPlantas.values()) {

			// si no es eolica la salteo y paso a la siguiente
			if (!(planta instanceof PlantaEolica)) {
				continue;
			}

			PlantaEolica eolica = (PlantaEolica) planta;//poruqe lleva parentesis?
			double minima = eolica.getVelocidadMinimaViento();

			//el valor es el viento más alto de esa hora, un hashmap x planta
			//si la hora no tiene registros directamente no está como clave
			HashMap<Integer, Double> vientoMaximo = new HashMap<>();

			//recorro los monitoreos de esta planta 
			for (Monitoreo m : conjuntoMonitoreos.get(planta.getNombre()).values()) {

				if (m instanceof MonitoreoEolica) {


					int hora = m.getFechaHora().getHour();
					double viento = ((MonitoreoEolica) m).getVelVientoPromedio();  

					//si la hora no existe o este viento supera al guardado, actualizo el máximo
					if (!vientoMaximo.containsKey(hora) || viento > vientoMaximo.get(hora)) {
						vientoMaximo.put(hora, viento);
					}
				}
			}

			texto.append("- ").append(planta.getNombre()).append(":  ").append(minima).append(" m/s\n");

			//recorro las horas 0 a 23 en orden 
			for (int hora = 0; hora < 24; hora++) {

				//se imprime solo si hubo registros (lo miro con el containsKey) y si el máximo no llegó al mínimo
				if (vientoMaximo.containsKey(hora) && vientoMaximo.get(hora) < minima) {

					texto.append("       ");
					if (hora < 10) {
						texto.append("0");                     // se le agrega el 0 para que no rompa con los test, daba error, me parece que con esto pasa
					}
					texto.append(hora).append(": ").append(vientoMaximo.get(hora)).append(" m/s\n");
				}
			}
		}

		return texto.toString();
	}

	@Override
	public int limpiarMonitoreosSinDatos() {
		int borrados = 0;
		for (HashMap<LocalDateTime, Monitoreo> monitoreosDePlanta : conjuntoMonitoreos.values()) {

			Iterator<Monitoreo> it = monitoreosDePlanta.values().iterator(); // iterador sobre los monitoreos de la planta

			while (it.hasNext()) { // mientras queden monitoreos
				Monitoreo m = it.next(); // toma el siguiente y avanza
				if (m.getEnergiaGenerada() == 0 && m.getCantidadIncidencias() == 0) {
					it.remove();
					borrados++;
				}
			}
		}
		return borrados;
	}
	
@Override
public List<String> plantasOrdenadasPorEnergiaGenerada() {

    List<String> resultado = new ArrayList<>(); // textos "nombre: energia" ordenados de mayor a menor
    List<Double> energias = new ArrayList<>();  // energias en el mismo orden, para comparar

    for (String nombre : ordenPlantas) { // en orden de registro, asi los empates quedan predecibles

        double energia = totalGenerado(nombre); // reutiliza el calculo del total
        int posicion = 0;

        while (posicion < energias.size() && energias.get(posicion) >= energia) { // busca su lugar
            posicion++;
        }

        energias.add(posicion, energia);
        resultado.add(posicion, nombre + ": " + energia);
    }
    return resultado;
}


	
	@Override
	public List<String> plantasInactivasEn(String fechaHora) {
		
		List<String> lista = new ArrayList<>();
		LocalDateTime fecha = normalizarFecha(fechaHora);
		
		for(String nombre: conjuntoPlantas.keySet()) { //recorre las plantas
			
			Planta planta= conjuntoPlantas.get(nombre);
				
			HashMap<LocalDateTime,LocalDateTime> registroInactividad= planta.getRegistroInactividad(); //toma el registro de inactividad de la planta
			
			for(LocalDateTime inicio: registroInactividad.keySet()) { //recorre el registro
				LocalDateTime fin= registroInactividad.get(inicio);
				
				//estaba mal porque los is estaban al reves if(!fecha.isAfter(inicio)&& (fin==null || fecha.isBefore(fin))){  
				
				
				if (!fecha.isBefore(inicio) && (fin == null || fecha.isBefore(fin))) { //verifica que la fecha esté después del inicioInactividad, y antes del finInactividad 
					lista.add(nombre);  //si fecha está entre ese periodo, agrega el nombre de la planta a la lista, deja de recorrer el registro, y salta a la siguiente planta.
					break;
				}
			}
		}
		return lista;
	}
	
	
	//devuelve la planta o lanza excepción si no existe
	private Planta obtenerPlanta(String nombre) {
		noExistePlanta(nombre);
	    return conjuntoPlantas.get(nombre);
	}

	//chequea que no sea null y convierte el String a LocalDateTime
	private LocalDateTime normalizarFecha(String fechaHora) {
	    if (fechaHora == null) {
	        throw new RuntimeException("La hora no puede ser nula");
	    }
	    return LocalDateTime.parse(fechaHora);
	}

	//devuelve la planta si puede recibir un monitoreo en esa fecha: existe, está activa y no es anterior a la puesta en marcha
	private Planta obtenerPlantaParaMonitoreo(String nombre, LocalDateTime fecha) {
	    Planta planta = obtenerPlanta(nombre);

	    if (!planta.estaActiva()) {
	        throw new RuntimeException("La planta esta inactiva.");
	    }
	    if (fecha.isBefore(LocalDateTime.parse(planta.getFechaHoraPuestaEnFuncionamiento()))) {
	        throw new RuntimeException("La hora es anterior a la puesta en marcha de la planta.");
	    }
	    return planta;
	}

	//rechaza el duplicado y guarda el monitoreo en el diccionario de la planta
	private void guardarMonitoreo(String nombre, LocalDateTime fecha, Monitoreo monitoreo) {
	    HashMap<LocalDateTime, Monitoreo> monitoreosDePlanta = conjuntoMonitoreos.get(nombre);

	    if (monitoreosDePlanta.containsKey(fecha)) {
	        throw new RuntimeException("Ya hay un monitoreo para esa hora.");
	    }
	    monitoreosDePlanta.put(fecha, monitoreo);
	}
	//para imprimir SmartGrids
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		
		sb.append("====== SMARTGRIDS ======\n");
		sb.append("PLANTAS REGISTRADAS\n");
		sb.append("-------------------\n");
		for (String nombre: conjuntoPlantas.keySet()) {;
			Planta planta= conjuntoPlantas.get(nombre);
			sb.append(planta.toString()); //imprime los datos de la planta	
			sb.append("\n\n");
		}
		sb.append("MONITOREOS REGISTRADOS\n");
		sb.append("----------------------\n");
		for (String nombre: conjuntoMonitoreos.keySet()) {
			HashMap<LocalDateTime, Monitoreo> diccionarioInterior = conjuntoMonitoreos.get(nombre);
			
			for(LocalDateTime f: diccionarioInterior.keySet()) {
				
				sb.append("   ").append(f.toString()).append(" : ");
				
				Monitoreo m= diccionarioInterior.get(f);
				
				if (m==null) {
					sb.append("Sin Monitoreos.");
				}else {
					sb.append(m.toString()); //imprime los datos del monitoreo
				}
				sb.append("\n");
			} 
			sb.append("\n");		
		}
		return sb.toString();
	}

	private void existePlanta(String nombre) {
		if (conjuntoPlantas.containsKey(nombre)) {
			throw new IllegalArgumentException("Ya existe una planta con ese nombre");
		}
	}

	private void noExistePlanta(String nombre) {
		if (!conjuntoPlantas.containsKey(nombre)) {
			throw new IllegalArgumentException("No existe una planta con ese nombre");
		}
	}

	private void guardarPlanta(String nombre, Planta planta) {
		conjuntoPlantas.put(nombre, planta);
		ordenPlantas.add(nombre);
		conjuntoMonitoreos.put(nombre, new HashMap<>());
	}



}
