package da.obligatorio.obligatorioDA.modelo;

public class Exonerados extends CriterioAsignacionBonificacion {

    public Exonerados() {
        super("Exonerados");
    }

    @Override
    public double calcularDescuento(double montoTarifa,  Propietario propietario,Puesto puesto) {
        return Math.max(0, montoTarifa);
    }
}
