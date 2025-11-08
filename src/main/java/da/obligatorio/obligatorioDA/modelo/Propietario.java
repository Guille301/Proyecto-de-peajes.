package da.obligatorio.obligatorioDA.modelo;

import java.util.ArrayList;
import java.util.List;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

public class Propietario extends Usuario {
    private int id;
    private String nombreCompleto;
    private double saldo;
    private Bonificacion bonificacion;
    private String cedula;
    private EstadoPropietario estadoPropietario;
    private List<Notificacion> listaNotificaciones;
    private List<Vehiculo> listVehiculos;
    private List<Bonificacion> listBonificaciones;

    public Propietario() {
    }

    public Propietario(int id, String nombreCompleto, double saldo, Bonificacion bonificacion,
                       String cedula, EstadoPropietario estadoPropietario,
                       List<Notificacion> listaNotificaciones, List<Vehiculo> listVehiculos,
                       List<Bonificacion> listBonificaciones) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.saldo = saldo;
        this.bonificacion = bonificacion;
        this.cedula = cedula;
        this.estadoPropietario = estadoPropietario;
        this.listaNotificaciones = listaNotificaciones;
        this.listVehiculos = listVehiculos;
        this.listBonificaciones = listBonificaciones;
    }

    public Propietario(int id, String nombreCompleto, String cedula, String contrasena) {
        super(cedula, contrasena);
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Bonificacion getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(Bonificacion bonificacion) {
        this.bonificacion = bonificacion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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
    public void setNotificaciones(Notificacion n) {
        listaNotificaciones.add(n);
    }

    public List<Vehiculo> getListVehiculos() {
        return listVehiculos;
    }
    //Corregido
    public void setVehiculos(Vehiculo v) {
        listVehiculos.add(v);
    }

    public List<Bonificacion> getListBonificaciones() {
        return listBonificaciones;
    }
     //Corregido
    public void setBonificaciones(Bonificacion b) {
        listBonificaciones.add(b);
    }

      public void debitarPorTransito(double costo) throws ObligatorioException {
        if (this.saldo < costo) {
            throw new ObligatorioException("Saldo insuficiente: " + this.saldo);
        }
        this.saldo -= costo;
    }



      
    


    




}