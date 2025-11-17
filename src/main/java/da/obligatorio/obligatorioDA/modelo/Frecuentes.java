package da.obligatorio.obligatorioDA.modelo;



public class Frecuentes extends CriterioAsignacionBonificacion {

    

   public Frecuentes() {
        super("Frecuentes");  
    }


  @Override
    public double calcularDescuento(double montoTarifa, Propietario propietario, Puesto puesto) {
        
        return Math.max(0, Math.round(montoTarifa * 0.5f)); 
    }
    
}
