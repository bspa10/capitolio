package br.capitolio.minecraft;

import br.capitolio.engine.core.scene.Scene;
import br.capitolio.framework.cdi.annotation.Module;
import br.capitolio.framework.cdi.annotation.Provider;
import br.capitolio.minecraft.scene.WorldScene;

@Module
public class GameModule {

    @Provider(overridable = false)
    public Scene gameLogic() {
        return new WorldScene();
    }

}
