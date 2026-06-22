package tda;

import interfaces.IColeccion;

public class GrafoRutas implements IColeccion {

    private static final int SIN_RUTA = 0;
    private static final int INFINITO = 1000000000;

    private final int maxZonas;
    private final String[] zonas;
    private final int[][] pesos;
    private int cantidad;

    public GrafoRutas(int maxZonas) {
        this.maxZonas = maxZonas;
        this.zonas = new String[maxZonas];
        this.pesos = new int[maxZonas][maxZonas];
        this.cantidad = 0;
    }

    public boolean agregarZona(String nombre) {
        if (cantidad >= maxZonas) return false;
        if (indiceDe(nombre) != -1) return false;
        zonas[cantidad] = nombre;
        cantidad++;
        return true;
    }

    public boolean agregarRuta(String zonaA, String zonaB, int peso) {
        int ia = indiceDe(zonaA);
        int ib = indiceDe(zonaB);
        if (ia == -1 || ib == -1 || ia == ib || peso <= 0) return false;
        pesos[ia][ib] = peso;
        pesos[ib][ia] = peso;
        return true;
    }

    public boolean existeRuta(String zonaA, String zonaB) {
        int ia = indiceDe(zonaA);
        int ib = indiceDe(zonaB);
        if (ia == -1 || ib == -1) return false;
        return pesos[ia][ib] != SIN_RUTA;
    }

    public int pesoRuta(String zonaA, String zonaB) {
        int ia = indiceDe(zonaA);
        int ib = indiceDe(zonaB);
        if (ia == -1 || ib == -1) return -1;
        return pesos[ia][ib];
    }

    public int rutaMasCorta(String origen, String destino) {
        int inicio = indiceDe(origen);
        int fin = indiceDe(destino);
        if (inicio == -1 || fin == -1) return -1;

        int[] distancia = new int[cantidad];
        boolean[] visitado = new boolean[cantidad];
        for (int i = 0; i < cantidad; i++) distancia[i] = INFINITO;
        distancia[inicio] = 0;

        for (int paso = 0; paso < cantidad; paso++) {
            int actual = -1;
            int menor = INFINITO;
            for (int i = 0; i < cantidad; i++) {
                if (!visitado[i] && distancia[i] < menor) {
                    menor = distancia[i];
                    actual = i;
                }
            }
            if (actual == -1) break;
            visitado[actual] = true;

            for (int i = 0; i < cantidad; i++) {
                if (pesos[actual][i] != SIN_RUTA && !visitado[i]) {
                    int nueva = distancia[actual] + pesos[actual][i];
                    if (nueva < distancia[i]) distancia[i] = nueva;
                }
            }
        }

        return distancia[fin] == INFINITO ? -1 : distancia[fin];
    }

    public void mostrar() {
        System.out.println("Rutas del deposito (zona <-> zona : peso):");
        for (int i = 0; i < cantidad; i++) {
            for (int j = i + 1; j < cantidad; j++) {
                if (pesos[i][j] != SIN_RUTA) {
                    System.out.println("   " + zonas[i] + " <-> " + zonas[j] + " : " + pesos[i][j]);
                }
            }
        }
    }

    private int indiceDe(String zona) {
        for (int i = 0; i < cantidad; i++) {
            if (zonas[i].equals(zona)) return i;
        }
        return -1;
    }

    @Override
    public boolean estaVacia() { return cantidad == 0; }

    @Override
    public int getCantidad() { return cantidad; }
}
