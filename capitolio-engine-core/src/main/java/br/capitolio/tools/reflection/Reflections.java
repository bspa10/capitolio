package br.capitolio.tools.reflection;

import br.capitolio.CapitolioException;
import lombok.SneakyThrows;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Reflections {
    private Reflections() {

    }

    public static class General {
        public static Class<?>[] getCallingClasses() {
            return new CallingClass().getCallingClasses();
        }

    }

    public static class Classes {
        public static boolean isAbstract(Class<?> klass) {
            return Modifier.isAbstract(klass.getModifiers());
        }

        public static boolean isPublic(Class<?> klass) {
            return Modifier.isPublic(klass.getModifiers());
        }

        public static boolean isLocal(Class<?> klass) {
            return klass.getCanonicalName().startsWith("br.com.gedjus");
        }

        public static Type[] getGenericTypes(Object object, Class<?> klass) {
            for (var genericInterface : object.getClass().getGenericInterfaces()) {
                if (! genericInterface.getTypeName().contains(klass.getCanonicalName()))
                    continue;

                if (genericInterface instanceof final ParameterizedType parameterizedType) {
                    return parameterizedType.getActualTypeArguments();
                }
            }

            return new Type[]{};
        }

        public static Type[] getGenericTypes(Class<?> klass, Class<?> sklass) {
            for (var genericInterface : klass.getGenericInterfaces()) {
                if (! genericInterface.getTypeName().contains(sklass.getCanonicalName()))
                    continue;

                if (genericInterface instanceof final ParameterizedType parameterizedType) {
                    return parameterizedType.getActualTypeArguments();
                }
            }

            final var type = klass.getGenericSuperclass();
            if (type.getTypeName().contains(sklass.getCanonicalName())) {
                if (type instanceof final ParameterizedType parameterizedType) {
                    return parameterizedType.getActualTypeArguments();
                }
            }

            return new Type[]{};
        }

        public static List<Class<?>> getParents(Object object) {
            return getParents(object.getClass());
        }

        public static List<Class<?>> getParents(Class<?> klass) {
            final var parents = new ArrayList<Class<?>>();
            parents.add(klass);

            Class<?> parent = klass;
            while ((parent = parent.getSuperclass()) != null) {
                parents.add(parent);
            }

            return parents;
        }

        /**
         * Cria uma nova instância do objeto assumindo que todos os parâmetros passados correspondem
         * ao primeiro e único construtor da classe.
         *
         * @param klass Classe que será construída.
         * @param parameters Parâmetros do construtor
         */
        @SneakyThrows
        public static <T> T newInstance(Class<T> klass, Object ... parameters) {
            if (klass.getEnclosingClass() != null) {
                throw new ReflectionException("Oh no!! InnerClass?!");

            }

            for (var constructor: klass.getDeclaredConstructors()) {
                if (constructor.getParameterCount() == parameters.length) {
                    Constructors.makeAccessible(constructor);
                    return (T) constructor.newInstance(parameters);
                }
            }

            throw new ReflectionException("Constructor for [%s] not found".formatted(klass.getCanonicalName()));
        }

        public static Method getMethod(Class<?> klass, String name, Object ... parameters) {
            if (parameters.length > 0) {
                final var kparam = Arrays.stream(parameters).map(Object::getClass).collect(Collectors.toList());
                return getMethod(klass, name, kparam.toArray(new Class[]{}));
            }

            return getMethod(klass, name, null);
        }

        public static Method getMethod(Class<?> klass, String name, Class<?> ... parameters) {
            try {
                return klass.getDeclaredMethod(name, parameters);
            } catch (Exception ex) {
                throw new ReflectionException(ex.getMessage(), ex);
            }
        }

        public static boolean hasAnnotation(Object object, Class<? extends Annotation> annotation) {
            return hasAnnotation(object.getClass(), annotation);
        }

        public static boolean hasAnnotation(Class<?> klass, Class<? extends Annotation> annotation) {
            return klass.isAnnotationPresent(annotation);
        }

        public static List<Method> getMethods(Object object, Class<? extends Annotation> annotation) {
            return getMethods(object.getClass(), annotation);
        }

        public static List<Method> getMethods(Class<?> klass, Class<? extends Annotation> annotation) {
            final var methods = new ArrayList<Method>();

            for (var method : klass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(annotation)) {
                    methods.add(method);
                }
            }

            return methods;
        }
    }

    public static class Intertfaces {
        public static Type[] getGenericTypes(Class<?> klass, Class<?> interf) {
            for (var entry: getGenericTypes(klass).entrySet()) {
                final var typeName = entry.getKey().getTypeName();
                final var typeNameWithoutParameter = typeName.substring(0, typeName.indexOf('<'));

                if (typeNameWithoutParameter.equalsIgnoreCase(interf.getName())) {
                    return entry.getValue();
                }
            }

            return new Type[]{};
        }

        public static Map<Type, Type[]> getGenericTypes(Class<?> klass) {
            final var result = new HashMap<Type, Type[]>();
            for (Type gi : klass.getGenericInterfaces()) {
                final var typeName = gi.getTypeName();

                if (!typeName.startsWith("java.") && !typeName.startsWith("sun.")) {
                    if (gi.getClass().getGenericInterfaces().length > 0) {
                        result.putAll(getGenericTypes(gi.getClass()));
                    }
                }

                if (gi instanceof ParameterizedType) {
                    result.putIfAbsent(gi, ((ParameterizedType) gi).getActualTypeArguments());
                }
            }

            return result;
        }
    }

    public static final class Methods {
        public static boolean isPublic(Method method) {
            return Modifier.isPublic(method.getModifiers());
        }

        public static boolean isLocal(Method method) {
            return Classes.isLocal(method.getDeclaringClass());
        }

        public static <T extends Annotation> boolean isAnnotated(Method method, Class<T> klass) {
            return method.getAnnotation(klass) != null;
        }

        public static Object invoke(Object target, String name, Object ... parameters) {
            final var method = Classes.getMethod(target.getClass(), name, parameters);
            return invoke(target, method, parameters);
        }

        public static Object invoke(Object target, Method method, Object ... parameters) {
            try {
                method.setAccessible(true);
                return method.invoke(target, parameters);
            } catch (Exception ex) {
                final Throwable cause = ex.getCause();
                if (cause instanceof CapitolioException) {
                    throw (CapitolioException) cause;
                }

                throw new ReflectionException(ex.getMessage(), ex);
            }
        }

        public static Class<?> getReturnType(Method method) {
            return method.getReturnType();
        }
    }

    public static final class Constructors {

        public static void makeAccessible(Constructor<?> constructor) {
            constructor.setAccessible(true);
        }
    }

}
