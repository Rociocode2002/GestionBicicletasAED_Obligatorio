/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author rocio
 */

public class Cola<T>  implements ICola<T> {

    private NodoSE<T> primero;
    private NodoSE<T> ultimo;
    private int cantidadDeElementos;

    public Cola() {
        primero = null;
        ultimo = null;
        cantidadDeElementos = 0;
    }

    @Override
    public void encolar(T dato) {
        NodoSE<T> nodo = new NodoSE<>(dato);
        if (esVacia()) {
            primero = nodo;
            ultimo = nodo;
        } else {
            ultimo.setSiguiente(nodo);
            ultimo = nodo;
        }
        cantidadDeElementos++;
    }

    @Override
    public T desencolar() {
        if (esVacia()) {
            throw new IllegalStateException("Cola vacía");
        }

        T dato = primero.getDato();
        primero = primero.getSiguiente();
        cantidadDeElementos--;

        if (primero == null) {
            ultimo = null;
        }

        return dato;
    }

    @Override
    public T primero() {
        if (esVacia()) {
            throw new IllegalStateException("Cola vacía");
        }
        return primero.getDato();
    }

    @Override
    public boolean esVacia() {
        return primero == null && ultimo == null;
    }

    @Override
    public int cantidadDeElementos() {
        return cantidadDeElementos;
    }

    @Override
    public void mostrar() {
        NodoSE<T> aux = primero;
        while (aux != null) {
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }

    @Override
    public <T> Cola<T> clonarCola(Cola<T> original) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
