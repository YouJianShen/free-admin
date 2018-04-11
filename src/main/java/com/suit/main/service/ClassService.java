package com.suit.main.service;

import com.suit.core.exception.CoreException;
import com.suit.core.service.BaseService;
import com.suit.main.Class;

public interface ClassService extends BaseService<Class>{

    void createDB (Class classInfo) throws CoreException;

}
