package playlist;

import comercial.Episodio;
import comercial.Filme;

/** VISITOR — ConcreteVisitor. "Relatório com o Nome dos Elementos da Playlist". */
public class RelatorioNomes implements PlaylistVisitor {

    private final StringBuilder relatorio = new StringBuilder("--- Relatório da Playlist ---");

    @Override
    public void visitar(Filme filme) { linha("Filme", filme.getTitulo()); }

    @Override
    public void visitar(Episodio episodio) { linha("Episódio", episodio.getTitulo()); }

    @Override
    public void visitar(MP3 mp3) { linha("MP3", mp3.getTitulo()); }

    @Override
    public void visitar(VideoClipe clipe) { linha("VideoClipe", clipe.getTitulo()); }

    private void linha(String tipo, String titulo) {
        relatorio.append(System.lineSeparator())
                 .append("* ").append(tipo).append(": ").append(titulo);
    }

    public String getRelatorio() { return relatorio.toString(); }
}
