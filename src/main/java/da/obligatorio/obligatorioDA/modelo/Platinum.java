package da.obligatorio.obligatorioDA.modelo;

public class Platinum extends CriterioAsignacionBonificacion {

    public Platinum() {
        super("Platinum");
    }

    @Override
    public double calcularDescuento(double montoTarifa, Propietario propietario, Puesto puesto) {
        return Math.max(0, Math.round(montoTarifa * 0.30f)); // 30% de descuento
    }
    
}
