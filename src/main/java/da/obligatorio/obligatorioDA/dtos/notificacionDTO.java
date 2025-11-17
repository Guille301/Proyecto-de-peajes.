

package da.obligatorio.obligatorioDA.dtos;
import java.util.Date;
import da.obligatorio.obligatorioDA.modelo.Notificacion;

public class notificacionDTO {
    
    private Date fecha;
    private String mensaje;

    public notificacionDTO(Notificacion n) {
        
        this.mensaje = n.getMensaje();
    }

   
    public String getMensaje() { return mensaje; }
}
