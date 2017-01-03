package com.fatheroctober.drunkard.engine.core.tools.iocmodule;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@BindingAnnotation
public @interface PolicemanInject {
}


