package ar.edu.ungs.prog2.smartgrids;
import java.time.LocalDateTime;

public class MonitoreoEolica extends Monitoreo {
    private double velVientoPromedio;

    public MonitoreoEolica (Planta planta, LocalDateTime fechaHora, double energiaGenerada, int cantidadIncidencias, double velVientoPromedio) {

        super(planta, fechaHora, energiaGenerada, cantidadIncidencias);
        if(velVientoPromedio<0) {
        	throw new RuntimeException("velVientoPromedio no puede ser menor a cero");
        }
        
        this.velVientoPromedio = velVientoPromedio;
        
    }

}
