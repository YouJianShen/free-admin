package com.suit.main.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.suit.core.exception.CoreException;
import com.suit.jpa.core.BaseDao;
import com.suit.main.InOut;

public interface InOutDao extends BaseDao<InOut> {

	@Query("from InOut t where t.belongsId = ?1 order by createTime desc")
	public Page<InOut> getPageList(String dormId, Pageable pageRequest);

	@Query("from InOut t where t.createUserId = ?1 and t.belongsId = ?2  order by createTime desc")
	public Page<InOut> getPageList(String partner, String dormId,
			Pageable pageRequest);

	@Query("select sum(money) from InOut t where t.belongsId = ?1 group by inFlag")
	public double[] getSum(String dormId);

	@Query("select coalesce(sum(money),0) from InOut t where t.createTime >= ?1 and t.createTime <= ?2 and t.inFlag = false")
	public double getSumByTime(Date startDate, Date endDate) throws CoreException;
}
