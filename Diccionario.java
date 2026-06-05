public class Diccionario<K, V> implements IColeccion {

    private static final int CAPACIDAD = 16;

    private class Entrada {
        K clave;
        V valor;
        Entrada siguiente;

        Entrada(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    private final Object[] tabla;
    private int cantidad;

    public Diccionario() {
        tabla = new Object[CAPACIDAD];
    }

    private int calcularPosicion(K clave) {
        int codigoHash = clave.hashCode();
        if (codigoHash < 0) codigoHash = -codigoHash;
        return codigoHash % CAPACIDAD;
    }

    public void insertar(K clave, V valor) {
        int posicion = calcularPosicion(clave);
        @SuppressWarnings("unchecked")
        Entrada entradaActual = (Entrada) tabla[posicion];

        while (entradaActual != null) {
            if (entradaActual.clave.equals(clave)) {
                entradaActual.valor = valor;
                return;
            }
            entradaActual = entradaActual.siguiente;
        }

        Entrada nuevaEntrada = new Entrada(clave, valor);
        @SuppressWarnings("unchecked")
        Entrada primera = (Entrada) tabla[posicion];
        nuevaEntrada.siguiente = primera;
        tabla[posicion] = nuevaEntrada;
        cantidad++;
    }

    public V buscar(K clave) {
        int posicion = calcularPosicion(clave);
        @SuppressWarnings("unchecked")
        Entrada entradaActual = (Entrada) tabla[posicion];

        while (entradaActual != null) {
            if (entradaActual.clave.equals(clave)) return entradaActual.valor;
            entradaActual = entradaActual.siguiente;
        }
        return null;
    }

    public boolean contiene(K clave) {
        return buscar(clave) != null;
    }

    public boolean eliminar(K clave) {
        int posicion = calcularPosicion(clave);
        @SuppressWarnings("unchecked")
        Entrada entradaActual = (Entrada) tabla[posicion];
        Entrada entradaAnterior = null;

        while (entradaActual != null) {
            if (entradaActual.clave.equals(clave)) {
                if (entradaAnterior == null) tabla[posicion] = entradaActual.siguiente;
                else entradaAnterior.siguiente = entradaActual.siguiente;
                cantidad--;
                return true;
            }
            entradaAnterior = entradaActual;
            entradaActual = entradaActual.siguiente;
        }
        return false;
    }

    @Override
    public boolean estaVacia() { return cantidad == 0; }

    @Override
    public int getCantidad() { return cantidad; }
}
