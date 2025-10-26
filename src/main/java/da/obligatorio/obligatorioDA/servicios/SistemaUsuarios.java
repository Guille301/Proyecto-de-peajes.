package da.obligatorio.obligatorioDA.servicios;

import java.util.ArrayList;
import java.util.List;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Sesion;
import da.obligatorio.obligatorioDA.modelo.Usuario;

public class SistemaUsuarios {
    private List<Propietario> propietarios;
    private List<Administrador> administradores;
    private List<Sesion> sesiones;

      public SistemaUsuarios() {
        this.propietarios = new ArrayList<>();
        this.administradores = new ArrayList<>();
       this.sesiones = new ArrayList<>();
    }

    public void agregar(Propietario usuario) {
        propietarios.add(usuario);
    }

    public void agregar(Administrador usuario) {
        administradores.add(usuario);
    }


    //Login
 

    public Administrador loginAdmin(String cedula, String contrasenia) throws ObligatorioException{
        
        Administrador usuario = (Administrador) login(cedula, contrasenia,administradores);
        if(usuario!=null){
            return usuario;
        }        
        throw new ObligatorioException("Usuario y/o contraseña incorrectos");
    }

    public Propietario loginPropietario(String cedula, String contrasenia) throws ObligatorioException{
      Propietario usuario = (Propietario) login(cedula, contrasenia,propietarios);
        if(usuario!=null){
            return usuario;
        }        
        throw new ObligatorioException("Usuario y/o contraseña incorrectos");
    }

    public Usuario login(String cedula, String contrasena, List lista) throws ObligatorioException {
        Usuario usuario;
        for (Object o: lista) {
            usuario = (Usuario)o;
            if (usuario.getCedula().equals(cedula) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null;
    }

}