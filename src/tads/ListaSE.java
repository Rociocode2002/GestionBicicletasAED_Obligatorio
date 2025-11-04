package tads;

/* Clase ListaSE: Implementacion del TDA Lista usando
 * nodos simplemente enlazados, con apuntador al primer nodo 
 */
public class ListaSE<T extends Comparable<T>> implements ILista<T> {

    protected NodoSE<T> cabeza;
    protected int longitud;

    public ListaSE() {
        cabeza = null;
        longitud = 0;
    }

    @Override
    public void Adicionar(T x) {
        NodoSE<T> nodo_nuevo =new NodoSE<T>(x);// con esto creamos el nuevo nodo con el dato x, con siguiente = null
        if (cabeza == null) 
            cabeza = nodo_nuevo;
        else {
            NodoSE<T> actual = cabeza;
            while (actual.getSiguiente()!= null){
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nodo_nuevo);
        }
        longitud++;
    }
    
    // de clase del 30/09
    @Override
 
public void AdicionarOrdenado(T x) {
    NodoSE<T> nodo_nuevo = new NodoSE<T>(x);
    
   
    if (cabeza == null || nodo_nuevo.getDato().compareTo(cabeza.getDato()) <= 0) {
        nodo_nuevo.setSiguiente(cabeza);
        cabeza = nodo_nuevo;
    } 
  
    else {
        NodoSE<T> actual = cabeza;
        
       
        while (actual.getSiguiente() != null && 
               nodo_nuevo.getDato().compareTo(actual.getSiguiente().getDato()) > 0) {
            actual = actual.getSiguiente();
        }
        
      
        nodo_nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nodo_nuevo);
    }
    longitud++;
}
    
    // de clase del 30/09
    @Override
    public boolean existeElemento (T x) {
        
        if (cabeza == null) 
            return false;
        
        else  {
            NodoSE<T> actual = cabeza;
            while (actual != null)
                if (actual.getDato().equals(x))
                    return true;
                else 
                    actual = actual.getSiguiente();
            
        }
        return false;    
    }
    

    @Override
    public void Insertar(T x, int pos) throws PosFueraDeRangoException {
        if (pos <0 || pos>= Longitud()) throw new PosFueraDeRangoException();
        NodoSE<T> nodo_nuevo =new NodoSE<T>(x);
        if (pos == 0) {
            nodo_nuevo.setSiguiente(cabeza);
            cabeza = nodo_nuevo;  
            
        }    
        else {
            NodoSE<T> actual = cabeza;
            int pos_actual=0;
            while (pos_actual < pos-1){
                actual = actual.getSiguiente();
                pos_actual++;
            }
            nodo_nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nodo_nuevo);
            
        }  
        longitud++;
    }
    

    @Override
    public T Obtener(int pos){
        
        if (pos <0 || pos>= Longitud())
            throw new PosFueraDeRangoException();
        else {
        NodoSE<T> actual = cabeza;
        int pos_actual=0;
        while (pos_actual < pos){
            actual = actual.getSiguiente();
            pos_actual++;
        }
        return actual.getDato();
        }
    }

    @Override
    public void Eliminar(int pos)   {
        if (pos <0 || pos>= Longitud());
        NodoSE<T> actual = cabeza;
        if (pos == 0) {
            
            cabeza = actual.getSiguiente(); 
        }    
        else {
            
            int pos_actual=0;
            while (pos_actual < pos-1){
                actual = actual.getSiguiente();
                pos_actual++;
            }
            NodoSE<T>temporal = actual.getSiguiente();
            actual.setSiguiente(temporal.getSiguiente()); // la profe puso dos .getSiguiente juntos en vez de usar el temporal  
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
    public String mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // este metodo que borra los elementos tomando como parametro el objeto
   // primero verifica si el elemento está en la cabeza y en ese caso la mueve
    // al siguiente nodo, pero si está en otra posición recorre 
    //  la lista hasta encontrar el nodo anterior al que contiene
     //el dato buscado y luego reconecta los enlaces saltando 
     //  el nodo a eliminar, manteniendo así la integridad de la lista.
       @Override
      public void borrarElemento (T dato){
    if (cabeza != null) {

            if (cabeza.getDato().equals(dato)) {
                cabeza = cabeza.getSiguiente();
            } else {
                NodoSE actual = cabeza;
                 while (actual.getSiguiente() != null && !actual.getSiguiente().getDato().equals(dato)) {
                actual = actual.getSiguiente();
                }
                if (actual.getSiguiente() != null) {
                    NodoSE <T> aBorrar = actual.getSiguiente();
                    actual.setSiguiente(aBorrar.getSiguiente());
                    aBorrar.setSiguiente(null);

                }
            }
        }
   }
    
    
    
}