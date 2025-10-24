package da.obligatorio.obligatorioDA.servicios;

import java.util.ArrayList;
import java.util.List;

import da.obligatorio.obligatorioDA.modelo.Bonificacion;

public class SistemaBonificaciones {
    private List<Bonificacion> bonificaciones;

    public SistemaBonificaciones() {
        this.bonificaciones = new ArrayList<>();
    }

    public SistemaBonificaciones(List<Bonificacion> bonificaciones) {
        this.bonificaciones = bonificaciones != null ? bonificaciones : new ArrayList<>();
    }

    public List<Bonificacion> getBonificaciones() {
        return bonificaciones;
    }

    public void setBonificaciones(List<Bonificacion> bonificaciones) {
        this.bonificaciones = bonificaciones;
    }

    public void agregarBonificacion(Bonificacion bonificacion) {
        if (bonificacion != null) {
            bonificaciones.add(bonificacion);
        }
    }

    public boolean eliminarBonificacion(Bonificacion bonificacion) {
        return bonificaciones.remove(bonificacion);
    }

    public Bonificacion obtenerBonificacionPorId(int id) {
        for (Bonificacion b : bonificaciones) {
            if (b.getId() == id) return b;
        }
        return null;
    }
}