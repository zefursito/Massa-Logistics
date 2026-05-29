public class Main {

    public static void main(String[] args) {

        System.out.println("=== Massa Logistics ===");

        
        Producto p1 = new Producto("P001", "Laptop HP", "Computadoras", 10, "A-01");
        Producto p2 = new Producto("P002", "iPhone 15", "Celulares", 3, "B-02");
        Producto p3 = new Producto("P003", "Arroz 1kg", "Alimentos", 50, "C-03");

        System.out.println("\n-- Productos --");
        p1.mostrar();
        p2.mostrar();
        p3.mostrar();

        
        Pedido pedido = new Pedido(1, "Juan Perez");
        pedido.agregarProducto(p1);
        pedido.agregarProducto(p2);

        System.out.println("\n-- Pedido --");
        pedido.mostrar();

        
        Movimiento m = new Movimiento("ENTRADA", "2025-05-29", "P001", 5);

        System.out.println("\n-- Movimiento --");
        m.mostrar();
    }
}
