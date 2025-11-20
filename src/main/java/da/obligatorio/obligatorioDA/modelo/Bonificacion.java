package da.obligatorio.obligatorioDA.modelo;

import java.time.LocalDate;

import da.obligatorio.obligatorioDA.excepciones.ObligatorioException;


public class Bonificacion {
    private int id;
    private Propietario propietario;
    private String nombre;
    private Puesto puestos;
    private LocalDate fechaAsignacion;
    private double montoDescuento;
    private CriterioAsignacionBonificacion criterio;

    public Bonificacion() {
    }

    public Bonificacion(int id, Propietario propietario, String nombre, Puesto puestos, LocalDate fechaAsignacion, double montoDescuento, CriterioAsignacionBonificacion criterio) {
        this.id = id;
        this.propietario = propietario;
        this.nombre = nombre;
        this.puestos = puestos;
        this.fechaAsignacion = fechaAsignacion;
        this.montoDescuento = montoDescuento;
        this.criterio = criterio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Puesto getPuestos() {
        return puestos;
    }

    public void setPuestos(Puesto puestos) {
        this.puestos = puestos;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public double getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(double montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

        public CriterioAsignacionBonificacion getCriterio() {
        return criterio;
    }

    public double calcularDescuento(double montoTarifa) throws ObligatorioException {
        if (criterio == null) return 0;
        return criterio.calcularDescuento(montoTarifa, propietario, puestos);
    }

    public static Bonificacion sinBonificacion() {
        Bonificacion b = new Bonificacion();
        b.setNombre("Sin bonificación");
        b.setMontoDescuento(0);
        return b;
    }

}