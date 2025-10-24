package da.obligatorio.obligatorioDA.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import da.obligatorio.obligatorioDA.modelo.Usuario;
import da.obligatorio.obligatorioDA.servicios.Fachada;
import jakarta.servlet.http.HttpSession;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

@RestController
@RequestMapping("/acceso")
public class ControladorLogin {
    @PostMapping("/login")
       public List<Respuesta> login(HttpSession sesionHttp, @RequestParam String userName, @RequestParam String password) throws ObligatorioException {
           
           Usuario unUsuario = Fachada.getInstancia().login(userName, password);
           sesionHttp.setAttribute("usuarioLogueado", unUsuario);
           return Respuesta.lista(new Respuesta("loginExitoso","menu.html"));
           
       
       }
}
