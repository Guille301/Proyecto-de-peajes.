package da.obligatorio.obligatorioDA.servicios;

import java.util.ArrayList;
import java.util.List;

import da.obligatorio.obligatorioDA.modelo.Puesto;

public class SistemaPuestos {
    private List<Puesto> puestos;

    public SistemaPuestos() {
        this.puestos = new ArrayList<>();
    }

    public SistemaPuestos(List<Puesto> puestos) {
        this.puestos = puestos != null ? puestos : new ArrayList<>();
    }

    public List<Puesto> getPuestos() {
        return puestos;
    }

    public void setPuestos(List<Puesto> puestos) {
        this.puestos = puestos;
    }

    public void agregarPuesto(Puesto puesto) {
        if (puesto != null) {
            puestos.add(puesto);
        }
    }

    public boolean eliminarPuesto(Puesto puesto) {
        return puestos.remove(puesto);
    }

    public Puesto obtenerPuestoPorId(int id) {
        for (Puesto p : puestos) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}