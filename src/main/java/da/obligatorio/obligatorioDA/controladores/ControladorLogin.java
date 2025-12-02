package da.obligatorio.obligatorioDA.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Sesion;
import da.obligatorio.obligatorioDA.modelo.Usuario;
import da.obligatorio.obligatorioDA.servicios.Fachada;
import jakarta.servlet.http.HttpSession;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

@RestController
@RequestMapping("/acceso")
public class ControladorLogin {

    //Administrador
    @PostMapping("/loginAdmin")
    public List<Respuesta> loginAdmin(HttpSession sesionHttp,@RequestParam String Cedula,@RequestParam String contrasenia) throws ObligatorioException {
        Sesion sesion = Fachada.getInstancia().loginAdmin(Cedula, contrasenia);
        sesionHttp.setAttribute("sesionAdmin", sesion);
        sesionHttp.setAttribute("usuarioAdmin", (Administrador) sesion.getUsuario());

        return Respuesta.lista(new Respuesta("loginExitoso", "menuAdmin.html"));
    }


    @PostMapping("/logoutAdmin")
    public List<Respuesta> logoutAdmin(HttpSession sesionHttp) {
        Sesion sesion = (Sesion) sesionHttp.getAttribute("sesionAdmin");
        if (sesion != null) {
            Fachada.getInstancia().logout(sesion);
            sesionHttp.removeAttribute("sesionAdmin");
            sesionHttp.removeAttribute("usuarioAdmin");
        }
        return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
    }

 
    // Propietario
    @PostMapping("/loginPropietario")
    public List<Respuesta> loginPropietario(HttpSession sesionHttp,@RequestParam String Cedula,@RequestParam String contrasenia) throws ObligatorioException {
        Sesion sesion = Fachada.getInstancia().loginUsuarioPropietario(Cedula, contrasenia);
        sesionHttp.setAttribute("sesionPropietario", sesion);
        sesionHttp.setAttribute("usuarioPropietario", (Propietario) sesion.getUsuario());

        return Respuesta.lista(new Respuesta("loginExitoso", "menuPropietario.html"));
    }


    @PostMapping("/logoutPropietario")
    public List<Respuesta> logoutPropietario(HttpSession sesionHttp) {
        Sesion sesion = (Sesion) sesionHttp.getAttribute("sesionPropietario");
        if (sesion != null) {
            Fachada.getInstancia().logout(sesion);   
            sesionHttp.removeAttribute("sesionPropietario");
            sesionHttp.removeAttribute("usuarioPropietario");
        }
        return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
    }


}
