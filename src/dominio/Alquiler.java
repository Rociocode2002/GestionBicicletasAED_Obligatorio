/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;
import java.util.Date;

/**
 *
 * @author rocio
 */
public class Alquiler {
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
    
}
