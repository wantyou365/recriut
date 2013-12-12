package com.janan.net;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

import com.janan.data.bean.News;
import com.janan.recruit.BaseActivity;

public class WorkDynamicsInfoEngine extends BaseEngine {
	private News mNews;
	private String newsId;

	public WorkDynamicsInfoEngine(BaseActivity activity, boolean isperson) {
		super(activity, isperson);
	}

	public void start() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
				addEncodePara(newsId, "id", _Params);
				requestToNet(mGetNewsInfo, _Params);
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
			
			_JsonParser = new JSONTokener(result);
			JSONObject _ResultObject = (JSONObject) _JsonParser.nextValue();
			int _ResultId = _ResultObject.optInt("result");
			String _ResultText;
			if (_ResultId == 0) {
				_ResultText = _ResultObject.optString("result_text");
				mActivity.callBack(mActivity.CallbackError, _ResultText);
			} else {
				if (_ResultObject.has("info")) {
					mNews = new News();		
					JSONObject _InfoObject = _ResultObject.optJSONObject("info");
					mNews.setmId(_InfoObject.optString("id"));
					mNews.setmTitle(_InfoObject.optString("title"));
					mNews.setmSmallTitle(_InfoObject.optString("smalltitle"));
					mNews.setmKeyWords(_InfoObject.optString("keywords"));
					mNews.setmCopyFrom(_InfoObject.optString("copyfrom"));
					mNews.setmUsername(_InfoObject.optString("username"));
					mNews.setfName(_InfoObject.optString("fname"));
					mNews.setmLastView(_InfoObject.optString("lastview"));
					mNews.setmHitString(_InfoObject.optString("hits"));
					mNews.setmContent(_InfoObject.optString("content"));
					mActivity.callBack(mActivity.CallbackSuccess, null);
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

	public News getmNews() {
		return mNews;
	}

	public void setmNews(News mNews) {
		this.mNews = mNews;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

}
