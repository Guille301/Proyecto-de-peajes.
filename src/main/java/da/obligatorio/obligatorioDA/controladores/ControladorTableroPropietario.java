package da.obligatorio.obligatorioDA.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Usuario;
import da.obligatorio.obligatorioDA.modelo.Vehiculo;
import da.obligatorio.obligatorioDA.servicios.Fachada;
import jakarta.servlet.http.HttpSession;
import da.obligatorio.obligatorioDA.dtos.bonificacionPropietarioDto;
import da.obligatorio.obligatorioDA.dtos.vehiculosDto;
import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

@RestController
@RequestMapping("/tablero")
public class ControladorTableroPropietario {
private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ControladorTableroPropietario.class);

    List<Vehiculo> vehiculosPropietario;
    
    @GetMapping("/resumenDelPropietario")
    public List<Respuesta> resumenDelPropietario(@SessionAttribute(name = "usuarioPropietario", required=false) Propietario usuario){
        if (usuario == null) {
             // Manejar el caso en que el usuario no está en la sesión pide redireccionar a la página de login
             return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginPropietario.html"));
         }
         return Respuesta.lista(
             new Respuesta("nombreCompleto", usuario.getNombreCompleto()),
             new Respuesta("estadoPropietario", usuario.getEstadoPropietario().getNombre()),
             new Respuesta("saldoPropietario", String.valueOf(usuario.getSaldo())),
             vehiculosConTransito(usuario),
             bonficacionesPropietario(usuario)      
         );
        
    }

    private Respuesta vehiculosConTransito(Propietario usuario){
        List<Vehiculo> vehiculos = usuario.traerVehiculosConTransito();
        List<vehiculosDto> vehDtos = new ArrayList<>();
        for(Vehiculo tc : vehiculos){
            vehDtos.add(new vehiculosDto(tc));
        }
        return new Respuesta("vehiculosConTransito", vehDtos);
    }

    private Respuesta bonficacionesPropietario(Propietario usuario){
    List<Bonificacion> bonificaciones = usuario.getListBonificaciones();
        List<bonificacionPropietarioDto> bonDtos = new ArrayList<>();
        for(Bonificacion tc : bonificaciones){
            bonDtos.add(new bonificacionPropietarioDto(tc));
        }

        return new Respuesta("bonificacionesPropietario", bonDtos);
    }
    
}
