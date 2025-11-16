package da.obligatorio.obligatorioDA.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import da.obligatorio.obligatorioDA.dtos.BonificacionAsignadaDTO;
import da.obligatorio.obligatorioDA.dtos.bonificacionDefinidaDTO;
import da.obligatorio.obligatorioDA.dtos.propietarioAsignarBonifDTO;
import da.obligatorio.obligatorioDA.dtos.puestoDTO;
import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Puesto;
import da.obligatorio.obligatorioDA.servicios.Fachada;

@RestController
@RequestMapping("/asignarBonificacion")
@Scope("session")
public class ControladorAsignarBonificacion {

    @GetMapping("/vistaConectada")
    public List<Respuesta> inicializarVista(@SessionAttribute(name = "usuarioAdmin", required = false) Administrador usuario) {
        if (usuario == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginAdmin.html"));
        }
        return Respuesta.lista( puestosRespuesta(), bonificacionesRespuesta());
    }

    @PostMapping("/asignar")
    public List<Respuesta> asignarBonificacion( @RequestParam String cedula, @RequestParam int idPuesto, @RequestParam String nombreBonificacion) {
        try {            
            Bonificacion bonificacion = Fachada.getInstancia().asignarBonificacion(cedula, idPuesto, nombreBonificacion);
            BonificacionAsignadaDTO dto = new BonificacionAsignadaDTO(bonificacion.getPuestos().getNombre(),bonificacion.getNombre(), bonificacion.getFechaAsignacion());
            
            return Respuesta.lista(new Respuesta("resultadoAsignacionBonificacion", dto));

        } catch (ObligatorioException ex) {
            return Respuesta.lista(new Respuesta("errorAsignacionBonificacion", ex.getMessage()));
        }
    }

    @PostMapping("/buscarPropietario")
    public List<Respuesta> buscarPropietario( @RequestParam String cedula,@SessionAttribute(name = "usuarioAdmin", required = false) Administrador usuario) {
        try {
            if (usuario == null) {
                return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginAdmin.html"));
            }

            if (cedula == null || cedula.isBlank()) {
                throw new ObligatorioException("Valor ingresado inválido");
            }

            Propietario propietario = Fachada.getInstancia().obtenerPropietarioPorCedula(cedula);
            if (propietario == null) {
                throw new ObligatorioException("No se encuentra un propietario con esa cédula");
            }

            propietarioAsignarBonifDTO dto = new propietarioAsignarBonifDTO(propietario);

            return Respuesta.lista(new Respuesta("propietarioBusqueda", dto));

        } catch (ObligatorioException e) {
            return Respuesta.lista(new Respuesta("errorBuscador", e.getMessage()));
        }
    }

    private Respuesta puestosRespuesta() {
        List<Puesto> lista = Fachada.getInstancia().getPuestos();
        List<puestoDTO> puestosDto = new ArrayList<>();
            for (Puesto p : lista) {
                puestosDto.add(new puestoDTO(p));
            }
            return new Respuesta("puestos", puestosDto); 
    }

    private Respuesta bonificacionesRespuesta() {
        List<Bonificacion> lista = Fachada.getInstancia().getBonificaciones();
        List<bonificacionDefinidaDTO> bonificacionesDto = new ArrayList<>();
        for (Bonificacion b : lista) {
            bonificacionesDto.add(new bonificacionDefinidaDTO(b));
        }
        return new Respuesta("bonificaciones", bonificacionesDto);
    }
}