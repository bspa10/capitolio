package br.capitolio.framework.cdi.context;

import br.capitolio.framework.cdi.CDIException;
import br.capitolio.framework.cdi.annotation.Module;
import br.capitolio.framework.cdi.annotation.Provider;
import br.capitolio.tools.reflection.Reflections;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class DefaultContext implements InjectionContext {
    private static final ConcurrentMap<String, Object> modules = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, Method> providers = new ConcurrentHashMap<>();

    private static void reset() {
        modules.clear();
        providers.clear();
    }

    public static void load(Class<?> ... modules) {
        Arrays
                .stream(modules)
                .forEach(DefaultContext::load);
    }

    public static void load(Class<?> module) {
        if (module == null) {
            throw new CDIException("", "null module");
        }

        load(Reflections.Classes.newInstance(module));
    }

    public static void load(Object module) {
        if (module == null) {
            throw new CDIException("", "null module");
        }

        if (!Reflections.Classes.hasAnnotation(module, Module.class)) {
            throw new CDIException("", "@Module annotation not present in [%s]".formatted(module.getClass().getCanonicalName()));
        }

        final var found = Reflections.Classes.getMethods(module, Provider.class);
        if (found.isEmpty()) {
            throw new CDIException("", "No @Provider method found in [%s] module".formatted(module.getClass().getCanonicalName()));
        }

        for (var method : found) {
            final var type = Reflections.Methods.getReturnType(method);
            final var key = type.getCanonicalName();
            modules.putIfAbsent(key, module);

            if (!providers.containsKey(key)) {
                providers.putIfAbsent(key, method);
                continue;
            }

            final var previews = providers.get(key);
            final var annotation = previews.getAnnotation(Provider.class);
            if (!annotation.overridable()) {
                throw new CDIException("", "Override forbidden for [%s]".formatted(key));
            }

            modules.replace(key, module);
            providers.replace(key, previews, method);
        }
    }

    @Override
    public <T> T get(Class<T> klass) {
        if (!modules.containsKey(klass.getCanonicalName())){
            return null;
        }

        final var module = modules.get(klass.getCanonicalName());
        final var factory = providers.get(klass.getCanonicalName());

        return (T) Reflections.Methods.invoke(module, factory);
    }

    public Set<String> getModules() {
        final var names = new TreeSet<String>();
        modules.values().forEach(k -> names.add(k.getClass().getCanonicalName()));
        return names;
    }

    public Set<String> providers() {
        return providers.keySet();
    }
}
