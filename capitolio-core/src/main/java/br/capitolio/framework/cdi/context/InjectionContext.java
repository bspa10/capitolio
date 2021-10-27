package br.capitolio.framework.cdi.context;

public interface InjectionContext {

    <T> T get(Class<T> klass);

}
