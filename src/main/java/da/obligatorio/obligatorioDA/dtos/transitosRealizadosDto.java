package da.obligatorio.obligatorioDA.dtos;
import da.obligatorio.obligatorioDA.modelo.Transito;
import da.obligatorio.obligatorioDA.modelo.Puesto;

public class transitosRealizadosDto {

    private String nombrePuesto;
    private String matriculaVehiculo;
    private String categoriaVehiculo;
    private double montoTarifa;
    private String nombreBonificacion;
    private double montoBonificacion;
    private double montoPagado;
    private String fechaYHora; 

    public transitosRealizadosDto(Transito transito, Puesto puesto) {
        this.nombrePuesto = transito.getPuesto().getNombre();
        this.matriculaVehiculo = transito.getVehiculo().getMatricula();
        this.categoriaVehiculo = transito.getVehiculo().getCategoriaVehiculo().getNombre();
        this.montoTarifa = puesto.obtenerTarifaPara(transito.getVehiculo()).getMonto();
        this.nombreBonificacion = puesto.obtenerBonificacion(transito).getNombre();
        this.montoBonificacion = puesto.obtenerBonificacion(transito).getMontoDescuento();
        this.montoPagado = transito.costoTransito(transito.getVehiculo());
        this.fechaYHora = transito.getFechaYHora().toString();
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public String getCategoriaVehiculo() {
        return categoriaVehiculo;
    }

    public double getMontoTarifa() {
        return montoTarifa;
    }

    public String getNombreBonificacion() {
        return nombreBonificacion;
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
