/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.suit.web.account;

import com.suit.core.constant.WebConstant;
import com.suit.core.exception.CoreException;
import com.suit.core.tool.BaseController;
import com.suit.core.util.SystemUtil;
import com.suit.core.web.vo.OperateStatus;
import com.suit.main.service.ClassService;
import com.suit.model.core.common.EnumConstants;
import com.suit.system.core.service.SysUserService;
import com.suit.util.JsonUtil;
import net.sf.json.JSONArray;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * <p>
 * 真正登录的POST请求由Filter完成,
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    private static final Logger logger = LoggerFactory
            .getLogger(BaseController.class);

    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ClassService classService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws CoreException {

        return "index/Index";
    }

    @RequestMapping(value = "order", method = RequestMethod.GET)
    public String order(HttpServletRequest request) throws CoreException {


        return "index/order";
    }

    @ResponseBody
    @RequestMapping(value = "createClass", method = RequestMethod.POST)
    public String createClass(HttpServletRequest request, com.suit.main.Class classInfo) {
        OperateStatus status = new OperateStatus(true,
                WebConstant.COMMON_SUCCESS_MSG);

        try {
            classService.addDBClass(classInfo);
        } catch (CoreException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            status.setSuccess(false);
            status.setMsg(e.getMessage());
        }

        return JsonUtil.genJson(status);
    }

    @ResponseBody
    @RequestMapping(value = "getClass", method = RequestMethod.GET)
    public String getClass(HttpServletRequest request, String id) {
        OperateStatus status = new OperateStatus(true,
                WebConstant.COMMON_SUCCESS_MSG);

        try {
            if(id.equals("")){
                List<com.suit.main.Class> datas = classService.getAll();
                status.setData(JSONArray.fromObject(datas));
            }else{
                status.setData(classService.findUniqueBy("id",id));
            }
        } catch (CoreException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            status.setSuccess(false);
            status.setMsg(e.getMessage());
        }

        return JsonUtil.genJson(status);
    }

    /**
     * enum实体获取
     * @param request
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "enum", method = RequestMethod.POST)
    public String getEnum(HttpServletRequest request, String name) {
        OperateStatus status = new OperateStatus(true,
                WebConstant.COMMON_SUCCESS_MSG);

        try {
            String packageName = EnumConstants.class.getPackage() + ".EnumConstants$" + name;
            Class cls = EnumConstants.class.forName(packageName.replace("package ",""));
            JSONArray json = SystemUtil.genEnumJOSN(cls);
            status.setData(json);
        } catch (ClassNotFoundException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            status.setSuccess(false);
            status.setMsg(e.getMessage());
        }

        return JsonUtil.genJson(status);
    }

}
