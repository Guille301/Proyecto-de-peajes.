package da.obligatorio.obligatorioDA.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import da.obligatorio.obligatorioDA.dtos.estadoPropietarioDTO;
import da.obligatorio.obligatorioDA.dtos.notificacionDTO;
import da.obligatorio.obligatorioDA.dtos.propietarioDto;
import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.EstadoPropietario;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.observador.Observable;
import da.obligatorio.obligatorioDA.observador.Observador;
import da.obligatorio.obligatorioDA.servicios.Fachada;
import da.obligatorio.obligatorioDA.utils.ConexionNavegador;

@RestController
@RequestMapping("/cambiarEstado")
@Scope("session")
public class ControladorCambiarEstado implements Observador {
   
  private final ConexionNavegador conexionNavegador;
  private Propietario usuarioSesion;
  private List<EstadoPropietario> estadosCache = new ArrayList<>();

  
  @GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public SseEmitter registrarSSE() {
    conexionNavegador.conectarSSE();
    return conexionNavegador.getConexionSSE();
  }

  @Autowired
  public ControladorCambiarEstado(@Autowired ConexionNavegador conexionNavegador) {
    this.conexionNavegador = conexionNavegador;
  }
    
  @GetMapping("/vistaConectada")
  public List<Respuesta> inicializarVista( @SessionAttribute(name = "usuarioAdmin", required = false) Administrador usuario) {
      if (usuario == null) {
          return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginAdmin.html"));
      }
      Fachada.getInstancia().agregarObservador(this);
      this.estadosCache = new ArrayList<>(Fachada.getInstancia().getListEstadosPropietario());
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
     this.usuarioSesion = propietario;
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
    // usar el mismo orden que estadosCache
    List<estadoPropietarioDTO> estadosDto = new ArrayList<>();
    for (EstadoPropietario ep : estadosCache){
      estadosDto.add(new estadoPropietarioDTO(ep));
    } 
    return new Respuesta("mostrarEstados", estadosDto);
  }

  @PostMapping("/cambiarEstadoPropietario")
  public List<Respuesta> cambiarEstadoPropietario(@RequestParam int idPropietario,@RequestParam int posMostrarEstados) throws ObligatorioException {
    try{
      Propietario propietario = Fachada.getInstancia().getPropietarioPorId(idPropietario);
      EstadoPropietario nuevoEstado = estadosCache.get(posMostrarEstados);
      propietario.cambiarEstado(nuevoEstado);
      Fachada.getInstancia().registrarNotificacionesEstado(propietario, nuevoEstado);
      
      return Respuesta.lista(new Respuesta("limpiarCampos",true));
      
    } catch(ObligatorioException e){
        return Respuesta.lista(new Respuesta("errorEstadoPropietario", e.getMessage()));
      }
  }

  //Notificacion
  private Respuesta notificacionesPropietario(Propietario usuario) {
      var lista = usuario.getListaNotificaciones();
      List<notificacionDTO> notifsDto = new ArrayList<>();
      if (lista != null) {
          for (var n : lista) {
              notifsDto.add(new notificacionDTO(n));
          }
      }
      return new Respuesta("notificacionesPropietario", notifsDto);
  }

  @Override
  public void actualizar(Object evento, Observable origen) {
    if (evento.equals(Fachada.eventos.NOTIFICACION_CAMBIO_ESTADO)) {
            Respuesta estadoActual  = new Respuesta("estadoActual",usuarioSesion.getEstadoPropietario().getNombre());
            Respuesta Notifs = notificacionesPropietario(usuarioSesion);
            conexionNavegador.enviarJSON( Respuesta.lista(estadoActual, Notifs));
    }
  }
  



}
