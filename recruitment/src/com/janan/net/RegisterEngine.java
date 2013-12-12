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
import android.widget.Toast;

import com.janan.data.bean.JobInfo;
import com.janan.data.bean.personal.User;
import com.janan.data.bean.search.NearSearch;
import com.janan.data.data.DataForSearchCondition;
import com.janan.recruit.BaseActivity;

public class RegisterEngine extends BaseEngine {
	private ArrayList<JobInfo> mList = null;
	private String mUserName;
	private String mPassWord;
	private String mConfirmPass;
	private String mEmail;
	private String mQQ;
	private String mUserTypeString;
	public RegisterEngine(BaseActivity activity,boolean isperson){
		super(activity, isperson);
	}
	public void start(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
				 addEncodePara(mUserName, "username", _Params);
				 addEncodePara(mEmail, "email", _Params);
				 addEncodePara(mPassWord, "password", _Params);
				 addEncodePara(mUserTypeString, "user_type", _Params);
				 addEncodePara(mQQ, "qq", _Params);
				 requestToNet(mRegisterUrl,_Params);
			}
		}).start();
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
				mActivity.callBack(mActivity.CallbackSuccess,
						_ResultObject.optString("result_text"));
			}
		} catch (Exception ex) {
			// 异常处理代码
			
			mActivity.callBack(mActivity.CallbackError, MSGHANDLERESULTERROR_STRING);
			Log.i("janan", "处理出错");
			ex.printStackTrace();
		}
	}

	public ArrayList<JobInfo> getmList() {
		
		return mList;
	}

	public void setmList(ArrayList<JobInfo> mList) {
		this.mList = mList;
	}
	public String getmUserName() {
		return mUserName;
	}
	public void setmUserName(String mUserName) {
		this.mUserName = mUserName;
	}
	public String getmPassWord() {
		return mPassWord;
	}
	public void setmPassWord(String mPassWord) {
		this.mPassWord = mPassWord;
	}
	public String getmConfirmPass() {
		return mConfirmPass;
	}
	public void setmConfirmPass(String mConfirmPass) {
		this.mConfirmPass = mConfirmPass;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmUserTypeString() {
		return mUserTypeString;
	}
	public void setmUserTypeString(String mUserTypeString) {
		this.mUserTypeString = mUserTypeString;
	}
	public String getmQQ() {
		return mQQ;
	}
	public void setmQQ(String mQQ) {
		this.mQQ = mQQ;
	}

	
}
