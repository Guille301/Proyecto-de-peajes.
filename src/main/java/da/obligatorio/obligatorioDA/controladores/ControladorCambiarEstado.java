package da.obligatorio.obligatorioDA.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import da.obligatorio.obligatorioDA.dtos.bonificacionPropietarioDto;
import da.obligatorio.obligatorioDA.dtos.estadoPropietarioDTO;
import da.obligatorio.obligatorioDA.dtos.propietarioDto;
import da.obligatorio.obligatorioDA.dtos.puestoDTO;
import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.EstadoPropietario;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Puesto;
import da.obligatorio.obligatorioDA.servicios.Fachada;

@RestController
@RequestMapping("/cambiarEstado")
@Scope("session")
public class ControladorCambiarEstado {
    
  @GetMapping("/vistaConectada")
  public List<Respuesta> inicializarVista( @SessionAttribute(name = "usuarioAdmin", required = false) Administrador usuario) {
      if (usuario == null) {
          return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginAdmin.html"));
      }
    return Respuesta.lista(
      new Respuesta("ok", true),
      mostrarEstados()
      );
  }

  @PostMapping("/buscarPropietario")
  public List<Respuesta> buscarPropietario(@RequestParam String cedula) throws ObligatorioException {
    
    try{
      if(cedula.isBlank()){
        throw new ObligatorioException("Valor ingreado inválido");
      } 
      Propietario propietario = Fachada.getInstancia().buscarPropietarioPorCedula(cedula);

      if(propietario == null){
        throw new ObligatorioException("No se encuentra un propietario con esa cédula");
      } 
     
      List<Respuesta> respuestas = Respuesta.lista(resultado(propietario));
      return respuestas;


    } catch(ObligatorioException e){
        return Respuesta.lista(new Respuesta("errorBuscador", e.getMessage()));
    }
  }

  private Respuesta resultado(Propietario propietario){
    return new Respuesta("propietarioEncontrado", new propietarioDto(propietario));
  }

  private Respuesta mostrarEstados(){
    List<EstadoPropietario> listaEstado = Fachada.getInstancia().getListEstadosPropietario();
    List<estadoPropietarioDTO> estadosDto = new ArrayList<>();
    for(EstadoPropietario ep : listaEstado){
      estadosDto.add(new estadoPropietarioDTO(ep));
    }
    return new Respuesta("mostrarEstados", estadosDto);
  }



}
