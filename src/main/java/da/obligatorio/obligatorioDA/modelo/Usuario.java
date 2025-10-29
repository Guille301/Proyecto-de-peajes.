package da.obligatorio.obligatorioDA.modelo;

public abstract class Usuario {
    private int id;
    private String cedula;
    private String contrasenia;

    public Usuario() {
    }

    public Usuario(int id, String cedula, String contrasenia) {
        this.id = id;
        this.cedula = cedula;
        this.contrasenia = contrasenia;
    }

     public Usuario(String cedula, String contrasenia) {
        this.cedula = cedula;
        this.contrasenia = contrasenia;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}