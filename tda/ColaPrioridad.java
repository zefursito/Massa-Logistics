package tda;

import interfaces.IColeccion;
import interfaces.IPriorizable;

public class ColaPrioridad<T extends IPriorizable> implements IColeccion {

    private static final int CAPACIDAD = 100;
    private final Object[] arreglo;
    private int cantidad;

    public ColaPrioridad() {
        arreglo = new Object[CAPACIDAD];
    }

    @SuppressWarnings("unchecked")
    private T elem(int i) { return (T) arreglo[i]; }

    public boolean insertar(T elemento) {
        if (cantidad >= CAPACIDAD) return false;
        arreglo[cantidad] = elemento;
        subirNodo(cantidad);
        cantidad++;
        return true;
    }

    public T extraerMinimo() {
        if (estaVacia()) return null;
        T minimo = elem(0);
        cantidad--;
        arreglo[0] = arreglo[cantidad];
        arreglo[cantidad] = null;
        bajarNodo(0);
        return minimo;
    }

    public T verMinimo() {
        if (estaVacia()) return null;
        return elem(0);
    }

    private void subirNodo(int posicion) {
        while (posicion > 0) {
            int posPadre = (posicion - 1) / 2;
            if (elem(posPadre).obtenerPrioridad() > elem(posicion).obtenerPrioridad()) {
                intercambiar(posPadre, posicion);
                posicion = posPadre;
            } else {
                break;
            }
        }
    }

    private void bajarNodo(int posicion) {
        while (true) {
            int posHijoIzquierdo = 2 * posicion + 1;
            int posHijoDerecho   = 2 * posicion + 2;
            int posMenor         = posicion;

            if (posHijoIzquierdo < cantidad &&
                elem(posHijoIzquierdo).obtenerPrioridad() < elem(posMenor).obtenerPrioridad()) {
                posMenor = posHijoIzquierdo;
            }
            if (posHijoDerecho < cantidad &&
                elem(posHijoDerecho).obtenerPrioridad() < elem(posMenor).obtenerPrioridad()) {
                posMenor = posHijoDerecho;
            }

            if (posMenor == posicion) break;

            intercambiar(posicion, posMenor);
            posicion = posMenor;
        }
    }

    private void intercambiar(int i, int j) {
        Object aux = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = aux;
    }

    @Override
    public boolean estaVacia() { return cantidad == 0; }

    @Override
    public int getCantidad() { return cantidad; }
}
