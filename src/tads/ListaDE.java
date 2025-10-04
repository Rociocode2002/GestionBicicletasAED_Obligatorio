package tads;

import java.util.HashSet;

/* Clase ListaDE: Implementacion del TDA Lista usando
 * nodos doblemente enlazados, con apuntador al primer nodo 
 */
public class ListaDE<T> implements ILista<T> {

    protected NodoDE<T> cabeza;
    protected int longitud;

    public ListaDE() {
        cabeza = null;
        longitud = 0;
    }
    
//de la clase del 30/09:
    @Override
    public void Adicionar(T x) {
        NodoDE<T> nodo_nuevo =new NodoDE<T>(x);// con esto creamos el nuevo nodo con el dato x, con siguiente = null
        if (cabeza == null) 
            cabeza = nodo_nuevo;
        else {
            NodoDE<T> actual = cabeza;
            while (actual.getSiguiente()!= null){
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nodo_nuevo);
            nodo_nuevo.setAnterior(actual);
        }
        longitud++; 
    }

    //de la clase del 30/09:
    @Override
    public void Insertar(T x, int pos){
        if (pos <0 || pos>= Longitud()) throw new PosFueraDeRangoException();
        NodoDE<T> nodo_nuevo =new NodoDE<T>(x);
        if (pos == 0) {
            nodo_nuevo.setSiguiente(cabeza);
            if (cabeza != null)
                cabeza.setAnterior(nodo_nuevo);
            cabeza = nodo_nuevo;      
        }    
        else {
            NodoDE<T> actual = cabeza;
            nodo_nuevo = new NodoDE<T>(x);
            
            for (int i=0; i<pos-1;i++)
                actual = actual.getSiguiente();
            
            nodo_nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nodo_nuevo);
            nodo_nuevo.setAnterior(actual);
            nodo_nuevo.getSiguiente().setAnterior(nodo_nuevo);   
        }  
        longitud++;  
    }
    

    @Override
    public T Obtener(int pos) throws PosFueraDeRangoException {
          if (pos <0 || pos>= Longitud())
            throw new PosFueraDeRangoException();
        else {
        NodoDE<T> actual = cabeza;
        int pos_actual=0;
        while (pos_actual < pos){
            actual = actual.getSiguiente();
            pos_actual++;
        }
        return actual.getDato();
        }
    }

    @Override
    public void Eliminar(int pos) throws PosFueraDeRangoException, ListaVaciaException {
        if (pos <0 || pos>= Longitud()) throw new PosFueraDeRangoException();
        
        if (pos == 0) {
            if (cabeza.getSiguiente()!=null )
                cabeza.getSiguiente().setAnterior(null);
            cabeza = cabeza.getSiguiente(); 
        }    
        else {
            NodoDE<T> actual = cabeza;
            for (int i=0; i<pos-1; i++)
                actual = actual.getSiguiente();
            
            if (actual.getSiguiente().getSiguiente() != null)
                actual.getSiguiente().setAnterior(actual);   
            actual.setSiguiente(actual.getSiguiente().getSiguiente()); 
        }  
        longitud--;
    }

    @Override
    public int Longitud() {
        return longitud;
    }

    @Override
    public boolean Vacia() {
        return (longitud == 0);
    }

    @Override
    public boolean existeElemento(T elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
