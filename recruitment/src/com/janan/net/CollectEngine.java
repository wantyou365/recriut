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
import com.janan.data.bean.search.NearSearch;
import com.janan.data.data.DataForSearchCondition;
import com.janan.recruit.BaseActivity;

public class CollectEngine extends BaseEngine {
	private ArrayList<JobInfo> mList = null;
	private JobInfo mInfo;
	public CollectEngine(BaseActivity activity,boolean isperson){
		super(activity,isperson);
	}
	
	public JobInfo getmInfo() {
		return mInfo;
	}

	public void setmInfo(JobInfo mInfo) {
		this.mInfo = mInfo;
	}

	public void start(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
				addEncodePara(mInfo.getmJobId(), "job_id", _Params);
				addEncodePara(mInfo.getmComId(), "com_id", _Params);
				requestToNet(mApplyJobUrl, _Params);
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
			// 此时还未读取任何json文本，直接读取就是一个JSONObject对象。
			// 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）
			JSONObject _ResultObject = (JSONObject) jsonParser.nextValue();
			int _ResultId = _ResultObject.optInt("result");
			String _TextString = _ResultObject.optString("result_text");
			if (_ResultId == 0) {			
				mActivity.callBack(mActivity.CallbackError,_TextString);
			} else if (_ResultId == 1) {
				mActivity.callBack(mActivity.CallbackApplySuccess,null);
			}
		} catch (Exception ex) {
			// 异常处理代码
			mActivity.callBack(mActivity.CallbackError,MSGHANDLERESULTERROR_STRING);
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

	
}
