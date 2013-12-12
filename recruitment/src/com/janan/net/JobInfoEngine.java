package com.janan.net;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.janan.data.bean.JobInfo;
import com.janan.data.data.City;
import com.janan.data.data.DataForSearchCondition;
import com.janan.data.data.JobName;
import com.janan.recruit.BaseActivity;

public class JobInfoEngine extends BaseEngine {
	private JobInfo mInfo;
	private String Jid;

	public JobInfoEngine(BaseActivity activity, boolean isperson) {
		super(activity, isperson);
		// TODO Auto-generated constructor stub
	}

	public JobInfo getmInfo() {
		return mInfo;
	}

	public void setmInfo(JobInfo mInfo) {
		this.mInfo = mInfo;
	}

	public void start() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
				
				addEncodePara(Jid, "job_id", _Params);
				requestToNet(mGetJobInfo, _Params);
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
				mActivity.callBack(mActivity.CallbackError, _TextString);
			} else if (_ResultId == 1) {
				JSONObject _InfoObject = _ResultObject
						.optJSONObject("job_info");
				mInfo = new JobInfo();
				mInfo.setmJobId(_InfoObject.optString("JobId"));
				mInfo.setmComId(_InfoObject.optString("Comid"));
				mInfo.setmZhiweimingcheng(_InfoObject.optString("JobName"));
				mInfo.setmGongsimingchen(_InfoObject.optString("CompanyName"));
				String jobType = _InfoObject.optString("JobType");
				jobType = replaceIntToString(jobType, DataForSearchCondition.mJobtypeStrings, DataForSearchCondition.mJobtypeInt);
				mInfo.setmGongzuoleixingString(jobType);
				String _DealString = _InfoObject.optString("Deal");
				if("0".equals(_DealString)){
					mInfo.setmXinzidaiyu("面议");
				}else{
					mInfo.setmXinzidaiyu(_DealString);
				}			
				mInfo.setmZhaopinmumenString(_InfoObject.optString("JobClass"));
				mInfo.setmZhaopinrenshu(_InfoObject.optString("Number"));
				mInfo.setmJiezhiriqi(_InfoObject.optString("End_Date"));
				mInfo.setmCityString(_InfoObject.optString("Work_City"));
				mInfo.setmAreaString(_InfoObject.optString("Work_Area"));
				mInfo.setmSuozaiCityString(_InfoObject.optString("City"));
				mInfo.setmSuozaiAreaString(_InfoObject.optString("Area"));
				mInfo.setmZhiweimiaoshu(_InfoObject.optString("Require"));
				mInfo.setmJobUrlString(_InfoObject.optString("job_url"));
				String Edus = _InfoObject.optString("Edus").trim();
				Edus = replaceIntToString(Edus,
						DataForSearchCondition.mXueliyaoqiuStrings,
						DataForSearchCondition.mXueliyaoqiuInts);
				mInfo.setmXueliyaoqiu(Edus);
				String sex = _InfoObject.optString("Sex").trim();
				sex = replaceIntToString(sex, DataForSearchCondition.mXingbieyaoqiuStrings, DataForSearchCondition.mXingbieyaoqiuInts1);
				mInfo.setmXingbie(sex);
				String works = _InfoObject.optString("Works");
				works = replaceIntToString(works,
						DataForSearchCondition.mGongzuonianxianStrings, DataForSearchCondition.mGongzuonianxianInts);
				mInfo.setmGongzuonianxianString(works);
				mInfo.setmFaburiqi(_InfoObject.optString("LastUpdate_Time"));
				mInfo.setWorkTimeString(_InfoObject.optString("gzsj"));
				String czqk = _InfoObject.optString("czqk").trim();
				czqk = replaceIntToString(czqk,
						DataForSearchCondition.mBaochizhuStrings,
						DataForSearchCondition.mBaochizhuInt);

				mInfo.setmChizhuqingkuangString(czqk);
				mInfo.setmMax_Age(_InfoObject.optString("Max_Age"));
				mInfo.setmMin_Age(_InfoObject.optString("Min_Age"));
				mInfo.setmContactPerson(_InfoObject.optString("ContactPerson"));
				mInfo.setmContactPhone(_InfoObject.optString("Phone"));
				mInfo.setmCompanyFax(_InfoObject.optString("CompanyFax"));
				mInfo.setmEmail(_InfoObject.optString("Email"));
				mInfo.setmAddress(_InfoObject.optString("Address"));
				mInfo.setmViewClicks(_InfoObject.optString("ViewClicks"));
				mActivity.callBack(mActivity.CallbackSuccess, null);
			}
		} catch (Exception ex) {
			// 异常处理代码
			mActivity.callBack(mActivity.CallbackError,
					MSGHANDLERESULTERROR_STRING);
			ex.printStackTrace();
		}
	}

	public JobInfo getmJobInfo() {
		return mInfo;
	}

	public void setmJobInfo(JobInfo mJobInfo) {
		this.mInfo = mJobInfo;
	}

	public String getJid() {
		return Jid;
	}

	public void setJid(String jid) {
		Jid = jid;
	}

}
