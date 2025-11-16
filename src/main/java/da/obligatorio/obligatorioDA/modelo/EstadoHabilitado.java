package da.obligatorio.obligatorioDA.modelo;

public class EstadoHabilitado extends EstadoPropietario {
    
    public EstadoHabilitado(Propietario propietario) {
        super(1, "Habilitado", propietario);
    }

    @Override public void validarPuedeTransitar() { }
    @Override public void validarIngresarSistema() { }
    @Override public void validarAsignarBonificacion() { }

}
