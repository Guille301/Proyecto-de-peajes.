package da.obligatorio.obligatorioDA.modelo;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

public class EstadoSuspendido extends EstadoPropietario {

    public EstadoSuspendido(Propietario propietario) {
        super(3, "Suspendido", propietario);
    }

    @Override 
    public void validarPuedeTransitar() throws ObligatorioException {
        throw new ObligatorioException("El propietario está suspendido: no puede realizar tránsito.");
    }

    @Override public void validarIngresarSistema() { }
    @Override public void validarAsignarBonificacion() { }
    @Override public boolean validacionesPenalizado() {return true; }

}
