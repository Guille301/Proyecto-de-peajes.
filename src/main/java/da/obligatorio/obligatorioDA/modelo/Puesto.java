package da.obligatorio.obligatorioDA.modelo;

import java.util.List;

public class Puesto {
    private int id;
    private String nombre;
    private String direccion;
    private List<Tarifa> listTarifas;
    private List<Transito> listTransito;
    private List<Bonificacion> listBonificacion;
    private List<Cobro> listaCobro;

//Puesto

    public Puesto() {
    }

    public Puesto(int id,String nombre, String direccion, List<Tarifa> listTarifas,
                  List<Transito> listTransito, List<Bonificacion> listBonificacion,
                  List<Cobro> listaCobro) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.listTarifas = listTarifas;
        this.listTransito = listTransito;
        this.listBonificacion = listBonificacion;
        this.listaCobro = listaCobro;
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

    public void setListTarifas(List<Tarifa> listTarifas) {
        this.listTarifas = listTarifas;
    }

    public List<Transito> getListTransito() {
        return listTransito;
    }

    public void setListTransito(Transito nuevTransito) {
        this.listTransito.add(nuevTransito);
    }

    public List<Bonificacion> getListBonificacion() {
        return listBonificacion;
    }

    public void setListBonificacion(Bonificacion nuevaBonificacion) {
        this.listBonificacion.add(nuevaBonificacion);
    }

    public List<Cobro> getListaCobro() {
        return listaCobro;
    }

    public void setListaCobro(List<Cobro> listaCobro) {
        this.listaCobro = listaCobro;
    }
}