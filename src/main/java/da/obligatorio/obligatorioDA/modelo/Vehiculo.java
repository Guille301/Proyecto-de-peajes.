package da.obligatorio.obligatorioDA.modelo;

import java.util.ArrayList;
import java.util.List;

public class Vehiculo {
    private int id;
    private String matricula;
    private String modelo;
    private String color;
    private CategoriaVehiculo categoriaVehiculo;
    private Propietario propietario;
    private List<Transito> listaTransito;

    public Vehiculo() {
    }

    public Vehiculo(int id, String matricula, String modelo, String color,
                    CategoriaVehiculo categoriaVehiculo, Propietario propietario,
                    List<Transito> listaTransito) {
        this.id = id;
        this.matricula = matricula;
        this.modelo = modelo;
        this.color = color;
        this.categoriaVehiculo = categoriaVehiculo;
        this.propietario = propietario;
        this.listaTransito = listaTransito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CategoriaVehiculo getCategoriaVehiculo() {
        return categoriaVehiculo;
    }

    public void setCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) {
        this.categoriaVehiculo = categoriaVehiculo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public List<Transito> getListaTransito() {
        return listaTransito;
    }

    public void setTransito(Transito t) {
        listaTransito.add(t);
    }


       public double calcularMontoTotalTodosLosTransitos(){
        double montoTotal = 0.0;
        for(Transito t : this.listaTransito){
            montoTotal += t.costoTransito(this);
        }
        return montoTotal;
    }

  

}