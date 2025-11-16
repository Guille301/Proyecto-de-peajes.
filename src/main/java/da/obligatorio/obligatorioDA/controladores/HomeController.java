package da.obligatorio.obligatorioDA.controladores;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

     @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }
}
