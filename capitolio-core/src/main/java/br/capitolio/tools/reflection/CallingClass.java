package br.capitolio.tools.reflection;

final class CallingClass extends SecurityManager {

    public Class<?>[] getCallingClasses() {
        return getClassContext();
    }

}
