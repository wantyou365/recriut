package com.janan.net;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.janan.data.bean.JobFairs;
import com.janan.recruit.BaseActivity;

public class JobFairsListEngine extends BaseEngine {
	private int mPage;
	private ArrayList<JobFairs> mList;
	public JobFairsListEngine(BaseActivity activity, boolean isperson) {
		super(activity, isperson);
		// TODO Auto-generated constructor stub
	}

	public void start() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
				addEncodePara(mPage + "", "page", _Params);
				addEncodePara(mPageNum + "", "page_num", _Params);
				requestToNet(mGetJobFairsList, _Params);
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
			String _TextString;
			if (_ResultId == 0) {
				_TextString = _ResultObject.optString("result_text");
				mActivity.callBack(mActivity.CallbackError, _TextString);
			} else if (_ResultId == 1) {
				if(_ResultObject.has("records")){
					JSONArray _Records = null;
					try{
						_Records = _ResultObject.optJSONArray("records");
					}catch (Exception e) {
						// TODO: handle exception
					}
					if(_Records!=null&&_Records.length()>0){
						
						mList = new ArrayList<JobFairs>();
						for(int i=0;i<_Records.length();i++){
							JSONObject _InfoObject = _Records.optJSONObject(i);
							JobFairs _JobFairs = new JobFairs();
							_JobFairs.setmContent(_InfoObject.optString("content"));
							_JobFairs.setmDateString(_InfoObject.optString("DateAndTime"));
							_JobFairs.setmId(_InfoObject.optString("NewId"));
							_JobFairs.setmCity(_InfoObject.optString("Cityid"));
							_JobFairs.setmTitle(_InfoObject.optString("Title"));
							_JobFairs.setmCopyFrom(_InfoObject.optString("NewFrom"));
							_JobFairs.setmTypeName(_InfoObject.optString("TypeName"));
							_JobFairs.setmHitString(_InfoObject.optString("Hits"));
							mList.add(_JobFairs);
						}
						mActivity.callBack(mActivity.CallbackSuccess, null);
						
					}else{
						_TextString = "没有招聘会信息";
						mActivity.callBack(mActivity.CallbackError, _TextString);
					}
				}else{
					mActivity.callBack(mActivity.CallbackError,
							MSGHANDLERESULTERROR_STRING);
				}
				
			}
		} catch (Exception ex) {
			// 异常处理代码
			mActivity.callBack(mActivity.CallbackError,
					MSGHANDLERESULTERROR_STRING);
			ex.printStackTrace();
		}
	}

	public int getmPage() {
		return mPage;
	}

	public void setmPage(int mPage) {
		this.mPage = mPage;
	}

	public ArrayList<JobFairs> getmList() {
		return mList;
	}

	public void setmList(ArrayList<JobFairs> mList) {
		this.mList = mList;
	}
	
	
}
