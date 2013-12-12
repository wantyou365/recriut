package com.janan.net;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.JobInfo;
import com.janan.data.bean.personal.Resume;
import com.janan.data.bean.search.NearSearch;
import com.janan.data.data.DataForSearchCondition;
import com.janan.recruit.BaseActivity;
import com.janan.util.Util;

public class SearchResumeEngine extends BaseEngine {

	private NearSearch mSearchkeys;
	private int mPage;
	private ArrayList<Resume> mList = null;
	private Resume mResume;
	private boolean isSubscribe = false;
	
	public SearchResumeEngine(BaseActivity activity,boolean isperson) {
		super(activity,isperson);
	}
	
	

	public NearSearch getmSearchkeys() {
		return mSearchkeys;
	}

	public void setmSearchkeys(NearSearch mSearchkeys) {
		this.mSearchkeys = mSearchkeys;
	}

	public int getmPage() {
		return mPage;
	}

	public void setmPage(int mPage) {
		this.mPage = mPage;
	}

	public void start() {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
				if(Util.isConditionNull(mSearchkeys.getmInentJob())){
					_Params.add(new BasicNameValuePair("work_will", null));
				}else{
					addEncodePara(mSearchkeys.getmInentJob(),"work_will",_Params);
				}
				
				if(Util.isConditionNull(mSearchkeys.getmIntentPlace())){
					_Params.add(new BasicNameValuePair("work_area", null));
				}else{
					addEncodePara(mSearchkeys.getmIntentPlace(),"work_area",_Params);
				}
				if(Util.isConditionNull(mSearchkeys.getmWorkingYears())){
					_Params.add(new BasicNameValuePair("works", null));
				}else{
					addEncodePara(DataForSearchCondition.getWorkingYear(mSearchkeys.getmWorkingYears())+"","works",_Params);
				}
				if(Util.isConditionNull(mSearchkeys.getmSex())){
					_Params.add(new BasicNameValuePair("sex", null));
				}else{
					addEncodePara(DataForSearchCondition.getXingbie4Search2(mSearchkeys.getmSex()) +"","sex",_Params);
				}
				if(Util.isConditionNull(mSearchkeys.getmAge())){
					_Params.add(new BasicNameValuePair("min_age", null));
					_Params.add(new BasicNameValuePair("max_age", null));
				}else{
					String[] _AgeStrings = mSearchkeys.getmAge().split("-");
					addEncodePara(_AgeStrings[0],"min_age",_Params);
					addEncodePara(_AgeStrings[1],"max_age",_Params);
						
					
				}
				if(Util.isConditionNull(mSearchkeys.getmBusiness())){
					_Params.add(new BasicNameValuePair("work_class", null));
				}else{
					addEncodePara(mSearchkeys.getmBusiness(),"work_class",_Params);
				}
				addEncodePara(mPage + "", "page", _Params);
				addEncodePara(mPageNum + "", "page_num", _Params);
				requestToNet(mSearchResumeUrl, _Params);

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
			JSONTokener _JsonParser;
//			String resultString = EntityUtils.toString(httpResponse.getEntity());
			_JsonParser = new JSONTokener(result);
			JSONObject _ResultObject = (JSONObject) _JsonParser.nextValue();
			int _ResultId = _ResultObject.optInt("result");
			String _TextString = "";
			if(_ResultObject.has("result_text")){
				_TextString = _ResultObject.optString("result_text");
			}
			
			if (_ResultId == 0) {
				
				mActivity.callBack(mActivity.CallbackError, _TextString);
			}else{
				int _Count = -1;
				if(_ResultObject.has("total_count")){
					_Count = _ResultObject.optInt("total_count");
				}				
				if(_Count==0){
					_TextString = mActivity.getString(R.string.toast_noresume);
					mActivity.callBack(mActivity.CallbackError, _TextString);
				}else if (_ResultId == 1) {
					JSONArray _Jobs = _ResultObject.optJSONArray("persons");
					mList = new ArrayList<Resume>();
					for (int i = 0; i < _Jobs.length(); i++) {
						mResume = new Resume();
						
						JSONObject _InfoObject = _Jobs.optJSONObject(i);
						
						mResume.setmUserId(_InfoObject.optString("PerId"));
						mResume.setmNameString(_InfoObject.optString("RealName"));
						mResume.setmSexString(DataForSearchCondition.getXingbie4SearchString(_InfoObject.optInt("Sex")));
						mResume.setmMinZuString(_InfoObject.optString("Nation"));
						replaceIntToString(_InfoObject.optString("Edus"), DataForSearchCondition.mXueliyaoqiuStrings, DataForSearchCondition.mXueliyaoqiuInts);
						mResume.setmYihun(null);
						if(_InfoObject.optInt("Age") == 0){
							mResume.setmAgeString(null);
						}else{
							mResume.setmAgeString(_InfoObject.optString("Age"));
						}
						if(_InfoObject.optInt("BirthYear") == 0){
							mResume.setmBirthday(null);
							mResume.setmBirth_Year(null);
							mResume.setmBirth_Month(null);
							mResume.setmBirth_Day(null);					
						}else{
							mResume.setmBirth_Year(_InfoObject.optString("BirthYear"));
							mResume.setmBirth_Month(_InfoObject.optString("BirthMonth"));
							mResume.setmBirthday(_InfoObject.optString("BirthDay"));
							mResume.setmBirthday(_InfoObject.optString("BirthYear")+"-"+_InfoObject.optString("BirthMonth")+"-"+_InfoObject.optString("BirthDay"));					
						}
						mResume.setmDoor_City(_InfoObject.optString("Door_City"));
						mResume.setmDoor_AreaString(_InfoObject.optString("Door_Area"));
						mResume.setmResidence_City(_InfoObject.optString("Locus_City"));
						mResume.setmResidence_Area(_InfoObject.optString("Locus_Area"));
						if("0".equals(_InfoObject.optString("Works"))){
							mResume.setmWorkingYears(null);
						}else{
							mResume.setmWorkingYears(_InfoObject.optString("Works")+"年");
//							mResume.setmWorkingYears(replaceIntToString(_InfoObject.optString("Works"), DataForSearchCondition.mGongzuonianxianStrings, DataForSearchCondition.mGongzuonianxianInts));		
						}
						mResume.setmQiwangyuexin(_InfoObject.optString("Deal"));
						mResume.setmMsnOrQQ(_InfoObject.optString("Msnqq"));
						mResume.setmIntentionJobClass(_InfoObject.optString("WorkWillClass1"));
						mResume.setmIntentionJob(_InfoObject.optString("WorkWill1"));
						mResume.setmIntentionPlaceString(_InfoObject.optString("AreaWill1"));
						mResume.setmLastSchoolString(_InfoObject.optString("LastSchool"));
						mResume.setmZhuanYeString(_InfoObject.optString("Speciality"));
						mResume.setmZhuanyeClass(_InfoObject.optString("ZyClass"));
						if("0".equals(_InfoObject.optString("Byear"))){
							mResume.setmBiyeYear(null);
						}else{
							mResume.setmBiyeYear(_InfoObject.optString("Byear"));
						}
						
						mResume.setmJianjie(_InfoObject.optString("Appraise"));
						mList.add(mResume);
					}
					mActivity.callBack(mActivity.CallbackSuccess, null);
				}
			}
			
			
		} catch (Exception ex) {
			// 异常处理代码
			mActivity.callBack(mActivity.CallbackError,
					MSGHANDLERESULTERROR_STRING);
			Log.i("janan", "处理出错");
			ex.printStackTrace();
		}
	}

	public ArrayList<Resume> getmList() {

		return mList;
	}

	public void setmList(ArrayList<Resume> mList) {
		this.mList = mList;
	}



	public boolean isSubscribe() {
		return isSubscribe;
	}



	public void setSubscribe(boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	
}
