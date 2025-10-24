package da.obligatorio.obligatorioDA.modelo;

public class Usuario {
    private int id;
    private String cedula;
    private String contrasena;

    public Usuario() {
    }

    public Usuario(int id, String cedula, String contrasena) {
        this.id = id;
        this.cedula = cedula;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}