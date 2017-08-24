package com.io.ssm.system.security.login;

import com.io.ssm.system.framework.collections.CData;
import com.io.ssm.system.framework.persistence.annotation.MyRepository;

/**
 * @date 2017年6月25日
 * @author lvyong
 * @version 2017-06-25 上午10:15:38
 */
@MyRepository
public interface LoginDao {

	CData selectByUserName(String username);

    void updateUserInfo(String remoteHost, String username);
}
