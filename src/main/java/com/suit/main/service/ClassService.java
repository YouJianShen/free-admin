package com.suit.main.service;

import com.suit.core.exception.CoreException;
import com.suit.core.service.BaseService;
import com.suit.main.Class;

public interface ClassService extends BaseService<Class>{

    /**
     * 添加数据库
     * @param classInfo
     * @throws CoreException
     */
    void addDBClass (Class classInfo) throws CoreException;

    /**
     * 添加数据库表
     * @param classInfo
     * @throws CoreException
     */
    void createDB (Class classInfo) throws CoreException;

}
