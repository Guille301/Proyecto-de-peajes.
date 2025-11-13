package da.obligatorio.obligatorioDA.dtos;

import da.obligatorio.obligatorioDA.modelo.Bonificacion;

public class bonificacionDefinidaDTO {

   
    private String nombre;

    public bonificacionDefinidaDTO() {
    }

    public bonificacionDefinidaDTO( Bonificacion bonificacion) {
        
        this.nombre = bonificacion.getNombre();
    }

    public String getNombreBonificacion() {
        return nombre;
    }

}
