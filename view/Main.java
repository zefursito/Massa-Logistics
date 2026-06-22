package view;

import model.Camion;
import model.Movimiento;
import model.Pedido;
import model.Producto;
import service.GestorInventario;
import service.GestorMovimientos;
import service.GestorPedidos;
import service.GestorReparto;
import tda.ArbolCategorias;
import tda.GrafoRutas;

public class Main {

    public static void main(String[] args) {

        System.out.println("############################################################");
        System.out.println("#                     MASSA LOGISTICS                      #");
        System.out.println("#              Demostracion de TDAs del sistema            #");
        System.out.println("############################################################");

        Producto p1 = new Producto("P001", "Laptop HP",    "Computadoras", 10, "A-01");
        Producto p2 = new Producto("P002", "iPhone 15",    "Celulares",     3, "B-02");
        Producto p3 = new Producto("P003", "Arroz 1kg",    "Alimentos",    50, "C-03");
        Producto p4 = new Producto("P004", "Smart TV 55",  "Electronica",   8, "D-04");
        Producto p5 = new Producto("P005", "Auriculares",  "Electronica",  15, "D-05");
        Producto p6 = new Producto("P006", "Leche 1L",     "Lacteos",      30, "E-06");

        titulo(1, "Gestion de Productos", "Diccionario + Conjunto + ColaPrioridad");
        GestorInventario inventario = new GestorInventario();
        registrar(inventario, p1);
        registrar(inventario, p2);
        registrar(inventario, p3);
        registrar(inventario, p4);
        registrar(inventario, p5);
        registrar(inventario, p6);
        registrar(inventario, new Producto("P001", "Laptop Dell", "Computadoras", 5, "A-05"));

        System.out.println("\n  Total de productos en catalogo: " + inventario.cantidadProductos());
        System.out.println("  Busqueda por codigo (P002):");
        System.out.print("  -> ");
        inventario.buscarPorCodigo("P002").mostrar();

        titulo(2, "Stock Critico", "Cola con Prioridad");
        System.out.println("  Producto con menor stock:");
        System.out.print("  -> ");
        inventario.stockMasCritico().mostrar();

        titulo(3, "Pedidos por orden de llegada", "Cola FIFO + Lista Enlazada");
        GestorPedidos pedidos = new GestorPedidos();

        Pedido ped1 = new Pedido(1, "Juan Perez");
        ped1.agregarProducto(p1);
        ped1.agregarProducto(p2);

        Pedido ped2 = new Pedido(2, "Maria Lopez");
        ped2.agregarProducto(p3);

        pedidos.registrarPedido(ped1);
        pedidos.registrarPedido(ped2);

        System.out.println("  Pedidos activos: " + pedidos.pedidosActivos());
        System.out.println("  Atendiendo pedidos en orden de llegada:");
        while (pedidos.hayPendientes()) {
            pedidos.atenderSiguiente().mostrar();
        }

        pedidos.entregarPedido(0);
        System.out.println("\n  Tras entregar el primer pedido, quedan activos: " + pedidos.pedidosActivos());

        titulo(4, "Historial y reversion de movimientos", "Pila + Pila Enlazada");
        GestorMovimientos movimientos = new GestorMovimientos();
        Movimiento m1 = new Movimiento("ENTRADA", "2025-05-29", "P001", 5);
        Movimiento m2 = new Movimiento("SALIDA",  "2025-05-30", "P002", 2);
        movimientos.registrar(m1);
        movimientos.registrar(m2);

        System.out.println("\n  Ultimo movimiento registrado:");
        System.out.print("  -> ");
        movimientos.ultimoMovimiento().mostrar();

        Movimiento aRevertir = movimientos.revertirUltima();
        if (aRevertir != null) {
            System.out.print("  -> ");
            aRevertir.revertir();
        }

        titulo(5, "Camiones disponibles para reparto", "Cola Circular");
        GestorReparto reparto = new GestorReparto(3);
        reparto.registrarCamion(new Camion("AB123CD", "Carlos Gomez", 1500));
        reparto.registrarCamion(new Camion("EF456GH", "Ana Diaz",     2000));
        reparto.registrarCamion(new Camion("IJ789KL", "Luis Romero",  1200));

        System.out.println("  Camiones en cola: " + reparto.camionesDisponibles());
        System.out.println("  Proximo a salir:");
        System.out.print("  -> ");
        reparto.proximoEnSalir().mostrar();

        Camion camionAsignado = reparto.asignarReparto();
        System.out.println("\n  Camion asignado a un reparto:");
        System.out.print("  -> ");
        camionAsignado.mostrar();

        reparto.registrarCamion(new Camion("MN012OP", "Sofia Vega", 1800));
        System.out.println("\n  Camiones en cola tras reasignar: " + reparto.camionesDisponibles());

        titulo(6, "Categorias del deposito", "Arbol binario");
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

        titulo(7, "Rutas entre zonas del deposito", "Grafo ponderado no dirigido");
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

    private static void registrar(GestorInventario inventario, Producto p) {
        if (inventario.registrarProducto(p)) {
            System.out.println("  [OK] Registrado: " + p.getCodigo() + " - " + p.getNombre());
        } else {
            System.out.println("  [X] Codigo duplicado: " + p.getCodigo() + " - ignorado.");
        }
    }
}
