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
import com.janan.data.bean.personal.Resume;
import com.janan.data.data.City;
import com.janan.data.data.DataForSearchCondition;
import com.janan.data.data.Province;
import com.janan.recruit.BaseActivity;

public class ResumeInfoEngine extends BaseEngine{
	private Resume mResume;
	private String perId = null;
	public ResumeInfoEngine(BaseActivity activity, boolean isperson) {
		super(activity, isperson);
		// TODO Auto-generated constructor stub
	}
	public void start(){		
		new Thread(new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
				if(perId == null){
					addEncodePara(mActivity.mUser.getmUserID(), "per_id", _Params);
				}else{
					addEncodePara(perId, "per_id", _Params);
				}
				
				requestToNet(mGetResumePreview, _Params);
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
			
			if (_ResultId == 0) {	
				String _TextString = _ResultObject.optString("result_text");
				mActivity.callBack(mActivity.CallbackError,_TextString);
			} else if (_ResultId == 1) {
				mResume = new Resume();
				JSONObject _PersonInfoObj = _ResultObject.optJSONObject("person_info");
				JSONObject _BaseInfoObj = _ResultObject.optJSONObject("person_base");
				if(_PersonInfoObj!=null){
					mResume.setmEmailString(_BaseInfoObj.optString("UserEmail"));
					mResume.setmUserId(_PersonInfoObj.optString("PerId"));
					mResume.setmNameString(_PersonInfoObj.optString("RealName"));
					mResume.setmPhoneNum(_PersonInfoObj.optString("Mt"));
					mResume.setmJobContentString(_PersonInfoObj.optString("JobContent"));
					mResume.setmSexString(DataForSearchCondition.getXingbie4SearchString(_PersonInfoObj.optInt("Sex")));
					mResume.setmMinZuString(_PersonInfoObj.optString("Nation"));
					replaceIntToString(_PersonInfoObj.optString("Edus"), DataForSearchCondition.mXueliyaoqiuStrings, DataForSearchCondition.mXueliyaoqiuInts);
					mResume.setmYihun(null);
					if(_PersonInfoObj.optInt("Age") == 0){
						mResume.setmAgeString(null);
					}else{
						mResume.setmAgeString(_PersonInfoObj.optString("Age")+"");
					}
					if(_PersonInfoObj.optInt("BirthYear") == 0){
						mResume.setmBirthday(null);
						mResume.setmBirth_Year(null);
						mResume.setmBirth_Month(null);
						mResume.setmBirth_Day(null);					
					}else{
						mResume.setmBirth_Year(_PersonInfoObj.optString("BirthYear"));
						mResume.setmBirth_Month(_PersonInfoObj.optString("BirthMonth"));
						mResume.setmBirth_Day(_PersonInfoObj.optString("BirthDay"));
						mResume.setmBirthday(_PersonInfoObj.optString("BirthYear")+"-"+_PersonInfoObj.optString("BirthMonth")+"-"+_PersonInfoObj.optString("BirthDay"));					
					}
					mResume.setmDoor_City(_PersonInfoObj.optString("Door_City"));
					mResume.setmDoor_AreaString(_PersonInfoObj.optString("Door_Area"));
					mResume.setmResidence_City(_PersonInfoObj.optString("Locus_City"));
					mResume.setmResidence_Area(_PersonInfoObj.optString("Locus_Area"));
					if("0".equals(_PersonInfoObj.optString("Works"))){
						mResume.setmWorkingYears(null);
					}else{
//						mResume.setmWorkingYears(_PersonInfoObj.optString("Works")+"年");
						mResume.setmWorkingYears(replaceIntToString(_PersonInfoObj.optString("Works"), DataForSearchCondition.mGongzuonianxianStrings, DataForSearchCondition.mGongzuonianxianInts));		
					}
					mResume.setmQiwangyuexin(_PersonInfoObj.optString("Deal"));
					mResume.setmMsnOrQQ(_PersonInfoObj.optString("Msnqq"));
					mResume.setmIntentionJobClass(_PersonInfoObj.optString("WorkWillClass1"));
					mResume.setmIntentionJob(_PersonInfoObj.optString("WorkWill1"));
					mResume.setmIntentionJob1(_PersonInfoObj.optString("WorkWill2"));
					mResume.setmIntentionJob2(_PersonInfoObj.optString("WorkWill3"));
					mResume.setmIntentionPlaceString(_PersonInfoObj.optString("AreaWill1"));
					mResume.setmIntentionPlaceString1(_PersonInfoObj.optString("AreaWill2"));
					mResume.setmIntentionPlaceString2(_PersonInfoObj.optString("AreaWill3"));
					mResume.setmLastSchoolString(_PersonInfoObj.optString("LastSchool"));
					mResume.setmZhuanYeString(_PersonInfoObj.optString("Speciality"));
					mResume.setmZhuanyeClass(_PersonInfoObj.optString("ZyClass"));
					if("0".equals(_PersonInfoObj.optString("Byear"))){
						mResume.setmBiyeYear(null);
					}else{
						mResume.setmBiyeYear(_PersonInfoObj.optString("Byear"));
					}
					
					mResume.setmJianjie(_PersonInfoObj.optString("Appraise"));
				}
				if(_BaseInfoObj!=null){
					mResume.setmUserId(_BaseInfoObj.optString("perId"));
					mResume.setmUsername(_BaseInfoObj.optString("UserName"));
				}
				
				mActivity.callBack(mActivity.CallbackSuccess,null);
			}
		} catch (Exception ex) {
			// 异常处理代码
			mActivity.callBack(mActivity.CallbackError,MSGHANDLERESULTERROR_STRING);
			ex.printStackTrace();
		}
	}
	public Resume getmResume() {
		return mResume;
	}
	public void setmResume(Resume mResume) {
		this.mResume = mResume;
	}
	public String getPerId() {
		return perId;
	}
	public void setPerId(String perId) {
		this.perId = perId;
	}

	
	
}
