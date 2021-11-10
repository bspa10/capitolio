package br.capitolio.tools.cdi.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Provider {

    /**
     * Indicates that a previews registered provider can be overwritten.
     * <p>
     *  <br>
     *  default: true
     * </p>
     */
    boolean overridable() default true;
}
