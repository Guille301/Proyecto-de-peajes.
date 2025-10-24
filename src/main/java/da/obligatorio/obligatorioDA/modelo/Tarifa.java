package da.obligatorio.obligatorioDA.modelo;

public class Tarifa {
    private int id;
    private double monto;
    private CategoriaVehiculo categoriaVehiculo;

    public Tarifa() {
    }

    public Tarifa(int id, double monto, CategoriaVehiculo categoriaVehiculo) {
        this.id = id;
        this.monto = monto;
        this.categoriaVehiculo = categoriaVehiculo;
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

    public CategoriaVehiculo getCategoriaVehiculo() {
        return categoriaVehiculo;
    }

    public void setCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) {
        this.categoriaVehiculo = categoriaVehiculo;
    }
}