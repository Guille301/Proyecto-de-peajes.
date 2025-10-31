package da.obligatorio.obligatorioDA.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Usuario;
import da.obligatorio.obligatorioDA.servicios.Fachada;
import jakarta.servlet.http.HttpSession;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

@RestController
@RequestMapping("/tablero")
public class ControladorTableroPropietario {
    
    @GetMapping("/resumenDelPropietario")
    public List<Respuesta> resumenDelPropietario(@SessionAttribute(name = "usuarioPropietario", required=false) Propietario usuario){
        if (usuario == null) {
             // Manejar el caso en que el usuario no está en la sesión pide redireccionar a la página de login
             return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginPropietario.html"));
         }
         return Respuesta.lista(
             new Respuesta("nombreCompleto", usuario.getNombreCompleto()),
             new Respuesta("estadoPropietario", usuario.getEstadoPropietario().getNombre()),
             new Respuesta("saldoPropietario", String.valueOf(usuario.getSaldo()))
         );
        
    }
    
}
