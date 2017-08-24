package com.io.ssm.system.security.shiro.authc;

/**
 * @author lenovo
 * @version 2017-07-22 09:19:23
 * @date 2017年07月22日
 */
public class UsernamePasswordVerifyCodeToken extends org.apache.shiro.authc.UsernamePasswordToken {
    private static final long serialVersionUID = 1L;
    private String verifyCode;

    public UsernamePasswordVerifyCodeToken() {
    }

    public UsernamePasswordVerifyCodeToken(String username, char[] password, boolean rememberMe, String host, String verifyCode) {
        super(username, password, rememberMe, host);
        this.verifyCode = verifyCode;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}