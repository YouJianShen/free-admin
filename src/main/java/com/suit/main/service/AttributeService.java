package com.suit.main.service;

import com.suit.core.exception.CoreException;
import com.suit.core.service.BaseService;
import com.suit.main.Attribute;

import java.util.List;

public interface AttributeService extends BaseService<Attribute> {

    /**
     *批量添加属性
     * @param attrs
     * @throws CoreException
     */
    void createAttributes(List<Attribute> attrs) throws CoreException;

    /**
     *批量添加属性
     * @param attrs
     * @throws CoreException
     */
    void createAttributes(List<Attribute> attrs,String belongsId) throws CoreException;

    /**
     * 添加属性项
     * @param attr
     * @throws CoreException
     */
    void createAttribute(Attribute attr) throws CoreException;

}
