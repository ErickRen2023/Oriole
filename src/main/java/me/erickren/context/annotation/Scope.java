package me.erickren.context.annotation;

import java.lang.annotation.*;

/**
 * DateTime: 2023/10/16 - 12:22
 * Author: ErickRen
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
    
    String value() default "singleton";
}
