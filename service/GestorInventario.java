package service;

import model.Producto;
import tda.ColaPrioridad;
import tda.Conjunto;
import tda.Diccionario;

public class GestorInventario {

    private final Diccionario<String, Producto> catalogo = new Diccionario<>();
    private final Conjunto<String> codigosUsados = new Conjunto<>();
    private final ColaPrioridad<Producto> stockCritico = new ColaPrioridad<>();

    public boolean registrarProducto(Producto p) {
        if (!codigosUsados.agregar(p.getCodigo())) return false;
        catalogo.insertar(p.getCodigo(), p);
        stockCritico.insertar(p);
        return true;
    }

    public Producto buscarPorCodigo(String codigo) {
        return catalogo.buscar(codigo);
    }

    public boolean existeCodigo(String codigo) {
        return codigosUsados.contiene(codigo);
    }

    public int cantidadProductos() {
        return catalogo.getCantidad();
    }

    public Producto stockMasCritico() {
        return stockCritico.verMinimo();
    }
}
