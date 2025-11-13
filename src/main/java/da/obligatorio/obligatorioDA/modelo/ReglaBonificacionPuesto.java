
package da.obligatorio.obligatorioDA.modelo;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

public class ReglaBonificacionPuesto implements ReglaAsignacionBonificacion {
    @Override
    public void validar(Propietario p, Puesto puesto) throws ObligatorioException {
        if (p.getListBonificaciones() == null) return;

  for (Bonificacion b : p.getListBonificaciones()) {
    if (b != null) {
        Puesto pp = b.getPuestos();
        if (pp != null && pp.getId() == puesto.getId()) {
            throw new ObligatorioException("Ya tiene una bonificación asignada para ese puesto");
        }
    }
    }
}

}
