package com.janan.net;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.personal.User;
import com.janan.recruit.BaseActivity;
import com.janan.util.DBHelper;
import com.janan.util.LocationUtil;
import com.janan.util.NetConnectUtil;
import com.janan.util.Util;

public class BaseEngine implements EngineUtilInter {

	public BaseActivity mActivity = null;
	private boolean isWifiConnected = false;
	private boolean isMobileConnected = false;
	private NetConnectUtil netUtil = null;
	public static String msgString = null;
	public static String msgStringErrorString = null;
	public static String MSGHANDLERESULTERROR_STRING = null;
	public boolean isSuccessful = false;
	// public String strResult;
	public static CookieStore mPersonCookie;
	public static CookieStore mCompantCookie;
	public static final String TAG_SEAHCH = "search";
	public static final String BaseUrl = "http://app.dyteam.com/api/index.php";
	
	public static final String mSearchUrl = BaseUrl+"?module=job&method=search";
	public static final String mSearchResumeUrl = "http://app.dyteam.com/api/index.php?module=resume&method=search";
	public static final String mPersonLoginUrl = BaseUrl+"?module=user&method=login";
	public static final String mPersonLogoutUrl = "";
	public static final String mApplyJobUrl = BaseUrl+"?module=job&method=apply";
	public static final String mRegisterUrl = BaseUrl+"?module=user&method=register";
	public static final String mChangePassword = BaseUrl+"?module=user&method=updatePassword";
	public static final String mSeeMyResume = BaseUrl+"?module=user&method=getMessages";
	public static final String mApplyRecords = BaseUrl+"?module=user&method=getSendResumeRecords";
	public static final String mPersonChangePassWord = BaseUrl+"?module=user&method=updatePassword";
	public static final String mConpanyChangePassWord = BaseUrl+"?module=company&method=updatePassword";
	public static final String mCompanyRegister = BaseUrl+"?module=company&method=register";
	public static final String mCompanyLogin = BaseUrl+"?module=company&method=login";
	public static final String mCompanyChangeData = "http://app.dyteam.com/api/index.php?module=company&method=editCompanyInfo";
	public static final String mCompanyDataSearch = "http://app.dyteam.com/api/index.php?module=company&method=getCompanyInfo";
	public static final String mCompanyRecieveResume = "http://app.dyteam.com/api/index.php?module=company&method=getMessages";
	public static final String mSendNotify = "http://app.dyteam.com/api/index.php?module=Company&method=sendMessage";
	public static final String mJobsInfo = BaseUrl+"?module=job&method=view";
	public static final String mGetProvince = BaseUrl+"?module=gethat&method=getProvince";
	public static final String mGetCities = BaseUrl+"?module=gethat&method=getCity";
	public static final String mGetDistrict = BaseUrl+"?module=gethat&method=getArea";
	public static final String mGetJobDept = BaseUrl+"?module=gethat&method=getJobDept";
	public static final String mGetJobName = BaseUrl+"?module=gethat&method=getJobNames";
	public static final String mGetJobInfo = BaseUrl+"?module=job&method=view";
	public static final String mSendJobInfo = "http://app.dyteam.com/api/index.php?module=company&method=addJob";
	public static final String mChangResume = BaseUrl+"?module=resume&method=editPersonInfo";
	public static final String mSendResumeOut = "http://app.dyteam.com/api/index.php?module=user&method=sendOutMessage";
	public static final String mSendResumeOutRecords = "http://app.dyteam.com/api/index.php?module=user&method=getOutMessages";
	public static final String mGetResumePreview = BaseUrl+"?module=resume&method=view";
	public static final String mGetSubscribeInfo = "http://app.dyteam.com/api/index.php?module=job&method=getOrderJob";
	public static final String mSubscribeJob = "http://app.dyteam.com/api/index.php?module=job&method=orderJob";
	public static final String mGetNews = "http://app.dyteam.com/api/index.php?module=news&method=getList";
	public static final String mShiXi = "http://app.dyteam.com/api/index.php?module=news&method=getTrainNews";
	public static final String mGetNewsInfo = "http://app.dyteam.com/api/index.php?module=news&method=view";
	public static final String mGetJobFairsList = "http://app.dyteam.com/api/index.php?module=job&method=getJobFairs";
	public static final String mRefreshResume = "http://app.dyteam.com/api/index.php?module=resume&method=refresh";
	public static final int mPageNum = 20;

