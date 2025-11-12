package da.obligatorio.obligatorioDA.servicios;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Puesto;
import da.obligatorio.obligatorioDA.modelo.Transito;
import da.obligatorio.obligatorioDA.modelo.Vehiculo;


public class SistemaPuestos {
    private List<Puesto> puestos;

    public SistemaPuestos() {
        this.puestos = new ArrayList<>();
    }

    public SistemaPuestos(List<Puesto> puestos) {
        this.puestos = puestos != null ? puestos : new ArrayList<>();
    }

    public List<Puesto> getPuestos() {
        return puestos;
    }

    public void setPuestos(List<Puesto> puestos) {
        this.puestos = puestos;
    }

    public void agregarPuesto(Puesto puesto) {
        if (puesto != null) {
            puestos.add(puesto);
        }
    }

    public boolean eliminarPuesto(Puesto puesto) {
        return puestos.remove(puesto);
    }

    public Puesto obtenerPuestoPorId(int id) {
        for (Puesto p : puestos) {
            if (p.getId() == id) return p;
        }
        return null;
    }



    public Transito emularTransito(int idPuesto, String matricula, Date fechaHora)
            throws ObligatorioException {

        Fachada fachada = Fachada.getInstancia();

        
        Puesto puesto = fachada.obtenerPuestoPorId(idPuesto);
        

        
        Vehiculo vehiculo = fachada.obtenerVehiculoPorMatriculaObligatorio(matricula);
        Propietario propietario = fachada.obtenerPropietarioPorVehiculoObligatorio(vehiculo);

        
        fachada.validarEstadoParaTransito(propietario);

    
        Transito transito = new Transito(0, puesto, vehiculo, fechaHora);

        
        double costo = transito.costoTransitoEmulacion();
        propietario.debitarPorTransito(costo);

        
        vehiculo.agregarTransito(transito);
        puesto.agregarTransitoPuesto(transito);

        
        fachada.registrarNotificacionesTransito(propietario, puesto, vehiculo);

    
        return transito;
    }



}