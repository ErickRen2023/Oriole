package me.erickren.context.event;

import me.erickren.context.ApplicationContext;
import me.erickren.context.ApplicationEvent;

/**
 * DateTime: 2023/10/03 - 19:44
 * Author: ErickRen
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }
    
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
