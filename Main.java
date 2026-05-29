import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Massa Logistics ===");

        // Creamos una lista de envíos
        ArrayList<Envio> envios = new ArrayList<>();
        envios.add(new Envio("E001", "Buenos Aires", "Córdoba", 12.5));
        envios.add(new Envio("E002", "Rosario", "Mendoza", 8.0));
        envios.add(new Envio("E003", "La Plata", "Salta", 25.3));

        // Mostramos todos los envíos y el costo de cada uno
        double total = 0;
        for (Envio envio : envios) {
            envio.mostrar();
            total += envio.calcularCosto();
        }

        System.out.printf("%nCosto total de los envíos: $%.2f%n", total);
    }
}

class Envio {
    private String codigo;
    private String origen;
    private String destino;
    private double pesoKg;

    public Envio(String codigo, String origen, String destino, double pesoKg) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.pesoKg = pesoKg;
    }

    // Costo simple: $1500 base + $200 por kilo
    public double calcularCosto() {
        return 1500 + (pesoKg * 200);
    }

    public void mostrar() {
        System.out.printf("Envío %s: %s -> %s (%.1f kg) | Costo: $%.2f%n",
                codigo, origen, destino, pesoKg, calcularCosto());
    }

    public String getCodigo() {
        return codigo;
    }
}
