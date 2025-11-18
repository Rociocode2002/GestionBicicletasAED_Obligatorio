
package dominio;
import java.util.Date;

public class Alquiler implements Comparable <Alquiler>{
    private String codigoBicicleta;
    private String cedulaUsuario;
    private String nombreEstacionOrigen;
    private Date fechaAlquiler;

   

    public String getCodigoBicicleta() {
        return codigoBicicleta;
    }

    public void setCodigoBicicleta(String codigoBicicleta) {
        this.codigoBicicleta = codigoBicicleta;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getNombreEstacionOrigen() {
        return nombreEstacionOrigen;
    }

    public void setNombreEstacionOrigen(String nombreEstacionOrigen) {
        this.nombreEstacionOrigen = nombreEstacionOrigen;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    @Override
    public int compareTo(Alquiler o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
