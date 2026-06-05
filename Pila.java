public class Pila<T> implements IColeccion {

    private static final int CAPACIDAD = 100;
    private final Object[] elementos;
    private int tope;

    public Pila() {
        elementos = new Object[CAPACIDAD];
        tope = -1;
    }

    public void apilar(T dato) {
        if (tope < CAPACIDAD - 1) {
            tope++;
            elementos[tope] = dato;
        }
    }

    @SuppressWarnings("unchecked")
    public T desapilar() {
        if (estaVacia()) return null;
        T datoExtraido = (T) elementos[tope];
        elementos[tope] = null;
        tope--;
        return datoExtraido;
    }

    @SuppressWarnings("unchecked")
    public T verTope() {
        if (estaVacia()) return null;
        return (T) elementos[tope];
    }

    @Override
    public boolean estaVacia() { return tope == -1; }

    @Override
    public int getCantidad() { return tope + 1; }
}
