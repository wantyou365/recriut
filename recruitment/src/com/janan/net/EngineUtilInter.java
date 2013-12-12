package com.janan.net;

import java.util.ArrayList;

import org.apache.http.NameValuePair;


/**
 * 网络引擎接口
 * */
public interface EngineUtilInter {

	
	
	/**
	 * 发起网络请求
	 * */
	public void requestToNet(String url,ArrayList<NameValuePair> params);

	

	/**
	 * 解析网络返回数据
	 * */
	public void parseResponse(String result);

	/**
	 * 验证联网信息
	 * */
	public boolean isConnected();
}
