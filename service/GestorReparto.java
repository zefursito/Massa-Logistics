package service;

import model.Camion;
import tda.ColaCircular;

public class GestorReparto {

    private final ColaCircular<Camion> disponibles;

    public GestorReparto(int capacidad) {
        disponibles = new ColaCircular<>(capacidad);
    }

    public boolean registrarCamion(Camion c) {
        return disponibles.encolar(c);
    }

    public Camion proximoEnSalir() {
        return disponibles.verFrente();
    }

    public Camion asignarReparto() {
        Camion c = disponibles.desencolar();
        if (c != null) c.cambiarEstado("EN RUTA");
        return c;
    }

    public int camionesDisponibles() {
        return disponibles.getCantidad();
    }
}
