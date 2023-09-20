package me.erickren.core.io.resource.loader;

import me.erickren.core.io.resource.Resource;

/**
 * Resource loader interface.
 * DateTime: 2023/09/19 - 14:08
 * Author: ErickRen
 */
public interface ResourceLoader {

    /**
     * Get resource.
     *
     * @param location location.
     * @return Resource.
     */
    Resource getResource(String location);
}
