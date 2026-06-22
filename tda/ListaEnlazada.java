package tda;

import interfaces.IColeccion;

public class ListaEnlazada<T> implements IColeccion {

    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) { this.dato = dato; }
    }

    private Nodo cabeza;
    private int cantidad;

    public void agregar(T dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) actual = actual.siguiente;
            actual.siguiente = nuevo;
        }
        cantidad++;
    }

    public void agregarInicio(T dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        cantidad++;
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= cantidad) return null;
        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) actual = actual.siguiente;
        return actual.dato;
    }

    public boolean eliminar(int indice) {
        if (indice < 0 || indice >= cantidad) return false;
        if (indice == 0) {
            cabeza = cabeza.siguiente;
            cantidad--;
            return true;
        }
        Nodo anterior = cabeza;
        for (int i = 0; i < indice - 1; i++) anterior = anterior.siguiente;
        anterior.siguiente = anterior.siguiente.siguiente;
        cantidad--;
        return true;
    }

    public boolean contiene(T dato) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(dato)) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public boolean estaVacia() { return cabeza == null; }

    @Override
    public int getCantidad() { return cantidad; }
}
