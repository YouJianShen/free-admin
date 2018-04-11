package com.suit.main.service.impl;

import com.suit.core.service.impl.BaseServiceImpl;
import com.suit.jpa.core.BaseDao;
import com.suit.main.Attribute;
import com.suit.main.dao.AttributeDao;
import com.suit.main.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("AttributeService")
@Transactional(readOnly = true)
public class AttributeServiceImpl extends BaseServiceImpl<Attribute> implements AttributeService {

    @Autowired
    private AttributeDao attributeDao;


    @Override
    protected BaseDao<Attribute> getBaseDao() {
        return attributeDao;
    }

    @Override
    protected Class getEntityClass() {
        return this.getClass();
    }
}
