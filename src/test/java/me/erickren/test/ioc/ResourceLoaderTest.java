package me.erickren.test.ioc;

import cn.hutool.core.io.IoUtil;
import me.erickren.core.io.resource.FileSystemResource;
import me.erickren.core.io.resource.Resource;
import me.erickren.core.io.resource.UrlResource;
import me.erickren.core.io.resource.loader.DefaultResourceLoader;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTime: 2023/09/19 - 14:12
 * Author: ErickRen
 */
public class ResourceLoaderTest {

    @Test
    public void classPathTest() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:HelloWorld.txt");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        assertThat(content).isEqualTo("Hello,World!");
    }

    @Test
    public void fileSystemTest() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("src/test/resources/HelloWorld.txt");
        InputStream inputStream = resource.getInputStream();
        assertThat(resource instanceof FileSystemResource).isTrue();
    }

    @Test
    public void URLTest() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("https://www.baidu.com");
        InputStream inputStream = resource.getInputStream();
        assertThat(resource instanceof UrlResource).isTrue();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
}
