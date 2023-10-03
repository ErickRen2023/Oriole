package me.erickren.context.event;

import me.erickren.context.ApplicationEvent;

/**
 * DateTime: 2023/10/03 - 19:47
 * Author: ErickRen
 */
public class ContextRefreshedEvent extends ApplicationEvent {

    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
