package com.beecho.springxoxo.beanDefinition;

import com.beecho.springxoxo.model.Application;
import com.beecho.springxoxo.model.Registry;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author 春哥大魔王
 */

public class RegisterBeanDefinitionParser implements BeanDefinitionParser {

    private Class<?> clazz;

    public RegisterBeanDefinitionParser(Class<?> cls){
        this.clazz = cls;
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(clazz);

        String id = element.getAttribute("id");
        if(!StringUtils.hasText(id)) {
            if (clazz == Registry.class) {
                id = "beecho_registry";
            } else {
                throw new IllegalStateException("RegisterBeanDefinitionParser.parse,异常");
            }
        }


        String address = element.getAttribute("address");
        String protocol = element.getAttribute("protocol");
        beanDefinition.getPropertyValues().addPropertyValue("address", address);
        beanDefinition.getPropertyValues().addPropertyValue("protocol", protocol);

        if(id!=null && id.length()>0) {
            if(!parserContext.getRegistry().containsBeanDefinition(id)) {
                parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            }
        }

        return beanDefinition;
    }
}
