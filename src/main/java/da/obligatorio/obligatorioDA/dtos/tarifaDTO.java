package da.obligatorio.obligatorioDA.dtos;

import da.obligatorio.obligatorioDA.modelo.Tarifa;

public class tarifaDTO {

    private String categoria; // nombre de la categoría
    private double monto;

    public tarifaDTO(Tarifa t) {
        this.categoria = t.getCategoriaVehiculo().getNombre();
        this.monto = t.getMonto();
    }

    public String getCategoria() {
        return categoria;
    }

    public double getMonto() {
        return monto;
    }
}
