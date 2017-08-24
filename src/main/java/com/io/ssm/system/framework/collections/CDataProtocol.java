package com.io.ssm.system.framework.collections;
/**
 * @date 2017年6月25日
 * @author lvyong
 * @version 2017-06-25 上午9:51:07
 */

import java.util.LinkedHashMap;
import java.util.Map;

public class CDataProtocol extends LinkedHashMap {
	private static final long serialVersionUID = 1L;
	protected String name = null;

	protected boolean nullToInitialize = false;
	// protected RMetaData metaData;

	public CDataProtocol(int arg0, float arg1) {
		super(arg0, arg1);
	}

	public CDataProtocol(int arg0) {
		super(arg0);
	}

	public CDataProtocol() {
	}

	public CDataProtocol(Map arg0) {
		super(arg0);
	}

	public CDataProtocol(int arg0, float arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNullToInitialize() {
		return this.nullToInitialize;
	}

	public void setNullToInitialize(boolean nullToInitialize) {
		this.nullToInitialize = nullToInitialize;
	}

	/*
	 * public RMetaData getMetaData() { return this.metaData; }
	 * 
	 * public void setMetaData(RMetaData metaData) { this.metaData = metaData; }
	 */
}