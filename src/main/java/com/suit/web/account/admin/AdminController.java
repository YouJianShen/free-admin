package com.suit.web.account.admin;

import com.suit.core.exception.CoreException;
import com.suit.core.util.SystemUtil;
import com.suit.model.system.core.SysUser;
import com.suit.shiro.ShiroUser;
import com.suit.system.core.service.SysUserService;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shenyoujian on 2018/1/29.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws CoreException {
        ShiroUser suser = SystemUtil.getShiroUser();
        SysUser user = sysUserService.findUniqueBy("userAccount", suser.getUserAccount());
        if(user != null){
            request.setAttribute("user", user);
        }
        return "admin/HomeView";
    }

}
