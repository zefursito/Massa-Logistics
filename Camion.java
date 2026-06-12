public class Camion implements IMostrable {

    private final String patente;
    private final String conductor;
    private final int capacidadKg;
    private String estado;

    public Camion(String patente, String conductor, int capacidadKg) {
        this.patente = patente;
        this.conductor = conductor;
        this.capacidadKg = capacidadKg;
        this.estado = "DISPONIBLE";
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public String getPatente() { return patente; }
    public String getConductor() { return conductor; }
    public int getCapacidadKg() { return capacidadKg; }
    public String getEstado() { return estado; }

    @Override
    public void mostrar() {
        System.out.println("Camion " + patente + " | Conductor: " + conductor +
                " | Capacidad: " + capacidadKg + "kg | Estado: " + estado);
    }
}
