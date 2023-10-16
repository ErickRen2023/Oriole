package me.erickren.aop;

/**
 * DateTime: 2023/10/07 - 17:08
 * Author: ErickRen
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
