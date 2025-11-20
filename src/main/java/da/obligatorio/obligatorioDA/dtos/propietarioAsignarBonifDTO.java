package da.obligatorio.obligatorioDA.dtos;


import java.util.List;

import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;

public class propietarioAsignarBonifDTO {

    private String cedula;
    private String nombreCompleto;
    private String estado;
    private int bonificaciones;

    public propietarioAsignarBonifDTO(Propietario p) {
        this.cedula = p.getCedula();
        this.nombreCompleto = p.getNombreCompleto();
        this.estado = p.getEstadoPropietario().getNombre();
        this.bonificaciones = p.getListBonificaciones().size();
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEstado() {
        return estado;
    }

    public int getBonificaciones() {
        return bonificaciones;
    }
}
