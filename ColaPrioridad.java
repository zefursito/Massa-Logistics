public class ColaPrioridad implements IColeccion {

    private static final int CAPACIDAD = 100;
    private final Producto[] arreglo;
    private int cantidad;

    public ColaPrioridad() {
        arreglo = new Producto[CAPACIDAD];
    }

    public void insertar(Producto producto) {
        if (cantidad >= CAPACIDAD) return;
        arreglo[cantidad] = producto;
        subirNodo(cantidad);
        cantidad++;
    }

    public Producto extraerMinimo() {
        if (estaVacia()) return null;
        Producto minimo = arreglo[0];
        cantidad--;
        arreglo[0] = arreglo[cantidad];
        arreglo[cantidad] = null;
        bajarNodo(0);
        return minimo;
    }

    public Producto verMinimo() {
        if (estaVacia()) return null;
        return arreglo[0];
    }

    private void subirNodo(int posicion) {
        while (posicion > 0) {
            int posPadre = (posicion - 1) / 2;
            if (arreglo[posPadre].obtenerStock() > arreglo[posicion].obtenerStock()) {
                Producto aux = arreglo[posPadre];
                arreglo[posPadre] = arreglo[posicion];
                arreglo[posicion] = aux;
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
                arreglo[posHijoIzquierdo].obtenerStock() < arreglo[posMenor].obtenerStock()) {
                posMenor = posHijoIzquierdo;
            }
            if (posHijoDerecho < cantidad &&
                arreglo[posHijoDerecho].obtenerStock() < arreglo[posMenor].obtenerStock()) {
                posMenor = posHijoDerecho;
            }

            if (posMenor == posicion) break;

            Producto aux = arreglo[posicion];
            arreglo[posicion] = arreglo[posMenor];
            arreglo[posMenor] = aux;
            posicion = posMenor;
        }
    }

    @Override
    public boolean estaVacia() { return cantidad == 0; }

    @Override
    public int getCantidad() { return cantidad; }
}
