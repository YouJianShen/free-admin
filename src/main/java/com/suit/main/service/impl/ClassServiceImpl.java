package com.suit.main.service.impl;

import com.suit.base.domain.BaseModel;
import com.suit.core.exception.CoreException;
import com.suit.core.service.BaseService;
import com.suit.core.service.impl.BaseServiceImpl;
import com.suit.jpa.core.BaseDao;
import com.suit.main.Class;
import com.suit.main.dao.ClassDao;
import com.suit.main.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClassServiceImpl extends BaseServiceImpl<Class> implements ClassService{

    @Autowired
    private ClassDao classDao;



    @Override
    protected BaseDao<Class> getBaseDao() {
        return classDao;
    }

    @Override
    protected java.lang.Class getEntityClass() {
        return Class.class;
    }
}
