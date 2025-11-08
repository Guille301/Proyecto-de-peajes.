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
    

//Puesto

    public Puesto() {
    }

    public Puesto(int id,String nombre, String direccion, List<Tarifa> listTarifas,
                  List<Transito> listTransito, List<Bonificacion> listBonificacion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.listTarifas = listTarifas;
        this.listTransito = listTransito;
        this.listBonificacion = listBonificacion;
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
    //Corregido
    public void setTarifas(Tarifa tarifa) {
        listTarifas.add(tarifa);
    }

    public List<Transito> getListTransito() {
        return listTransito;
    }
      //Corregido
    public void setTransito(Transito t) {
        listTransito.add(t);
    }

    public List<Bonificacion> getListBonificacion() {
        return listBonificacion;
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