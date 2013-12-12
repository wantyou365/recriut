package com.janan.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;
import android.widget.Toast;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.JobInfo;
import com.janan.data.bean.search.NearSearch;
import com.janan.data.data.DataForSearchCondition;
import com.janan.recruit.BaseActivity;
import com.janan.util.Util;

public class SubscribeJobEngine extends BaseEngine {
	private int mPage;
	private ArrayList<JobInfo> mList = null;
	private JobInfo mInfo;
	private boolean isSubscribe;
	private NearSearch mSearchkeys;

	public SubscribeJobEngine(BaseActivity activity, boolean isperson) {
		super(activity, isperson);
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

				_Params.add(new BasicNameValuePair("per_id", mActivity.mUser
						.getmUserID()));
				if (!isSubscribe) {
					addEncodePara(mPage + "", "page", _Params);
					addEncodePara(mPageNum + "", "page_num", _Params);
					requestToNet(mGetSubscribeInfo, _Params);
				} else {
					if (Util.isConditionNull(mSearchkeys.getmInentJob())) {
						_Params.add(new BasicNameValuePair("job_name", null));
					} else {
						addEncodePara(mSearchkeys.getmInentJob(), "job_name",
								_Params);
					}

					if (Util.isConditionNull(mSearchkeys.getmComid())) {
						_Params.add(new BasicNameValuePair("comid", null));
					} else {
						addEncodePara(mSearchkeys.getmComid(), "comid", _Params);
					}
					if (Util.isConditionNull(mSearchkeys.getmIntentPlace())) {
						_Params.add(new BasicNameValuePair("work_area", null));
					} else {
						addEncodePara(mSearchkeys.getmIntentPlace(),
								"work_area", _Params);
					}
					if (Util.isConditionNull(mSearchkeys.getmWorkingYears())) {
						_Params.add(new BasicNameValuePair("work_year", null));
					} else {
						addEncodePara(
								DataForSearchCondition
										.getWorkingYear(mSearchkeys
												.getmWorkingYears())
										+ "", "work_year", _Params);
					}
					if (Util.isConditionNull(mSearchkeys.getmSex())) {
						_Params.add(new BasicNameValuePair("sex", null));
					} else {
						_Params.add(new BasicNameValuePair("sex",
								DataForSearchCondition
										.getXingbie4Search(mSearchkeys
												.getmSex())
										+ ""));
						addEncodePara(DataForSearchCondition
								.getXingbie4Search(mSearchkeys
										.getmSex())
								+ "",
								"sex", _Params);
					}
					if (Util.isConditionNull(mSearchkeys.getmAge())) {
						_Params.add(new BasicNameValuePair("min_age", null));
						_Params.add(new BasicNameValuePair("max_age", null));
					} else {
						String[] _AgeStrings = mSearchkeys.getmAge().split("-");
						// if(_AgeStrings.length>1){
						
						addEncodePara(_AgeStrings[0], "min_age", _Params);
						addEncodePara(_AgeStrings[1], "max_age", _Params);

					}
					if (Util.isConditionNull(mSearchkeys.getmEducation())) {
						_Params.add(new BasicNameValuePair("education", null));
					} else {
						addEncodePara(DataForSearchCondition
								.getXueliyaoqiu(mSearchkeys
										.getmEducation())
								+ "", "education", _Params);
					}
					if (Util.isConditionNull(mSearchkeys.getmBusiness())) {
						_Params.add(new BasicNameValuePair("job_class", null));
					} else {
						addEncodePara(mSearchkeys.getmBusiness(), "job_class",
								_Params);
					}
					if (Util.isConditionNull(mSearchkeys.getmStarLevel())) {
						_Params.add(new BasicNameValuePair("stars", null));
					} else {
						addEncodePara(mSearchkeys.getmStarLevel(), "stars",
								_Params);
//						_Params.add(new BasicNameValuePair("stars",
//								DataForSearchCondition.getXingji(mSearchkeys
//										.getmStarLevel()) + ""));
					}
					if (Util.isConditionNull(mSearchkeys.getmSalary())) {
						_Params.add(new BasicNameValuePair("min_month_pay",
								null));
						_Params.add(new BasicNameValuePair("max_month_pay",
								null));
					} else {
						String[] _AgeStrings = mSearchkeys.getmSalary().split(
								"-");
						if (_AgeStrings.length > 1) {
							addEncodePara(_AgeStrings[0], "min_month_pay", _Params);
							addEncodePara(_AgeStrings[1], "max_month_pay", _Params);
						} else {
							addEncodePara(null, "min_month_pay", _Params);
							addEncodePara(_AgeStrings[0].substring(0, 5),
									"max_month_pay", _Params);
						}
					}
					if (Util.isConditionNull(mSearchkeys.getmKeyWord())) {
						_Params.add(new BasicNameValuePair("key_word", null));
					} else {
						addEncodePara(mSearchkeys.getmKeyWord(), "key_word",
								_Params);
					}

					if (Util.isConditionNull(mSearchkeys.getmPostDate())) {
						_Params.add(new BasicNameValuePair("min_publish_date",
								null));
					} else {
						
						addEncodePara(Util.getPushDate(DataForSearchCondition
										.getFaburiqiInt(mSearchkeys
												.getmPostDate())), "min_publish_date", _Params);
					}
					// if(isSubscribe){
					// _Params.add(new BasicNameValuePair("per_id",
					// mActivity.mUser.getmUserID()));
					// requestToNet(mGetSubscribeInfo, _Params);
					// }else{
					// _Params.add(new BasicNameValuePair("page", mPage + ""));
					// _Params.add(new BasicNameValuePair("page_num",
					// mPageNum+""));
					requestToNet(mSubscribeJob, _Params);
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
			int _ResultId = _ResultObject.optInt("result");

			String _TextString = _ResultObject.optString("result_text");

			if (_ResultId == 0) {

				mActivity.callBack(mActivity.CallbackError, _TextString);
			} else {
				if (isSubscribe) {
					mActivity.callBack(mActivity.CallbackSubscribeSearch, null);
					return;
				} else {
					int _Count = _ResultObject.optInt("total_count");
					if (_Count == 0) {
						_TextString = mActivity.getString(R.string.toast_nojob);
						mActivity
								.callBack(mActivity.CallbackError, _TextString);
					} else {
						JSONArray _Jobs = _ResultObject.optJSONArray("jobs");
						if (_Jobs == null) {
							_TextString = mActivity
									.getString(R.string.toast_nojob);
							mActivity.callBack(mActivity.CallbackError,
									_TextString);
						} else {
							mList = new ArrayList<JobInfo>();

							for (int i = 0; i < _Jobs.length(); i++) {
								mInfo = new JobInfo();
								JSONObject _InfoObject = _Jobs.optJSONObject(i);
								mInfo.setmJobId(_InfoObject.optString("JobId"));
								mInfo.setmComId(_InfoObject.optString("Comid"));
								mInfo.setmZhiweimingcheng(_InfoObject
										.optString("JobName"));
								mInfo.setmGongsimingchen(_InfoObject
										.optString("CompanyName"));
								mInfo.setmCityString(_InfoObject
										.optString("Work_City"));
								mInfo.setmAreaString(_InfoObject
										.optString("Work_Area"));
								mInfo.setmViewClicks(_InfoObject
										.optString("ViewClicks"));
								mInfo.setmFaburiqi(_InfoObject
										.optString("LastUpdate_Time"));
								mInfo.setmZhiweimiaoshu(_InfoObject
										.optString("Require"));
								mInfo.setmAddress(_InfoObject
										.optString("Address"));

								mList.add(mInfo);
							}
							mActivity.callBack(mActivity.CallbackSuccess, null);

						}
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

	public ArrayList<JobInfo> getmList() {

		return mList;
	}

	public void setmList(ArrayList<JobInfo> mList) {
		this.mList = mList;
	}

	public boolean isSubscribe() {
		return isSubscribe;
	}

	public void setSubscribe(boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public NearSearch getmSearchkeys() {
		return mSearchkeys;
	}

	public void setmSearchkeys(NearSearch mSearchkeys) {
		this.mSearchkeys = mSearchkeys;
	}

}
