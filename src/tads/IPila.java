/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author rocio
 */
public interface IPila <T> {
    public boolean estaVacia();
    
    public void apilar(T dato);
    
    public T top(); //devuelve el valor pero no lo quita
    
    public T desapilar();
    
    public void vaciar();
    
    public void mostrar();
    
    public int cantidadElementos();
}