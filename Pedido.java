public class Pedido implements IMostrable {

    private class Nodo {
        Producto dato;
        Nodo siguiente;

        Nodo(Producto dato) {
            this.dato = dato;
        }
    }

    private final int id;
    private final String cliente;
    private String estado;
    private Nodo cabeza;
    private int cantidadProductos;

    public Pedido(int id, String cliente) {
        this.id = id;
        this.cliente = cliente;
        this.estado = "PENDIENTE";
        this.cabeza = null;
        this.cantidadProductos = 0;
    }

    public void agregarProducto(Producto p) {
        Nodo nuevo = new Nodo(p);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) actual = actual.siguiente;
            actual.siguiente = nuevo;
        }
        cantidadProductos++;
    }

    public boolean eliminarProducto(Producto p) {
        if (cabeza == null) return false;
        if (cabeza.dato == p) {
            cabeza = cabeza.siguiente;
            cantidadProductos--;
            return true;
        }
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato == p) {
                actual.siguiente = actual.siguiente.siguiente;
                cantidadProductos--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public Producto obtenerProducto(int indice) {
        if (indice < 0 || indice >= cantidadProductos) return null;
        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) actual = actual.siguiente;
        return actual.dato;
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void mostrar() {
        System.out.println("Pedido #" + id + " | Cliente: " + cliente + " | Estado: " + estado);
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println("   - " + actual.dato.getNombre());
            actual = actual.siguiente;
        }
    }

    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public String getEstado() { return estado; }
    public int getCantidadProductos() { return cantidadProductos; }
}
