package com.suit.web.account;

import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-3-16
 * <p>Version: 1.0
 */

//@RequiresPermissions("session:*")
@Controller
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private SessionDAO sessionDAO;

    @ResponseBody
    @RequestMapping()
    public String alllist(Model model) {
        Collection<Session> sessions =  sessionDAO.getActiveSessions();
        StringBuffer sb = new StringBuffer();
        for(Session s:sessions){
        	System.out.println("===list(Model model)==="+s.getId()+"--"+s);
        	sb.append(s.getId()).append("|");
        }
        return sb.toString();
    }

//    @RequestMapping("/{sessionId}/forceLogout")
//    public String forceLogout(
//            @PathVariable("sessionId") String sessionId, RedirectAttributes redirectAttributes) {
//        try {
//            Session session = sessionDAO.readSession(sessionId);
//            if(session != null) {
//            	sessionDAO.delete(session);
//                session.setAttribute(Constants.SESSION_FORCE_LOGOUT_KEY, Boolean.TRUE);
//            }
//        } catch (Exception e) {/*ignore*/}
//        redirectAttributes.addFlashAttribute("msg", "强制退出成功！");
//        return "redirect:/sessions";
//    }

}
