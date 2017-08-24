package com.io.ssm.system.common;
/**
 * @date 2017年6月26日
 * @author lvyong
 * @version 2017-06-26 下午9:58:58
 */

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.io.ssm.system.framework.collections.CData;

public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected CData data;
	
	@SuppressWarnings("unchecked")
	@ModelAttribute
	public void getData(@RequestParam HashMap<String, String> map,HttpServletRequest request) {
		data = new CData();
		data.putAll(map);
	} 

}
