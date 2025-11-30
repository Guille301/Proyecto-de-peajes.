package da.obligatorio.obligatorioDA.modelo;

import java.util.Date;
import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

public abstract class CriterioAsignacionBonificacion {

    private String nombre;

    public CriterioAsignacionBonificacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract double calcularDescuento(
            double montoTarifa,
            Propietario propietario,
            Puesto puesto,
            Vehiculo vehiculo,
            Date fechaTransito
    ) throws ObligatorioException;
}

