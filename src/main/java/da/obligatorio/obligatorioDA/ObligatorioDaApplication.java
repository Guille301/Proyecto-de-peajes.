package da.obligatorio.obligatorioDA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;
import java.util.Date;
import da.obligatorio.obligatorioDA.servicios.Fachada;
import da.obligatorio.obligatorioDA.modelo.Administrador;
import da.obligatorio.obligatorioDA.modelo.Bonificacion;
import da.obligatorio.obligatorioDA.modelo.CategoriaVehiculo;
import da.obligatorio.obligatorioDA.modelo.Cobro;
import da.obligatorio.obligatorioDA.modelo.Notificacion;
import da.obligatorio.obligatorioDA.modelo.Propietario;
import da.obligatorio.obligatorioDA.modelo.Puesto;
import da.obligatorio.obligatorioDA.modelo.Tarifa;
import da.obligatorio.obligatorioDA.modelo.Transito;
import da.obligatorio.obligatorioDA.modelo.Usuario;
import da.obligatorio.obligatorioDA.modelo.Vehiculo;
import da.obligatorio.obligatorioDA.modelo.EstadoPropietario;

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

        // Estado propietario
        EstadoPropietario activo = new EstadoPropietario(1, "Activo");
        EstadoPropietario deshabilitado = new EstadoPropietario(1, "Deshabilitado");

        // -- Propietarios (5) -- (creados de forma simple, parecido a Notificacion)
        Propietario p1 = new Propietario();
        p1.setId(1);
        p1.setNombreCompleto("Juan Perez");
        p1.setContrasenia("123456");
        p1.setCedula("2001");
        p1.setSaldo(150.0);
        p1.setEstadoPropietario(activo);
        p1.setListaNotificaciones(Arrays.asList(new Notificacion(1, new Date(), "Bienvenido Juan")));

        Propietario p2 = new Propietario();
        p2.setId(2);
        p1.setContrasenia("123456");
        p2.setNombreCompleto("María Gomez");
        p2.setCedula("2002");
        p2.setSaldo(200.0);
        p2.setEstadoPropietario(deshabilitado);
        p2.setListaNotificaciones(Arrays.asList(new Notificacion(2, new Date(), "Bienvenida María")));

        Propietario p3 = new Propietario();
        p3.setId(3);
        p1.setContrasenia("123456");
        p3.setNombreCompleto("Carlos Ruiz");
        p3.setCedula("2003");
        p3.setSaldo(50.0);
        p3.setEstadoPropietario(activo);
        p3.setListaNotificaciones(Arrays.asList(new Notificacion(3, new Date(), "Saldo bajo Carlos")));

        Propietario p4 = new Propietario();
        p4.setId(4); 
        p1.setContrasenia("123456");
        p4.setNombreCompleto("Lucía Fernández");
        p4.setCedula("2004");
        p4.setSaldo(300.0);
        p4.setEstadoPropietario(activo);
        p4.setListaNotificaciones(Arrays.asList(new Notificacion(4, new Date(), "Promoción disponible")));

        Propietario p5 = new Propietario();
        p5.setId(5);
        p1.setContrasenia("123456");
        p5.setNombreCompleto("Diego López");
        p5.setCedula("2005");
        p5.setSaldo(75.0);
        p5.setEstadoPropietario(activo);
        p5.setListaNotificaciones(Arrays.asList(new Notificacion(5, new Date(), "Recarga recomendada")));

        // agregar propietarios a fachada
        f.agregarPropietario(p1);
        f.agregarPropietario(p2);
        f.agregarPropietario(p3);
        f.agregarPropietario(p4);
        f.agregarPropietario(p5);

        f.agregar(p1);
        f.agregar(p2);
        f.agregar(p3);
        f.agregar(p4);

        //Administrador
        Administrador admin = new Administrador(1, "Juan", "a",  "a");
        f.agregar(admin);


        // -- Vehiculos (5) --
        Vehiculo v1 = new Vehiculo(1, "ABC-101", "Fiesta", "Rojo", catAuto, p1, null);
        Vehiculo v2 = new Vehiculo(2, "BCD-202", "Civic", "Azul", catAuto, p2, null);
        Vehiculo v3 = new Vehiculo(3, "CDE-303", "Tornado", "Blanco", catMoto, p3, null);
        Vehiculo v4 = new Vehiculo(4, "DEF-404", "Corolla", "Gris", catAuto, p4, null);
        Vehiculo v5 = new Vehiculo(5, "EFG-505", "Pulsar", "Negro", catMoto, p5, null);

        // asignar vehiculos a sus propietarios (listas simples)
        p1.setListVehiculos(Arrays.asList(v1));
        p2.setListVehiculos(Arrays.asList(v2));
        p3.setListVehiculos(Arrays.asList(v3));
        p4.setListVehiculos(Arrays.asList(v4));
        p5.setListVehiculos(Arrays.asList(v5));

        // -- Puestos (3) --
        Puesto puesto1 = new Puesto(1, "Puesto Centro", "Av. Principal 100", null, null, null, null);
        Puesto puesto2 = new Puesto(2, "Puesto Norte", "Calle Norte 45", null, null, null, null);
        Puesto puesto3 = new Puesto(3, "Puesto Sur", "Ruta 10 Km 5", null, null, null, null);

        f.agregarPuesto(puesto1);
        f.agregarPuesto(puesto2);
        f.agregarPuesto(puesto3);

        // -- Tarifas de ejemplo --
        Tarifa t1 = new Tarifa(1, 50.0, catAuto);
        Tarifa t2 = new Tarifa(2, 30.0, catMoto);

        puesto1.setListTarifas(Arrays.asList(t1, t2)); // Centro: auto y moto
        puesto2.setListTarifas(Arrays.asList(t1));     // Norte: solo autos
        puesto3.setListTarifas(Arrays.asList(t2));  

        // -- Bonificaciones y Cobros simples --
        Bonificacion b1 = new Bonificacion(1, p1, "Fidelidad", puesto1);
        Bonificacion b2 = new Bonificacion(2, p2, "Promoción", puesto2);
        f.agregarBonificacion(b1);
        f.agregarBonificacion(b2);
        p1.setListBonificaciones(Arrays.asList(b1));
        p2.setListBonificaciones(Arrays.asList(b2));

        Cobro cob1 = new Cobro(1, 50.0, puesto1);
        Cobro cob2 = new Cobro(2, 30.0, puesto2);
        puesto1.setListaCobro(Arrays.asList(cob1));
        puesto2.setListaCobro(Arrays.asList(cob2));

        // -- Transitos (5) --
        Transito tr1 = new Transito(1, puesto1, v1, new Date());
        Transito tr2 = new Transito(2, puesto1, v2, new Date());
        Transito tr3 = new Transito(3, puesto2, v3, new Date());
        Transito tr4 = new Transito(4, puesto3, v4, new Date());
        Transito tr5 = new Transito(5, puesto2, v5, new Date());

        // asignar transitos a vehiculos y puestos
        v1.setListaTransito(Arrays.asList(tr1));
        v2.setListaTransito(Arrays.asList(tr2));
        v3.setListaTransito(Arrays.asList(tr3));
        v4.setListaTransito(Arrays.asList(tr4));
        v5.setListaTransito(Arrays.asList(tr5));

        puesto1.setListTransito(Arrays.asList(tr1, tr2));
        puesto2.setListTransito(Arrays.asList(tr3, tr5));
        puesto3.setListTransito(Arrays.asList(tr4));

        // Administrador (ejemplo)

        // Datos cargados

	}

}
