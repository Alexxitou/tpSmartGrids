package ar.edu.ungs.prog2.smartgrids;
import java.time.LocalDateTime;

public class Monitoreo {
    private Planta planta;
    private LocalDateTime fechaHora;
    protected double energiaGenerada;
    private int cantidadIncidencias;

    public Monitoreo(Planta planta, LocalDateTime fechaHora, double energiaGenerada, int cantidadIncidencias) {
        if (planta == null) {
        throw new RuntimeException("El monitoreo necesita una planta");
        }
        if (fechaHora == null) {
            throw new RuntimeException("El monitoreo necesita una fechaHora");
        }
        if (energiaGenerada < 0) {
            throw new RuntimeException("La energia generada no puede ser negativa");
        }
        if (cantidadIncidencias < 0) {
            throw new RuntimeException("Las incidencias no pueden ser negativas");
        }
        this.planta = planta;
        this.fechaHora = fechaHora;
        this.energiaGenerada = energiaGenerada;
        this.cantidadIncidencias = cantidadIncidencias;
    }


	public Planta getPlanta() {
		return planta;
	}



    //revisar si este get se usa, sino sacarlo
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}


	public double getEnergiaGenerada() {
		return energiaGenerada;
	}
	
	@Override
	public String toString() {
		StringBuilder st= new StringBuilder();
		st.append("Planta: ").append(planta).append("\n");
		st.append("Fecha y hora del monitoreo: ").append(fechaHora).append("\n");
		st.append("Energía generada: ").append(energiaGenerada).append("\n");
		st.append("Cantidad de incidencias: ").append(cantidadIncidencias).append("\n");
		
		return st.toString();
	}
	
}
