package da.obligatorio.obligatorioDA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
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

    // Estados de propietario
    EstadoPropietario activo = new EstadoPropietario(1, "Activo");
    EstadoPropietario deshabilitado = new EstadoPropietario(2, "Deshabilitado");

    // ========= PROPIETARIOS =========
    // Usamos el constructor grande con listas inicializadas

    Propietario p1 = new Propietario(
            1,
            "Juan Perez",
            150.0,
            null,                    // bonificacion individual (si la usás)
            "2001",
            activo,
            new ArrayList<>(),       // listaNotificaciones
            new ArrayList<>(),       // listVehiculos
            new ArrayList<>()        // listBonificaciones
    );
    p1.setContrasenia("123456");
    p1.setNotificaciones(new Notificacion(1, new Date(), "Bienvenido Juan"));

    Propietario p2 = new Propietario(
            2,
            "María Gomez",
            200.0,
            null,
            "2002",
            deshabilitado,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
    );
    p2.setContrasenia("123456");
    p2.setNotificaciones(new Notificacion(2, new Date(), "Bienvenida María"));

    Propietario p3 = new Propietario(
            3,
            "Carlos Ruiz",
            50.0,
            null,
            "2003",
            activo,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
    );
    p3.setContrasenia("123456");
    p3.setNotificaciones(new Notificacion(3, new Date(), "Saldo bajo Carlos"));

    Propietario p4 = new Propietario(
            4,
            "Lucía Fernández",
            300.0,
            null,
            "2004",
            activo,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
    );
    p4.setContrasenia("123456");
    p4.setNotificaciones(new Notificacion(4, new Date(), "Promoción disponible"));

    Propietario p5 = new Propietario(
            5,
            "Diego López",
            75.0,
            null,
            "2005",
            activo,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
    );
    p5.setContrasenia("123456");
    p5.setNotificaciones(new Notificacion(5, new Date(), "Recarga recomendada"));

    // Agregar propietarios a fachada
    f.agregarPropietario(p1);
    f.agregarPropietario(p2);
    f.agregarPropietario(p3);
    f.agregarPropietario(p4);
    f.agregarPropietario(p5);

    // También como usuarios, si Fachada.agregar(Usuario u) existe
    f.agregar(p1);
    f.agregar(p2);
    f.agregar(p3);
    f.agregar(p4);
    f.agregar(p5);

    // ========= ADMINISTRADOR =========
    Administrador admin = new Administrador(1, "Juan", "a", "a");
    f.agregar(admin);

    // ========= VEHÍCULOS =========
    // Ojo: pasamos new ArrayList<>() para listaTransito

    Vehiculo v1 = new Vehiculo(1, "ABC-101", "Fiesta",  "Rojo",  catAuto, p1, new ArrayList<>());
    Vehiculo v2 = new Vehiculo(2, "BCD-202", "Civic",   "Azul",  catAuto, p2, new ArrayList<>());
    Vehiculo v3 = new Vehiculo(3, "CDE-303", "Tornado", "Blanco",catMoto, p3, new ArrayList<>());
    Vehiculo v4 = new Vehiculo(4, "DEF-404", "Corolla", "Gris",  catAuto, p4, new ArrayList<>());
    Vehiculo v5 = new Vehiculo(5, "EFG-505", "Pulsar",  "Negro", catMoto, p5, new ArrayList<>());

    // Asignar vehículos a propietarios usando los métodos corregidos
    p1.setVehiculos(v1);
    p2.setVehiculos(v2);
    p3.setVehiculos(v3);
    p4.setVehiculos(v4);
    p5.setVehiculos(v5);

    // ========= PUESTOS =========
    // También pasamos listas inicializadas

    Puesto puesto1 = new Puesto(
            1,
            "Puesto Centro",
            "Av. Principal 100",
            new ArrayList<>(),   // listTarifas
            new ArrayList<>(),   // listTransito
            new ArrayList<>()    // listBonificacion
    );

    Puesto puesto2 = new Puesto(
            2,
            "Puesto Norte",
            "Calle Norte 45",
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
    );

    Puesto puesto3 = new Puesto(
            3,
            "Puesto Sur",
            "Ruta 10 Km 5",
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
    );

    f.agregarPuesto(puesto1);
    f.agregarPuesto(puesto2);
    f.agregarPuesto(puesto3);

    // ========= TARIFAS =========

    Tarifa t1 = new Tarifa(1, 50.0, catAuto);
    Tarifa t2 = new Tarifa(2, 30.0, catMoto);

    // Usamos setTarifas(Tarifa) que agrega una sola tarifa
    puesto1.setTarifas(t1);
    puesto1.setTarifas(t2);   // Centro: auto y moto

    puesto2.setTarifas(t1);   // Norte: solo autos
    puesto3.setTarifas(t2);   // Sur: solo motos

    // ========= BONIFICACIONES =========

    Bonificacion b1 = new Bonificacion(1, p1, "Fidelidad",  puesto1);
    Bonificacion b2 = new Bonificacion(2, p2, "Promoción",  puesto2);

    f.agregarBonificacion(b1);
    f.agregarBonificacion(b2);

    // Propietarios: agregamos bonificaciones usando setBonificaciones(Bonificacion)
    p1.setBonificaciones(b1);
    p2.setBonificaciones(b2);

    // Puestos: agregamos bonificaciones usando setBonificacion(Bonificacion)
    puesto1.setBonificacion(b1);
    puesto2.setBonificacion(b2);

    // ========= TRÁNSITOS =========

    Transito tr1 = new Transito(1, puesto1, v1, new Date());
    Transito tr2 = new Transito(2, puesto1, v2, new Date());
    Transito tr3 = new Transito(3, puesto2, v3, new Date());
    Transito tr4 = new Transito(4, puesto3, v4, new Date());
    Transito tr5 = new Transito(5, puesto2, v5, new Date());

    // Vehículos: usar setTransito(Transito t) que agrega a listaTransito
    v1.setTransito(tr1);
    v2.setTransito(tr2);
    v3.setTransito(tr3);
    v4.setTransito(tr4);
    v5.setTransito(tr5);

    // Puestos: usar setTransito(Transito t) que agrega a listTransito
    puesto1.setTransito(tr1);
    puesto1.setTransito(tr2);

    puesto2.setTransito(tr3);
    puesto2.setTransito(tr5);

    puesto3.setTransito(tr4);
}

}
