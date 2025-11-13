package da.obligatorio.obligatorioDA.modelo;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

public class CriterioPuestoConBonificacion extends CriterioAsignacionBonificacion {

    public CriterioPuestoConBonificacion() {
        super("Ya tiene bonificación para el puesto");
    }

    @Override
    public void validar(Propietario propietario, Puesto puesto) throws ObligatorioException {

        if (propietario.getListBonificaciones() == null) {
            return;
        }

        for (Bonificacion b : propietario.getListBonificaciones()) {
            if (b != null && b.getPuestos() != null) {
                Puesto pp = b.getPuestos();
                if (pp.getId() == puesto.getId()) {
                    throw new ObligatorioException("Ya tiene una bonificación asignada para ese puesto");
                }
            }
        }
    }
}
