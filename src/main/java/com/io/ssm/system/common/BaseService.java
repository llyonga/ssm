package com.io.ssm.system.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * @date 2017年6月27日
 * @author lvyong
 * @version 2017-06-27 下午10:19:32
 */
@Transactional(readOnly=true)
public abstract class BaseService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
}
