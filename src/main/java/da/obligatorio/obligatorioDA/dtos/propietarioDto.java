package da.obligatorio.obligatorioDA.dtos;


import da.obligatorio.obligatorioDA.modelo.Propietario;

public class propietarioDto {
        private int id;
        private String nombreCompleto;
        private String cedula;
        private String estadoPropietario;


        public propietarioDto(Propietario propietario) {
            this.id = propietario.getId();
            this.nombreCompleto = propietario.getNombreCompleto();
            this.cedula = propietario.getCedula();
            this.estadoPropietario = propietario.getEstadoPropietario().getNombre();
        }
        
        public int getId() {
            return id;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public String getCedula() {
            return cedula;
        }

        public String getEstadoPropietario() {
            return estadoPropietario;
        }
}
