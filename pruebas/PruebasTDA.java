package pruebas;

import model.Producto;
import tda.ArbolCategorias;
import tda.Cola;
import tda.ColaCircular;
import tda.ColaPrioridad;
import tda.Conjunto;
import tda.Diccionario;
import tda.GrafoRutas;
import tda.ListaEnlazada;
import tda.Pila;
import tda.PilaEnlazada;

public class PruebasTDA {

    private static int total = 0;
    private static int aprobadas = 0;

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("     CASOS DE PRUEBA - MASSA LOGISTICS");
        System.out.println("==================================================\n");

        pruebaDiccionario();
        pruebaConjunto();
        pruebaColaPrioridad();
        pruebaCola();
        pruebaPila();
        pruebaPilaEnlazada();
        pruebaColaCircular();
        pruebaListaEnlazada();
        pruebaArbolCategorias();
        pruebaGrafoRutas();

        System.out.println("==================================================");
        System.out.println("RESULTADO FINAL: " + aprobadas + "/" + total + " pruebas aprobadas.");
        System.out.println("==================================================");
    }

    private static void verificar(String funcionalidad, String entrada,
                                  Object esperado, Object obtenido, String observaciones) {
        total++;
        boolean ok = (esperado == null && obtenido == null)
                || (esperado != null && esperado.equals(obtenido));
        if (ok) aprobadas++;
        System.out.println("Funcionalidad evaluada : " + funcionalidad);
        System.out.println("Datos de entrada       : " + entrada);
        System.out.println("Resultado esperado     : " + esperado);
        System.out.println("Resultado obtenido     : " + obtenido);
        System.out.println("Observaciones          : " + (observaciones.isEmpty() ? "-" : observaciones));
        System.out.println("Estado                 : " + (ok ? "[PASS]" : "[FAIL]"));
        System.out.println("--------------------------------------------------");
    }

    private static void titulo(String t) {
        System.out.println("\n##### " + t + " #####");
    }

    private static void pruebaDiccionario() {
        titulo("Diccionario");
        Diccionario<String, Producto> d = new Diccionario<>();
        d.insertar("P001", new Producto("P001", "Laptop", "Computadoras", 10, "A-01"));

        verificar("Diccionario.buscar clave existente", "insertar(P001); buscar(P001)",
                "Laptop", d.buscar("P001").getNombre(), "");
        verificar("Diccionario.buscar clave inexistente", "buscar(P999)",
                null, d.buscar("P999"), "Debe devolver null");
        verificar("Diccionario.contiene", "contiene(P001)",
                true, d.contiene("P001"), "");

        d.insertar("P001", new Producto("P001", "Laptop Dell", "Computadoras", 5, "A-05"));
        verificar("Diccionario clave repetida actualiza valor", "insertar(P001, Laptop Dell)",
                "Laptop Dell", d.buscar("P001").getNombre(), "No duplica, reemplaza");
        verificar("Diccionario.getCantidad con clave repetida", "1 clave unica",
                1, d.getCantidad(), "");

        verificar("Diccionario.eliminar", "eliminar(P001)",
                true, d.eliminar("P001"), "");
        verificar("Diccionario.estaVacia tras eliminar", "estaVacia()",
                true, d.estaVacia(), "");
    }

    private static void pruebaConjunto() {
        titulo("Conjunto");
        Conjunto<String> c = new Conjunto<>();
        verificar("Conjunto.agregar elemento nuevo", "agregar(P001)",
                true, c.agregar("P001"), "");
        verificar("Conjunto.agregar elemento duplicado", "agregar(P001) de nuevo",
                false, c.agregar("P001"), "No admite repetidos");
        verificar("Conjunto.contiene", "contiene(P001)",
                true, c.contiene("P001"), "");
        verificar("Conjunto.getCantidad", "1 elemento",
                1, c.getCantidad(), "");
        verificar("Conjunto.eliminar", "eliminar(P001)",
                true, c.eliminar("P001"), "");
    }

    private static void pruebaColaPrioridad() {
        titulo("ColaPrioridad (min-heap por stock)");
        ColaPrioridad<Producto> cp = new ColaPrioridad<>();
        cp.insertar(new Producto("P001", "Laptop", "Computadoras", 10, "A-01"));
        cp.insertar(new Producto("P002", "iPhone", "Celulares", 3, "B-02"));
        cp.insertar(new Producto("P003", "Arroz", "Alimentos", 50, "C-03"));

        verificar("ColaPrioridad.verMinimo", "stocks: 10, 3, 50",
                "iPhone", cp.verMinimo().getNombre(), "Prioriza el menor stock");
        verificar("ColaPrioridad.extraerMinimo", "extrae el menor",
                "iPhone", cp.extraerMinimo().getNombre(), "");
        verificar("ColaPrioridad nuevo minimo tras extraer", "quedan 10 y 50",
                "Laptop", cp.verMinimo().getNombre(), "Reordena el heap");
        verificar("ColaPrioridad.getCantidad", "2 restantes",
                2, cp.getCantidad(), "");
    }

    private static void pruebaCola() {
        titulo("Cola (FIFO)");
        Cola<String> q = new Cola<>();
        q.encolar("A"); q.encolar("B"); q.encolar("C");
        verificar("Cola.verFrente", "encolar A, B, C",
                "A", q.verFrente(), "");
        verificar("Cola.desencolar (primero en entrar)", "desencolar()",
                "A", q.desencolar(), "Sale el primero en entrar");
        verificar("Cola.desencolar siguiente", "desencolar()",
                "B", q.desencolar(), "");
        verificar("Cola.getCantidad", "queda C",
                1, q.getCantidad(), "");
    }

    private static void pruebaPila() {
        titulo("Pila (LIFO)");
        Pila<String> s = new Pila<>();
        s.apilar("A"); s.apilar("B"); s.apilar("C");
        verificar("Pila.verTope", "apilar A, B, C",
                "C", s.verTope(), "");
        verificar("Pila.desapilar (ultimo en entrar)", "desapilar()",
                "C", s.desapilar(), "Sale el ultimo en entrar");
        verificar("Pila.getCantidad", "quedan A, B",
                2, s.getCantidad(), "");
    }

    private static void pruebaPilaEnlazada() {
        titulo("PilaEnlazada");
        PilaEnlazada<String> pe = new PilaEnlazada<>();
        pe.apilar("X"); pe.apilar("Y");
        verificar("PilaEnlazada.verTope", "apilar X, Y",
                "Y", pe.verTope(), "");
        verificar("PilaEnlazada.desapilar", "desapilar()",
                "Y", pe.desapilar(), "");
        pe.desapilar();
        verificar("PilaEnlazada.estaVacia", "tras desapilar todo",
                true, pe.estaVacia(), "");
    }

    private static void pruebaColaCircular() {
        titulo("ColaCircular");
        ColaCircular<String> cc = new ColaCircular<>(3);
        cc.encolar("c1"); cc.encolar("c2"); cc.encolar("c3");
        verificar("ColaCircular.encolar estando llena", "capacidad 3, agregar 4to",
                false, cc.encolar("c4"), "Rechaza si esta llena");
        verificar("ColaCircular.verFrente", "frente de la cola",
                "c1", cc.verFrente(), "");
        cc.desencolar();
        verificar("ColaCircular reutiliza espacio liberado", "desencolar y encolar c4",
                true, cc.encolar("c4"), "Espacio circular reutilizado");
        verificar("ColaCircular.getCantidad", "c2, c3, c4",
                3, cc.getCantidad(), "");
    }

    private static void pruebaListaEnlazada() {
        titulo("ListaEnlazada");
        ListaEnlazada<String> l = new ListaEnlazada<>();
        l.agregar("a"); l.agregar("b"); l.agregar("c");
        verificar("ListaEnlazada.obtener por indice", "agregar a, b, c; obtener(1)",
                "b", l.obtener(1), "");
        verificar("ListaEnlazada.contiene", "contiene(c)",
                true, l.contiene("c"), "");
        l.eliminar(0);
        verificar("ListaEnlazada.eliminar indice 0", "eliminar(0); obtener(0)",
                "b", l.obtener(0), "Se corre la cabeza");
        verificar("ListaEnlazada.getCantidad", "quedan b, c",
                2, l.getCantidad(), "");
        verificar("ListaEnlazada.obtener fuera de rango", "obtener(99)",
                null, l.obtener(99), "Debe devolver null");
    }

    private static void pruebaArbolCategorias() {
        titulo("ArbolCategorias (binario)");
        ArbolCategorias arbol = new ArbolCategorias("Productos");
        arbol.agregar("Productos", "Electronica");
        arbol.agregar("Productos", "Alimentos");
        arbol.agregar("Electronica", "Celulares");

        verificar("Arbol.agregar hijo a padre existente", "agregar(Electronica, Celulares)",
                true, arbol.contiene("Celulares"), "");
        verificar("Arbol.agregar a padre inexistente", "agregar(Zzz, Nuevo)",
                false, arbol.agregar("Zzz", "Nuevo"), "El padre no existe");
        verificar("Arbol.agregar categoria duplicada", "agregar(Productos, Electronica)",
                false, arbol.agregar("Productos", "Electronica"), "Ya existe");
        verificar("Arbol.contiene inexistente", "contiene(Juguetes)",
                false, arbol.contiene("Juguetes"), "");
        verificar("Arbol.getCantidad", "raiz + 3 categorias",
                4, arbol.getCantidad(), "");
    }

    private static void pruebaGrafoRutas() {
        titulo("GrafoRutas (camino mas corto)");
        GrafoRutas g = new GrafoRutas(5);
        g.agregarZona("Recepcion");
        g.agregarZona("AlmacenA");
        g.agregarZona("AlmacenB");
        g.agregarZona("Expedicion");
        g.agregarRuta("Recepcion", "AlmacenA", 4);
        g.agregarRuta("Recepcion", "AlmacenB", 7);
        g.agregarRuta("AlmacenA", "AlmacenB", 2);
        g.agregarRuta("AlmacenB", "Expedicion", 3);

        verificar("Grafo.existeRuta directa (no hay)", "Recepcion <-> Expedicion",
                false, g.existeRuta("Recepcion", "Expedicion"), "No hay arista directa");
        verificar("Grafo.existeRuta directa (si hay)", "Recepcion <-> AlmacenA",
                true, g.existeRuta("Recepcion", "AlmacenA"), "");
        verificar("Grafo.rutaMasCorta", "Recepcion -> Expedicion",
                9, g.rutaMasCorta("Recepcion", "Expedicion"), "4+2+3 es mejor que 7+3");
        verificar("Grafo.agregarZona duplicada", "agregarZona(Recepcion)",
                false, g.agregarZona("Recepcion"), "");
        verificar("Grafo.rutaMasCorta a zona inexistente", "Recepcion -> Zzz",
                -1, g.rutaMasCorta("Recepcion", "Zzz"), "Debe devolver -1");
        verificar("Grafo.caminoMasCorto", "Recepcion -> Expedicion",
                "Recepcion -> AlmacenA -> AlmacenB -> Expedicion",
                g.caminoMasCorto("Recepcion", "Expedicion"), "Camino reconstruido con el arreglo previo[]");
        verificar("Grafo.caminoMasCorto a zona inexistente", "Recepcion -> Zzz",
                null, g.caminoMasCorto("Recepcion", "Zzz"), "Debe devolver null");
    }
}
