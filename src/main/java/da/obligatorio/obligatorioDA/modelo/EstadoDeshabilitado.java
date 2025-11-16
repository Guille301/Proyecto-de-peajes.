package da.obligatorio.obligatorioDA.modelo;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

public class EstadoDeshabilitado extends EstadoPropietario {
    
    public EstadoDeshabilitado(Propietario propietario) {
        super(2, "Deshabilitado", propietario);
    }

    @Override public void validarIngresarSistema() throws ObligatorioException { 
        throw new ObligatorioException("El propietario está Deshabilitado: no puede ingresar al sistema.");
    }

    @Override public void validarPuedeTransitar() throws ObligatorioException { 
        throw new ObligatorioException("El propietario está Deshabilitado: no se le puede registrar transitos.");
    }
    
    @Override public void validarAsignarBonificacion() throws ObligatorioException { 
        throw new ObligatorioException("El propietario está Deshabilitado: no se le pueden asignar bonificaciones.");
    }

    @Override public boolean aceptaNotificaciones() {return true; }

}
