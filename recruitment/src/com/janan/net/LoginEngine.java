package com.janan.net;

import java.util.ArrayList;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;


import com.janan.data.bean.JobInfo;
import com.janan.data.bean.company.CompanyInfo;
import com.janan.data.bean.personal.User;
import com.janan.data.bean.search.NearSearch;
import com.janan.data.data.DataForSearchCondition;
import com.janan.recruit.BaseActivity;

public class LoginEngine extends BaseEngine {

	private String mEmail;
	private String mPassWord;

	public LoginEngine(BaseActivity activity, boolean isperson) {
		super(activity, isperson);
	}

	
	public String getmEmail() {
		return mEmail;
	}


	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}


	public String getmPassWord() {
		return mPassWord;
	}


	public void setmPassWord(String mPassWord) {
		this.mPassWord = mPassWord;
	}


	public void start() {
	
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();

				addEncodePara(mEmail, "email", _Params);
				addEncodePara(mPassWord, "password", _Params);
				if (isPerson) {
					requestToNet(mPersonLoginUrl, _Params);
				} else {
					requestToNet(mCompanyLogin, _Params);
				}
			}
		}).start();

		// ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
		// _Params.add(new BasicNameValuePair("username","wantyou365"));
		// _Params.add(new BasicNameValuePair("email","wantyou365@126.com"));
		// _Params.add(new BasicNameValuePair("password","123321"));
		// _Params.add(new BasicNameValuePair("user_type","2"));
		// requestToNet(mRegisterUrl,_Params);
	}

	@Override
	public void requestToNet(String url, ArrayList<NameValuePair> params) {
		// TODO Auto-generated method stub
		if (super.isConnected()) {
			super.requestToNet(url, params);
		}

	}

	@Override
	public void parseResponse(String result) {
		// TODO Auto-generated method stub
		try {
			
				JSONTokener jsonParser = new JSONTokener(result);
				JSONObject _ResultObject = (JSONObject) jsonParser.nextValue();
				int _ResultId = _ResultObject.optInt("result");
				if (_ResultId == 0) {
					String _TextString = _ResultObject.optString("result_text");
					mActivity.callBack(mActivity.CallbackError, _TextString);
				} else if (_ResultId == 1) {
					if(isPerson){
						mActivity.mUser = new User();
						JSONObject _UserInfoJsonObject = _ResultObject
								.optJSONObject("userinfo");
						mActivity.mUser.setmUserType(_UserInfoJsonObject
								.optString("UserType"));
						mActivity.mUser.setmComId(_UserInfoJsonObject
								.optString("Comid"));
						mActivity.mUser.setmUserID(_UserInfoJsonObject
								.optString("perId"));
						mActivity.mUser.setmUserName(_UserInfoJsonObject
								.optString("UserName"));
						mActivity.mUser.setmUserEmail(_UserInfoJsonObject
								.optString("UserEmail"));
						// mActivity.mUser.setmPassWord(_UserInfoJsonObject.optString("Password"));
						mActivity.mUser.setmPassWord(mPassWord);
						mActivity.callBack(mActivity.handle_Login, null);
					}else{
						mActivity.mCompany = new CompanyInfo();
						JSONObject _UserInfoJsonObject = _ResultObject
								.optJSONObject("userinfo");
						mActivity.mCompany.setmCompanyId(_UserInfoJsonObject
								.optString("Comid"));
						mActivity.mCompany.setmEmail(_UserInfoJsonObject
								.optString("Email"));
						mActivity.mCompany.setmUserName(_UserInfoJsonObject
								.optString("UserName"));
						mActivity.mCompany.setmPassWord(mPassWord);
						mActivity.mCompany.setmCompanyName(_UserInfoJsonObject.optString("CompanyName"));
						mActivity.callBack(mActivity.handle_Login, null);
					}
					
				}
			
		} catch (Exception ex) {
			// 异常处理代码

			mActivity.callBack(mActivity.CallbackError,
					MSGHANDLERESULTERROR_STRING);
			Log.i("janan", "处理出错");
			ex.printStackTrace();
		}
	}

}
