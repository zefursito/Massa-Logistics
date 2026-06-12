public class Main {

    public static void main(String[] args) {

        System.out.println("############################################################");
        System.out.println("#                     MASSA LOGISTICS                      #");
        System.out.println("#              Demostracion de TDAs del sistema            #");
        System.out.println("############################################################");

        Producto p1 = new Producto("P001", "Laptop HP",    "Computadoras", 10, "A-01");
        Producto p2 = new Producto("P002", "iPhone 15",   "Celulares",     3, "B-02");
        Producto p3 = new Producto("P003", "Arroz 1kg",   "Alimentos",    50, "C-03");
        Producto p4 = new Producto("P004", "Smart TV 55", "Electronica",   8, "D-04");
        Producto p5 = new Producto("P005", "Auriculares", "Electronica",  15, "D-05");
        Producto p6 = new Producto("P006", "Leche 1L",    "Lacteos",      30, "E-06");

        titulo(1, "Gestion de Productos", "Diccionario + Conjunto");
        Diccionario<String, Producto> catalogo = new Diccionario<>();
        Conjunto<String> codigos = new Conjunto<>();

        registrar(catalogo, codigos, p1);
        registrar(catalogo, codigos, p2);
        registrar(catalogo, codigos, p3);
        registrar(catalogo, codigos, p4);
        registrar(catalogo, codigos, p5);
        registrar(catalogo, codigos, p6);
        registrar(catalogo, codigos, new Producto("P001", "Laptop Dell", "Computadoras", 5, "A-05"));

        System.out.println("\n  Busqueda por codigo (P002):");
        System.out.print("  -> ");
        catalogo.buscar("P002").mostrar();

        p1.actualizarUbicacion("A-99");
        p2.reducirStock(1);

        titulo(2, "Stock Critico", "Cola con Prioridad");
        ColaPrioridad stockCritico = new ColaPrioridad();
        stockCritico.insertar(p1);
        stockCritico.insertar(p2);
        stockCritico.insertar(p3);

        System.out.println("  Producto con menor stock:");
        System.out.print("  -> ");
        stockCritico.verMinimo().mostrar();

        titulo(3, "Pedidos por orden de llegada", "Cola FIFO");
        Cola<Pedido> colaPedidos = new Cola<>();

        Pedido ped1 = new Pedido(1, "Juan Perez");
        ped1.agregarProducto(p1);
        ped1.agregarProducto(p2);

        Pedido ped2 = new Pedido(2, "Maria Lopez");
        ped2.agregarProducto(p3);

        colaPedidos.encolar(ped1);
        colaPedidos.encolar(ped2);

        System.out.println("  Atendiendo pedidos en orden de llegada:");
        while (!colaPedidos.estaVacia()) {
            colaPedidos.desencolar().mostrar();
        }

        titulo(4, "Historial de Movimientos", "Pila");
        Pila<Movimiento> historial = new Pila<>();

        Movimiento m1 = new Movimiento("ENTRADA", "2025-05-29", "P001", 5);
        Movimiento m2 = new Movimiento("SALIDA",  "2025-05-30", "P002", 2);
        historial.apilar(m1);
        historial.apilar(m2);

        System.out.println("  Ultimo movimiento registrado:");
        System.out.print("  -> ");
        historial.verTope().mostrar();

        titulo(5, "Reversion de Operaciones", "Pila Enlazada");
        PilaEnlazada<Movimiento> operaciones = new PilaEnlazada<>();
        operaciones.apilar(m1);
        operaciones.apilar(m2);

        Movimiento aRevertir = operaciones.desapilar();
        if (aRevertir != null) {
            System.out.print("  -> ");
            aRevertir.revertir();
        }

        titulo(6, "Camiones disponibles para reparto", "Cola Circular");
        ColaCircular<Camion> camiones = new ColaCircular<>(3);

        camiones.encolar(new Camion("AB123CD", "Carlos Gomez", 1500));
        camiones.encolar(new Camion("EF456GH", "Ana Diaz",     2000));
        camiones.encolar(new Camion("IJ789KL", "Luis Romero",  1200));

        System.out.println("  Camiones en cola: " + camiones.getCantidad());
        System.out.println("  Proximo a salir:");
        System.out.print("  -> ");
        camiones.verFrente().mostrar();

        Camion camionAsignado = camiones.desencolar();
        camionAsignado.cambiarEstado("EN RUTA");
        System.out.println("\n  Camion asignado a un reparto:");
        System.out.print("  -> ");
        camionAsignado.mostrar();

        camiones.encolar(new Camion("MN012OP", "Sofia Vega", 1800));
        System.out.println("\n  Camiones en cola tras reasignar: " + camiones.getCantidad());

        titulo(7, "Pedidos registrados", "Lista Enlazada");
        ListaEnlazada<Pedido> listaPedidos = new ListaEnlazada<>();
        listaPedidos.agregar(ped1);
        listaPedidos.agregar(ped2);

        System.out.println("  Total de pedidos en la lista: " + listaPedidos.getCantidad());
        for (int i = 0; i < listaPedidos.getCantidad(); i++) {
            listaPedidos.obtener(i).mostrar();
        }

        listaPedidos.eliminar(0);
        System.out.println("\n  Tras entregar el primer pedido, quedan: " + listaPedidos.getCantidad());

        titulo(8, "Categorias del deposito", "Arbol Binario");
        ArbolCategorias categorias = new ArbolCategorias("Productos");
        categorias.agregar("Productos",   "Electronica");
        categorias.agregar("Productos",   "Alimentos");
        categorias.agregar("Productos",   "Bebidas");
        categorias.agregar("Electronica", "Computadoras");
        categorias.agregar("Electronica", "Celulares");
        categorias.agregar("Alimentos",   "Lacteos");
        categorias.agregar("Alimentos",   "Secos");
        categorias.agregar("Bebidas",     "Alcoholicas");
        categorias.agregar("Bebidas",     "Sin alcohol");

        categorias.mostrar();
        System.out.println("\n  Total de categorias: " + categorias.getCantidad());
        System.out.println("  Contiene 'Celulares'? " + categorias.contiene("Celulares"));
        System.out.println("  Contiene 'Juguetes'?  " + categorias.contiene("Juguetes"));

        titulo(9, "Rutas entre zonas del deposito", "Grafo ponderado no dirigido");
        GrafoRutas rutas = new GrafoRutas(5);
        rutas.agregarZona("Recepcion");
        rutas.agregarZona("Almacen-A");
        rutas.agregarZona("Almacen-B");
        rutas.agregarZona("Expedicion");

        rutas.agregarRuta("Recepcion", "Almacen-A", 4);
        rutas.agregarRuta("Recepcion", "Almacen-B", 7);
        rutas.agregarRuta("Almacen-A", "Almacen-B", 2);
        rutas.agregarRuta("Almacen-B", "Expedicion", 3);

        rutas.mostrar();
        System.out.println("\n  Ruta directa Recepcion <-> Expedicion? "
                + rutas.existeRuta("Recepcion", "Expedicion"));
        System.out.println("  Peso minimo Recepcion -> Expedicion:   "
                + rutas.rutaMasCorta("Recepcion", "Expedicion"));


    }

    private static void titulo(int numero, String nombre, String estructura) {
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println("  " + numero + ") " + nombre);
        System.out.println("     TDA: " + estructura);
        System.out.println("------------------------------------------------------------");
    }

    private static void registrar(Diccionario<String, Producto> catalogo,
                                   Conjunto<String> codigos, Producto p) {
        if (!codigos.agregar(p.getCodigo())) {
            System.out.println("  [X] Codigo duplicado: " + p.getCodigo() + " - ignorado.");
            return;
        }
        catalogo.insertar(p.getCodigo(), p);
        System.out.println("  [OK] Registrado: " + p.getCodigo() + " - " + p.getNombre());
    }
}
