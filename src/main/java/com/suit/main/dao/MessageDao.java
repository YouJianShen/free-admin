package com.suit.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.suit.jpa.core.BaseDao;
import com.suit.main.Message;
import com.suit.model.core.common.EnumConstants.BelongsType;

public interface MessageDao extends BaseDao<Message>{
	
	@Query("from Message t where t.belongsId=?1 and t.belongsType=?2 order by createTime desc")
	public List<Message> findByIdAndBelongsType(String id,BelongsType belongsType);

}
