public class Producto {
    private String codigo;
    private String nombre;
    private String categoria;
    private int stock;
    private String ubicacion;

    public Producto(String codigo, String nombre, String categoria, int stock, String ubicacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.ubicacion = ubicacion;
    }

    public void agregarStock(int cantidad) {
        this.stock += cantidad;
    }

    public boolean reducirStock(int cantidad) {
        if (this.stock >= cantidad) {
            this.stock -= cantidad;
            return true;
        }
        return false;
    }

    public void actualizarUbicacion(String nuevaUbicacion) {
        this.ubicacion = nuevaUbicacion;
    }

    public int obtenerStock() { return stock; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public String getUbicacion() { return ubicacion; }

    public void mostrar() {
        System.out.println("Codigo: " + codigo + " | Nombre: " + nombre +
                " | Categoria: " + categoria + " | Stock: " + stock + " | Ubicacion: " + ubicacion);
    }
}
