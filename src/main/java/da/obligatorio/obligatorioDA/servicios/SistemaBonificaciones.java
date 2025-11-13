package da.obligatorio.obligatorioDA.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.CriterioAsignacionBonificacion;
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


   public Bonificacion asignarBonificacion( String cedula,  int idPuesto, String nombreBonificacion,LocalDate fecha  ) throws ObligatorioException {

            Propietario propietario = Fachada.getInstancia().obtenerPropietarioPorCedula(cedula);
            Puesto puesto = Fachada.getInstancia().obtenerPuestoPorId(idPuesto);

        if (propietario == null) throw new ObligatorioException("No existe el propietario");
        if (puesto == null) throw new ObligatorioException("Debe especificar un puesto");
        if (nombreBonificacion == null) throw new ObligatorioException("Debe especificar una bonificación");

        
        for (CriterioAsignacionBonificacion criterio : criteriosAsignacion) {
            criterio.validar(propietario, puesto);
        }

        
        Bonificacion asignada = new Bonificacion( 0,  propietario, nombreBonificacion, puesto,fecha,0);

        propietario.setBonificaciones(asignada);
        puesto.agregarBonificacionPuesto(asignada);

        agregarBonificacion(asignada);

        return asignada;
    }




}