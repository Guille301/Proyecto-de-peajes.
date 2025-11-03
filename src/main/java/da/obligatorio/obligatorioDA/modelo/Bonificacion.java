package da.obligatorio.obligatorioDA.modelo;

import java.time.LocalDate;


public class Bonificacion {
    private int id;
    private Propietario propietario;
    private String nombre;
    private Puesto puestos;
    private LocalDate fechaAsignacion;

    public Bonificacion() {
    }

    public Bonificacion(int id, Propietario propietario, String nombre, Puesto puestos, LocalDate fechaAsignacion) {
        this.id = id;
        this.propietario = propietario;
        this.nombre = nombre;
        this.puestos = puestos;
        this.fechaAsignacion = fechaAsignacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Puesto getPuestos() {
        return puestos;
    }

    public void setPuestos(Puesto puestos) {
        this.puestos = puestos;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }
}