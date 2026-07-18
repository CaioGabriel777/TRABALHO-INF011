package tecnico;

public class Track {

    private final String tipo;
    private final int duracaoMin;

    public Track(String tipo, int duracaoMin) {
        this.tipo = tipo;
        this.duracaoMin = duracaoMin;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDuracaoMin() {
        return duracaoMin;
    }
}
