/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author rocio
 */

public interface ILista<T> {

    void Adicionar(T x);

    void Insertar(T x, int pos) throws Exception;

    T Obtener(int pos) throws Exception;

    void Eliminar(int pos) throws Exception;

    int Longitud();

    boolean Vacia();
}


