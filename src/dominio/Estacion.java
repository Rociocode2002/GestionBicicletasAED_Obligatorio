/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;
import tads.Cola;
import tads.ListaSE;
import dominio.Usuario;
/**
 *
 * @author rocio
 */
public class Estacion implements Comparable <Estacion>{

    


    
    private String Nombre;
    private String Barrio;
    private int Capacidad;
    private ListaSE<Bicicleta> bicicletasAncladas;
    private int cntBicicletasAncladas ;
    

   
    private Cola<Usuario> ColaEspera;

    public Cola<Usuario> getColaEspera() {
        return ColaEspera;
    }

    public Cola<Alquiler> getColaAlquiler() {
        return ColaAlquiler;
    }

    public Cola<Usuario> getColaDevolucion() {
        return ColaDevolucion;
    }
    private Cola<Alquiler> ColaAlquiler;
    private Cola<Usuario> ColaDevolucion;
    
    public boolean tieneAnclajeLibre() {
        return bicicletasAncladas.Longitud() < Capacidad;
    }
    

    
    
    
    

    public Estacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int compareTo(Estacion o) {
        return this.Nombre.compareToIgnoreCase(o.Nombre); 
    }
    
    @Override
    public String toString() {
        return "Estacion{" + "Nombre=" + Nombre + ", Barrio=" + Barrio + ", Capacidad=" + Capacidad + ", bicicletas=" + bicicletasAncladas + ", cntBicicletasAncladas=" + cntBicicletasAncladas + '}';
    }

   
      public Estacion(String Nombre, String Barrio, int Capacidad) {
        this.Nombre = Nombre;
        this.Barrio = Barrio;
        this.Capacidad = Capacidad;
     
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getBarrio() {
        return Barrio;
    }

    public void setBarrio(String Barrio) {
        this.Barrio = Barrio;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int Capacidad) {
        this.Capacidad = Capacidad;
    }

    public ListaSE<Bicicleta> getBicicletas() {
        return bicicletasAncladas;
    }

    public void setBicicletas(ListaSE<Bicicleta> bicicletas) {
        this.bicicletasAncladas = bicicletas;
    }

    public int getCntBicicletasAncladas() {
        return cntBicicletasAncladas;
    }

    public void setCntBicicletasAncladas(int cntBicicletasAncladas) {
        this.cntBicicletasAncladas = cntBicicletasAncladas;
    }
    

    
    
  //Sobreescribimos el equals para que luego existeElemento funcione de ListaSE funcione como queremos.
   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estacion)) return false;
        Estacion otra = (Estacion) o;
        return this.Nombre.equalsIgnoreCase(otra.Nombre);
    }

    @Override
    public int hashCode() {
        return Nombre.toLowerCase().hashCode();
    }

    
}
