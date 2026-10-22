package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class MonitoreoTermica extends Monitoreo {
    private double Co2Generado;
    private double combustibleUtilizado;

    public MonitoreoTermica(Planta planta, LocalDateTime fechaHora, double energiaGenerada, int cantidadIncidencias, double Co2Generado, double combustibleUtilizado) {
        super(planta, fechaHora, energiaGenerada, cantidadIncidencias);
        this.Co2Generado = Co2Generado;
        this.combustibleUtilizado = combustibleUtilizado;
    }

    public crearMonitoreoTermica() {
    }

	public double getCO2generado() {
		return Co2Generado;
	}

	public double getEnergiaGenerada() {
		return energiaGenerada;
	}
}
