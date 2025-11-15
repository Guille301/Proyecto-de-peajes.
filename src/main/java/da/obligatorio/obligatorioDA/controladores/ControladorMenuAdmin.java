package da.obligatorio.obligatorioDA.controladores;

import da.obligatorio.obligatorioDA.modelo.Administrador;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RestController
@RequestMapping("/menuAdmin")
public class ControladorMenuAdmin {

    @GetMapping("/vistaConectada")
    public List<Respuesta> inicializarVista(@SessionAttribute(name = "usuarioAdmin", required = false) Administrador usuario) {
        // Si no hay admin logueado -> volver al login
        if (usuario == null) {
            return Respuesta.lista(
                    new Respuesta("usuarioNoAutenticado", "loginAdmin.html")
            );
        }
        // Mandamos el nombre del admin a la vista
        return Respuesta.lista(
                new Respuesta("nombreCompleto", usuario.getNombreCompleto())
        );
    }
}
