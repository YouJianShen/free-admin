package com.suit.system.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensymphony.util.GUID;
import com.suit.core.constant.WebConstant;
import com.suit.core.exception.CoreException;
import com.suit.core.exception.ErrorConstant;
import com.suit.core.service.BaseService;
import com.suit.core.tool.BaseController;
import com.suit.core.util.SystemUtil;
import com.suit.core.web.servlet.CaptchaServlet;
import com.suit.core.web.vo.OperateStatus;
import com.suit.model.core.common.EnumConstants.SysUserType;
import com.suit.model.system.core.SysUser;
import com.suit.shiro.UsernamePasswordCaptchaToken;
import com.suit.system.core.service.SysUserService;
import com.suit.system.core.service.vo.RegisterVo;
import com.suit.util.JsonUtil;

@Controller
@RequestMapping(value = "/system/user")
public class SysUserController extends BaseController<SysUser> {
    private static final Logger logger = LoggerFactory
            .getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected BaseService getBaseService() {
        return sysUserService;
    }

    /**
     * 用户注册
     */
    @ResponseBody
    @RequestMapping(value = "register")
    public String register(RegisterVo regInfo) throws CoreException {
        OperateStatus status = new OperateStatus(false,
                WebConstant.COMMON_FAIL_MSG);
        try {
            regInfo.setUserType(SysUserType.PARTNER);
            String sysUserId = sysUserService.register(regInfo);
            SysUser sysUser = sysUserService.getById(sysUserId);
            status.setSuccess(true);
            status.setMsg(WebConstant.COMMON_SUCCESS_MSG);
            status.setData(sysUser);
        } catch (CoreException e) {
            logger.error(e.getMessage());
            status.setSuccess(false);
            status.setMsg(e.getMessage());
            status.setCode(e.getCode());
        }
        return JsonUtil.getJSONSerializer(getIncludeRefProperties(),
                getExcludeProperties(), WebConstant.DATEFORMAT_TIME).serialize(
                status);
    }

    @ResponseBody
    @RequestMapping(value = "regist", method = RequestMethod.POST)
    public String register(RegisterVo info, HttpServletRequest request)
            throws CoreException {
        OperateStatus status = new OperateStatus(true,
                WebConstant.COMMON_SUCCESS_MSG);

        //        $.ajax({
        //            url: "http://localhost:8081/dorm-manage-web/system/user/regist",
        //            type:"post",
        //            data: {account: "admin", password: "2252dino00edu", phoneNumber: 18281606741, name: "admin"}
        //        })

        try {
            String exitCode = (String) SecurityUtils.getSubject().getSession()
                    .getAttribute(CaptchaServlet.KEY_CAPTCHA);
            info.setUserType(SysUserType.PARTNER);
            String psd = info.getPassword();
            // checkPassword(psd);
            String salt = GUID.generateGUID();
            info.setSalt(salt);
            String md5Psd = SystemUtil.encryPass(psd, salt);
            info.setPassword(md5Psd);
            HttpSession session = request.getSession();
            session.setAttribute("num", info.getAccount());
            String id = sysUserService.register(info);
            status.setData(id);


        } catch (CoreException e) {
            e.printStackTrace();
            status.setSuccess(false);
            status.setMsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            status.setSuccess(false);
            status.setMsg("注册失败");
        }
        return JsonUtil.genJson(status);
    }

    /**
     * 成员加入
     *
     * @param partnerId
     * @param dormId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(String partnerId, String dormId) throws CoreException {
        OperateStatus status = new OperateStatus(true,
                WebConstant.COMMON_SUCCESS_MSG);
        try {
            sysUserService.jionDorm(partnerId, dormId);
        } catch (CoreException e) {
            status.setSuccess(false);
            status.setMsg(e.getMessage());
        }
        return JsonUtil.genJson(status);
    }

    /**
     * 成员退出
     *
     * @param partnerId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String cancelDorm(String partnerId) throws CoreException {
        OperateStatus status = new OperateStatus(true,
                WebConstant.COMMON_SUCCESS_MSG);
        try {
            sysUserService.cancelDorm(partnerId);
        } catch (CoreException e) {
            status.setSuccess(false);
            status.setMsg(e.getMessage());
        }
        return JsonUtil.genJson(status);
    }

    /**
     * 修改密码
     *
     * @param account
     * @param old
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update/pass", method = RequestMethod.POST)
    public String upatePassword(String account, String old, String password)
            throws CoreException {
        OperateStatus status = new OperateStatus(true,
                WebConstant.COMMON_SUCCESS_MSG);
        try {
            sysUserService.updatePassword(account, old, password);
        } catch (CoreException e) {
            status.setSuccess(false);
            status.setMsg(e.getMessage());
        }
        return JsonUtil.genJson(status);
    }

    /**
     * 检查密码是否满足规则
     *
     * @param password
     * @throws CoreException
     */
    private void checkPassword(String password) throws CoreException {
        if ((password.length() > 15 || password.length() < 8)
                || password.matches("[0-9]{8,15}")
                || password.matches("[a-zA-Z]{8,15}")) {
            throw new CoreException(ErrorConstant.REGISTER_PASSWORD_ERROR,
                    "密码不符合规定(长度为8-15位且须为数字与字母组合)！");
        }
    }

}