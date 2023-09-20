package me.erickren.core.io.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource interface.
 * DateTime: 2023/09/19 - 13:56
 * Author: ErickRen
 */
public interface Resource {

    /**
     * Get the input stream of the resource.
     *
     * @return InputStream
     * @throws IOException IoException.
     */
    InputStream getInputStream() throws IOException;
}
