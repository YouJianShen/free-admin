package com.suit.main.service.impl;

import com.suit.core.exception.CoreException;
import com.suit.core.service.impl.BaseServiceImpl;
import com.suit.jpa.core.BaseDao;
import com.suit.main.Attribute;
import com.suit.main.dao.AttributeDao;
import com.suit.main.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("AttributeService")
@Transactional(readOnly = false)
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

    @Override
    public void createAttributes(List<Attribute> attrs) throws CoreException {
        attributeDao.save(attrs);
    }

    @Override
    public void createAttribute(Attribute attr) throws CoreException {
        attributeDao.save(attr);
    }

    @Override
    public void createAttributes(List<Attribute> attrs, String belongsId) throws CoreException {
        for(int i = 0;i<attrs.size();i++){
            attrs.get(i).setBelongsId(belongsId);
        }
        attributeDao.save(attrs);
    }
}
