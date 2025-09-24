/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author rocio
 */
public class Bicicleta {
    
    private String Codigo;
    private String Tipo;
    private String Estado;

    public Bicicleta(String codigo, String tipo) {
        this.Codigo = codigo;
        this.Tipo = tipo;
    }

    public Bicicleta() {
    }

    public String getCodigo() {
        return Codigo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public void setCodigo(String codigo) {
        this.Codigo = codigo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
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
