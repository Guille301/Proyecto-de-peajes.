package da.obligatorio.obligatorioDA.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.Usuario;
import da.obligatorio.obligatorioDA.servicios.Fachada;
import jakarta.servlet.http.HttpSession;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;

@RestController
@RequestMapping("/acceso")
public class ControladorLogin {

 //Administrador
    @PostMapping("/loginAdmin")
    public List<Respuesta> loginAdmin(HttpSession sesionHttp, @RequestParam String nombre, @RequestParam String contrasenia) throws ObligatorioException {
        Administrador usuarioAdmin  = Fachada.getInstancia().loginAdmin(nombre, contrasenia);
        sesionHttp.setAttribute("usuarioAdmin",usuarioAdmin);
        return Respuesta.lista(new Respuesta("loginExitoso", "menuAdmin.html"));
    }

 
// Propietario
    @PostMapping("/loginPropietario")
       public List<Respuesta> loginPropietario(HttpSession sesionHttp, @RequestParam String userName, @RequestParam String password) throws ObligatorioException {
           
           Usuario unUsuario = Fachada.getInstancia().loginUsuarioPropietario(userName, password);
           sesionHttp.setAttribute("usuarioLogueado", unUsuario);
           return Respuesta.lista(new Respuesta("loginExitoso","menuPropietario.html"));
           
       
    }




}
