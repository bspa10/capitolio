package br.capitolio.engine.system;

import br.capitolio.framework.cdi.context.DefaultContext;

public final class Capitolio {
    private static final Capitolio INTANCE = new Capitolio();

    public static Capitolio getInstance() {
        return INTANCE;
    }

    public void load(Class<? extends CapitolioBinding> binding) {
        DefaultContext.load(binding);
    }
}
