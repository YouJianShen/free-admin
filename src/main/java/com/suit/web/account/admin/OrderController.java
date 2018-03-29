package com.suit.web.account.admin;

import com.suit.core.constant.WebConstant;
import com.suit.core.exception.CoreException;
import com.suit.core.web.vo.JsonPageData;
import com.suit.core.web.vo.OperateStatus;
import com.suit.main.Order;
import com.suit.main.service.OrderService;
import com.suit.system.core.service.SysUserService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by shenyoujian on 2018/1/29.
 */
@Controller
@RequestMapping(value = "/admin/order")
public class OrderController {
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws CoreException {
        return "admin/Order/OrderListView";
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String create(Order order)
            throws CoreException {
        OperateStatus status = new OperateStatus(true,
                WebConstant.COMMON_SUCCESS_MSG);

        try {
            orderService.createOrder(order);
        } catch (CoreException e) {
            status.setSuccess(false);
            status.setMsg(e.getMessage());
        }

        return JSONObject.fromObject(status).toString();
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getList(String keywords, Date startTime, Date endTime, HttpServletRequest request)
            throws CoreException {
        OperateStatus status = new OperateStatus(true,
                WebConstant.COMMON_SUCCESS_MSG);


        String page = request.getParameter("page");
        String rows = request.getParameter("rows");

        try {
            Page<Order> pages = orderService.getOrderList(keywords, startTime, endTime, Integer.valueOf(page), Integer.valueOf(rows));
            // 封装响应数据
            JsonPageData<Order> data = new JsonPageData<Order>(
                    pages.getNumber() + 1, pages.getSize(),
                    pages.getTotalElements(), pages.getContent(), pages.getTotalPages());

            status.setData(data);
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            return JSONObject.fromObject(status, jsonConfig).toString();
        } catch (CoreException e) {
            status.setSuccess(false);
            status.setMsg(e.getMessage());
        }

        return JSONObject.fromObject(status).toString();
    }

    @ResponseBody
    @RequestMapping(value = "garbage", method = RequestMethod.POST)
    public String getList(String id)
            throws CoreException {
        OperateStatus status = new OperateStatus(true,
                WebConstant.COMMON_SUCCESS_MSG);

        try {
            orderService.garbageOrder(id);
        } catch (CoreException e) {
            status.setSuccess(false);
            status.setMsg(e.getMessage());
        }

        return JSONObject.fromObject(status).toString();
    }
}
