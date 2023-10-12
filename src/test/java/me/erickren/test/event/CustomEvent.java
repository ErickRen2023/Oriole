package me.erickren.test.event;

import me.erickren.context.ApplicationEvent;

/**
 * DateTime: 2023/10/03 - 19:58
 * Author: ErickRen
 */
public class CustomEvent extends ApplicationEvent {

    public CustomEvent(Object source) {
        super(source);
    }
}
