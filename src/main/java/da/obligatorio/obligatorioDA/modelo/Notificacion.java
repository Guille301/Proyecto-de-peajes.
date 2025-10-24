package da.obligatorio.obligatorioDA.modelo;

import java.util.Date;

public class Notificacion {
    private int id;
    private Date fechaYHora;
    private String mensaje;

    public Notificacion() {
    }

    public Notificacion(int id, Date fechaYHora, String mensaje) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.mensaje = mensaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(Date fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}