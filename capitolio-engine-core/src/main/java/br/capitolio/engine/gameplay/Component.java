package br.capitolio.engine.gameplay;

import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;

public abstract class Component {
    private final Logger LOGGER;
    protected final GameObject parent;

    public Component(GameObject parent) {
        this.parent = parent;
        LOGGER = LoggerFactory.getLogger(getClass());
    }

    protected abstract void doOnAdded();
    public final void onAdded() {
        LOGGER.debug("Componente [%s] added to the Game Object", getClass().getTypeName());
        doOnAdded();
    }

    protected abstract void doOnRemove();
    public final void onRemoved() {
        LOGGER.debug("Componente [%s] Removed to the Game Object", getClass().getTypeName());
        doOnRemove();
    }

    protected abstract void doOnUpdate();
    public final void onUpdate() {
        doOnUpdate();
    }

}
