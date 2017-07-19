package com.beecho.springxoxo.namespaceHandler;

import com.beecho.springxoxo.beanDefinition.*;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by Administrator on 2017/7/17.
 */
public class BeechoNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("xxoo", new XxooBeanDefinitionParser());
        registerBeanDefinitionParser("application", new ApplicationBeanDefinitionParser());
        registerBeanDefinitionParser("registry", new RegisterBeanDefinitionParser());
        registerBeanDefinitionParser("protocol", new ProtocolBeanDefinitionParser());
        registerBeanDefinitionParser("service", new ServiceBeanDefinitionParser());
    }

}