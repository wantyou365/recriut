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

import com.gazecloud.dyteam.R;
import com.janan.data.bean.JobInfo;
import com.janan.data.bean.personal.User;
import com.janan.data.bean.search.NearSearch;
import com.janan.data.data.DataForSearchCondition;
import com.janan.recruit.BaseActivity;

public class ChangePassWordEngine extends BaseEngine {

	private String mOldpass;
	private String mNewPass;
	private boolean isPerson;
	public ChangePassWordEngine(BaseActivity activity,boolean isperson){
		super(activity,isperson);
	}
	public void start(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
				addEncodePara(mOldpass, "old_password", _Params);
				addEncodePara(mNewPass, "new_password", _Params);
				if(isPerson()){
					requestToNet(mPersonChangePassWord, _Params);
				}else{
					requestToNet(mConpanyChangePassWord, _Params);
				}
				
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
			int _ResultId = _ResultObject.getInt("result");
			if (_ResultId == 0) {
				String _TextString = _ResultObject.optString("result_text");
				mActivity.callBack(mActivity.CallbackError, _TextString);
			} else if (_ResultId == 1) {
				String _TextString = mActivity.getString(R.string.change_sccuess);
				mActivity.callBack(mActivity.CallbackSuccess, _TextString);
			}
		} catch (Exception ex) {
			// 异常处理代码
			
			mActivity.callBack(mActivity.CallbackError, MSGHANDLERESULTERROR_STRING);
			ex.printStackTrace();
		}
	}
	public String getmOldpass() {
		return mOldpass;
	}
	public void setmOldpass(String mOldpass) {
		this.mOldpass = mOldpass;
	}
	public String getmNewPass() {
		return mNewPass;
	}
	public void setmNewPass(String mNewPass) {
		this.mNewPass = mNewPass;
	}
	public boolean isPerson() {
		return isPerson;
	}
	public void setPerson(boolean isPerson) {
		this.isPerson = isPerson;
	}
	



	
}
