package me.erickren.context;

import me.erickren.beans.factory.HierarchicalBeanFactory;
import me.erickren.beans.factory.ListableBeanFactory;
import me.erickren.core.io.resource.loader.ResourceLoader;

/**
 * DateTime: 2023/09/27 - 17:32
 * Author: ErickRen
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader {
    
}
