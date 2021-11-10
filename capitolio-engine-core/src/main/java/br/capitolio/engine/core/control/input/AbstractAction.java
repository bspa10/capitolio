package br.capitolio.engine.core.control.input;

import br.capitolio.engine.core.scene.Scene;

public abstract class AbstractAction {
    protected Scene scene;

    public final void setScene(Scene scene) {
        this.scene = scene;
    }

    public abstract void process();

}
