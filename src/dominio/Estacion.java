/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author rocio
 */
public class Estacion implements Comparable <Estacion>{

    @Override
    public int compareTo(Estacion o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private String Nombre;
    private String Barrio;
    private int Capacidad;

    
    public Estacion(String nombre, String barrio, int capacidad) {
        this.Nombre = nombre;
        this.Barrio = barrio;
        this.Capacidad = capacidad;
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
