package me.erickren.context;

import java.util.EventObject;

/**
 * DateTime: 2023/10/03 - 19:33
 * Author: ErickRen
 */
public abstract class ApplicationEvent extends EventObject {
    
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred
     * @throws IllegalArgumentException If source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
