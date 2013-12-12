package com.janan.net;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

import com.janan.data.bean.JobInfo;
import com.janan.data.bean.personal.CoverLetter;
import com.janan.data.bean.personal.User;
import com.janan.recruit.BaseActivity;

public class ApplyRecoedsEngine extends BaseEngine {

	private int mPage;
	private ArrayList<JobInfo> mList;
	public ApplyRecoedsEngine(BaseActivity activity,boolean isperson) {
		super(activity,isperson);
		// TODO Auto-generated constructor stub
	}

	public void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
				addEncodePara(mPage+"", "page", _Params);
				addEncodePara(mPageNum+"", "page_num", _Params);
				requestToNet(mSeeMyResume, _Params);
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
			Log.i("janan", "求职记录"+EntityUtils.toString(httpResponse.getEntity()));
			JSONObject _ResultObject = (JSONObject) jsonParser.nextValue();
			int _ResultId = _ResultObject.getInt("result");
			if (_ResultId == 0) {
				String _TextString = _ResultObject.getString("result_text");
				mActivity.callBack(mActivity.CallbackError, _TextString);
			} else if (_ResultId == 1) {
				mList = new ArrayList<JobInfo>();
				JSONArray _Records = _ResultObject.optJSONArray("records");
//				for(int i=0;i<_Records.length();i++){
//					JSONObject _Result =  _Records.getJSONObject(i);
//					CoverLetter _Letter = new CoverLetter();
//					_Letter.setJobId(_Result.getString("Jobid"));
//					_Letter.setmContentString(_Result.getString("Memo"));
//					_Letter.setmDate(_Result.getString("AddDate"));
//					_Letter.setmTitleString(_Result.getString("Title"));
//					_Letter.setmJobName(_Result.getString("JobName"));
//					_Letter.setmUerId(_Result.getString("Perid"));
//					mList.add(_Letter);
//				}
				mActivity.callBack(mActivity.CallbackSuccess, null);
			}
		} catch (Exception ex) {
			// 异常处理代码
			
			mActivity.callBack(mActivity.CallbackError, MSGHANDLERESULTERROR_STRING);
			ex.printStackTrace();
		}
	}

	public int getmPage() {
		return mPage;
	}

	public void setmPage(int mPage) {
		this.mPage = mPage;
	}

	public ArrayList<JobInfo> getmList() {
		return mList;
	}

}
