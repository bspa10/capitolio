package br.capitolio.minecraft;

import br.capitolio.engine.GameLogic;
import br.capitolio.framework.cdi.annotation.Module;
import br.capitolio.framework.cdi.annotation.Provider;

@Module
public class GameModule {

    @Provider(overridable = false)
    public GameLogic gameLogic() {
        return new Game();
    }

}
