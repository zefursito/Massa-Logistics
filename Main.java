public class Main {

    public static void main(String[] args) {

        System.out.println("=== Massa Logistics ===");

        // Productos
        Producto p1 = new Producto("P001", "Laptop HP",  "Computadoras", 10, "A-01");
        Producto p2 = new Producto("P002", "iPhone 15",  "Celulares",     3, "B-02");
        Producto p3 = new Producto("P003", "Arroz 1kg",  "Alimentos",    50, "C-03");

        // --- Diccionario + Conjunto: registro sin duplicados ---
        System.out.println("\n-- Gestion de Productos --");
        Diccionario<String, Producto> catalogo = new Diccionario<>();
        Conjunto<String> codigos = new Conjunto<>();

        registrar(catalogo, codigos, p1);
        registrar(catalogo, codigos, p2);
        registrar(catalogo, codigos, p3);
        registrar(catalogo, codigos, new Producto("P001", "Laptop Dell", "Computadoras", 5, "A-05"));

        System.out.println("Buscar P002:");
        catalogo.buscar("P002").mostrar();

        p1.actualizarUbicacion("A-99");
        p2.reducirStock(1);

        // --- Cola con Prioridad: stock critico ---
        System.out.println("\n-- Stock Critico --");
        ColaPrioridad stockCritico = new ColaPrioridad();
        stockCritico.insertar(p1);
        stockCritico.insertar(p2);
        stockCritico.insertar(p3);
        stockCritico.verMinimo().mostrar();

        // --- Cola FIFO: pedidos por orden de llegada ---
        System.out.println("\n-- Cola de Pedidos --");
        Cola<Pedido> colaPedidos = new Cola<>();

        Pedido ped1 = new Pedido(1, "Juan Perez");
        ped1.agregarProducto(p1);
        ped1.agregarProducto(p2);

        Pedido ped2 = new Pedido(2, "Maria Lopez");
        ped2.agregarProducto(p3);

        colaPedidos.encolar(ped1);
        colaPedidos.encolar(ped2);

        while (!colaPedidos.estaVacia()) {
            colaPedidos.desencolar().mostrar();
        }

        // --- Pila: historial de movimientos ---
        System.out.println("\n-- Historial de Movimientos --");
        Pila<Movimiento> historial = new Pila<>();

        Movimiento m1 = new Movimiento("ENTRADA", "2025-05-29", "P001", 5);
        Movimiento m2 = new Movimiento("SALIDA",  "2025-05-30", "P002", 2);
        historial.apilar(m1);
        historial.apilar(m2);
        historial.verTope().mostrar();

        // --- Pila Enlazada: reversion de operaciones ---
        System.out.println("\n-- Reversion de Operaciones --");
        PilaEnlazada<Movimiento> operaciones = new PilaEnlazada<>();
        operaciones.apilar(m1);
        operaciones.apilar(m2);

        Movimiento aRevertir = operaciones.desapilar();
        if (aRevertir != null) aRevertir.revertir();
    }

    private static void registrar(Diccionario<String, Producto> catalogo,
                                   Conjunto<String> codigos, Producto p) {
        if (!codigos.agregar(p.getCodigo())) {
            System.out.println("Codigo duplicado: " + p.getCodigo() + " — ignorado.");
            return;
        }
        catalogo.insertar(p.getCodigo(), p);
        System.out.println("Registrado: " + p.getCodigo() + " - " + p.getNombre());
    }
}
