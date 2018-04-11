package com.suit.main.dao;

import com.suit.core.exception.CoreException;
import com.suit.jpa.core.BaseDao;
import com.suit.main.Class;
import org.springframework.data.jpa.repository.Query;


public interface ClassDao extends BaseDao<Class>{

//    @Query("create table if not exists ?1 (?2)")
//    public void createDB(String dbName,String arguments) throws CoreException;

//    @Query("drop table if exists ?1")
//    public void dropDB(String dbName) throws CoreException;
}
