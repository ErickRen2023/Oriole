package me.erickren.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * DateTime: 2023/10/23 - 11:37
 * Author: ErickRen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Inherited
@Documented
public @interface Qualifier {
    
    String value() default "";
}