	public boolean isPerson;

	public BaseEngine(BaseActivity activity, boolean isperson) {
		this.mActivity = activity;
		this.isPerson = isperson;
		netUtil = new NetConnectUtil(mActivity);
		if(mActivity!=null&&msgString == null){
			msgString = mActivity.getString(R.string.toast_neterror);
			msgStringErrorString = mActivity.getString(R.string.toast_neterror1);
			MSGHANDLERESULTERROR_STRING = mActivity.getString(R.string.toast_neterror2);
			
		}
	}

	HttpResponse httpResponse;

	@Override
	public void requestToNet(String url, ArrayList<NameValuePair> params) {
		// TODO Auto-generated method stub
		HttpPost httpRequest = new HttpPost(url);
		try {
		
			// 发出HTTP request
			if (params != null) {
				httpRequest.setEntity(new UrlEncodedFormEntity(params,
						HTTP.UTF_8));
			}

			DefaultHttpClient _Client = new DefaultHttpClient();
			if (isPerson && mPersonCookie != null
					&& mPersonCookie.getCookies().size() > 0) {
				_Client.setCookieStore(mPersonCookie);
				
			} else if (!isPerson && mCompantCookie != null
					&& mCompantCookie.getCookies().size() > 0) {
				_Client.setCookieStore(mCompantCookie);
				
			}
			// 请求超时
			_Client.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 100000);
			// 读取超时
			_Client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					50000);

			// 取得HTTP response
			httpResponse = _Client.execute(httpRequest);
			if (isPerson) {
				if (_Client.getCookieStore() != null
						&& _Client.getCookieStore().getCookies().size() > 0) {
					mPersonCookie = _Client.getCookieStore();
					
				}
			} else {
				if (_Client.getCookieStore() != null
						&& _Client.getCookieStore().getCookies().size() > 0) {
					mCompantCookie = _Client.getCookieStore();
					
				}
			}
			String resultString = EntityUtils.toString(httpResponse.getEntity());
			if(resultString.startsWith("<")){
				String[] strs1 = resultString.split("\\{");
				resultString = strs1[1];
				String[] strs2 = resultString.split("\\}");
				resultString = "{"+strs2[0]+"}";
//				resultString = resultString.split("{")[1].split("}")[0];
			}
			parseResponse(resultString);

		} catch (Exception e) {
			mActivity.callBack(mActivity.CallbacknetError, msgStringErrorString);
			e.printStackTrace();
		}
		
		
	}
	public void addEncodePara(String value,String key,ArrayList<NameValuePair> _Params){
		try {
			if(value!=null){
				String keywordDataString = URLEncoder.encode(value.trim(),"GB18030");
				_Params.add(new BasicNameValuePair(key,
						keywordDataString));
			}else{
				_Params.add(new BasicNameValuePair(key,
						null));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			_Params.add(new BasicNameValuePair(key,
					null));
			e.printStackTrace();
		}
	}
	@Override
	public void parseResponse(String result) {
		// TODO Auto-generated method stub

		System.out.println();
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		if (netUtil != null) {
			isWifiConnected = netUtil.isWifiConnected();
			isMobileConnected = netUtil.isMobileConnected();
			if (!(isWifiConnected || isMobileConnected)) {
				mActivity.callBack(mActivity.CallbackError, msgString);
			}
			return (isWifiConnected || isMobileConnected);

		} else {

			return false;
		}

	}

	public boolean isWifiConnected() {
		isConnected();
		return isWifiConnected;
	}

	public boolean isMobileConnected() {
		isConnected();
		return isMobileConnected;
	}

	public String getMsgString() {
		return msgString;
	}
	
	public void start(){
		
	}

	public void start(ArrayList list){
		
	}
	
	public String replaceIntToString(String in,String[] replace,Integer[] source){
		if(replace.length == source.length){
			for(int i=0;i<replace.length;i++){
				in = in.replaceAll(source[i]+"", replace[i]);
			}
			return in;
		}else {
			return "";
		}
	}
}
