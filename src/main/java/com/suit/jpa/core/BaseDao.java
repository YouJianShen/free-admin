package com.suit.jpa.core;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.suit.base.domain.BaseModel;

public interface BaseDao<T extends BaseModel> extends
		PagingAndSortingRepository<T, String>,JpaSpecificationExecutor<T> {
}
