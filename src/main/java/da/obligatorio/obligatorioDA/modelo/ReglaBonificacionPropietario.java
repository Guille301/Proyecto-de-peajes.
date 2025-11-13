
package da.obligatorio.obligatorioDA.modelo;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

public class ReglaBonificacionPropietario implements ReglaAsignacionBonificacion {

    @Override
    public void validar(Propietario p, Puesto puesto) throws ObligatorioException {

        if (!"Activo".equalsIgnoreCase(p.getEstadoPropietario().getNombre())) {

            throw new ObligatorioException( "El propietario esta deshabilitado. No se pueden asignar bonificaciones" );

        }
    }
}

