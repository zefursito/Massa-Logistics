package service;

import model.Movimiento;
import tda.Pila;
import tda.PilaEnlazada;

public class GestorMovimientos {

    private final Pila<Movimiento> historial = new Pila<>();
    private final PilaEnlazada<Movimiento> operaciones = new PilaEnlazada<>();

    public void registrar(Movimiento m) {
        historial.apilar(m);
        operaciones.apilar(m);
        m.registrar();
    }

    public Movimiento ultimoMovimiento() {
        return historial.verTope();
    }

    public Movimiento revertirUltima() {
        return operaciones.desapilar();
    }

    public int cantidadMovimientos() {
        return historial.getCantidad();
    }
}
