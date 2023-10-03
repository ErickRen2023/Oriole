package me.erickren.context;

/**
 * DateTime: 2023/10/03 - 19:35
 * Author: ErickRen
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
