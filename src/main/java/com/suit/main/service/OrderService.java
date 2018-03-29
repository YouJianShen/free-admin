package com.suit.main.service;

import com.suit.core.exception.CoreException;
import com.suit.core.service.BaseService;
import com.suit.main.Dorm;
import com.suit.main.Order;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface OrderService extends BaseService<Order>{

    /**
     * 创建、修改订单
     * @param order
     * @return
     * @throws CoreException
     */
    public String createOrder(Order order) throws CoreException;

    /**
     * 垃圾处理
     */
    public void garbageOrder(String orderId) throws CoreException;

    /**
     * 获取宿舍信息
     * @return
     * @throws CoreException
     */
    public Page<Order> getOrderList(String keywords, Date startTime, Date endTime, int page, int rows) throws CoreException;

    /**
     * 删除成员
     * @param id
     * @throws CoreException
     */
    public void deleteOrder(String id) throws CoreException;
}
