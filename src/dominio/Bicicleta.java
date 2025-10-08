
package dominio;
import java.util.Date;
import java.util.logging.Logger;
import tads.ListaDE;
import tads.ListaSE;

public class Bicicleta implements Comparable <Bicicleta>{
 
    private String Codigo;
    private Tipo_Bicicleta tipo;
    private Estado_Bicicleta estado ;
    private Estacion estacionActual;
    private String motivoMantenimiento;
    private Date fechaIngresoDeposito;
    private ListaSE<Bicicleta> listaDeposito;


  
    public Bicicleta(String codigo, Tipo_Bicicleta tipo) {
     this.Codigo = codigo;
     this.tipo = tipo; 
     this.estado = Estado_Bicicleta.DISPONIBLE;
     
    }


    public Bicicleta() {
    }

    @Override
    public int compareTo(Bicicleta o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public ListaSE<Bicicleta> getListaDeposito() {
        return listaDeposito;
    }

    public void setListaDeposito(ListaSE<Bicicleta> listaDeposito) {
        this.listaDeposito = listaDeposito;
    }

    public Tipo_Bicicleta getTipo() {
        return tipo;
    }

    public void setTipo(Tipo_Bicicleta tipo) {
        this.tipo = tipo;
    }

    public Estado_Bicicleta getEstado() {
        return estado;
    }

    public void setEstado(Estado_Bicicleta estado) {
        this.estado = estado;
    }

    public Estacion getEstacionActual() {
        return estacionActual;
    }

    public void setEstacionActual(Estacion estacionActual) {
        this.estacionActual = estacionActual;
    }

    public String getMotivoMantenimiento() {
        return motivoMantenimiento;
    }

    public void setMotivoMantenimiento(String motivoMantenimiento) {
        this.motivoMantenimiento = motivoMantenimiento;
    }

    public Date getFechaIngresoDeposito() {
        return fechaIngresoDeposito;
    }

    public void setFechaIngresoDeposito(Date fechaIngresoDeposito) {
        this.fechaIngresoDeposito = fechaIngresoDeposito;
    }

  


  
  //Sobreescribimos el equals para que luego existeElemento funcione de ListaSE funcione como queremos.
   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bicicleta)) return false;
        Bicicleta otra = (Bicicleta) o;
        return this.Codigo.equalsIgnoreCase(otra.Codigo);
    }

    @Override
    public int hashCode() {
        return Codigo.toLowerCase().hashCode();
    }
    
    @Override
    public String toString() {
        String estadoFormateado = "";
        switch (estado) {
            case DISPONIBLE:
                estadoFormateado = "Disponible";
                break;
            case MANTENIMIENTO:
                estadoFormateado = "Mantenimiento";
                break;
            case ALQUILADA:
                estadoFormateado = "Alquilada";
                break;
            default:
                estadoFormateado = estado.name();
        }
        
        return Codigo + "#" + tipo + "#" + estadoFormateado;
    }
}
    

