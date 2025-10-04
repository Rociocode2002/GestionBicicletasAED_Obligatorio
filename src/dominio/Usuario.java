/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author rocio
 */
public class Usuario implements Comparable <Usuario>{

    @Override
    public int compareTo(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   private String Nombre;
   private String Cedula;

    public Usuario(String cedula, String nombre) {
        this.Nombre = nombre;
        this.Cedula = cedula;
    }
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }
   
      //Sobreescribimos el equals para que luego existeElemento funcione de ListaSE funcione como queremos.
   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario otra = (Usuario) o;
        return this.Nombre.equalsIgnoreCase(otra.Nombre);
    }

    @Override
    public int hashCode() {
        return Nombre.toLowerCase().hashCode();
    }
    
    
  
}
