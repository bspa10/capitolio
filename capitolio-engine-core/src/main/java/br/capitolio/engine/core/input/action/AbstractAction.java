package br.capitolio.engine.core.input.action;

import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;
import br.capitolio.engine.core.scene.Scene;

public abstract class AbstractAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAction.class);
    protected Scene scene;

    public final void setScene(Scene scene) {
        this.scene = scene;
    }

    protected abstract void doProcess();
    public final void process() {
        LOGGER.trace("Executing [%s]", getClass().getCanonicalName());
        doProcess();
        setScene(null);
    }

}
