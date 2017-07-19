package com.beecho.springxoxo.beanDefinition;

import com.beecho.springxoxo.model.Xxoo;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created by Administrator on 2017/7/17.
 */
public class XxooBeanDefinitionParser implements BeanDefinitionParser {

    private Class<?> clazz;

    public XxooBeanDefinitionParser(Class<?> cls){
        this.clazz = cls;
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(clazz);

        String id = element.getAttribute("id");
        if(!StringUtils.hasText(id)) {
            if (clazz == Xxoo.class) {
                id = "beecho_xxoo";
            } else {
                throw new IllegalStateException("XxooBeanDefinitionParser.parse,异常");
            }
        }

        String userName = element.getAttribute("userName");
        String email = element.getAttribute("email");
        beanDefinition.getPropertyValues().addPropertyValue("userName", userName);
        beanDefinition.getPropertyValues().addPropertyValue("email", email);

        if(id!=null && id.length()>0){
            if(parserContext.getRegistry().containsBeanDefinition(id)) {
                throw new IllegalStateException("Duplicate spring bean id " + id);
            }
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        }

        return beanDefinition;
    }
}
