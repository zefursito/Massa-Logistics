public class ColaCircular<T> implements IColeccion {

    private final Object[] elementos;
    private final int max;
    private int frente;
    private int fondo;
    private int cantidad;

    public ColaCircular(int capacidad) {
        this.max = capacidad;
        this.elementos = new Object[capacidad];
        this.frente = 0;
        this.fondo = 0;
        this.cantidad = 0;
    }

    public boolean encolar(T dato) {
        if (estaLlena()) return false;
        elementos[fondo] = dato;
        fondo = (fondo + 1) % max;
        cantidad++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T desencolar() {
        if (estaVacia()) return null;
        T datoExtraido = (T) elementos[frente];
        elementos[frente] = null;
        frente = (frente + 1) % max;
        cantidad--;
        return datoExtraido;
    }

    @SuppressWarnings("unchecked")
    public T verFrente() {
        if (estaVacia()) return null;
        return (T) elementos[frente];
    }

    public boolean estaLlena() { return cantidad == max; }

    @Override
    public boolean estaVacia() { return cantidad == 0; }

    @Override
    public int getCantidad() { return cantidad; }
}
