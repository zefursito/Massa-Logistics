public class Diccionario<K, V> implements IColeccion {

    private static final int CAPACIDAD = 100;

    private class Entrada {
        K clave;
        V valor;
    }

    private final Entrada[] datos;
    private int cant;

    @SuppressWarnings("unchecked")
    public Diccionario() {
        datos = new Diccionario.Entrada[CAPACIDAD];
        for (int i = 0; i < CAPACIDAD; i++) datos[i] = new Entrada();
    }

    private int buscarPosicion(K claveBuscada) {
        int i = 1;
        while (i <= cant) {
            if (datos[i].clave.equals(claveBuscada)) return i;
            i++;
        }
        return -1;
    }

    public void insertar(K clave, V valor) {
        int pos = buscarPosicion(clave);
        if (pos != -1) {
            datos[pos].valor = valor;
            return;
        }
        cant++;
        datos[cant].clave = clave;
        datos[cant].valor = valor;
    }

    public V buscar(K clave) {
        int pos = buscarPosicion(clave);
        if (pos != -1) return datos[pos].valor;
        return null;
    }

    public boolean contiene(K clave) {
        return buscarPosicion(clave) != -1;
    }

    public boolean eliminar(K clave) {
        int pos = buscarPosicion(clave);
        if (pos == -1) return false;
        datos[pos].clave = datos[cant].clave;
        datos[pos].valor = datos[cant].valor;
        datos[cant].clave = null;
        datos[cant].valor = null;
        cant--;
        return true;
    }

    @Override
    public boolean estaVacia() { return cant == 0; }

    @Override
    public int getCantidad() { return cant; }
}
