package da.obligatorio.obligatorioDA.modelo;

public class EstadoPenalizado extends EstadoPropietario {

    public EstadoPenalizado(Propietario propietario) {
        super(4, "Penalizado", propietario);
    }

    @Override public void validarPuedeTransitar() { }
    @Override public void validarIngresarSistema() { }
    @Override public void validarAsignarBonificacion() { }


}
