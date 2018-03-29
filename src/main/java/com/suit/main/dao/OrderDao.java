package com.suit.main.dao;

import com.suit.jpa.core.BaseDao;
import com.suit.main.Dorm;
import com.suit.main.InOut;
import com.suit.main.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface OrderDao extends BaseDao<Order> {

    @Query("from Order t where t.topic like %?1% and t.createTime >= ?2 and t.createTime <= ?3 and t.status = 0 order by createTime desc")
    public Page<Order> getPageList(String keywords, Date startTime, Date endTime , Pageable pageRequest);
}
