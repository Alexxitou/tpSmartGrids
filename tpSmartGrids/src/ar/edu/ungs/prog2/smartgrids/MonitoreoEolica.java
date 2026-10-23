package ar.edu.ungs.prog2.smartgrids;
import java.time.LocalDateTime;

public class MonitoreoEolica extends Monitoreo {
    private double velVientoPromedio;

    public MonitoreoEolica (Planta planta, LocalDateTime fechaHora, double energiaGenerada, int cantidadIncidencias, double velVientoPromedio) {

        super(planta, fechaHora, energiaGenerada, cantidadIncidencias);
        this.velVientoPromedio = velVientoPromedio;
        
    }

}
