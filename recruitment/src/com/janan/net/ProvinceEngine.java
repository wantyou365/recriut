package com.janan.net;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

import com.janan.data.bean.JobInfo;
import com.janan.data.data.Province;
import com.janan.recruit.BaseActivity;
import com.janan.util.Util;

public class ProvinceEngine extends BaseEngine{
	private ArrayList<Province> mList = null;
	public ProvinceEngine(BaseActivity activity, boolean isperson) {
		super(activity, isperson);
		// TODO Auto-generated constructor stub
	}
	public void start(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				requestToNet(mGetProvince, null);
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
	private boolean canTop = false;
	@Override
	public void parseResponse(String result) {
		// TODO Auto-generated method stub
		try {
			JSONTokener jsonParser = new JSONTokener(result);
			// 此时还未读取任何json文本，直接读取就是一个JSONObject对象。
			// 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）
			SharedPreferences _pre = PreferenceManager.getDefaultSharedPreferences(mActivity);
    		Editor _editEditor = _pre.edit();
    		
    		String _province = _pre.getString("province", "");
			JSONObject _ResultObject = (JSONObject) jsonParser.nextValue();
			int _ResultId = _ResultObject.optInt("result");
			
			if (_ResultId == 0) {	
				String _TextString = _ResultObject.optString("result_text");
				mActivity.callBack(mActivity.CallbackError,_TextString);
			} else if (_ResultId == 1) {
				mList = new ArrayList<Province>();
				JSONArray _Array= _ResultObject.optJSONArray("provinces");
				for(int i=0;i<_Array.length();i++){
					JSONObject proObject = _Array.optJSONObject(i);
					Province _Province = new Province();
					_Province.setmId(proObject.optString("provinceID"));
					_Province.setmName(proObject.optString("province"));
					if(Util.checkString(_province)){
						if(_Province.getmName().contains(_province)){
							canTop = true;
						}
					}
					if(canTop){
						mList.add(0, _Province)	;
						canTop = false;
					}else{
						mList.add(_Province);
					}
				}
				mActivity.callBack(mActivity.CallbackSuccess,null);
			}
		} catch (Exception ex) {
			// 异常处理代码
			mActivity.callBack(mActivity.CallbackError,MSGHANDLERESULTERROR_STRING);
			ex.printStackTrace();
		}
	}

	public ArrayList<Province> getmList() {
		
		return mList;
	}

	public void setmList(ArrayList<Province> mList) {
		this.mList = mList;
	}

}
