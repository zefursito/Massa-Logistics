package tda;

import interfaces.IColeccion;

public class ArbolCategorias implements IColeccion {

    private class Nodo {
        String nombre;
        Nodo hijoIzquierdo;
        Nodo hijoDerecho;

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
        if (padre.hijoIzquierdo == null) {
            padre.hijoIzquierdo = hija;
        } else if (padre.hijoDerecho == null) {
            padre.hijoDerecho = hija;
        } else {
            return false;
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
        Nodo enIzquierdo = buscarNodo(actual.hijoIzquierdo, nombre);
        if (enIzquierdo != null) return enIzquierdo;
        return buscarNodo(actual.hijoDerecho, nombre);
    }

    private static final String RAMA     = "|-- ";
    private static final String ULTIMA   = "\\-- ";
    private static final String VERTICAL = "|   ";
    private static final String VACIO    = "    ";

    public void mostrar() {
        System.out.println(raiz.nombre);
        mostrarHijos(raiz, "");
    }

    private void mostrarHijos(Nodo actual, String prefijo) {
        boolean tieneIzquierdo = actual.hijoIzquierdo != null;
        boolean tieneDerecho = actual.hijoDerecho != null;

        if (tieneIzquierdo) {
            boolean esUltimo = !tieneDerecho;
            System.out.println(prefijo + (esUltimo ? ULTIMA : RAMA) + actual.hijoIzquierdo.nombre);
            mostrarHijos(actual.hijoIzquierdo, prefijo + (esUltimo ? VACIO : VERTICAL));
        }
        if (tieneDerecho) {
            System.out.println(prefijo + ULTIMA + actual.hijoDerecho.nombre);
            mostrarHijos(actual.hijoDerecho, prefijo + VACIO);
        }
    }

    @Override
    public boolean estaVacia() { return cantidad == 0; }

    @Override
    public int getCantidad() { return cantidad; }
}
