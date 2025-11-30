package da.obligatorio.obligatorioDA.modelo;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import da.obligatorio.obligatorioDA.dtos.BonificacionAsignadaDTO;
import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

public class Propietario extends Usuario {
    
    private String nombreCompleto;
    private double saldo;
    private EstadoPropietario estadoPropietario;
    private List<Notificacion> listaNotificaciones;
    private List<Vehiculo> listVehiculos;
    private List<Bonificacion> listBonificaciones;

    public Propietario() {
        this.listaNotificaciones = new ArrayList<>();
        this.listVehiculos = new ArrayList<>();
        this.listBonificaciones = new ArrayList<>();
    }

    public Propietario(int id, String nombreCompleto, double saldo, Bonificacion bonificacion,
                       String cedula, EstadoPropietario estadoPropietario,
                       List<Notificacion> listaNotificaciones, List<Vehiculo> listVehiculos,
                       List<Bonificacion> listBonificaciones) {
         
        this.nombreCompleto = nombreCompleto;
        this.saldo = saldo;
        this.estadoPropietario = estadoPropietario;
        this.listaNotificaciones = listaNotificaciones;
        this.listVehiculos = listVehiculos;
        this.listBonificaciones = listBonificaciones;
    }

    public Propietario(int id, String nombreCompleto, String cedula, String contrasena) {
        super(id,cedula, contrasena);
       
        this.nombreCompleto = nombreCompleto;
    }



    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }



    public EstadoPropietario getEstadoPropietario() {
        return estadoPropietario;
    }

    public void setEstadoPropietario(EstadoPropietario estadoPropietario) {
        this.estadoPropietario = estadoPropietario;
    }

    public List<Notificacion> getListaNotificaciones() {
        return listaNotificaciones;
    }
    //Corregido
    public void agregarNotificaciones(Notificacion n) {
        listaNotificaciones.add(n);
        listaNotificaciones.sort((a, b) -> b.getFechaYHora().compareTo(a.getFechaYHora()));
    }

    public List<Vehiculo> getListVehiculos() {
        return listVehiculos;
    }

    public void agregarVehiculoPropietario(Vehiculo nuevoVehiculo) {
        this.listVehiculos.add(nuevoVehiculo);
    }

    public List<Bonificacion> getListBonificaciones() {
        return listBonificaciones;
    }

    public void agregarBonificacionPropietario(Bonificacion nuevBonificacion) {
        this.listBonificaciones.add(nuevBonificacion);
    }

    //Traer los vehiculos que tuvieron un transito
    public List<Vehiculo> traerVehiculosConTransito() {
        List<Vehiculo> vehiculosConTransito = new java.util.ArrayList<>();
        if (this.listVehiculos != null ) {
            for (Vehiculo v : this.listVehiculos) {
                if (v.getListaTransito() != null && !v.getListaTransito().isEmpty()) {
                    vehiculosConTransito.add(v);
                }
            }
        }
       
        return vehiculosConTransito;
    }

    //Traigo los transitos de mis vehiculos
    public List<Transito> traerTransitosDeMisVehiculos() {
        List<Transito> transitosPropietario = new java.util.ArrayList<>();
        for (Vehiculo v : this.listVehiculos) {
            transitosPropietario.addAll(v.getListaTransito());
        }
        transitosPropietario.sort((a, b) -> b.getFechaYHora().compareTo(a.getFechaYHora()));
        return transitosPropietario;
    }

    public void setBonificaciones(Bonificacion b) {
        listBonificaciones.add(b);
    }

    public void debitarPorTransito(double costo) throws ObligatorioException {
        if (this.saldo < costo) {
            throw new ObligatorioException("Saldo insuficiente: " + this.saldo);
        }
        this.saldo -= costo;
    }

    public void borrarNotificaciones() {
        if (listaNotificaciones != null) {
            listaNotificaciones.clear();
        }
    }

    //Cambio de estado
    public void cambiarEstado(EstadoPropietario nuevoEstado) throws ObligatorioException {
        if (this.estadoPropietario == nuevoEstado) {
            throw new ObligatorioException("El propietario ya esta en estado: " + this.estadoPropietario.getNombre());
        }
        this.estadoPropietario = nuevoEstado;
    }

    //Validaciones de propietario
    public void validarIngresarSistema() throws ObligatorioException {
        this.estadoPropietario.validarIngresarSistema();
    }

    public void validarPoderTransitar() throws ObligatorioException {
        this.estadoPropietario.validarPuedeTransitar();
    }

    public void validarAsignarBonificacion() throws ObligatorioException {
        this.estadoPropietario.validarAsignarBonificacion();
    }

    public boolean validarPenalizado() throws ObligatorioException {
        return this.estadoPropietario.validacionesPenalizado();
    }


public long contarTransitosDelDia(Puesto puesto, Vehiculo vehiculo, Date fecha) {

    if (vehiculo == null || vehiculo.getListaTransito() == null) {
        return 0;
    }

    LocalDate diaTransito = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    long contador = 0;

    for (Transito t : vehiculo.getListaTransito()) {

        
        if (!t.getPuesto().equals(puesto)) {
            continue;
        }

        LocalDate diaT = t.getFechaYHora().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // mismo día
        if (diaT.equals(diaTransito)) {
            contador++;
        }
    }

    return contador;
}
    

}
