package da.obligatorio.obligatorioDA.dtos;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import da.obligatorio.obligatorioDA.modelo.Propietario;

public class propietarioDto {
        private String nombreCompleto;
        private String cedula;
        private String estadoPropietario;


        public propietarioDto(Propietario propietario) {
            this.nombreCompleto = propietario.getNombreCompleto();
            this.cedula = propietario.getCedula();
            this.estadoPropietario = propietario.getEstadoPropietario().getNombre();
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
