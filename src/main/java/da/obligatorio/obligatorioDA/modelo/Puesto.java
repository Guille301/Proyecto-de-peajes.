package da.obligatorio.obligatorioDA.modelo;

import java.util.ArrayList;
import java.util.List;

public class Puesto {
    private int id;
    private String nombre;
    private String direccion;
    private List<Tarifa> listTarifas;
    private List<Transito> listTransito;
    private List<Bonificacion> listBonificacion;
    
    public Puesto() {
        this.listTarifas = new ArrayList<>();
        this.listTransito = new ArrayList<>();
        this.listBonificacion = new ArrayList<>();
    }

    public Puesto(int id,String nombre, String direccion, List<Tarifa> listTarifas,
                  List<Transito> listTransito, List<Bonificacion> listBonificacion
                  ) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.listTarifas = new ArrayList<>();
        this.listTransito = new ArrayList<>();
        this.listBonificacion = new ArrayList<>();
        this.id = id;
    }

     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Tarifa> getListTarifas() {
        return listTarifas;
    }

    public void agrgarTarifaPuesto(Tarifa nuevaTarifa) {
        this.listTarifas.add(nuevaTarifa);
    }

    public List<Transito> getListTransito() {
        return listTransito;
    }

    public void agregarTransitoPuesto(Transito nuevTransito) {
        this.listTransito.add(nuevTransito);
    }

    public List<Bonificacion> getListBonificacion() {
        return listBonificacion;
    }

    public void agregarBonificacionPuesto(Bonificacion nuevaBonificacion) {
        this.listBonificacion.add(nuevaBonificacion);
    }

    //Traer la bonificacion para aplicar en el transito
    public Bonificacion obtenerBonificacion(Transito transito){

        if(listBonificacion == null){
            return null;
        }

        for (Bonificacion bonificacion : listBonificacion) {
            if(bonificacion.getPropietario().equals(transito.getVehiculo().getPropietario())){
                return bonificacion;
            }
        }
        return null;

    }

    //Corregido
    public void setBonificacion(Bonificacion b) {
       listBonificacion.add(b);
    }

    public Tarifa obtenerTarifaPara(Vehiculo vehiculo) {
        if (listTarifas == null) {
            return null;
        }
        for (Tarifa t : listTarifas) { 
            if (t.getCategoriaVehiculo().equals(vehiculo.getCategoriaVehiculo())) {
                 return t; 
                }
            }

        return null;
    }

    public Bonificacion obtenerBonificacionPara(Propietario propietario) {
        if (listBonificacion == null) {
            return null;
        }
        for (Bonificacion b : listBonificacion) {
            if (b.getPropietario().equals(propietario)) { 
                return b;
            }
        }
        return null;
    }
}
