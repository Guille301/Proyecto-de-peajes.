package da.obligatorio.obligatorioDA.servicios;


import java.util.Date;
import java.util.List;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Puesto;
import da.obligatorio.obligatorioDA.modelo.Sesion;
import da.obligatorio.obligatorioDA.modelo.Usuario;
import da.obligatorio.obligatorioDA.modelo.Vehiculo;
import da.obligatorio.obligatorioDA.modelo.Transito;
import da.obligatorio.obligatorioDA.observador.Observable;;


public class Fachada extends Observable {
    private static Fachada instancia;

    private SistemaUsuarios sistemaUsuarios;
    private SistemaPuestos sistemaPuestos;
    private SistemaBonificaciones sistemaBonificaciones;
    private SistemaPropietario sistemaPropietario;


    public enum eventos {
        NOTIFICACION_TRANSITO
    }
  
    

    private Fachada()  {
        sistemaUsuarios = new SistemaUsuarios();
        sistemaPuestos = new SistemaPuestos();
        sistemaBonificaciones = new SistemaBonificaciones();
        sistemaPropietario = new SistemaPropietario();
       
    }

    public static Fachada getInstancia() {
        if (instancia == null) instancia = new Fachada();
        return instancia;
    }

  
    //Agregar propietario y admin
    public void agregar(Propietario usuario) {
        sistemaUsuarios.agregar(usuario);
    }

    public void agregar(Administrador usuario) {
        sistemaUsuarios.agregar(usuario);
    }


    //Login
    public Propietario loginUsuarioPropietario(String nombre, String contrasenia) throws ObligatorioException {
        Propietario p = sistemaUsuarios.loginPropietario(nombre, contrasenia);   
        sistemaPropietario.setPropietarioActual(p);
        return p;
    }

    public Administrador loginAdmin(String nombre, String contrasenia) throws ObligatorioException {
        return sistemaUsuarios.loginAdmin(nombre, contrasenia);
    }

    // Puestos
    public void agregarPuesto(Puesto p) {
        sistemaPuestos.agregarPuesto(p);
    }

    public boolean eliminarPuesto(Puesto p) {
        return sistemaPuestos.eliminarPuesto(p);
    }

    public List<Puesto> getPuestos() {
        return sistemaPuestos.getPuestos();
    }

    public Puesto obtenerPuestoPorId(int id) {
        return sistemaPuestos.obtenerPuestoPorId(id);
    }

    // Bonificaciones
    public void agregarBonificacion(Bonificacion b) {
        sistemaBonificaciones.agregarBonificacion(b);
    }

    public boolean eliminarBonificacion(Bonificacion b) {
        return sistemaBonificaciones.eliminarBonificacion(b);
    }

    public List<Bonificacion> getBonificaciones() {
        return sistemaBonificaciones.getBonificaciones();
    }

    public Bonificacion obtenerBonificacionPorId(int id) {
        return sistemaBonificaciones.obtenerBonificacionPorId(id);
    }

    // Propietarios
    public void agregarPropietario(Propietario p) {
        sistemaPropietario.agregarPropietario(p);
    }

    public boolean eliminarPropietario(Propietario p) {
        return sistemaPropietario.eliminarPropietario(p);
    }

    public List<Propietario> getPropietarios() {
        return sistemaPropietario.getPropietarios();
    }

    public Propietario obtenerPropietarioPorCedula(String cedula) {
        return sistemaPropietario.obtenerPropietarioPorCedula(cedula);
    }

    public Propietario obtenerPropietarioPorId(int id) {
        return sistemaPropietario.obtenerPropietarioPorId(id);
    }

    //Traer vehiculos con transitos
    public List<Vehiculo> obtenerVehiculosDePropietariosConTransitos(){
        return sistemaPropietario.obtenerVehiculosDePropietariosConTransitos();
    }

    //Bonificaciones
    public List<Bonificacion> obtenerBonificacionesDePropietarioActual(){
        return sistemaPropietario.obtenerBonificacionesDePropietarioActual();
    }

    public Vehiculo obtenerVehiculoPorMatricula(String matricula) {
    return sistemaPropietario.obtenerVehiculoPorMatricula(matricula);
}

public Propietario obtenerPropietarioPorVehiculo(Vehiculo v) {
    return sistemaPropietario.obtenerPropietarioPorVehiculo(v);
}

//Nuevos métodos de propiertario

public void registrarNotificacionesTransito(Propietario propietario, Puesto puesto,Vehiculo vehiculo) {
    sistemaPropietario.registrarNotificacionesTransito(propietario, puesto, vehiculo);
}


    public Vehiculo obtenerVehiculoPorMatriculaObligatorio(String matricula)
            throws ObligatorioException {
        return sistemaPropietario.obtenerVehiculoPorMatriculaObligatorio(matricula);
    }

    public Propietario obtenerPropietarioPorVehiculoObligatorio(Vehiculo vehiculo)
            throws ObligatorioException {
        return sistemaPropietario.obtenerPropietarioPorVehiculoObligatorio(vehiculo);
    }


   

public void validarEstadoParaTransito(Propietario propietario) throws ObligatorioException {
    sistemaPropietario.validarEstadoParaTransito(propietario);
}


 public void borrarNotificacionesPropietario(Propietario propietario) {
        sistemaPropietario.borrarNotificaciones(propietario);
    }



      public Transito emularTransito(int idPuesto, String matricula, Date fechaHora) throws ObligatorioException {
        return sistemaPuestos.emularTransito(idPuesto, matricula, fechaHora);
    }

    public Bonificacion asignarBonificacion(String cedula, int idPuesto, String nombreBonificacion, java.time.LocalDate fecha) throws ObligatorioException {
        return sistemaBonificaciones.asignarBonificacion(cedula, idPuesto, nombreBonificacion, fecha);
    }




  
}