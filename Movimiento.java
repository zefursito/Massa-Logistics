
public class Movimiento implements IMostrable, IMovimiento {
    private String tipo;          // "ENTRADA" o "SALIDA"
    private String fecha;
    private String codigoProducto;
    private int cantidad;

    public Movimiento(String tipo, String fecha, String codigoProducto, int cantidad) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
    }


    public void mostrar() {
        System.out.println("[" + fecha + "] " + tipo + " | Producto: " + codigoProducto + " | Cantidad: " + cantidad);
    }

    public void registrar() {
        System.out.println("[" + fecha + "] " + tipo + " | Producto: " + codigoProducto + " | Cantidad: " + cantidad);
    }

    public void revertir() {
        System.out.println("Revirtiendo: [" + fecha + "] " + tipo + " de " + cantidad + " unidades - Producto: " + codigoProducto);
    }

    public String getTipo() { return tipo; }
    public String getFecha() { return fecha; }
    public String getCodigoProducto() { return codigoProducto; }
    public int getCantidad() { return cantidad; }
}
