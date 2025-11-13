package da.obligatorio.obligatorioDA.modelo;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

public class CriterioPropietarioHabilitado extends CriterioAsignacionBonificacion {

    public CriterioPropietarioHabilitado() {
        super("Propietario debe estar habilitado");
    }

    @Override
    public void validar(Propietario p, Puesto puesto) throws ObligatorioException {
        
        if (!"Activo".equalsIgnoreCase(p.getEstadoPropietario().getNombre())) {

            throw new ObligatorioException( "El propietario está deshabilitado. No se pueden asignar bonificaciones");

        }
}

}
