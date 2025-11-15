package da.obligatorio.obligatorioDA.modelo;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

public class Promocion extends CriterioAsignacionBonificacion {

      public Promocion() {
        super("Promoción");
    }

       @Override
    public double calcularDescuento(double montoTarifa, Propietario propietario, Puesto puesto) throws ObligatorioException {
        return Math.max(0, Math.round(montoTarifa * 0.10f)); // 10% de descuento
    }
}
