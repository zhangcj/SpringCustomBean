package com.beecho.springxoxo.beanDefinition;

import com.beecho.springxoxo.model.Application;
import com.beecho.springxoxo.model.Protocol;
import com.beecho.springxoxo.model.Xxoo;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author 春哥大魔王
 */

public class ProtocolBeanDefinitionParser implements BeanDefinitionParser {
    private Class<?> clazz;

    public ProtocolBeanDefinitionParser(Class<?> cls){
        this.clazz = cls;
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(clazz);

        String id = element.getAttribute("id");
        if(!StringUtils.hasText(id)) {
            if (clazz == Protocol.class) {
                id = "beecho_protocol";
            } else {
                throw new IllegalStateException("ProtocolBeanDefinitionParser.parse,异常");
            }
        }
        String name = element.getAttribute("name");
        beanDefinition.getPropertyValues().addPropertyValue("name", name);
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);

        if(id!=null && id.length()>0) {
            if(!parserContext.getRegistry().containsBeanDefinition(id)) {
                parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            }
        }
        return beanDefinition;
    }
}
