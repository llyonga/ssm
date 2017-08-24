package com.io.ssm.system.security.shiro.realm;


import com.io.ssm.module.common.utils.config.Constants;
import com.io.ssm.system.security.shiro.authc.UsernamePasswordVerifyCodeToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.io.ssm.system.framework.collections.CData;
import com.io.ssm.system.security.login.LoginService;

/**
 * @date 2017年6月25日
 * @author lvyong
 * @version 2017-06-25 上午10:18:51
 */
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private LoginService loginService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取基于用户名和密码的令牌
		//实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		UsernamePasswordVerifyCodeToken passwordToken = (UsernamePasswordVerifyCodeToken) token;

		//首先校验验证码
		Session session = getSession();
		String code = (String)session.getAttribute(Constants.VERIFY_CODE);
		System.out.println(passwordToken.getVerifyCode());
		System.out.println(passwordToken.getVerifyCode().equalsIgnoreCase(code));
		if (passwordToken.getVerifyCode() == null || !passwordToken.getVerifyCode().equalsIgnoreCase(code)){
			throw new AuthenticationException("msg:验证码错误, 请重试.");
		}
		//查询数据库验证密码
		String username = passwordToken.getUsername();
		CData map = loginService.selectByUserName(username);
		if (map.isEmpty()) {
			throw new UnknownAccountException("account [ "+username+" ] is not exist！");
		}
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, map.get("password"), credentialsSalt, getName());

		return info;
	}
	

	/**
	 * 保存登录名
	 */
	private void setSession(Object key, Object value){
		Session session = getSession();
		System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
		if(null != session){
			session.setAttribute(key, value);
		}
	}

	private Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
		}catch (InvalidSessionException e){

		}
		return null;
	}



	public static void main(String[] args) {
		String hashAlgorithmName = "SHA-256"; //40941538609061b2c98b2cc12860cc52a6abab96230e16ad0f0011088fe52dd8
		Object credentials = "admin";    	  //40941538609061b2c98b2cc12860cc52a6abab96230e16ad0f0011088fe52dd8
		Object salt = ByteSource.Util.bytes("admin");;
		int hashIterations = 1024;

		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}

}

