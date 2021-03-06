package br.capitolio.tools.cdi;

import br.capitolio.tools.cdi.context.DefaultContext;
import br.capitolio.tools.cdi.context.InjectionContext;
import br.capitolio.tools.reflection.Reflections;

import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Injector {
    private static final Queue<InjectionContext> contexts = new ConcurrentLinkedQueue<>();

    private Injector(){}

    static {
        contexts.add(new DefaultContext());
    }

    public static void register(Class<? extends InjectionContext> context) {
        final var instance = Reflections.Classes.newInstance(context);
        contexts.add(instance);
    }

    public static void register(InjectionContext context) {
        if (context instanceof DefaultContext) {
            throw new CDIException("Default Context already registered");
        }

        contexts.add(context);
    }

    public static Set<String> getModules() {
        final var modules = new TreeSet<String>();
        contexts.forEach(context -> modules.addAll(context.getModules()));

        return modules;
    }

    /**
     * Lookup a provider of the given class through the registered {@link InjectionContext}s.
     * <p>
     *  The search respect the {@link Injector#register(InjectionContext)} order.
     * </p>
     *
     * @throws CDIException if the target klass isn't known by any {@link InjectionContext}.
     */
    public static <T> T inject(Class<T> klass) {
        for (var context : contexts) {
            final var target = context.get(klass);
            if (target != null) {
                return target;
            }
        }

        throw new CDIException("Target [%s] not found".formatted(klass.getCanonicalName()));
    }
}
