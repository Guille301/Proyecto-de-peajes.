package da.obligatorio.obligatorioDA.modelo;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.Date;

public class Trabajadores extends CriterioAsignacionBonificacion {

    public Trabajadores() {
        super("Trabajadores");
    }

    @Override
    public double calcularDescuento(double montoTarifa,
                                    Propietario propietario,
                                    Puesto puesto,
                                    Vehiculo vehiculo,
                                    Date fechaTransito) {

       
        DayOfWeek dia = fechaTransito.toInstant().atZone(ZoneId.systemDefault()).getDayOfWeek();

        if (dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY) {
           
            return Math.round(montoTarifa * 0.8);
        }

        return 0;
    }
}
