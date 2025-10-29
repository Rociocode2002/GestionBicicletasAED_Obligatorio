/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author rocio
 */

public interface ICola <T>{
    
    public void encolar(T dato);
    
    public T desencolar();
    
    public T primero();
    
    public boolean esVacia();
    
    public int cantidadDeElementos();
    
    public void mostrar (); 
    
    public  <T> Cola<T> clonarCola(Cola<T> original);
    
    
}