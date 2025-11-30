package da.obligatorio.obligatorioDA.modelo;



import java.util.Date;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

import java.time.LocalDate;
import java.time.ZoneId;

public class Frecuentes extends CriterioAsignacionBonificacion {

    public Frecuentes() {
        super("Frecuentes");
    }

    @Override
    public double calcularDescuento(double montoTarifa,
                                    Propietario propietario,
                                    Puesto puesto,
                                    Vehiculo vehiculo,
                                    Date fechaTransito) throws ObligatorioException {

        long transitosPreviosHoy = propietario.contarTransitosDelDia(puesto, vehiculo, fechaTransito);

        
        if (transitosPreviosHoy >= 1) {
            return Math.round(montoTarifa * 0.5);
        }
      
        return 0;
    }
}

