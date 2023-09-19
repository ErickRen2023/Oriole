package me.erickren.core.io.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * DateTime: 2023/09/19 - 14:06
 * Author: ErickRen
 */
public class UrlResource implements Resource {
    
    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        return con.getInputStream();
    }
}
