package com.janan.net;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

import com.janan.data.bean.News;
import com.janan.data.bean.ShiXi;
import com.janan.recruit.BaseActivity;

public class WorkDynamicsListEngine extends BaseEngine {
	private int mPage;
	private boolean isShixi = false;
	private ArrayList<News> mList = new ArrayList<News>();
	private ArrayList<ShiXi> shiList = new ArrayList<ShiXi>();
	public WorkDynamicsListEngine(BaseActivity activity, boolean isperson) {
		super(activity, isperson);
	}

	public void start() {

		new Thread(new Runnable() {
			@Override
			public void run() {

				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
				addEncodePara(mPage + "", "page", _Params);
				addEncodePara(mPageNum + "", "page_num", _Params);
				if (isShixi) {
					requestToNet(mShiXi, _Params);
				} else {
					requestToNet(mGetNews, _Params);
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
			JSONTokener _JsonParser;
			_JsonParser = new JSONTokener(result);
			JSONObject _ResultObject = (JSONObject) _JsonParser.nextValue();
			if (isShixi) {
				int _ResultId = _ResultObject.optInt("result");
				String _ResultText;
				if (_ResultId == 0) {
					_ResultText = _ResultObject.optString("result_text");
					mActivity.callBack(mActivity.CallbackError, _ResultText);
				} else {
					JSONArray _Records = _ResultObject.optJSONArray("records");
					for (int i = 0; i < _Records.length(); i++) {
						JSONObject _Object = _Records.optJSONObject(i);
						ShiXi _ShiXi = new ShiXi();
						
						_ShiXi.setTitle(_Object.optString("title"));
						_ShiXi.setContent(_Object.optString("content"));
						_ShiXi.setPoster(_Object.optString("poster"));
						
						_ShiXi.setClickcount(_Object.optString("clickcount"));
						shiList.add(_ShiXi);
					}
					mActivity.callBack(mActivity.CallbackSuccess, null);
				}
			} else {
				int _ResultId = _ResultObject.optInt("result");
				String _ResultText;
				if (_ResultId == 0) {
					_ResultText = _ResultObject.optString("result_text");
					mActivity.callBack(mActivity.CallbackError, _ResultText);
				} else {
					if (_ResultObject.has("records")) {
						JSONArray _Records = _ResultObject
								.optJSONArray("records");
						for (int i = 0; i < _Records.length(); i++) {
							JSONObject _Object = _Records.optJSONObject(i);
							News _News = new News();
							_News.setmId(_Object.optString("id"));
							_News.setmTitle(_Object.optString("title"));
							_News.setmSmallTitle(_Object
									.optString("smalltitle"));
							_News.setmKeyWords(_Object.optString("keywords"));
							_News.setmCopyFrom(_Object.optString("copyfrom"));
							_News.setmUsername(_Object.optString("username"));
							_News.setfName(_Object.optString("fname"));
							_News.setmLastView(_Object.optString("lastview"));
							_News.setmHitString(_Object.optString("hits"));
							mList.add(_News);
						}
						mActivity.callBack(mActivity.CallbackSuccess, null);
					}
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

	public int getmPage() {
		return mPage;
	}

	public void setmPage(int mPage) {
		this.mPage = mPage;
	}

	public ArrayList<News> getmList() {
		return mList;
	}

	public void setmList(ArrayList<News> mList) {
		this.mList = mList;
	}

	public boolean isShixi() {
		return isShixi;
	}

	public void setShixi(boolean isShixi) {
		this.isShixi = isShixi;
	}

	public ArrayList<ShiXi> getShiList() {
		return shiList;
	}

	public void setShiList(ArrayList<ShiXi> shiList) {
		this.shiList = shiList;
	}

	
}
