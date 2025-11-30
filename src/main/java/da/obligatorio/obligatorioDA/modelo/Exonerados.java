package da.obligatorio.obligatorioDA.modelo;
import java.util.Date;

public class Exonerados extends CriterioAsignacionBonificacion {

    public Exonerados() {
        super("Exonerados");
    }

    @Override
    public double calcularDescuento(double montoTarifa,
                                    Propietario propietario,
                                    Puesto puesto,
                                    Vehiculo vehiculo,
                                    Date fechaTransito) {
   
        return montoTarifa;
    }
}

