package com.janan.util;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;



import android.util.Log;
import android.util.Xml;

public class MobileService {
	private static final String TAG = "MobileService";
	//根据inputStream和mobile(用户输入的手机号)来对文件mobileaddress.xml的mobile进行替换
	public static String readSoapFile(InputStream inputStream, String mobile) throws Exception {
		byte[] data = StreamTool.readInputStream(inputStream);
		Log.i(TAG, "datalength: " + data.length);
		String soapxml = new String(data);
		Log.i(TAG, "soapxml: " + soapxml);
		Map<String, String> params = new HashMap<String, String>();
		params.put("mobile", mobile);
		return replace(soapxml, params);
	
	}
	
	//完成替换的函数
	private static String replace(String xml, Map<String, String> params) throws Exception {
		String result = xml;
		if(params != null && !params.isEmpty()) {
			for(Map.Entry<String, String> entry: params.entrySet()) {
				String name = "\\{1}quot; "+ entry.getKey();
				Pattern pattern = Pattern.compile(name);
				Matcher matcher = pattern.matcher(result);
			    if(matcher.find()) {
			    	result = matcher.replaceAll(entry.getValue());
			    }
			}
		}
		return result;
	}
	
	public static String getMobileAddress(InputStream inStream, String mobile) throws Exception {
	    String soap = readSoapFile(inStream, mobile);
	    byte[] data = soap.getBytes();
		URL url = new URL("http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);  //设置允许输出
		conn.setConnectTimeout(5 * 1000); //设置超时时间为5秒
		conn.setRequestMethod("POST");  
		conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
		conn.setRequestProperty("Content-Length", String.valueOf(data.length));  //设置长度
		
		OutputStream outputStream = conn.getOutputStream();
		outputStream.write(data);
		outputStream.flush();
		outputStream.close();
		
		if(conn.getResponseCode() == 200) {
			return parseResponseXML(conn.getInputStream());  //解析服务器端返回的数据
		}
		
		return null;
	}
	
	//解析返回的xml数据，并获得手机号归属地址
	private static String parseResponseXML(InputStream inputStream) throws XmlPullParserException, IOException {
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inputStream, "UTF-8");
		int eventType = parser.getEventType();  //产生第一个事件
		while(eventType != XmlPullParser.END_DOCUMENT) {  //只要不是文档结束事件
			switch(eventType) {
			case XmlPullParser.START_TAG: 
				String name = parser.getName(); //获取解析器当前指向的元素的名称
				if("getMobileCodeInfoResult".equals(name)) {
					return parser.nextText();
				}
				break;
			}
			eventType = parser.next();
		}
		return null;
	}
	
}