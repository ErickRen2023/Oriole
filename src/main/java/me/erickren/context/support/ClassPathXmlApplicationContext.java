package me.erickren.context.support;

import me.erickren.beans.factory.exception.BeanException;

/**
 * DateTime: 2023/09/27 - 17:51
 * Author: ErickRen
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    /**
     * Load bean definitions from XML file and refresh the ApplicationContext.
     *
     * @param configLocations Locations.
     * @throws BeanException Exception.
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeanException {
        this.configLocations = configLocations;
        refresh();
    }

    public ClassPathXmlApplicationContext(String configLocation) throws BeanException {
        this(new String[]{configLocation});
    }

    @Override
    protected String[] getConfigLocations() {
        return this.configLocations;
    }
}
