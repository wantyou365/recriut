package com.janan.net;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.janan.data.bean.personal.Resume;
import com.janan.data.data.JobDept;
import com.janan.data.data.Province;
import com.janan.recruit.BaseActivity;

public class RecieveResumesEngine extends BaseEngine{
	private ArrayList<Resume> mList = null;
	private String jobIdString;
	private String personIdString;
	private String dateString;
	private boolean isSend = false;
	public RecieveResumesEngine(BaseActivity activity, boolean isperson) {
		super(activity, isperson);
		// TODO Auto-generated constructor stub
	}
	public void start(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(isSend()){
					ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
					addEncodePara(jobIdString, "job_id", _Params);
					addEncodePara(personIdString, "per_id", _Params);
					addEncodePara(dateString, "date", _Params);
					requestToNet(mSendNotify, _Params);
					
				}else{
					requestToNet(mCompanyRecieveResume, null);
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
			
			String [] ss1 = result.split("\\{");
			JSONTokener jsonParser;
			if(ss1.length>2){
				jsonParser = new JSONTokener(result);
			}else{
				String str1 = "{"+ss1[ss1.length-1];
				jsonParser = new JSONTokener(str1);
			}
			
			// 此时还未读取任何json文本，直接读取就是一个JSONObject对象。
			// 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）
			JSONObject _ResultObject = (JSONObject) jsonParser.nextValue();
			int _ResultId = _ResultObject.optInt("result");
			
			if (_ResultId == 0) {	
				String _TextString = _ResultObject.optString("result_text");
				mActivity.callBack(mActivity.CallbackError,_TextString);
			} else if (_ResultId == 1) {
				if(isSend){
					mActivity.callBack(mActivity.CallbackSendSuccess,null);
				}else{
					int count = _ResultObject.optInt("total_count");
					if(count>0){
						mList = new ArrayList<Resume>();
						JSONArray _Array = _ResultObject.optJSONArray("records");
						for(int i=0;i<_Array.length();i++){
							Resume _Resume = new Resume();
							JSONObject _Object = _Array.optJSONObject(i);
							_Resume.setmTitle(_Object.optString("Title"));
							_Resume.setmComid(_Object.optString("Comid"));
							_Resume.setmUserId(_Object.optString("Perid"));
							_Resume.setmJobid(_Object.optString("Jobid"));
							_Resume.setmAddDate(_Object.optString("AddDate"));
							_Resume.setmNameString(_Object.optString("Memo"));
							_Resume.setmJobName(_Object.optString("JobName"));
							mList.add(_Resume);
						}
						mActivity.callBack(mActivity.CallbackSuccess,null);
					}else{
						mActivity.callBack(mActivity.CallbackError,mActivity.toastNoResumes);
					}
				}
				

			}
		} catch (Exception ex) {
			// 异常处理代码
			mActivity.callBack(mActivity.CallbackError,MSGHANDLERESULTERROR_STRING);
			ex.printStackTrace();
		}
	}
	public ArrayList<Resume> getmList() {
		return mList;
	}
	public void setmList(ArrayList<Resume> mList) {
		this.mList = mList;
	}
	public String getJobIdString() {
		return jobIdString;
	}
	public void setJobIdString(String jobIdString) {
		this.jobIdString = jobIdString;
	}
	public String getPersonIdString() {
		return personIdString;
	}
	public void setPersonIdString(String personIdString) {
		this.personIdString = personIdString;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public boolean isSend() {
		return isSend;
	}
	public void setSend(boolean isSend) {
		this.isSend = isSend;
	}

	

}
