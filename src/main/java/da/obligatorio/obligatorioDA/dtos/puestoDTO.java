 package da.obligatorio.obligatorioDA.dtos;

 import da.obligatorio.obligatorioDA.modelo.Puesto;

public class puestoDTO {

    private int id;
    private String nombre;

    public puestoDTO(Puesto p) {
         this.id = p.getId();              
        this.nombre = p.getNombre();      
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
