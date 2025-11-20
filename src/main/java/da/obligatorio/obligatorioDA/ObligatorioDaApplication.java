package da.obligatorio.obligatorioDA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Date;

import da.obligatorio.obligatorioDA.servicios.Fachada;
import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.CategoriaVehiculo;
import da.obligatorio.obligatorioDA.modelo.CriterioAsignacionBonificacion;
import da.obligatorio.obligatorioDA.modelo.EstadoDeshabilitado;
import da.obligatorio.obligatorioDA.modelo.EstadoHabilitado;
import da.obligatorio.obligatorioDA.modelo.EstadoPenalizado;
import da.obligatorio.obligatorioDA.modelo.Notificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Puesto;
import da.obligatorio.obligatorioDA.modelo.Tarifa;
import da.obligatorio.obligatorioDA.modelo.Trabajadores;
import da.obligatorio.obligatorioDA.modelo.Transito;
import da.obligatorio.obligatorioDA.modelo.Vehiculo;
import da.obligatorio.obligatorioDA.modelo.EstadoPropietario;
import da.obligatorio.obligatorioDA.modelo.Frecuentes;
import da.obligatorio.obligatorioDA.modelo.EstadoSuspendido;
import da.obligatorio.obligatorioDA.modelo.Exonerados;

@SpringBootApplication
public class ObligatorioDaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObligatorioDaApplication.class, args);
		cargarDatosIniciales();
	}

	private static void cargarDatosIniciales() {
    Fachada f = Fachada.getInstancia();

    // Categorias de vehiculo
    CategoriaVehiculo catAuto = new CategoriaVehiculo(1, "Auto");
    CategoriaVehiculo catMoto = new CategoriaVehiculo(2, "Moto");

    // Estados de propietario
    EstadoPropietario habilitado = new EstadoHabilitado(null);
    EstadoPropietario deshabilitado = new EstadoDeshabilitado(null);
    EstadoPropietario penalizado = new EstadoPenalizado(null);
    EstadoPropietario suspendido = new EstadoSuspendido(null);

    f.agregarEstadosPropietario(habilitado);
    f.agregarEstadosPropietario(deshabilitado);
    f.agregarEstadosPropietario(penalizado);
    f.agregarEstadosPropietario(suspendido);

    // ========= PROPIETARIOS =========
    // Usamos el constructor grande con listas inicializadas

        // -- Propietarios (5) -- (creados de forma simple, parecido a Notificacion)
        Propietario propietario1 = new Propietario();
        propietario1.setId(1);
        propietario1.setNombreCompleto("Juan Perez");
        propietario1.setContrasenia("123456");
        propietario1.setCedula("2001");
        propietario1.setSaldo(1500.0);
        propietario1.setEstadoPropietario(habilitado);
        propietario1.agregarNotificaciones((new Notificacion(1, new Date(), "Bienvenido Juan")));


         Propietario propietario2 = new Propietario();
        propietario2.setId(2);
        propietario2.setContrasenia("123");
        propietario2.setNombreCompleto("Usuario Propietario");
        propietario2.setCedula("23456789");
        propietario2.setSaldo(2000.0);
        propietario2.setEstadoPropietario(habilitado);
        propietario2.agregarNotificaciones((new Notificacion(3, new Date(), "Bienvenido Usuario Propietario")));
    

        Propietario propietario3 = new Propietario();
        propietario3.setId(3);
        propietario3.setContrasenia("123456");
        propietario3.setNombreCompleto("Carlos Ruiz");
        propietario3.setCedula("2003");
        propietario3.setSaldo(5000.0);
        propietario3.setEstadoPropietario(habilitado);
        propietario3.agregarNotificaciones((new Notificacion(3, new Date(), "Bienvenido Carlos")));

       



        // agregar propietarios a fachada
        f.agregarPropietario(propietario1);
        f.agregarPropietario(propietario3);
        f.agregarPropietario(propietario2);


        f.agregar(propietario1);
        f.agregar(propietario3);
        f.agregar(propietario2);
   


    // ========= ADMINISTRADOR =========
    Administrador admin = new Administrador(1, "Juan", "a", "a");
    Administrador admi2 = new Administrador(2, "Usuario Administrador", "1234567", "123");
    f.agregar(admin);
    f.agregar(admi2);



    // ========= VEHÍCULOS =========
    // Ojo: pasamos new ArrayList<>() para listaTransito

        // -- Vehiculos (5) --
        Vehiculo vehiculo1 = new Vehiculo(1, "ABC-101", "Fiesta", "Rojo", catAuto, propietario1, null);
        Vehiculo vehiculo6 = new Vehiculo(6, "SDG-505", "Seagull", "Blanco", catAuto, propietario2, null);
        Vehiculo vehiculo3 = new Vehiculo(3, "CDE-303", "Tornado", "Blanco", catMoto, propietario3, null);
        Vehiculo vehiculoMoto = new Vehiculo(3, "AAA-000", "Ninja", "Negra", catMoto, propietario2, null);


        // asignar vehiculos a sus propietarios (listas simples)
        propietario1.agregarVehiculoPropietario(vehiculo1);
        propietario2.agregarVehiculoPropietario(vehiculo6);
        propietario3.agregarVehiculoPropietario(vehiculo3);
        propietario2.agregarVehiculoPropietario(vehiculoMoto);


        // -- Puestos (3) --
        Puesto puesto1 = new Puesto(1, "Puesto Centro", "Av. Principal 100", null, null, null);
        Puesto puesto2 = new Puesto(2, "Puesto Norte", "Calle Norte 45", null, null, null);
        Puesto puesto3 = new Puesto(3, "Puesto Sur", "Ruta 10 Km 5", null, null, null);


        

        f.agregarPuesto(puesto1);
        f.agregarPuesto(puesto2);
        f.agregarPuesto(puesto3);

        // -- Tarifas --
        Tarifa t1 = new Tarifa(1, 50.0, catAuto);
        Tarifa t2 = new Tarifa(2, 30.0, catMoto);
        Tarifa t3 = new Tarifa(3, 40.0, catAuto);

        puesto1.agrgarTarifaPuesto(t1);
        puesto2.agrgarTarifaPuesto(t2);
        puesto3.agrgarTarifaPuesto(t3);

        Tarifa t4 = new Tarifa(4, 55.0, catAuto);
        puesto2.agrgarTarifaPuesto(t4);


        

// ========= BONIFICACIONES =========

// Estrategias de bonificación (pueden reutilizarse, no guardan estado)
CriterioAsignacionBonificacion critFrecuentes = new Frecuentes();
CriterioAsignacionBonificacion critTrabajadores = new Trabajadores();
CriterioAsignacionBonificacion critExonerados   = new Exonerados();


Bonificacion b2 = new Bonificacion(
        3,
        null,
        "Trabajadores",
        puesto1,
        LocalDate.of(2024, 1, 15),
        10.0,
        critTrabajadores
);


// -- Bonificaciones y cobros simples --
Bonificacion b1 = new Bonificacion(
        1,
        propietario1,
        "Frecuentes",
        puesto1,
        LocalDate.of(2024, 1, 15),
        10.0,
        critFrecuentes
);



Bonificacion b3 = new Bonificacion(
        3,
        propietario3,
        "Exonerados",
        puesto3,
        LocalDate.of(2024, 1, 10),
        20.0,
        critExonerados
);




// Registrar bonificaciones en la fachada (no te olvides de b4 y b5)
f.agregarBonificacion(b1);
f.agregarBonificacion(b3);
f.agregarBonificacion(b2);


// Asociar bonificaciones a propietarios
propietario1.agregarBonificacionPropietario(b1);
propietario3.agregarBonificacionPropietario(b3);


// Asociar bonificaciones a puestos
puesto1.agregarBonificacionPuesto(b2);
puesto2.agregarBonificacionPuesto(b3);



// ========= TRÁNSITOS =========

Transito tr1 = new Transito(1, puesto1, vehiculo1, new Date());
Transito tr3 = new Transito(3, puesto2, vehiculo3, new Date());


// asignar tránsitos a vehículos
vehiculo1.agregarTransito(tr1);
vehiculo3.agregarTransito(tr3);



// asignar tránsitos a puestos (UNA sola vez en el puesto correcto)
puesto1.agregarTransitoPuesto(tr1);

puesto2.agregarTransitoPuesto(tr3);






        }

}
