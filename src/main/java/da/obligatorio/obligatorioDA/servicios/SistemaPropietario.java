package da.obligatorio.obligatorioDA.servicios;

import java.util.ArrayList;
import java.util.List;

import da.obligatorio.obligatorioDA.modelo.Propietario;

public class SistemaPropietario {
    private List<Propietario> propietarios;

    public SistemaPropietario() {
        this.propietarios = new ArrayList<>();
    }

    public SistemaPropietario(List<Propietario> propietarios) {
        this.propietarios = propietarios != null ? propietarios : new ArrayList<>();
    }

    public List<Propietario> getPropietarios() {
        return propietarios;
    }

    public void setPropietarios(List<Propietario> propietarios) {
        this.propietarios = propietarios;
    }

    public void agregarPropietario(Propietario propietario) {
        if (propietario != null) {
            propietarios.add(propietario);
        }
    }

    public boolean eliminarPropietario(Propietario propietario) {
        return propietarios.remove(propietario);
    }

    public Propietario obtenerPropietarioPorId(int id) {
        for (Propietario p : propietarios) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public Propietario obtenerPropietarioPorCedula(String cedula) {
        if (cedula == null) return null;
        for (Propietario p : propietarios) {
            if (cedula.equals(p.getCedula())) return p;
        }
        return null;
    }
}