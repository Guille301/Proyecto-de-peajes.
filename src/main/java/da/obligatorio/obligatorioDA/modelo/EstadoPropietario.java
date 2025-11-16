package da.obligatorio.obligatorioDA.modelo;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

public abstract class EstadoPropietario {
    private int id;
    private String nombre;
    private Propietario propietario;

    public EstadoPropietario() {
    }

    public EstadoPropietario(int id, String nombre, Propietario propietario) {
        this.id = id;
        this.nombre = nombre;
        this.propietario = propietario;
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

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    //Tengo que tener todos los metodos que van a implementar en cada estado
    public abstract void validarIngresarSistema() throws ObligatorioException;
    public abstract void validarPuedeTransitar() throws ObligatorioException;
    public abstract void validarAsignarBonificacion() throws ObligatorioException;

}