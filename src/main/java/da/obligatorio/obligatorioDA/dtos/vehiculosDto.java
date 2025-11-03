package da.obligatorio.obligatorioDA.dtos;
import java.util.List;

import da.obligatorio.obligatorioDA.modelo.CategoriaVehiculo;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Transito;
import da.obligatorio.obligatorioDA.modelo.Vehiculo;

public class vehiculosDto {

    private String matricula;
    private String modelo;
    private String color;
    //private List<Transito> listaTransito;

    public vehiculosDto(Vehiculo vehiculo) {
        this.matricula = vehiculo.getMatricula();
        this.modelo = vehiculo.getModelo();
        this.color = vehiculo.getColor();
      //  this.listaTransito = vehiculo.getListaTransito();
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

   // public List<Transito> getListaTransito() {
    //    return listaTransito;
   // }
}
