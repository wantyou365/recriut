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
import com.janan.util.Util;

public class ResumeInfoChangeEngine extends BaseEngine{
	private Resume mResume;
	public ResumeInfoChangeEngine(BaseActivity activity, boolean isperson) {
		super(activity, isperson);
		// TODO Auto-generated constructor stub
	}
	
	
	public Resume getmResume() {
		return mResume;
	}


	public void setmResume(Resume mResume) {
		this.mResume = mResume;
	}


	public void start(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();	
				if(Util.isConditionNull(mResume.getmNameString())){
					_Params.add(new BasicNameValuePair("real_name", null));
				}else{
					addEncodePara(mResume.getmNameString(), "real_name", _Params);
				}
				if(Util.isConditionNull(mResume.getmJobContentString())){
					_Params.add(new BasicNameValuePair("job_content", null));
				}else{
					addEncodePara(mResume.getmJobContentString(), "job_content", _Params);
				}
				if(Util.isConditionNull(mResume.getmPhoneNum())){
					_Params.add(new BasicNameValuePair("mt", null));
				}else{
					addEncodePara(mResume.getmPhoneNum(), "mt", _Params);
				}
				if(Util.isConditionNull(mResume.getmSexString())){
					_Params.add(new BasicNameValuePair("sex", null));
				}else{
					_Params.add(new BasicNameValuePair("sex",DataForSearchCondition.getXingbie4Search(mResume.getmSexString()) +""));
				}
				if(Util.isConditionNull(mResume.getmMinZuString())){
					_Params.add(new BasicNameValuePair("nation", null));
				}else{
					addEncodePara(mResume.getmMinZuString(), "nation", _Params);
				}
				if(Util.isConditionNull(mResume.getmEducationString())){
					_Params.add(new BasicNameValuePair("edus", null));
				}else{
					addEncodePara(DataForSearchCondition.getXueliyaoqiu(mResume.getmEducationString()) +"", "edus", _Params);
				}
				
				addEncodePara(mResume.getmLastSchoolString(), "last_school", _Params);
				addEncodePara(mResume.getmZhuanyeClass(), "zy_class", _Params);
				addEncodePara(mResume.getmZhuanYeString(), "speciality", _Params);
				addEncodePara(mResume.getmBiyeYear(), "byear", _Params);
				
				if(Util.isConditionNull(mResume.getmBirthday())){
					_Params.add(new BasicNameValuePair("birth_year", null));
					_Params.add(new BasicNameValuePair("birth_month", null));
					_Params.add(new BasicNameValuePair("birth_day", null));
				}else{
//					String _Birth = mResume.getmBirthday();
//					String[] _BirthSrings = Util.splitDate(_Birth);
					addEncodePara(mResume.getmBirth_Year(), "birth_year", _Params);
					addEncodePara(mResume.getmBirth_Month(), "birth_month", _Params);
					addEncodePara(mResume.getmBirth_Day(), "birth_day", _Params);
				}
				if(Util.isConditionNull(mResume.getmHukousuozaidi())){
					_Params.add(new BasicNameValuePair("door_area", null));
					_Params.add(new BasicNameValuePair("door_city", null));
				}else{
//					String suozaidi = mResume.getmHukousuozaidi();
//					String[] suozaidistrings =  Util.splitPlace(suozaidi);
					addEncodePara(mResume.getmDoor_AreaString(), "door_area", _Params);
					addEncodePara(mResume.getmDoor_City(), "door_city", _Params);
				}
				if(Util.isConditionNull(mResume.getmResidence())){
					_Params.add(new BasicNameValuePair("locus_area", null));
					_Params.add(new BasicNameValuePair("locus_city", null));
				}else{
//					String residence = mResume.getmResidence();
//					String[] residenceStrs = Util.splitPlace(residence);
					addEncodePara(mResume.getmResidence_Area(), "locus_area", _Params);
					addEncodePara(mResume.getmResidence_City(), "locus_city", _Params);
				}
				if(Util.isConditionNull(mResume.getmJianjie())){
					_Params.add(new BasicNameValuePair("appraise", null));
				}else{
					addEncodePara(mResume.getmJianjie(), "appraise", _Params);
				}
				if(Util.isConditionNull(mResume.getmIntentionJobClass())){
					_Params.add(new BasicNameValuePair("work_will_class1", null));
				}else{
					addEncodePara(mResume.getmIntentionJobClass(), "work_will_class1", _Params);
				}
				if(Util.isConditionNull(mResume.getmIntentionJobClass())){
					_Params.add(new BasicNameValuePair("work_will_class1", null));
				}else{
					addEncodePara(mResume.getmIntentionJobClass(), "work_will_class1", _Params);
				}
				_Params.add(new BasicNameValuePair("work_will_class2", null));
				_Params.add(new BasicNameValuePair("work_will_class3", null));
				if(Util.isConditionNull(mResume.getmIntentionJob())){
					_Params.add(new BasicNameValuePair("work_will1", null));
				}else{
					addEncodePara(mResume.getmIntentionJob(), "work_will1", _Params);
				}
				if(Util.isConditionNull(mResume.getmIntentionJob1())){
					_Params.add(new BasicNameValuePair("work_will2", null));
				}else{
					addEncodePara(mResume.getmIntentionJob1(), "work_will2", _Params);
				}
				if(Util.isConditionNull(mResume.getmIntentionJob2())){
					_Params.add(new BasicNameValuePair("work_will3", null));
				}else{
					addEncodePara(mResume.getmIntentionJob2(), "work_will3", _Params);
				}
				
				if(Util.isConditionNull(mResume.getmWorkingYears())){
					_Params.add(new BasicNameValuePair("works", null));
				}else{
					addEncodePara(DataForSearchCondition.getWorkingYear(mResume.getmWorkingYears())+"", "works", _Params);
					
				}
				if(Util.isConditionNull(mResume.getmIntentionPlaceString())){
					_Params.add(new BasicNameValuePair("area_will1", null));					
				}else{
					addEncodePara(mResume.getmIntentionPlaceString(), "area_will1", _Params);
				}
				if(Util.isConditionNull(mResume.getmIntentionPlaceString1())){
					_Params.add(new BasicNameValuePair("area_will2", null));					
				}else{
					addEncodePara(mResume.getmIntentionPlaceString1(), "area_will2", _Params);
				}
				if(Util.isConditionNull(mResume.getmIntentionPlaceString2())){
					_Params.add(new BasicNameValuePair("area_will3", null));					
				}else{
					addEncodePara(mResume.getmIntentionPlaceString2(), "area_will3", _Params);
				}
				if(Util.isConditionNull(mResume.getmQiwangyuexin())){
					_Params.add(new BasicNameValuePair("deal", null));
				}else{
					if(mResume.getmQiwangyuexin().length()>4){
						addEncodePara(mResume.getmQiwangyuexin().substring(0,5), "deal", _Params);					
					}else{
						addEncodePara(mResume.getmQiwangyuexin(), "deal", _Params);						
					}
				}
				if(Util.isConditionNull(mResume.getmMsnOrQQ())&&Util.isConditionNull(mResume.getmMsnOrQQ())){
					_Params.add(new BasicNameValuePair("msnqq", null));
				}else{
					addEncodePara(mResume.getmMsnOrQQ(), "msnqq", _Params);
					
				}
				requestToNet(mChangResume, _Params);
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
			
//				JSONArray _Array= _ResultObject.optJSONArray("citys");
//				for(int i=0;i<_Array.length();i++){
//					JSONObject proObject = _Array.optJSONObject(i);
//					City _City = new City();
//					_City.setmId(proObject.optString("cityID"));
//					_City.setmName(proObject.optString("city"));
//					
//				}
				mActivity.callBack(mActivity.CallbackSuccess,"修改成功");
			}
		} catch (Exception ex) {
			// 异常处理代码
			mActivity.callBack(mActivity.CallbackError,MSGHANDLERESULTERROR_STRING);
			ex.printStackTrace();
		}
	}

	

}
