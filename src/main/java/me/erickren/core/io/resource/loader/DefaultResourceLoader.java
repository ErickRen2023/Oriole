package me.erickren.core.io.resource.loader;

import me.erickren.core.io.resource.ClassPathResource;
import me.erickren.core.io.resource.FileSystemResource;
import me.erickren.core.io.resource.Resource;
import me.erickren.core.io.resource.UrlResource;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * DateTime: 2023/09/19 - 14:09
 * Author: ErickRen
 */
public class DefaultResourceLoader implements ResourceLoader {

    public static final String CLASSPATH_URL_PREFIX = "classpath:";


    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // Class path file resource.
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                // URL resource.
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException ex) {
                // File system resource.
                return new FileSystemResource(location);
            }
        }
    }
}
