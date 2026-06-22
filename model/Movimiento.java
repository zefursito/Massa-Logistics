package model;

import interfaces.IMostrable;
import interfaces.IMovimiento;

public class Movimiento implements IMostrable, IMovimiento {
    private String tipo;
    private String fecha;
    private String codigoProducto;
    private int cantidad;

    public Movimiento(String tipo, String fecha, String codigoProducto, int cantidad) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
    }

    private String detalle() {
        return "[" + fecha + "] " + tipo + " | Producto: " + codigoProducto + " | Cantidad: " + cantidad;
    }

    @Override
    public void mostrar() {
        System.out.println(detalle());
    }

    @Override
    public void registrar() {
        System.out.println("Movimiento registrado -> " + detalle());
    }

    @Override
    public void revertir() {
        System.out.println("Revirtiendo: [" + fecha + "] " + tipo + " de " + cantidad +
                " unidades - Producto: " + codigoProducto);
    }

    public String getTipo() { return tipo; }
    public String getFecha() { return fecha; }
    public String getCodigoProducto() { return codigoProducto; }
    public int getCantidad() { return cantidad; }
}
