package da.obligatorio.obligatorioDA.servicios;

import java.util.List;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Puesto;
import da.obligatorio.obligatorioDA.modelo.Sesion;
import da.obligatorio.obligatorioDA.modelo.Usuario;

public class Fachada {
    private static Fachada instancia;

    private SistemaUsuarios sistemaUsuarios;
    private SistemaPuestos sistemaPuestos;
    private SistemaBonificaciones sistemaBonificaciones;
    private SistemaPropietario sistemaPropietario;

    private Fachada() {
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
        return sistemaUsuarios.loginPropietario(nombre, contrasenia);
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
}