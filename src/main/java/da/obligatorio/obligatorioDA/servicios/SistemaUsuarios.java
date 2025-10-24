package da.obligatorio.obligatorioDA.servicios;

import java.util.ArrayList;
import java.util.List;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Usuario;

public class SistemaUsuarios {
    private List<Usuario> usuarios;

    public SistemaUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public SistemaUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios != null ? usuarios : new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void agregarUsuario(Usuario usuario) {
        if (usuario != null) {
            usuarios.add(usuario);
        }
    }

    public boolean eliminarUsuario(Usuario usuario) {
        return usuarios.remove(usuario);
    }

    public Usuario obtenerUsuarioPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    public Usuario obtenerUsuarioPorCedula(String cedula) {
        if (cedula == null) return null;
        for (Usuario u : usuarios) {
            if (cedula.equals(u.getCedula())) return u;
        }
        return null;
    }


    //Login
    public Usuario login(String cedula, String contrasena) throws ObligatorioException {
        for (Usuario u : usuarios) {
            if (u.getCedula().equals(cedula) && u.getContrasena().equals(contrasena)) {
                return u;
            }
            throw new ObligatorioException("Credenciales invalidas");
        }
        return null;
    }
}