/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.suit.web.account;

import javax.servlet.http.HttpServletRequest;

import com.suit.core.socket.SocketClient;
import com.suit.core.socket.SocketService;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suit.core.exception.CoreException;
import com.suit.core.util.SystemUtil;
import com.suit.model.system.core.SysUser;
import com.suit.shiro.ShiroUser;
import com.suit.system.core.service.SysUserService;

import java.io.IOException;
import java.util.Date;

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
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws CoreException {


        return "index/Index";
    }

    @RequestMapping(value = "order", method = RequestMethod.GET)
    public String order(HttpServletRequest request) throws CoreException {


        return "index/order";
    }
}
