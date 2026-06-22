package tda;

import interfaces.IColeccion;

public class ArbolCategorias implements IColeccion {

    private class Nodo {
        String nombre;
        Nodo primerHijo;
        Nodo siguienteHermano;

        Nodo(String nombre) { this.nombre = nombre; }
    }

    private final Nodo raiz;
    private int cantidad;

    public ArbolCategorias(String nombreRaiz) {
        this.raiz = new Nodo(nombreRaiz);
        this.cantidad = 1;
    }

    public boolean agregar(String nombrePadre, String nombreHija) {
        Nodo padre = buscarNodo(raiz, nombrePadre);
        if (padre == null) return false;
        if (buscarNodo(raiz, nombreHija) != null) return false;

        Nodo hija = new Nodo(nombreHija);
        if (padre.primerHijo == null) {
            padre.primerHijo = hija;
        } else {
            Nodo actual = padre.primerHijo;
            while (actual.siguienteHermano != null) actual = actual.siguienteHermano;
            actual.siguienteHermano = hija;
        }
        cantidad++;
        return true;
    }

    public boolean contiene(String nombre) {
        return buscarNodo(raiz, nombre) != null;
    }

    private Nodo buscarNodo(Nodo actual, String nombre) {
        if (actual == null) return null;
        if (actual.nombre.equals(nombre)) return actual;
        Nodo enHijos = buscarNodo(actual.primerHijo, nombre);
        if (enHijos != null) return enHijos;
        return buscarNodo(actual.siguienteHermano, nombre);
    }

    private static final String RAMA     = "|-- ";
    private static final String ULTIMA   = "\\-- ";
    private static final String VERTICAL = "|   ";
    private static final String VACIO    = "    ";

    public void mostrar() {
        System.out.println(raiz.nombre);
        mostrarHijos(raiz.primerHijo, "");
    }

    private void mostrarHijos(Nodo actual, String prefijo) {
        if (actual == null) return;
        boolean ultimo = (actual.siguienteHermano == null);
        System.out.println(prefijo + (ultimo ? ULTIMA : RAMA) + actual.nombre);
        mostrarHijos(actual.primerHijo, prefijo + (ultimo ? VACIO : VERTICAL));
        mostrarHijos(actual.siguienteHermano, prefijo);
    }

    @Override
    public boolean estaVacia() { return cantidad == 0; }

    @Override
    public int getCantidad() { return cantidad; }
}
