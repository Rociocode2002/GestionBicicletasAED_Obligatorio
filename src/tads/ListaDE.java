package tads;

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

    @Override
    public void Adicionar(T x) {
        
    }

    @Override
    public void Insertar(T x, int pos){
        
        
    }

    @Override
    public T Obtener(int pos) throws PosFueraDeRangoException {
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

    @Override
    public boolean existeElemento(T elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
