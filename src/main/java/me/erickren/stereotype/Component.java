package me.erickren.stereotype;

import java.lang.annotation.*;

/**
 * DateTime: 2023/10/16 - 12:27
 * Author: ErickRen
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    
    String value() default "";
}
