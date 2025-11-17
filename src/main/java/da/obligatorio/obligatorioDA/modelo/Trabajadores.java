package da.obligatorio.obligatorioDA.modelo;

public class Trabajadores extends CriterioAsignacionBonificacion {

    

   public Trabajadores() {
        super("Trabajadores");  
    }


  @Override
    public double calcularDescuento(double montoTarifa, Propietario propietario, Puesto puesto) {
        
        return Math.max(0, Math.round(montoTarifa * 0.8f)); 
    }
    
}
