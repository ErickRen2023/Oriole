package me.erickren.context.event;

import me.erickren.context.ApplicationEvent;
import me.erickren.context.ApplicationListener;

/**
 * DateTime: 2023/10/03 - 19:37
 * Author: ErickRen
 */
public interface ApplicationEventMulticaster {
    
    void addApplicationListener(ApplicationListener<?> listener);

	void removeApplicationListener(ApplicationListener<?> listener);

	void multicastEvent(ApplicationEvent event);
}
