package com.janan.net;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

import com.janan.data.bean.personal.CoverLetter;
import com.janan.data.bean.personal.User;
import com.janan.recruit.BaseActivity;

public class CompanyEmailEngine extends BaseEngine {

	private int mPage;
	private ArrayList<CoverLetter> mList;
	private int mTag;
	public CompanyEmailEngine(BaseActivity activity,boolean isperson) {
		super(activity,isperson);
		// TODO Auto-generated constructor stub
	}

	public int getmTag() {
		return mTag;
	}

	public void setmTag(int mTag) {
		this.mTag = mTag;
	}

	public void setmList(ArrayList<CoverLetter> mList) {
		this.mList = mList;
	}

	public void start() {
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
				addEncodePara(mPage+"", "page", _Params);
				addEncodePara(mPageNum+"", "page_num", _Params);
				if(mTag ==1){
					requestToNet(mSeeMyResume, _Params);
				}else if(mTag ==2){
					requestToNet(mApplyRecords, _Params);
				}else if(mTag ==3){
					requestToNet(mApplyRecords, _Params);//没有职位收藏url
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
	boolean isRecords = true;
	@Override
	public void parseResponse(String result) {
		// TODO Auto-generated method stub
		try {
			JSONTokener jsonParser = new JSONTokener( result);
			JSONObject _ResultObject = (JSONObject) jsonParser.nextValue();
			int _ResultId = _ResultObject.optInt("result");
			if (_ResultId == 0) {
				String _TextString = _ResultObject.optString("result_text");
				mActivity.callBack(mActivity.CallbackError, _TextString);
			} else if (_ResultId == 1) {
				
				mList = new ArrayList<CoverLetter>();
				int _Count = _ResultObject.optInt("total_count");
				if(_Count>0){
					JSONArray _Records = null;
					
					try {
						_Records = _ResultObject.optJSONArray("records");
					} catch (Exception e) {
						// TODO: handle exception	
						if(mTag == 2){
							mActivity.callBack(mActivity.CallbackError, "您现在还没有求职记录");							
							
						}
						isRecords = false;
					}
					if(isRecords){
						for(int i=0;i<_Records.length();i++){
							JSONObject _Result =  _Records.optJSONObject(i);
							CoverLetter _Letter = new CoverLetter();
							_Letter.setJobId(_Result.optString("Jobid"));
							if(_Result.has("Memo")){
								_Letter.setmContentString(_Result.optString("Memo"));
							}
							
							_Letter.setmDate(_Result.optString("AddDate"));
							_Letter.setmTitleString(_Result.optString("Title"));
							_Letter.setmJobName(_Result.optString("JobName"));
							_Letter.setmUerId(_Result.optString("Perid"));
							mList.add(_Letter);
						}
						mActivity.callBack(mActivity.CallbackSuccess, null);
					}
					
				}else{
					if(mTag == 2){
						mActivity.callBack(mActivity.CallbackError, "您现在还没有求职记录");							
						
					}else if(mTag == 1){
						mActivity.callBack(mActivity.CallbackError, "您现在还没有企业来信");
					}
				}
				
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

	public ArrayList<CoverLetter> getmList() {
		return mList;
	}

}
