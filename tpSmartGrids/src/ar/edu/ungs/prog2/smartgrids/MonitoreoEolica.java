package ar.edu.ungs.prog2.smartgrids;

public class MonitoreoEolica extends Monitoreo {
    private double velVientoPromedio;

    public Monitoreo(Planta planta, LocalDateTime fechaHora, double energiaGenerada, int cantidadIncidencias, double velVientoPromedio) {
        super(planta, fechaHora, energiaGenerada, cantidadIncidencias);
        this.velVientoPromedio = velVientoPromedio;
    }


    public crearMonitoreoTermica() {
    }
}
