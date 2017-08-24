package com.io.ssm.system.security.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.io.ssm.module.common.utils.config.Constants;
import com.io.ssm.module.common.utils.NetworkUtil;
import com.io.ssm.module.common.utils.VerifyCodeUtils;
import com.io.ssm.system.security.shiro.authc.UsernamePasswordVerifyCodeToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


/**
 * @date 2017年6月27日
 * @author lvyong
 * @version 2017-06-27 下午10:00:16
 */
@Controller
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="login",method = RequestMethod.POST)
	public String login(HttpServletRequest request) throws Exception{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verifyCode = request.getParameter("verifyCode");
		String rememberMe = request.getParameter("rememberMe");
		
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			String host = NetworkUtil.getIpAddress(request);
			System.out.println("工具类获取ip："+host);
			UsernamePasswordVerifyCodeToken token;
			if ("Y".equals(rememberMe)) {
				token = new UsernamePasswordVerifyCodeToken(username, password.toCharArray(),true, host, verifyCode);
			}else{
				token = new UsernamePasswordVerifyCodeToken(username, password.toCharArray(),false, host, verifyCode);
			}


			try {
				currentUser.login(token);

				loginService.updateUserInfo(request.getRemoteHost(),username);

				logger.info("username ：[ "+username+" ] login successfully");
			} catch (AuthenticationException e) {
				logger.info("username ：[ "+username+" ] login failed");
				e.printStackTrace();
			}
		}else {
			logger.info("【你已经登录记住密码，无需再次认证！】");
		}

		if (currentUser.isRemembered()) {
			logger.info("这是判断isRemembered方法。。。。");
		}
		return "home";
	}


	/**
	 * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
	 */
	@RequestMapping("/captcha")
	@ResponseBody
	public String captcha(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		/*//设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		String verifyCode = VerifyCodeUtils.createVerifyCode(4);
		//将验证码放到HttpSession里面
		request.getSession().setAttribute(Constants.VERIFY_CODE, verifyCode);
		System.out.println("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
		//设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtils.createVerifyCodeImage(1000,1000,verifyCode);
		//写给浏览器
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());*/

		String verifyCode = VerifyCodeUtils.createVerifyCode(4);
		request.getSession().setAttribute(Constants.VERIFY_CODE,verifyCode);
		logger.info("验证码【"+verifyCode+" 】已放入session中");

		return verifyCode;
	}

}
