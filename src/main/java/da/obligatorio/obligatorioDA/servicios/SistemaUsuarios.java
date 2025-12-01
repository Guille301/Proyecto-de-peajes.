package da.obligatorio.obligatorioDA.servicios;

import java.util.ArrayList;
import java.util.List;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.EstadoPropietario;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Sesion;
import da.obligatorio.obligatorioDA.modelo.Usuario;

public class SistemaUsuarios {
    private List<Propietario> propietarios;
    private List<Administrador> administradores;
    private List<Sesion> sesiones;
    private List<EstadoPropietario> listEstadosPropietario;

    public SistemaUsuarios() {
        this.propietarios = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.sesiones = new ArrayList<>();
        this.listEstadosPropietario = new ArrayList<>();
    }

    public void agregar(Propietario usuario) {
        propietarios.add(usuario);
    }

    public void agregar(Administrador usuario) {
        administradores.add(usuario);
    }

    //Login
    public Sesion loginAdmin(String cedula, String contrasenia) throws ObligatorioException{
        Sesion sesion = null;
        Administrador usuario = (Administrador) login(cedula, contrasenia,administradores);

        if(!esDiferente(usuario)){
            throw new ObligatorioException("Ud ya esta logueado en el sistema");
        }

        if(usuario!=null){
            sesion = new Sesion(usuario);
            sesiones.add(sesion);
            return sesion;
        }        
        throw new ObligatorioException("Usuario y/o contraseña incorrectos");
    }

    public boolean esDiferente(Administrador admin) throws ObligatorioException{
        for (Sesion sesion : this.sesiones) {
            if(sesion.getUsuario().equals(admin)){
                return false;
            }
        }
        return true;
    }

    public void logout(Sesion s){
        sesiones.remove(s);           
    }

   public Sesion loginPropietario(String cedula, String contrasenia) throws ObligatorioException {
    Propietario usuario = (Propietario) login(cedula, contrasenia, propietarios);

    if (usuario == null) {
        throw new ObligatorioException("Usuario y/o contraseña incorrectos");
    }
    
    usuario.validarIngresarSistema();
    if (yaEstaLogueado(usuario)) {
        throw new ObligatorioException("Ud ya está logueado en el sistema");
    }

    Sesion sesion = new Sesion(usuario);
    sesiones.add(sesion);
    return sesion;
}

private boolean yaEstaLogueado(Usuario usuario) {
    for (Sesion s : sesiones) {
        if (s.getUsuario().equals(usuario)) {
            return true;
        }
    }
    return false;
}



    public Usuario login(String cedula, String contrasena, List lista) throws ObligatorioException {
        Usuario usuario;
        for (Object o: lista) {
            usuario = (Usuario)o;
            if (usuario.getCedula().equals(cedula) && usuario.getContrasenia().equals(contrasena)) {
                return usuario;
            }
        }
        return null;
    }

    public void agregarEstadosPropietario(EstadoPropietario nuevoEstadoPropietario) {
        this.listEstadosPropietario.add(nuevoEstadoPropietario);
    }

    public List<EstadoPropietario> getListEstadosPropietario() {
        return listEstadosPropietario;
    }

    public EstadoPropietario getEstadoPropietarioPorId(int id) throws ObligatorioException {
        for (EstadoPropietario estado : listEstadosPropietario) {
            if (estado.getId() == id) {
                return estado;
            }
        }
        throw new ObligatorioException("EstadoPropietario con ID " + id + " no encontrado.");
    }

}