package da.obligatorio.obligatorioDA.modelo;

public class EstadoPropietario {
    private int id;
    private String nombre;

    public EstadoPropietario() {
    }

    public EstadoPropietario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}