package da.obligatorio.obligatorioDA.dtos;

import java.util.ArrayList;
import java.util.List;

import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;

public class propietarioAsignarBonifDTO {

    private String cedula;
    private String nombreCompleto;
    private String estado;
    private List<BonificacionAsignadaDTO> bonificaciones = new ArrayList<>();

    public propietarioAsignarBonifDTO(Propietario p) {
        this.cedula = p.getCedula();
        this.nombreCompleto = p.getNombreCompleto();
        this.estado = p.getEstadoPropietario().getNombre();
       

        if (p.getListBonificaciones() != null) {
            for (Bonificacion b : p.getListBonificaciones()) {
                bonificaciones.add( new BonificacionAsignadaDTO(b.getPuestos().getNombre(),b.getNombre(),b.getFechaAsignacion()));
            }
        }
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

    public List<BonificacionAsignadaDTO> getBonificaciones() {
        return bonificaciones;
    }
}
