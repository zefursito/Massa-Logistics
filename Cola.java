public class Cola<T> implements IColeccion {

    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) { this.dato = dato; }
    }

    private Nodo frente;
    private Nodo fondo;
    private int cantidad;

    public void encolar(T dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (fondo != null) fondo.siguiente = nuevoNodo;
        fondo = nuevoNodo;
        if (frente == null) frente = nuevoNodo;
        cantidad++;
    }

    public T desencolar() {
        if (estaVacia()) return null;
        T datoExtraido = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fondo = null;
        cantidad--;
        return datoExtraido;
    }

    public T verFrente() {
        if (estaVacia()) return null;
        return frente.dato;
    }

    @Override
    public boolean estaVacia() { return frente == null; }

    @Override
    public int getCantidad() { return cantidad; }
}
