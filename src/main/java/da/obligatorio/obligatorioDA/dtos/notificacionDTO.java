

package da.obligatorio.obligatorioDA.dtos;
import java.util.Date;
import da.obligatorio.obligatorioDA.modelo.Notificacion;

public class notificacionDTO {
    private int id;
    private Date fecha;
    private String mensaje;

    public notificacionDTO(Notificacion n) {
        this.id = n.getId();        
        this.fecha = n.getFechaYHora();  
        this.mensaje = n.getMensaje();
    }

    public int getId() { return id; }
    public Date getFecha() { return fecha; }
    public String getMensaje() { return mensaje; }
}
