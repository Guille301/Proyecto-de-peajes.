package da.obligatorio.obligatorioDA.modelo;



public class Fidelidad extends CriterioAsignacionBonificacion {

    

   public Fidelidad() {
        super("Fidelidad");  
    }


  @Override
    public double calcularDescuento(double montoTarifa, Propietario propietario, Puesto puesto) {
        
        return Math.max(0, Math.round(montoTarifa * 0.5f)); // 50% de descuento
    }
    
}
