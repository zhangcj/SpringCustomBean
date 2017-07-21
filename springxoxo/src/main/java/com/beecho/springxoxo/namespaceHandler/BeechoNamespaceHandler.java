package com.beecho.springxoxo.namespaceHandler;

import com.beecho.springxoxo.beanDefinition.*;
import com.beecho.springxoxo.model.*;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by Administrator on 2017/7/17.
 */
public class BeechoNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("xxoo", new XxooBeanDefinitionParser(Xxoo.class));
        registerBeanDefinitionParser("application", new ApplicationBeanDefinitionParser(Application.class));
        registerBeanDefinitionParser("registry", new RegisterBeanDefinitionParser(Registry.class));
        registerBeanDefinitionParser("protocol", new ProtocolBeanDefinitionParser(Protocol.class));
        registerBeanDefinitionParser("service", new ServiceBeanDefinitionParser(Service.class));
    }
}