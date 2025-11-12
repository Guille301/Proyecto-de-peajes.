package da.obligatorio.obligatorioDA.dtos;
import da.obligatorio.obligatorioDA.modelo.Transito;
import da.obligatorio.obligatorioDA.modelo.Puesto;

public class transitosRealizadosDto {

    private String puesto;
    private String matricula;
    private String categoria;
    private double montoTarifa;
    private String Bonificacion;
    private double montoBonificacion;
    private double montoPagado;
    private String fechaYHora; 

    public transitosRealizadosDto(Transito transito) {
        this.puesto = transito.getPuesto().getNombre();
        this.matricula = transito.getVehiculo().getMatricula();
        this.categoria = transito.getVehiculo().getCategoriaVehiculo().getNombre();
        this.montoTarifa = transito.tarifaDelTransito().getMonto();
        this.Bonificacion = transito.bonificacionDelTransito().getNombre();
        this.montoBonificacion = transito.bonificacionDelTransito().getMontoDescuento();
        this.montoPagado = transito.costoTransito(transito.getVehiculo());
        this.fechaYHora = transito.getFechaYHora().toString();
    }

    public String getPuesto() {
        return puesto;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getMontoTarifa() {
        return montoTarifa;
    }

    public String getBonificacion() {
        return Bonificacion;
    }

    public double getMontoBonificacion() {
        return montoBonificacion;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public String getFechaYHora() {
        return fechaYHora;
    }


    
}
