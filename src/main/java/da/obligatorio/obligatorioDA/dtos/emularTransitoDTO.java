package da.obligatorio.obligatorioDA.dtos;

import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Vehiculo;

public class emularTransitoDTO {

    private String nombrePropietario;
    private String estadoPropietario;
    private String categoriaVehiculo;
    private String nombreBonificacion; 
    private double costoTransito;
    private double saldoLuegoDelTransito;

   
    public emularTransitoDTO(Propietario propietario, Vehiculo vehiculo, Bonificacion bonificacionAplicada, double costoTransito,
    double saldoLuegoDelTransito) {

        this.nombrePropietario = propietario.getNombreCompleto();
        this.estadoPropietario = propietario.getEstadoPropietario().getNombre(); 
        this.categoriaVehiculo = vehiculo.getCategoriaVehiculo().getNombre();           

        this.nombreBonificacion = (bonificacionAplicada != null)
                ? bonificacionAplicada.getNombre()      
                : null;

        this.costoTransito = costoTransito;
        this.saldoLuegoDelTransito = saldoLuegoDelTransito;
    }

    // Solo getters, como en el ejemplo de AgendaDto

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public String getEstadoPropietario() {
        return estadoPropietario;
    }

    public String getCategoriaVehiculo() {
        return categoriaVehiculo;
    }

    public String getNombreBonificacion() {
        return nombreBonificacion;
    }

    public double getCostoTransito() {
        return costoTransito;
    }

    public double getSaldoLuegoDelTransito() {
        return saldoLuegoDelTransito;
    }
}
