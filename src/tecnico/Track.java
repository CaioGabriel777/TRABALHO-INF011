package tecnico;

/** Trilha física da mídia (vídeo, áudio, legenda...). Domínio técnico puro. */
public class Track {

    private final String tipo;
    private final int duracaoMin;

    public Track(String tipo, int duracaoMin) {
        this.tipo = tipo;
        this.duracaoMin = duracaoMin;
    }

    public String getTipo() { return tipo; }
    public int getDuracaoMin() { return duracaoMin; }
}
