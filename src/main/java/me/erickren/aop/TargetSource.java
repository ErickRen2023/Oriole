package me.erickren.aop;

/**
 * Target Object
 * DateTime: 2023/10/08 - 21:55
 * Author: ErickRen
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
