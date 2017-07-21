package com.beecho.springxoxo.beanDefinition;

import com.beecho.springxoxo.model.Application;
import com.beecho.springxoxo.model.Service;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author 春哥大魔王
 */

public class ServiceBeanDefinitionParser implements BeanDefinitionParser {
    private Class<?> clazz;

    public ServiceBeanDefinitionParser(Class<?> cls){
        this.clazz = cls;
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(clazz);

        String id = element.getAttribute("id");
        if(!StringUtils.hasText(id)) {
            if (clazz == Service.class) {
                id = "beecho_service";
            } else {
                throw new IllegalStateException("ServiceBeanDefinitionParser.parse,异常");
            }
        }
        String service = element.getAttribute("service");
        String ref = element.getAttribute("ref");
        String protocol = element.getAttribute("protocol");
        String group = element.getAttribute("group");
        beanDefinition.getPropertyValues().addPropertyValue("service", service);
        beanDefinition.getPropertyValues().addPropertyValue("ref", ref);
        beanDefinition.getPropertyValues().addPropertyValue("protocol", protocol);
        beanDefinition.getPropertyValues().addPropertyValue("group", group);

        if(id!=null && id.length()>0) {
            if(!parserContext.getRegistry().containsBeanDefinition(id)) {
                parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            }
        }

        return beanDefinition;
    }
}
