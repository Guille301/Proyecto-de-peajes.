package da.obligatorio.obligatorioDA.dtos;

import da.obligatorio.obligatorioDA.modelo.EstadoPropietario;

public class estadoPropietarioDTO {
        private String nombre;

        public estadoPropietarioDTO(EstadoPropietario estado){
            this.nombre = estado.getNombre();  
        }

    public String getNombre() { return nombre; }
 
}
