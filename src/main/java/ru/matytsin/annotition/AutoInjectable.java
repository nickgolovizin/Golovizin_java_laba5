package ru.matytsin.annotition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация применяется к полям классов и указывает объекту класса Injector
 * для каких полей нужно внедрить зависимость
 *
 * @author Matytsin Alexander
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AutoInjectable {}
