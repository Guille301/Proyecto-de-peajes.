package da.obligatorio.obligatorioDA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Date;
import da.obligatorio.obligatorioDA.servicios.Fachada;
import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.CategoriaVehiculo;
import da.obligatorio.obligatorioDA.modelo.EstadoDeshabilitado;
import da.obligatorio.obligatorioDA.modelo.EstadoHabilitado;
import da.obligatorio.obligatorioDA.modelo.EstadoPenalizado;
import da.obligatorio.obligatorioDA.modelo.Notificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Puesto;
import da.obligatorio.obligatorioDA.modelo.Tarifa;
import da.obligatorio.obligatorioDA.modelo.Transito;
import da.obligatorio.obligatorioDA.modelo.Vehiculo;
import da.obligatorio.obligatorioDA.modelo.EstadoPropietario;
import da.obligatorio.obligatorioDA.modelo.EstadoSuspendido;

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
        propietario1.setSaldo(150.0);
        propietario1.setEstadoPropietario(habilitado);
        propietario1.setNotificaciones((new Notificacion(1, new Date(), "Bienvenido Juan")));

        Propietario propietario2 = new Propietario();
        propietario2.setId(2);
        propietario2.setContrasenia("123456");
        propietario2.setNombreCompleto("María Gomez");
        propietario2.setCedula("2002");
        propietario2.setSaldo(200.0);
        propietario2.setEstadoPropietario(deshabilitado);
        propietario2.setNotificaciones((new Notificacion(2, new Date(), "Bienvenida María")));

        Propietario propietario3 = new Propietario();
        propietario3.setId(3);
        propietario3.setContrasenia("123456");
        propietario3.setNombreCompleto("Carlos Ruiz");
        propietario3.setCedula("2003");
        propietario3.setSaldo(50.0);
        propietario3.setEstadoPropietario(habilitado);
        propietario3.setNotificaciones((new Notificacion(3, new Date(), "Saldo bajo Carlos")));

        Propietario propietario4 = new Propietario();
        propietario4.setId(4); 
        propietario4.setContrasenia("123456");
        propietario4.setNombreCompleto("Lucía Fernández");
        propietario4.setCedula("2004");
        propietario4.setSaldo(300.0);
        propietario4.setEstadoPropietario(habilitado);
        propietario4.setNotificaciones((new Notificacion(4, new Date(), "Promoción disponible")));

        Propietario propietario5 = new Propietario();
        propietario5.setId(5);
        propietario5.setContrasenia("123456");
        propietario5.setNombreCompleto("Diego López");
        propietario5.setCedula("2005");
        propietario5.setSaldo(75.0);
        propietario5.setEstadoPropietario(habilitado);
        propietario5.setNotificaciones((new Notificacion(5, new Date(), "Recarga recomendada")));

        // agregar propietarios a fachada
        f.agregarPropietario(propietario1);
        f.agregarPropietario(propietario2);
        f.agregarPropietario(propietario3);
        f.agregarPropietario(propietario4);
        f.agregarPropietario(propietario5);

        f.agregar(propietario1);
        f.agregar(propietario2);
        f.agregar(propietario3);
        f.agregar(propietario4);
        f.agregar(propietario5);


    // ========= ADMINISTRADOR =========
    Administrador admin = new Administrador(1, "Juan", "a", "a");
    f.agregar(admin);

    // ========= VEHÍCULOS =========
    // Ojo: pasamos new ArrayList<>() para listaTransito

        // -- Vehiculos (5) --
        Vehiculo vehiculo1 = new Vehiculo(1, "ABC-101", "Fiesta", "Rojo", catAuto, propietario1, null);
        Vehiculo vehiculo2 = new Vehiculo(2, "BCD-202", "Civic", "Azul", catAuto, propietario2, null);
        Vehiculo vehiculo3 = new Vehiculo(3, "CDE-303", "Tornado", "Blanco", catMoto, propietario3, null);
        Vehiculo vehiculo4 = new Vehiculo(4, "DEF-404", "Corolla", "Gris", catAuto, propietario4, null);
        Vehiculo vehiculo5 = new Vehiculo(5, "EFG-505", "Pulsar", "Negro", catMoto, propietario5, null);
        Vehiculo vehiculo6 = new Vehiculo(6, "SDG-505", "Seagull", "Blanco", catAuto, propietario1, null);

        // asignar vehiculos a sus propietarios (listas simples)
        propietario1.agregarVehiculoPropietario(vehiculo1);
        propietario1.agregarVehiculoPropietario(vehiculo6);
        propietario2.agregarVehiculoPropietario(vehiculo2);
        propietario3.agregarVehiculoPropietario(vehiculo3);
        propietario4.agregarVehiculoPropietario(vehiculo4);
        propietario5.agregarVehiculoPropietario(vehiculo5);

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


        // -- Bonificaciones y Cobros simples --
        Bonificacion b1 = new Bonificacion(1, propietario1, "Fidelidad", puesto1, LocalDate.of(2024, 1, 15), 10.0);
        Bonificacion b2 = new Bonificacion(2, propietario2, "Promoción", puesto2,LocalDate.of(2024, 1, 10),20.0);
        Bonificacion b3 = new Bonificacion(3, propietario3, "Premium", puesto3,LocalDate.of(2024, 1, 10),20.0);
        Bonificacion b4 = new Bonificacion(4, propietario4, "Promo", puesto3, LocalDate.now(), 5.0);
        Bonificacion b5 = new Bonificacion(5, propietario5, "Platinum", puesto2, LocalDate.now(), 5.0);


        f.agregarBonificacion(b1);
        f.agregarBonificacion(b2);
        f.agregarBonificacion(b3);

        propietario1.agregarBonificacionPropietario(b1);
        propietario2.agregarBonificacionPropietario(b2);
        propietario3.agregarBonificacionPropietario(b3);
        propietario4.agregarBonificacionPropietario(b4);
        propietario5.agregarBonificacionPropietario(b5);

        puesto1.agregarBonificacionPuesto(b1);
        puesto2.agregarBonificacionPuesto(b2);
        puesto2.agregarBonificacionPuesto(b5);
        puesto2.agregarBonificacionPuesto(b3);
        puesto3.agregarBonificacionPuesto(b1);
        puesto3.agregarBonificacionPuesto(b3);
        puesto3.agregarBonificacionPuesto(b4);
      

        

        // -- Transitos (5) --
        Transito tr1 = new Transito(1, puesto1, vehiculo1, new Date());
        Transito tr2 = new Transito(2, puesto2, vehiculo2, new Date());
        Transito tr3 = new Transito(3, puesto2, vehiculo3, new Date());
        Transito tr4 = new Transito(4, puesto3, vehiculo4, new Date());
        Transito tr5 = new Transito(5, puesto2, vehiculo5, new Date());


        // asignar transitos a vehiculos y puestos
        vehiculo1.agregarTransito(tr1);
        vehiculo2.agregarTransito(tr2);
        vehiculo3.agregarTransito(tr3);
        vehiculo4.agregarTransito(tr4);
        vehiculo5.agregarTransito(tr5);

        puesto1.agregarTransitoPuesto(tr1);
        puesto1.agregarTransitoPuesto(tr2);
        puesto2.agregarTransitoPuesto(tr3);
        puesto2.agregarTransitoPuesto(tr5);
        puesto3.agregarTransitoPuesto(tr4);
        puesto2.agregarTransitoPuesto(tr2);


        }

}
