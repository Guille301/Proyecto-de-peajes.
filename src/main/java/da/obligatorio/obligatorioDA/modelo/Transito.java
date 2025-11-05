package da.obligatorio.obligatorioDA.modelo;

import java.util.Date;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

public class Transito {
    private int id;
    private Puesto puesto;         // <-- singular
    private Vehiculo vehiculo;     // <-- CAMBIO: Vehiculo singular
    private Date fechaYHora;

    public Transito() {
    }

    public Transito(int id, Puesto puesto, Vehiculo vehiculo, Date fechaYHora) {
        this.id = id;
        this.puesto = puesto;
        this.vehiculo = vehiculo;
        this.fechaYHora = fechaYHora;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Puesto getPuesto() { return puesto; }
    public void setPuesto(Puesto puesto) { this.puesto = puesto; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public Date getFechaYHora() { return fechaYHora; }
    public void setFechaYHora(Date fechaYHora) { this.fechaYHora = fechaYHora; }

     public double costoTransito(Vehiculo vehiculo){
       double monto = this.puesto.obtenerTarifaPara(vehiculo).getMonto();
       return monto; 
   }

   public double costoTransitoEmulacion() throws ObligatorioException {
    Tarifa tarifa = this.puesto.obtenerTarifaPara(this.vehiculo);
    if (tarifa == null) {
        throw new ObligatorioException(
            "No hay tarifa para la categoría del vehículo en este puesto"
        );
    }
    return tarifa.getMonto();
}


  }





