package com.janan.net;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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

public class LogoutEngine extends BaseEngine {

	private User mUser;
	public LogoutEngine(BaseActivity activity,boolean isperson){
		super(activity, isperson);
	}
	public void startLogout(){
		
	}
	public void startSearch(NearSearch searchkeys, int page){

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
			// 此时还未读取任何json文本，直接读取就是一个JSONObject对象。
			// 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）
			JSONObject _ResultObject = (JSONObject) jsonParser.nextValue();
			int _ResultId = _ResultObject.optInt("result");
			if (_ResultId == 0) {
				String _TextString = _ResultObject.optString("result_text");
				Toast.makeText(mActivity, _TextString, Toast.LENGTH_LONG)
						.show();
			} else if (_ResultId == 1) {

			
//				mActivity.callBack(true);
			}
		} catch (Exception ex) {
			// 异常处理代码
//			mActivity.callBack(false);
			Log.i("janan", "处理出错");
			ex.printStackTrace();
		}
	}
	public User getmUser() {
		return mUser;
	}
	public void setmUser(User mUser) {
		this.mUser = mUser;
	}
	
}
