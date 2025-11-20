package da.obligatorio.obligatorioDA.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.Transito;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Vehiculo;
import da.obligatorio.obligatorioDA.observador.Observador;
import da.obligatorio.obligatorioDA.servicios.Fachada;
import da.obligatorio.obligatorioDA.utils.ConexionNavegador;
import da.obligatorio.obligatorioDA.dtos.bonificacionPropietarioDto;
import da.obligatorio.obligatorioDA.dtos.notificacionDTO;
import da.obligatorio.obligatorioDA.dtos.transitosRealizadosDto;
import da.obligatorio.obligatorioDA.dtos.vehiculosDto;
import da.obligatorio.obligatorioDA.observador.Observable;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("/tablero")
@Scope("session")
public class ControladorTableroPropietario  implements Observador{
private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ControladorTableroPropietario.class);

   private final ConexionNavegador conexionNavegador;
   private Propietario usuarioSesion;

     @Autowired
    public ControladorTableroPropietario(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;
    }

    @GetMapping("/resumenDelPropietario")
    public List<Respuesta> resumenDelPropietario(@SessionAttribute(name = "usuarioPropietario", required=false) Propietario usuario){
        if (usuario == null) {
             // Manejar el caso en que el usuario no está en la sesión pide redireccionar a la página de login
             return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginPropietario.html"));
         }
         this.usuarioSesion = usuario;
         Fachada.getInstancia().agregarObservador(this);

        
         return Respuesta.lista(
             new Respuesta("nombreCompleto", usuario.getNombreCompleto()),
             new Respuesta("estadoPropietario", usuario.getEstadoPropietario().getNombre()),
             new Respuesta("saldoPropietario", String.valueOf(usuario.getSaldo())),
             vehiculosConTransito(usuario),
             bonficacionesPropietario(usuario),  
             transitosRealizados(usuario),
             notificacionesPropietario(usuario)    
         );
        
    }

    @PostMapping("/borrarNotificaciones")
    public List<Respuesta> borrarNotificaciones(
        @SessionAttribute(name = "usuarioPropietario", required = false) Propietario usuario) {

        if (usuario == null) {
            return Respuesta.lista(  new Respuesta("usuarioNoAutenticado", "loginPropietario.html"));
        }
        Fachada.getInstancia().borrarNotificacionesPropietario(usuario);
        Respuesta notifs = notificacionesPropietario(usuario);
        return Respuesta.lista(notifs);
    }

    private Respuesta transitosRealizados(Propietario usuario){
        List<Transito> transitos = usuario.traerTransitosDeMisVehiculos();
        List<transitosRealizadosDto> transDtos = new ArrayList<>();
        for(Transito tc : transitos){
            transDtos.add(new transitosRealizadosDto(tc));
        }
        return new Respuesta("transitosRealizados", transDtos);
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
    // recargo el propietario desde el sistema usando la cédula
    Propietario actualizado = Fachada.getInstancia()
                                     .obtenerPropietarioPorCedula(usuario.getCedula());

    List<Bonificacion> bonificaciones = actualizado.getListBonificaciones();
    List<bonificacionPropietarioDto> bonDtos = new ArrayList<>();

    if (bonificaciones != null) {
        for (Bonificacion b : bonificaciones){
            bonDtos.add(new bonificacionPropietarioDto(b));
        }
    }

    return new Respuesta("bonificacionesPropietario", bonDtos);
}


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

    @GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE() {
        conexionNavegador.conectarSSE();
        return conexionNavegador.getConexionSSE();
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
        if (evento.equals(Fachada.eventos.NOTIFICACION_TRANSITO)) {
            Respuesta Saldo  = new Respuesta("saldoPropietario",usuarioSesion.getSaldo());
            Respuesta Trans  = transitosRealizados(usuarioSesion);
            Respuesta Notifs = notificacionesPropietario(usuarioSesion);
            
            conexionNavegador.enviarJSON( Respuesta.lista(Saldo, Trans, Notifs));
            
        }

        if(evento.equals(Fachada.eventos.NOTIFICACION_BONIFICACION_ASIGNADA)){
        
            Respuesta Bonif =  bonficacionesPropietario(usuarioSesion);
            conexionNavegador.enviarJSON( Respuesta.lista(Bonif));

        }

        if (evento.equals(Fachada.eventos.NOTIFICACION_CAMBIO_ESTADO)) {
        Respuesta estado = new Respuesta("estadoPropietario", usuarioSesion.getEstadoPropietario().getNombre());
        Respuesta Notifs = notificacionesPropietario(usuarioSesion);
        conexionNavegador.enviarJSON(Respuesta.lista(estado, Notifs));
    }
    }


 


}
