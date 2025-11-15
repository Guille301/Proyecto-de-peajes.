package da.obligatorio.obligatorioDA.modelo;

public class Premium extends CriterioAsignacionBonificacion {

    public Premium() {
        super("Premium");
    }

    @Override
    public double calcularDescuento(double montoTarifa, Propietario propietario, Puesto puesto) {
        return Math.max(0, Math.round(montoTarifa * 0.20f)); // 20% de descuento
    }
    
}
