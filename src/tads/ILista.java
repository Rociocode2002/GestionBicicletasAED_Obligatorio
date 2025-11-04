package tads;

public interface ILista<T> {

    void Adicionar(T x); //este método siempre agrega AL FINAL, a diferencia del insertar en el que elijo la pos 

    void AdicionarOrdenado(T x);
    
    void Insertar(T x, int pos) throws Exception;// si inserto en una posición que ya estaba, se "mueven" los siguientes

    T Obtener(int pos);

    void Eliminar(int pos);

    int Longitud();

    boolean Vacia();
    
    boolean existeElemento(T elemento);
    
     String mostrar();
      void borrarElemento (T dato);
}
