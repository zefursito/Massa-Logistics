package tda;

import interfaces.IColeccion;

public class Conjunto<T> implements IColeccion {

    private static final int CAPACIDAD = 100;

    private final Object[] datos;
    private int cantidad;

    public Conjunto() {
        datos = new Object[CAPACIDAD];
    }

    public boolean agregar(T elemento) {
        if (contiene(elemento)) return false;
        if (cantidad >= CAPACIDAD) return false;
        datos[cantidad] = elemento;
        cantidad++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean contiene(T elemento) {
        for (int i = 0; i < cantidad; i++) {
            if (((T) datos[i]).equals(elemento)) return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean eliminar(T elemento) {
        for (int i = 0; i < cantidad; i++) {
            if (((T) datos[i]).equals(elemento)) {
                datos[i] = datos[cantidad - 1];
                datos[cantidad - 1] = null;
                cantidad--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean estaVacia() { return cantidad == 0; }

    @Override
    public int getCantidad() { return cantidad; }
}
