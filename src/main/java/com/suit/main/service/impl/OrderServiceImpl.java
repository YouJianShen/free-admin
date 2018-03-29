package com.suit.main.service.impl;

import java.util.Date;
import java.util.List;

import com.suit.core.exception.CoreException;
import com.suit.core.service.constant.SysConstant;
import com.suit.main.Dorm;
import com.suit.main.Order;
import com.suit.main.dao.OrderDao;
import com.suit.main.service.OrderService;
import com.suit.model.core.common.EnumConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.suit.core.service.impl.BaseServiceImpl;
import com.suit.jpa.core.BaseDao;
import com.suit.main.Message;

@Component("OrderService")
@Transactional(readOnly = true)
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    protected BaseDao<Order> getBaseDao() {
        return orderDao;
    }

    @Override
    protected Class getEntityClass() {
        return Message.class;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public String createOrder(Order order) throws CoreException {
        if (order != null) {
            save(order);
            return order.getId();
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public void garbageOrder(String orderId) throws CoreException {
        Order order = findUniqueBy("id", orderId);
        if (order != null) {
            order.setStatus(EnumConstants.BaseModelStatus.FROZEN);
            orderDao.save(order);
        } else {
            throw new CoreException("订单不存在！");
        }
    }

    @Override
    public Page<Order> getOrderList(String keywords, Date startTime, Date endTime, int page, int rows) throws CoreException {
        if (startTime == null) {
            startTime = new Date(1438725620l);
        }
        if (endTime == null) {
            endTime = new Date();
        }
        return orderDao.getPageList(keywords, startTime, endTime, new PageRequest(page - 1, rows));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public void deleteOrder(String id) throws CoreException {

    }
}
