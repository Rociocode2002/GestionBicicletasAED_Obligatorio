/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author rocio
 */
public class Bicicleta {
    
    private String Codigo;
    private Tipo_Bicicleta tipo;
    private Estado_Bicicleta estado ;
    private Estacion estacionActual;
    private String motivoMantenimiento;
    private Date fechaIngresoDeposito;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
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

    public Bicicleta(String Codigo, Tipo_Bicicleta tipo) {
        this.Codigo = Codigo;
        this.tipo = tipo;
       
    }

    @Override
    public String toString() {
        return "Bicicleta{" + "Codigo=" + Codigo + ", tipo=" + tipo + ", estado=" + estado + ", estacionActual=" + estacionActual + ", motivoMantenimiento=" + motivoMantenimiento + ", fechaIngresoDeposito=" + fechaIngresoDeposito + '}';
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
    
}
