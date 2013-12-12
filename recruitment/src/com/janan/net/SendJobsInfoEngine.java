package com.janan.net;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.janan.data.bean.JobInfo;
import com.janan.data.data.DataForSearchCondition;
import com.janan.recruit.BaseActivity;
import com.janan.util.Util;

public class SendJobsInfoEngine extends BaseEngine {

	private JobInfo mInfo;
	public SendJobsInfoEngine(BaseActivity activity, boolean isperson) {
		super(activity, isperson);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void requestToNet(String url, ArrayList<NameValuePair> params) {
		// TODO Auto-generated method stub
		if (super.isConnected()) {
			super.requestToNet(url, params);
		}

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
				addEncodePara(mInfo.getmZhaopinmumenString(), "job_class", _Params);
				addEncodePara(mInfo.getmZhiweimingcheng(), "job_name", _Params);
				addEncodePara(mInfo.getmProvinceString(), "work_area", _Params);
				addEncodePara(mInfo.getmCityString(), "work_city", _Params);
				addEncodePara(DataForSearchCondition.getWorkingYear(mInfo.getmGongzuonianxianString())+"", "works", _Params);
				if(Util.checkString(mInfo.getmXinzidaiyu())){
					String[] strs =  mInfo.getmXinzidaiyu().split("-");
					if(strs[0].length()>4){
						addEncodePara(mInfo.getmXinzidaiyu().substring(0, 5), "deal", _Params);
					}else{
						addEncodePara(mInfo.getmXinzidaiyu().replace("-", "~"), "deal", _Params);
					}
				}else{
					addEncodePara(null, "deal", _Params);
				}
				
				addEncodePara(mInfo.getmZhaopinrenshu(), "number", _Params);
				addEncodePara(mInfo.getJobRequire(), "require", _Params);
				
				addEncodePara(DataForSearchCondition.getJobtypeInt(mInfo.getmGongzuoleixingString())+"", "job_type", _Params);
				addEncodePara("1", DataForSearchCondition.getmZhaopiinleibie4send(mInfo.getmZhaopinleibieString()), _Params);
				addEncodePara(DataForSearchCondition.getBaochizhuInt(mInfo.getmChizhuqingkuangString())+"", "czqk", _Params);
				addEncodePara(DataForSearchCondition.getGongzuoshijianInt(mInfo.getWorkTimeString())+"", "gzsj", _Params);
				addEncodePara(mInfo.getmJiezhiriqi(), "end_date", _Params);
				requestToNet(mSendJobInfo, _Params);
			}
		}).start();
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
				mActivity.callBack(mActivity.CallbackSuccess, null);
			}
		} catch (Exception ex) {
			// 异常处理代码
			mActivity.callBack(mActivity.CallbackError,
					MSGHANDLERESULTERROR_STRING);
			ex.printStackTrace();
		}
	}

	public JobInfo getmInfo() {
		return mInfo;
	}

	public void setmInfo(JobInfo mInfo) {
		this.mInfo = mInfo;
	}
	
	
	
}
