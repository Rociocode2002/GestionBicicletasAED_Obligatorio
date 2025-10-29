/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;
import tads.NodoSE;

/**
 *
 * @author rocio
 */
public class Pila<T> implements IPila<T> {

    private NodoSE<T> tope;
    private int cantidadElementos;
    
    public Pila(){
        this.tope = null;
        cantidadElementos = 0;
    }
    
    
    @Override
    public boolean estaVacia() {
        return (this.tope == null);
    }

    @Override
    public void apilar(T dato) {
        NodoSE<T> nuevoNodo = new NodoSE<T>(dato);
        nuevoNodo.setDato(dato);
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
        cantidadElementos++;

    }

    @Override
    public T top() {
        if(estaVacia()){
            throw new IllegalStateException("La pila esta vacia");
        } 
        
        return (T) tope.getDato();
    }

    @Override
    public T desapilar() {
        if(estaVacia()){
            throw new IllegalStateException("La pila esta vacia");
        }

        NodoSE nodoTope = tope;
        
        tope = tope.getSiguiente();
        nodoTope.setSiguiente(null);
        
        cantidadElementos--;
        
        return (T) nodoTope.getDato();
        
    }

    @Override
    public void vaciar() {
        this.tope = null;
        this.cantidadElementos = 0;
    }

    @Override
    public void mostrar() {
        NodoSE<T> aux = tope;
        while (aux != null){
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }

    @Override
    public int cantidadElementos() {
        return cantidadElementos;
    }
    
}