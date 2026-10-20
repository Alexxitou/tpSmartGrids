package ar.edu.ungs.prog2.smartgrids;
import java.time.LocalDateTime;

public class Monitoreo {
    private Planta planta;
    private LocalDateTime fechaHora;
    private double energiaGenerada;
    private int cantidadIncidencias;

    public Monitoreo(Planta planta, LocalDateTime fechaHora, double energiaGenerada, int cantidadIncidencias) {
        this.planta = planta;
        this.fechaHora = fechaHora;
        this.energiaGenerada = energiaGenerada;
        this.cantidadIncidencias = cantidadIncidencias;
    }
    

    public void crearMonitoreo() {
    }

}
