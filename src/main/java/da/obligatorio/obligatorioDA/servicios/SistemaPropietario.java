package da.obligatorio.obligatorioDA.servicios;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.EstadoPropietario;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Vehiculo;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Notificacion;
import da.obligatorio.obligatorioDA.modelo.Puesto;

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

    // Nuevo método agregado 
    public Vehiculo obtenerVehiculoPorMatricula(String matricula) {
        for (Propietario p : propietarios) {          
            for (Vehiculo v : p.getListVehiculos()) {
                if (v.getMatricula().equalsIgnoreCase(matricula)) {
                    return v;
                }
            }
        }
        return null;
    }

    public Propietario obtenerPropietarioPorVehiculo(Vehiculo vehiculo) {
        for (Propietario p : propietarios) {
            if (p.getListVehiculos().contains(vehiculo)) {
                    return p;
                }
        }
        return null;
    }

    public void registrarNotificacionesTransito(Propietario propietario,  Puesto puesto, Vehiculo vehiculo) {
        Date ahora = new Date();
        String mensaje = ahora  + " Pasaste por el puesto " + puesto.getId() + " con el vehículo " + vehiculo.getMatricula();
        Notificacion notificacion = new Notificacion(0, ahora, mensaje);
        if (propietario.getEstadoPropietario().validacionesPenalizado()) { 
            propietario.agregarNotificaciones(notificacion);
            Fachada.getInstancia().avisarObservadores(Fachada.eventos.NOTIFICACION_TRANSITO);
        }
    }

    //Metodos con exceptions
    public Vehiculo obtenerVehiculoPorMatriculaObligatorio(String matricula) throws ObligatorioException {
        Vehiculo v = obtenerVehiculoPorMatricula(matricula);
        if (v == null) {
            throw new ObligatorioException("No existe el vehículo");
        }
        return v;
    }

    public Propietario obtenerPropietarioPorVehiculoObligatorio(Vehiculo vehiculo) throws ObligatorioException {
        Propietario p = obtenerPropietarioPorVehiculo(vehiculo);
        if (p == null) {
            throw new ObligatorioException("No existe un propietario asociado al vehículo");
        }
        return p;
    }

    public void borrarNotificaciones(Propietario propietario) {
        propietario.borrarNotificaciones();
    }

    //Buscador propietario
    public Propietario buscarPropietarioPorCedula(String cedula) {
        for (Propietario p : this.propietarios) {
            if (p.getCedula().equalsIgnoreCase(cedula)) {
                return p;
            }
        }
        return null;
    }

    //Estado
    public void registrarNotificacionesEstado(Propietario propietario,  EstadoPropietario nuevoEstado) {
        Date ahora = new Date();
        String mensaje = ahora  + "Se ha cambiado tu estado en el sistema. Tu estado actual es " + nuevoEstado.getNombre(); 
        Notificacion notificacion = new Notificacion(0, ahora, mensaje);
        if (propietario.getEstadoPropietario().validacionesPenalizado()) {
            propietario.agregarNotificaciones(notificacion);
            Fachada.getInstancia().avisarObservadores(Fachada.eventos.NOTIFICACION_CAMBIO_ESTADO);
        }
    }



    //Notificacion saldo insuficiente
     public void registrarNotificacionesSaldoInsuficiente(Propietario propietario,  Double costo) {
        Date ahora = new Date();
        String mensaje = ahora  + " Tu saldo actual es de  " + propietario.getSaldo() + "  Te recomendamos hacer una recarga" ;
        Notificacion notificacion = new Notificacion(0, ahora, mensaje);
            

            if(propietario.getSaldo() < costo) {
                propietario.agregarNotificaciones(notificacion);
                Fachada.getInstancia().avisarObservadores(Fachada.eventos.NOTIFICACION_SALDO_INSUFICIENTE);
            }
      
            
        
    }
}
