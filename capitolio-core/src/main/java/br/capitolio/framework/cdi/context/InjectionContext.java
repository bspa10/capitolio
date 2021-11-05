package br.capitolio.framework.cdi.context;

import java.util.Set;

public interface InjectionContext {

    <T> T get(Class<T> klass);

    Set<String> getModules();
}
