package com.io.ssm.system.security.login;


import com.io.ssm.system.security.login.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.ssm.system.common.BaseService;
import com.io.ssm.system.framework.collections.CData;


/**
 * @date 2017年6月25日
 * @author lvyong
 * @version 2017-06-25 上午10:15:27
 */
@Service
public class LoginService extends BaseService{
	
	@Autowired
	private LoginDao loginDao;

	public CData selectByUserName(String username) {
		return loginDao.selectByUserName(username);
	}

	public void updateUserInfo(String remoteHost, String username) {
		loginDao.updateUserInfo(remoteHost,username);
	}
}
