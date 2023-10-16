package me.erickren.aop.framework;

import me.erickren.aop.AdvisedSupport;

/**
 * DateTime: 2023/10/11 - 10:58
 * Author: ErickRen
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isCglibProxy()) {
            return new CglibAopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }
}
