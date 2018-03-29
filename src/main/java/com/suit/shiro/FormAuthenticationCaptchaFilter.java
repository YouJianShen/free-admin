package com.suit.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.suit.core.web.servlet.CaptchaServlet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hsy
 */
public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {
    private static final Logger log = LoggerFactory
            .getLogger(FormAuthenticationCaptchaFilter.class);

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {

        return captchaParam;

    }

    protected String getCaptcha(ServletRequest request) {

        return WebUtils.getCleanParam(request, getCaptchaParam());

    }

    protected AuthenticationToken createToken(

            ServletRequest request, ServletResponse response) {

        String username = getUsername(request);

        // String password = String.valueOf(Hex.encode(CodecSupport
        // .toBytes(getPassword(request))));
        String password = getPassword(request);

        String captcha = getCaptcha(request);

        boolean rememberMe = isRememberMe(request);

        String host = getHost(request);

        return new UsernamePasswordCaptchaToken(username,
                password.toCharArray(), rememberMe, host, captcha);

    }

    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                String exitCode = (String) SecurityUtils.getSubject().getSession()
                        .getAttribute(CaptchaServlet.KEY_CAPTCHA);

                String captcha = getCaptcha(request);

                if (captcha == null || !captcha.toLowerCase().equals(exitCode.toLowerCase())) {
                    AuthenticationToken token = this.createToken(request, response);
                    request.setAttribute("error", "com.easyfee.shiro.CaptchaException");
                    return this.onLoginFailure(token, new CaptchaException(), request, response);
                } else {
                    return executeLogin(request, response);
                }


            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                // allow them to see the login page ;)
                // HttpServletResponse r = (HttpServletResponse)response;
                // r.setStatus(401);
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the "
                        + "Authentication url [" + getLoginUrl() + "]");
            }

            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }

    protected void saveRequestAndRedirectToLogin(ServletRequest request,
                                                 ServletResponse response) throws IOException {
        saveRequest(request);
        redirectToLogin(request, response);
    }

    protected void redirectToLogin(ServletRequest request,
                                   ServletResponse response) throws IOException {
        String loginUrl = getLoginUrl();
        WebUtils.issueRedirect(request, response, loginUrl);
    }

}