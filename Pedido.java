import java.util.ArrayList;

public class Pedido implements IMostrable {
    private int id;
    private String cliente;
    private String estado;
    private ArrayList<Producto> productos;

    public Pedido(int id, String cliente) {
        this.id = id;
        this.cliente = cliente;
        this.estado = "PENDIENTE";
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void mostrar() {
        System.out.println("Pedido #" + id + " | Cliente: " + cliente + " | Estado: " + estado);
        for (Producto p : productos) {
            System.out.println("   - " + p.getNombre() + " (x" + "1)");
        }
    }

    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public String getEstado() { return estado; }
    public ArrayList<Producto> getProductos() { return productos; }
}
