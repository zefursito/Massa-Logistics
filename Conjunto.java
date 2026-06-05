public class Conjunto<T> implements IColeccion {

    private static final int CAPACIDAD = 16;

    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) { this.dato = dato; }
    }

    private final Object[] tabla;
    private int cantidad;

    public Conjunto() {
        tabla = new Object[CAPACIDAD];
    }

    private int calcularPosicion(T elemento) {
        int codigoHash = elemento.hashCode();
        if (codigoHash < 0) codigoHash = -codigoHash;
        return codigoHash % CAPACIDAD;
    }

    public boolean agregar(T elemento) {
        if (contiene(elemento)) return false;

        int posicion = calcularPosicion(elemento);
        Nodo nuevoNodo = new Nodo(elemento);
        @SuppressWarnings("unchecked")
        Nodo primero = (Nodo) tabla[posicion];
        nuevoNodo.siguiente = primero;
        tabla[posicion] = nuevoNodo;
        cantidad++;
        return true;
    }

    public boolean contiene(T elemento) {
        int posicion = calcularPosicion(elemento);
        @SuppressWarnings("unchecked")
        Nodo nodoActual = (Nodo) tabla[posicion];

        while (nodoActual != null) {
            if (nodoActual.dato.equals(elemento)) return true;
            nodoActual = nodoActual.siguiente;
        }
        return false;
    }

    public boolean eliminar(T elemento) {
        int posicion = calcularPosicion(elemento);
        @SuppressWarnings("unchecked")
        Nodo nodoActual = (Nodo) tabla[posicion];
        Nodo nodoAnterior = null;

        while (nodoActual != null) {
            if (nodoActual.dato.equals(elemento)) {
                if (nodoAnterior == null) tabla[posicion] = nodoActual.siguiente;
                else nodoAnterior.siguiente = nodoActual.siguiente;
                cantidad--;
                return true;
            }
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.siguiente;
        }
        return false;
    }

    @Override
    public boolean estaVacia() { return cantidad == 0; }

    @Override
    public int getCantidad() { return cantidad; }
}
