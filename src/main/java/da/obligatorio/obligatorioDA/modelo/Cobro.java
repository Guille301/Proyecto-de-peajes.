package da.obligatorio.obligatorioDA.modelo;

public class Cobro {
    private int id;
    private double monto;
    private Puesto puesto;

    public Cobro() {
    }

    public Cobro(int id, double monto, Puesto puesto) {
        this.id = id;
        this.monto = monto;
        this.puesto = puesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
}