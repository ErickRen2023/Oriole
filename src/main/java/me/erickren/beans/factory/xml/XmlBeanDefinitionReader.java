package me.erickren.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import me.erickren.beans.PropertyValue;
import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.config.BeanReference;
import me.erickren.beans.factory.exception.BeanException;
import me.erickren.beans.factory.support.AbstractBeanDefinitionReader;
import me.erickren.beans.factory.support.BeanDefinitionRegistry;
import me.erickren.core.io.resource.Resource;
import me.erickren.core.io.resource.loader.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import java.io.IOException;
import java.io.InputStream;

/**
 * XML reader.
 * DateTime: 2023/09/20 - 12:22
 * Author: ErickRen
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    
    public static final String BEAN_ELEMENT = "bean";
	public static final String PROPERTY_ELEMENT = "property";
	public static final String ID_ATTRIBUTE = "id";
	public static final String NAME_ATTRIBUTE = "name";
	public static final String CLASS_ATTRIBUTE = "class";
	public static final String VALUE_ATTRIBUTE = "value";
	public static final String REF_ATTRIBUTE = "ref";
	public static final String INIT_METHOD_ATTRIBUTE = "init-method";
	public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		super(registry);
	}

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
		super(registry, resourceLoader);
	}
    @Override
    public void loadBeanDefinitions(Resource resource) throws BeanException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
		} catch (IOException ex) {
			throw new BeanException("IOException parsing XML document from " + resource, ex);
		}
    }

    private void doLoadBeanDefinitions(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream);
		Element root = document.getDocumentElement();
		NodeList childNodes = root.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			if (childNodes.item(i) instanceof Element) {
				if (BEAN_ELEMENT.equals((childNodes.item(i)).getNodeName())) {
					// Parse bean tag.
					Element bean = (Element) childNodes.item(i);
					String id = bean.getAttribute(ID_ATTRIBUTE);
					String name = bean.getAttribute(NAME_ATTRIBUTE);
					String className = bean.getAttribute(CLASS_ATTRIBUTE);
					String initMethodNameAttribute = bean.getAttribute(INIT_METHOD_ATTRIBUTE);
					String destroyMethodNameAttribute = bean.getAttribute(DESTROY_METHOD_ATTRIBUTE);
					

					Class<?> cls;
					try {
						cls = Class.forName(className);
					} catch (ClassNotFoundException e) {
						throw new BeanException("Cannot find class [" + className + "]");
					}
					
					// Select ID as the beanName if id is not empty.
					String beanName = StrUtil.isNotEmpty(id) ? id : name;
					if (StrUtil.isEmpty(beanName)) {
						// Lower first letter as the bean name.
						// UserService --> userService.
						beanName = StrUtil.lowerFirst(cls.getSimpleName());
					}

					BeanDefinition beanDefinition = new BeanDefinition(cls);
					beanDefinition.setInitMethodName(initMethodNameAttribute);
					beanDefinition.setDestroyMethodName(destroyMethodNameAttribute);

					for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
						if (bean.getChildNodes().item(j) instanceof Element) {
							if (PROPERTY_ELEMENT.equals((bean.getChildNodes().item(j)).getNodeName())) {
								// Parse xml tag.
								Element property = (Element) bean.getChildNodes().item(j);
								String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
								String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
								String refAttribute = property.getAttribute(REF_ATTRIBUTE);

								if (StrUtil.isEmpty(nameAttribute)) {
									throw new BeanException("The name attribute cannot be null or empty");
								}
								
								Object value = valueAttribute;
								// Bean reference.
								if (StrUtil.isNotEmpty(refAttribute)) {
									value = new BeanReference(refAttribute);
								}
								// Property value.
								PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
								beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
							}
						}
					}
					if (getRegistry().containsBeanDefinition(beanName)) {
						// There is a same bean name in bean map.
						throw new BeanException("Duplicate beanName[" + beanName + "] is not allowed");
					}
					// registry bean definition.
					getRegistry().registerBeanDefinition(beanName, beanDefinition);
				}
			}
		}
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeanException {
        ResourceLoader resourceLoader = getResourceLoader();
		Resource resource = resourceLoader.getResource(location);
		loadBeanDefinitions(resource);
    }
}
