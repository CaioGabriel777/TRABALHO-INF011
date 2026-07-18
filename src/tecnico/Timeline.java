package tecnico;

import java.util.ArrayList;
import java.util.List;

public class Timeline implements RenderableContent {

    private final List<Track> tracks = new ArrayList<>();

    public Timeline adicionarTrack(Track track) {
        tracks.add(track);
        return this;
    }

    public static Timeline padrao(int duracaoMin) {
        return new Timeline()
                .adicionarTrack(new Track("video", duracaoMin))
                .adicionarTrack(new Track("audio", duracaoMin));
    }

    @Override
    public int getDuracaoMin() {
        return tracks.stream().mapToInt(Track::getDuracaoMin).max().orElse(0);
    }

    @Override
    public String renderizar() {
        return "Timeline[" + tracks.size() + " tracks | " + getDuracaoMin() + " min]";
    }
}
