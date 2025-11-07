package da.obligatorio.obligatorioDA.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import da.obligatorio.obligatorioDA.dtos.emularTransitoDTO;
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
public class ControladorEmularTransito implements  Observador{

    private ConexionNavegador conexionNavegador;

    private Transito transito;


    public ControladorEmularTransito(@Autowired ConexionNavegador unaConexion) {
        this.conexionNavegador = unaConexion;
    }
    

@GetMapping("/vistaConectada")
public List<Respuesta> inicializarVista(
        @SessionAttribute(name = "usuarioAdmin", required = false) Administrador usuario) {

    if (usuario == null) {
        return Respuesta.lista(
                new Respuesta("usuarioNoAutenticado", "loginAdmin.html")
        );
    }

     Fachada.getInstancia().agregarObservador(this);
 
    return Respuesta.lista(
            puestosRespuesta()
    );
   
}




    //  Cuando cambia el puesto, la vista manda el idPuesto

@PostMapping("/cambiarPuesto")
public List<Respuesta> cambiarPuesto(@RequestParam int idPuesto) {

    Puesto puesto = Fachada.getInstancia().obtenerPuestoPorId(idPuesto);

    if (puesto == null) {
        return Respuesta.lista(
            new Respuesta("errorEmulacion", "Puesto inexistente")
        );
    }

    List<Tarifa> tarifas = puesto.getListTarifas();

    // Pasar a DTO
    List<tarifaDTO> tarifasDto = new ArrayList<>();
    if (tarifas != null) {
        for (Tarifa t : tarifas) {
            tarifasDto.add(new tarifaDTO(t));
        }
    }

    return Respuesta.lista(
        new Respuesta("tarifasPuesto", tarifasDto)
    );
}




  @PostMapping("/emular")
public List<Respuesta> emular(@RequestParam int idPuesto,  @RequestParam String matricula,  @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date fechaHora) {

    try {
        
        Puesto puesto = Fachada.getInstancia().obtenerPuestoPorId(idPuesto);
        Vehiculo vehiculo = Fachada.getInstancia().obtenerVehiculoPorMatriculaObligatorio(matricula);
        Propietario propietario = Fachada.getInstancia().obtenerPropietarioPorVehiculoObligatorio(vehiculo);

        
        Fachada.getInstancia().validarEstadoParaTransito(propietario);

       
        transito = new Transito(0, puesto, vehiculo, fechaHora);

    
        double costo = transito.costoTransitoEmulacion();

        
        propietario.debitarPorTransito(costo);
        double nuevoSaldo = propietario.getSaldo();

        
        vehiculo.agregarTransito(transito);
        puesto.agregarTransito(transito);


        Fachada.getInstancia().registrarNotificacionesTransito(propietario, puesto, vehiculo);


        
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


  @Override
  public void actualizar(Object evento, Observable origen) {
    if(evento.equals(Fachada.eventos.NOTIFICACION_TRANSITO)) {
       
        Propietario propietario = Fachada.getInstancia().obtenerPropietarioPorVehiculo(transito.getVehiculo());
       //   Respuesta r = notificacionesPropietario(propietario);
       conexionNavegador.enviarJSON(Respuesta.lista(null) );
    }
    
  }






  

   
}
