package tecnico;

import java.util.ArrayList;
import java.util.List;

/**
 * Núcleo do Domínio Técnico: sabe COMO a mídia funciona (tracks, duração,
 * renderização), mas ignora completamente preços, catálogos e promoções.
 */
public class Timeline implements RenderableContent {

    private final List<Track> tracks = new ArrayList<>();

    public Timeline adicionarTrack(Track track) {
        tracks.add(track);
        return this;
    }

    /** Fábrica de conveniência: timeline típica com trilhas de vídeo e áudio. */
    public static Timeline padrao(int duracaoMin) {
        return new Timeline()
                .adicionarTrack(new Track("video", duracaoMin))
                .adicionarTrack(new Track("audio", duracaoMin));
    }

    /** Duração = maior trilha (as tracks tocam em paralelo). */
    @Override
    public int getDuracaoMin() {
        return tracks.stream().mapToInt(Track::getDuracaoMin).max().orElse(0);
    }

    @Override
    public String renderizar() {
        return "Timeline[" + tracks.size() + " tracks | " + getDuracaoMin() + " min]";
    }
}
