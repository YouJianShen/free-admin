package com.suit.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * extends UsernamePasswordToken for captcha
 *
 * @author hsy
 */
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {
 
	private static final long serialVersionUID = 1L;
 
	private String captcha;
 
	public String getCaptcha() {
		return captcha;
	}
 
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
 
	public UsernamePasswordCaptchaToken() {
		super();
 
	}
	public UsernamePasswordCaptchaToken(final String username, final String password) {
		super(username,password);
		
	}
 
	public UsernamePasswordCaptchaToken(String username, char[] password,
			boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}
 
}