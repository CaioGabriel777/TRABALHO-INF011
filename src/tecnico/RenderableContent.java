package tecnico;

/**
 * Domínio Técnico (herdado das fases anteriores do projeto).
 * Contrato do "motor técnico": tudo que pode ser renderizado pelo player.
 * Não conhece NADA de negócio (preço, catálogo, promoção).
 */
public interface RenderableContent {
    int getDuracaoMin();
    String renderizar();
}
