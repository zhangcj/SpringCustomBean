package com.beecho.springxoxo.namespaceHandler;

import com.beecho.springxoxo.beanDefinition.*;
import com.beecho.springxoxo.model.Xxoo;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by Administrator on 2017/7/17.
 */
public class BeechoNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("xxoo", new XxooBeanDefinitionParser(Xxoo.class));
        registerBeanDefinitionParser("application", new ApplicationBeanDefinitionParser());
        registerBeanDefinitionParser("registry", new RegisterBeanDefinitionParser());
        registerBeanDefinitionParser("protocol", new ProtocolBeanDefinitionParser());
        registerBeanDefinitionParser("service", new ServiceBeanDefinitionParser());
    }

}