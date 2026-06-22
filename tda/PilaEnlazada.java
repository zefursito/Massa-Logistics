package tda;

import interfaces.IColeccion;

public class PilaEnlazada<T> implements IColeccion {

    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) { this.dato = dato; }
    }

    private Nodo tope;
    private int cantidad;

    public void apilar(T dato) {
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
        cantidad++;
    }

    public T desapilar() {
        if (estaVacia()) return null;
        T datoExtraido = tope.dato;
        tope = tope.siguiente;
        cantidad--;
        return datoExtraido;
    }

    public T verTope() {
        if (estaVacia()) return null;
        return tope.dato;
    }

    @Override
    public boolean estaVacia() { return tope == null; }

    @Override
    public int getCantidad() { return cantidad; }
}
