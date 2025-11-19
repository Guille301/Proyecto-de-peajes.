package da.obligatorio.obligatorioDA.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import da.obligatorio.obligatorioDA.dtos.emularTransitoDTO;
import da.obligatorio.obligatorioDA.dtos.notificacionDTO;
import da.obligatorio.obligatorioDA.dtos.puestoDTO;
import da.obligatorio.obligatorioDA.dtos.tarifaDTO;
import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Puesto;
import da.obligatorio.obligatorioDA.modelo.Tarifa;
import da.obligatorio.obligatorioDA.modelo.Transito;
import da.obligatorio.obligatorioDA.modelo.Vehiculo;
import da.obligatorio.obligatorioDA.observador.Observable;
import da.obligatorio.obligatorioDA.observador.Observador;
import da.obligatorio.obligatorioDA.servicios.Fachada;
import da.obligatorio.obligatorioDA.utils.ConexionNavegador;


@RestController
@RequestMapping("/emularTransito")
@Scope("session")
public class ControladorEmularTransito  implements Observador {

    private Transito transito;

       private final ConexionNavegador conexionNavegador;
   private Propietario usuarioSesion;

     @Autowired
    public ControladorEmularTransito(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;
    }

    @GetMapping("/vistaConectada")
    public List<Respuesta> inicializarVista(@SessionAttribute(name = "usuarioAdmin", required = false) Administrador usuario) {
        if (usuario == null) {
             return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginAdmin.html"));
        }
        
         Fachada.getInstancia().agregarObservador(this);
        return Respuesta.lista(puestosRespuesta());
    }

    //  Cuando cambia el puesto, la vista manda el idPuesto

    @PostMapping("/cambiarPuesto")
    public List<Respuesta> cambiarPuesto(@RequestParam int idPuesto) {
        Puesto puesto = Fachada.getInstancia().obtenerPuestoPorId(idPuesto);
        if (puesto == null) {
            return Respuesta.lista(new Respuesta("errorEmulacion", "Puesto inexistente"));
        }
        List<Tarifa> tarifas = puesto.getListTarifas();

        // Pasar a DTO
        List<tarifaDTO> tarifasDto = new ArrayList<>();
        if (tarifas != null) {
            for (Tarifa t : tarifas) {
                tarifasDto.add(new tarifaDTO(t));
            }
        }

        return Respuesta.lista(new Respuesta("tarifasPuesto", tarifasDto));
    }




    @PostMapping("/emular")
    public List<Respuesta> emular(@RequestParam int idPuesto,  @RequestParam String matricula,  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date fechaHora) {

        try {
            transito = Fachada.getInstancia().emularTransito(idPuesto, matricula, fechaHora);
            Vehiculo vehiculo = transito.getVehiculo();
            Propietario propietario = vehiculo.getPropietario();
            this.usuarioSesion = propietario;
            Puesto puesto = transito.getPuesto();

            double costo = transito.costoTransitoEmulacion();
            double nuevoSaldo = propietario.getSaldo();
            Bonificacion bonificacionAplicada = puesto.obtenerBonificacionPara(propietario);
            

            emularTransitoDTO dto = new emularTransitoDTO(
                    propietario,
                    vehiculo,
                    bonificacionAplicada,
                    costo,
                    nuevoSaldo
            );

            return Respuesta.lista(new Respuesta("resultadoEmulacion", dto));

        } catch (ObligatorioException ex) {
            return Respuesta.lista(new Respuesta("errorEmulacion", ex.getMessage()));
        }
    }

    private Respuesta puestosRespuesta() {
        List<Puesto> lista = Fachada.getInstancia().getPuestos();
        List<puestoDTO> puestosDto = new ArrayList<>();
        for (Puesto p : lista) {
            puestosDto.add(new puestoDTO(p));
        }
        return new Respuesta("puestos", puestosDto);
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
        if (evento.equals(Fachada.eventos.NOTIFICACION_SALDO_INSUFICIENTE)) {
            Respuesta saldo  = new Respuesta("saldoActual",usuarioSesion.getSaldo());
            Respuesta Notifs = notificacionesPropietario(usuarioSesion);
            conexionNavegador.enviarJSON( Respuesta.lista(saldo, Notifs));
            
        }
    }
   
}
