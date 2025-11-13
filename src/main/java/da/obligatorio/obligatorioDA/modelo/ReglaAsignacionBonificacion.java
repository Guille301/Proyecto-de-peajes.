package da.obligatorio.obligatorioDA.modelo;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

public interface ReglaAsignacionBonificacion {
    void validar(Propietario p, Puesto puesto) throws ObligatorioException;
}