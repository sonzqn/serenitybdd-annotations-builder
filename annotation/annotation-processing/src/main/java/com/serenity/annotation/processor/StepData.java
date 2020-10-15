package com.serenity.annotation.processor;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface StepData {
    String value();
    int order() default StepOrder.Middle;
}
