package playlist;

/** VISITOR — ConcreteElement. Videoclipe curto carregado pelo cliente. */
public class VideoClipe implements ItemPlaylist {

    private final String titulo;
    private final double tamanhoMB;
    private final int duracaoMin;

    public VideoClipe(String titulo, double tamanhoMB, int duracaoMin) {
        this.titulo = titulo;
        this.tamanhoMB = tamanhoMB;
        this.duracaoMin = duracaoMin;
    }

    public String getTitulo() { return titulo; }
    public double getTamanhoMB() { return tamanhoMB; }
    public int getDuracaoMin() { return duracaoMin; }

    @Override
    public void aceitar(PlaylistVisitor visitante) {
        visitante.visitar(this);
    }
}
