package com.suit.core.exception;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.stereotype.Component;

/**
 * 异常信息处理类
 *
 * @author hsy
 */
@Component
public class TagsNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("error", new ErrorParser());
    }
}
