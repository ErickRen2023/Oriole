package me.erickren.event;

import me.erickren.context.ApplicationListener;

/**
 * DateTime: 2023/10/03 - 19:58
 * Author: ErickRen
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(this.getClass().getName() + " Called.");
    }
}
