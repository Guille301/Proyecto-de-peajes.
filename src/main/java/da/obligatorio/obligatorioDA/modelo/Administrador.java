package da.obligatorio.obligatorioDA.modelo;

public class Administrador extends Usuario {
    private int id;
    private String nombreCompleto;

    public Administrador() {
    }

    public Administrador(int id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public Administrador(int id, String nombreCompleto, String cedula, String contrasena) {
        super(cedula, contrasena);
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}