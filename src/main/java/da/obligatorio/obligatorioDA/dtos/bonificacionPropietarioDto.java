package da.obligatorio.obligatorioDA.dtos;


import java.time.LocalDate;

import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Puesto;

public class bonificacionPropietarioDto {
    private String nombre;
    private String puestos;
    private LocalDate fechaAsignacion;


    public bonificacionPropietarioDto() {}


    public bonificacionPropietarioDto(Bonificacion bonificacion) {
        this.nombre = bonificacion.getNombre();
        this.puestos = bonificacion.getPuestos().getNombre();
        this.fechaAsignacion = bonificacion.getFechaAsignacion();
    }


    public String getNombre() {
        return nombre;
    }

    public String getPuestos() {
        return puestos;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    


}
