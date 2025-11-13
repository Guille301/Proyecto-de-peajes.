package da.obligatorio.obligatorioDA.dtos;

import java.time.LocalDate;

public class BonificacionAsignadaDTO {




  
    private String nombrePuesto;

    private String nombreBonificacion;
    private LocalDate fecha;


    public BonificacionAsignadaDTO() {
    }

    public BonificacionAsignadaDTO(    String nombrePuesto, String nombreBonificacion, LocalDate fecha  ) {
        this.nombrePuesto = nombrePuesto;
        this.nombreBonificacion = nombreBonificacion;
        this.fecha = fecha;
        
    }

   



    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public String getNombreBonificacion() {
        return nombreBonificacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

}
