/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author rocio
 */
public class ListaSE<T> implements ILista<T> {

    protected NodoSE<T> cabeza;
    protected int longitud;

    public ListaSE() {
        cabeza = null;
        longitud = 0;
    }

    @Override
    public void Adicionar(T x) {
        
    }

    @Override
    public void Insertar(T x, int pos) throws PosFueraDeRangoException {
        
    }

    @Override
    public T Obtener(int pos){
        
        return null; 
    }

    @Override
    public void Eliminar(int pos) throws PosFueraDeRangoException, ListaVaciaException {
        
    }

    @Override
    public int Longitud() {
        return longitud;
    }

    @Override
    public boolean Vacia() {
        return (longitud == 0);
    }

}

