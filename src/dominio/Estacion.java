/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import tads.ListaSE;

/**
 *
 * @author rocio
 */
public class Estacion {
    private String Nombre;
    private String Barrio;
    private int Capacidad;
    private ListaSE<Bicicleta> bicicletas;
    private int cntBicicletasAncladas ;
    //falta cola alquiler y cola Devolucion

    @Override
    public String toString() {
        return "Estacion{" + "Nombre=" + Nombre + ", Barrio=" + Barrio + ", Capacidad=" + Capacidad + ", bicicletas=" + bicicletas + ", cntBicicletasAncladas=" + cntBicicletasAncladas + '}';
    }

    public Estacion(String Nombre, String Barrio, int Capacidad, ListaSE<Bicicleta> bicicletas, int cntBicicletasAncladas) {
        this.Nombre = Nombre;
        this.Barrio = Barrio;
        this.Capacidad = Capacidad;
        this.bicicletas = bicicletas;
        this.cntBicicletasAncladas = cntBicicletasAncladas;
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
        return bicicletas;
    }

    public void setBicicletas(ListaSE<Bicicleta> bicicletas) {
        this.bicicletas = bicicletas;
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
