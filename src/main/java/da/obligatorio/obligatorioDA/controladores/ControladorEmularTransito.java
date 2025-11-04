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

import da.obligatorio.obligatorioDA.dtos.emularTransitoDTO;
import da.obligatorio.obligatorioDA.dtos.puestoDTO;
import da.obligatorio.obligatorioDA.dtos.tarifaDTO;
import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;
import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Puesto;
import da.obligatorio.obligatorioDA.modelo.Tarifa;
import da.obligatorio.obligatorioDA.modelo.Vehiculo;
import da.obligatorio.obligatorioDA.servicios.Fachada;

@RestController
@RequestMapping("/emularTransito")
@Scope("session")
public class ControladorEmularTransito {

    

@GetMapping("/vistaConectada")
public List<Respuesta> inicializarVista(
        @SessionAttribute(name = "usuarioAdmin", required = false) Administrador usuario) {

    if (usuario == null) {
        return Respuesta.lista(
                new Respuesta("usuarioNoAutenticado", "loginAdmin.html")
        );
    }

 
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




    //  Cuando se aprieta "Emular tránsito"
    @PostMapping("/emular")
    public List<Respuesta> emular(  @RequestParam int idPuesto, @RequestParam String matricula,@RequestParam String fechaHora) {

        try {
            

            // Buscar el puesto
            Puesto puesto = Fachada.getInstancia().obtenerPuestoPorId(idPuesto);
            if (puesto == null) {
                throw new ObligatorioException("No existe el puesto");
            }

            // Buscar vehículo por matrícula
            Vehiculo vehiculo = Fachada.getInstancia().obtenerVehiculoPorMatricula(matricula); // delegando a sistemaPropietario
            if (vehiculo == null) {
                throw new ObligatorioException("No existe el vehículo");
            }

            // Buscar propietario del vehículo
            Propietario propietario = Fachada.getInstancia().obtenerPropietarioPorVehiculo(vehiculo);
           if (propietario != null&& propietario.getEstadoPropietario() != null && "Deshabilitado".equalsIgnoreCase(
                propietario.getEstadoPropietario().getNombre()
           )) {

    throw new ObligatorioException("El propietario del vehículo está deshabilitado, no puede realizar tránsitos");
}
    

            // Buscar tarifa según categoría del vehículo
            Tarifa tarifa = puesto.obtenerTarifaPara(vehiculo);
            if (tarifa == null) {
                throw new ObligatorioException("No hay tarifa para la categoría del vehículo en este puesto");
            }

            double costo = tarifa.getMonto();

           
            Bonificacion bonificacionAplicada = null;

            //  Verificar saldo
            if (propietario.getSaldo() < costo) {
                throw new ObligatorioException("Saldo insuficiente: " + propietario.getSaldo());
            }

            double nuevoSaldo = propietario.getSaldo() - costo;
            propietario.setSaldo(nuevoSaldo); 

            
            emularTransitoDTO dto = new emularTransitoDTO(propietario, vehiculo, bonificacionAplicada,costo,nuevoSaldo);

            return Respuesta.lista(new Respuesta("resultadoEmulacion", dto));

        } catch (ObligatorioException ex) {
            return Respuesta.lista(new Respuesta("errorEmulacion", ex.getMessage()));
        }
    }

   

  private Respuesta puestosRespuesta() {
    // Traigo los puestos desde la fachada
    List<Puesto> lista = Fachada.getInstancia().getPuestos();

    // Los paso a DTO
    List<puestoDTO> puestosDto = new ArrayList<>();
    for (Puesto p : lista) {
        puestosDto.add(new puestoDTO(p));
    }

    // Devuelvo la Respuesta lista para la vista
    return new Respuesta("puestos", puestosDto);
}






  

   
}
