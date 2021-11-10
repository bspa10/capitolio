package br.capitolio.tools.cdi.context;

import java.util.Set;

public interface InjectionContext {

    <T> T get(Class<T> klass);

    Set<String> getModules();
}
