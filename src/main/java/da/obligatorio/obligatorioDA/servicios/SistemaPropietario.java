package da.obligatorio.obligatorioDA.servicios;

import java.util.ArrayList;
import java.util.List;

import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Transito;
import da.obligatorio.obligatorioDA.modelo.Vehiculo;


public class SistemaPropietario {
    private List<Propietario> propietarios;
    private Propietario propietarioActual;

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

    //Traer vehiculos con transitos
       public Propietario getPropietarioActual() {
        return propietarioActual;
    }

    public void setPropietarioActual(Propietario propietarioActual) {
        this.propietarioActual = propietarioActual;
    }

    public List<Vehiculo> obtenerVehiculosDePropietariosConTransitos(){
       return propietarioActual.traerVehiculosConTransito();
    }

    //Bonificaciones
    public List<Bonificacion> obtenerBonificacionesDePropietarioActual(){
        return propietarioActual.getListBonificaciones();
    }

 

}
