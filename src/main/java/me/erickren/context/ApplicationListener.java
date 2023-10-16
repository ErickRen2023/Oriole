package me.erickren.context;

import java.util.EventListener;

/**
 * DateTime: 2023/10/03 - 19:35
 * Author: ErickRen
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
