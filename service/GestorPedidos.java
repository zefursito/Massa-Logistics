package service;

import model.Pedido;
import tda.Cola;
import tda.ListaEnlazada;

public class GestorPedidos {

    private final Cola<Pedido> porAtender = new Cola<>();
    
    private final Cola<Pedido> expressPorAtender = new Cola<>(); 
    private final ListaEnlazada<Pedido> activos = new ListaEnlazada<>();

    
    public void registrarPedido(Pedido p) {
        registrarPedido(p, false); 
    }

    
    public void registrarPedido(Pedido p, boolean esExpress) {
        if (esExpress) {
            expressPorAtender.encolar(p);
        } else {
            porAtender.encolar(p);
        }
        activos.agregar(p);
    }

    public Pedido atenderSiguiente() {
        Pedido p = null;

        
        if (!expressPorAtender.estaVacia()) {
            p = expressPorAtender.desencolar();
        } 
        
        else if (!porAtender.estaVacia()) {
            p = porAtender.desencolar();
        }

        if (p != null) {
            p.cambiarEstado("EN PREPARACION");
        }
        return p;
    }

    public boolean hayPendientes() {
        
        return !expressPorAtender.estaVacia() || !porAtender.estaVacia();
    }

    public int pedidosActivos() {
        return activos.getCantidad();
    }

    public Pedido pedidoActivo(int indice) {
        return activos.obtener(indice);
    }

    public boolean entregarPedido(int indice) {
        Pedido p = activos.obtener(indice);
        if (p == null) return false;
        p.cambiarEstado("ENTREGADO");
        return activos.eliminar(indice);
    }
}