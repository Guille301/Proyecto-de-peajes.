package da.obligatorio.obligatorioDA.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.CriterioAsignacionBonificacion;
import da.obligatorio.obligatorioDA.modelo.Exonerados;
import da.obligatorio.obligatorioDA.modelo.Frecuentes;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Puesto;

public class SistemaBonificaciones {
    private List<Bonificacion> bonificaciones;
    private List<CriterioAsignacionBonificacion> criteriosAsignacion = new ArrayList<>();


    public void agregarCriterioAsignacion(CriterioAsignacionBonificacion c) {
        if (c != null) {
            criteriosAsignacion.add(c);
        }
    }

    public SistemaBonificaciones() {
        this.bonificaciones = new ArrayList<>();
        agregarCriterioAsignacion(new Exonerados());

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

    public Bonificacion asignarBonificacion(String cedula,int idPuesto,String nombreBonificacion) throws ObligatorioException {

        Propietario propietario = Fachada.getInstancia().obtenerPropietarioPorCedula(cedula);
        Puesto puesto = Fachada.getInstancia().obtenerPuestoPorId(idPuesto);

        if (propietario == null) {
            throw new ObligatorioException("No existe el propietario");
        }

        propietario.validarAsignarBonificacion();
        
        if (puesto == null) {
            throw new ObligatorioException("Debe especificar un puesto");
        }
        
        if (nombreBonificacion == null || nombreBonificacion.isBlank()) {
            throw new ObligatorioException("Debe especificar una bonificación");
        }

        if (propietario.getListBonificaciones() != null) {
            for (Bonificacion b : propietario.getListBonificaciones()) {
                if (b != null && b.getPuestos() != null && b.getPuestos().getId() == puesto.getId()) {
                    throw new ObligatorioException("Ya tiene una bonificación asignada para ese puesto");
                }
            }
        }

        LocalDate fechaAsignacion = LocalDate.now();
        CriterioAsignacionBonificacion criterio = null;

        for (CriterioAsignacionBonificacion c : criteriosAsignacion) {
            if (c.getNombre().equalsIgnoreCase(nombreBonificacion)) {
                criterio = c;
                break;
            }
        }
    
        Bonificacion asignada = new Bonificacion(  0,propietario, nombreBonificacion, puesto,fechaAsignacion,0, criterio);
        propietario.setBonificaciones(asignada);   
        puesto.agregarBonificacionPuesto(asignada);
        agregarBonificacion(asignada);
        Fachada.getInstancia().avisarObservadores(Fachada.eventos.NOTIFICACION_BONIFICACION_ASIGNADA);

        return asignada;
    }




}