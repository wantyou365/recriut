package com.janan.data.bean;

import java.util.ArrayList;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.adapter.PositionSubscribeAdapter;
import com.janan.recruit.BaseActivity;

import com.janan.util.DBHelper;
import com.janan.util.Util;

public class JobInfo extends BaseBean implements java.io.Serializable {
	private String mJobId;// 职位id
	private String mComId;// 公司id
	private String mGongsimingchen;// 公司名称
	private String mGongzuoleixingString;// 工作类型，eg：全职
	private String mZhaopinleibieString;
	
	private String mEndYear;
	private String mEndMonth;
	private String mEndDay;
	
	public String getmEndYear() {
		return mEndYear;
	}

	public void setmEndYear(String mEndYear) {
		this.mEndYear = mEndYear;
	}

	public String getmEndMonth() {
		return mEndMonth;
	}

	public void setmEndMonth(String mEndMonth) {
		this.mEndMonth = mEndMonth;
	}

	public String getmEndDay() {
		return mEndDay;
	}

	public void setmEndDay(String mEndDay) {
		this.mEndDay = mEndDay;
	}

	public String getmZhaopinleibieString() {
		return mZhaopinleibieString;
	}

	public void setmZhaopinleibieString(String mZhaopinleibieString) {
		this.mZhaopinleibieString = mZhaopinleibieString;
	}

	public String getmLianxifangshi() {
		return mLianxifangshi;
	}

	public String getmGongzuoleixingKey(BaseActivity activity) {
		return activity.getString(R.string.gongzuoleixing);
	}
	private String mXinzidaiyu;// 薪资待遇,deal
	public String getmXinzidaiyuKey(BaseActivity activity) {
		return activity.getString(R.string.xinzidaiyu);
	} 
	private String mZhaopinmumenString;// 招聘部门
	public String getmZhaopinmumenKey(BaseActivity activity) {
		return activity.getString(R.string.zhaopinbumen);
	}
	private String mZhiweimingcheng;// 职位名称
	
	private String mZhaopinrenshu;// 招聘人数,number
	public String getmZhaopinrenshuKey(BaseActivity activity) {
		return activity.getString(R.string.zhaopinrenshu);
	}
	private String mJiezhiriqi;// 截至日期,End_Date
	public String getmJiezhiriqiKey(BaseActivity activity) {
		return activity.getString(R.string.jiezhiriqi);
	}
	private String mGongzuodidian;// 工作地点
	public String getmGongzuodidianKey(BaseActivity activity) {
		return activity.getString(R.string.gongzuodidian);
	}
	private String mProvinceString;//工作省份
	private String mCityString;// 工作城市
	private String mAreaString;// 工作地区
	
	private String mSuozaidiString;//所在地
	public String getmSuozaidiStringKey(BaseActivity activity) {
		return activity.getString(R.string.suozaidi);
	}
	
	private String mSuozaiCityString;
	private String mSuozaiAreaString;
	
	private String mZhiweimiaoshu;// 职位描述,Require
	public String getmZhiweimiaoshuKey(BaseActivity activity) {
		return activity.getString(R.string.zhiweimiaoshu);
	}
	private String mXueliyaoqiu;// 学历要求
	public String getmXueliyaoqiuKey(BaseActivity activity) {
		return activity.getString(R.string.xueliyaoqiu);
	}
	
	private String mXingbie;// 性别
	public String getmXingbieKey(BaseActivity activity) {
		return activity.getString(R.string.xingbieyaoqiu);
	}
	
	private String mGongzuonianxianString;// 工作年限，Works
	public String getmGongzuonianxianStringKey(BaseActivity activity) {
		return activity.getString(R.string.gongzuonianxian);
	}
	
	private String mFaburiqi;// 发布日期,LastUpdate_Time
	public String getmFaburiqiKey(BaseActivity activity) {
		return activity.getString(R.string.faburiqi);
	}
	private String mViewClicks;// 查看次数
	
	private String mSendClickString;// 投递人数
	public String getmSendClickStringKey(BaseActivity activity) {
		return activity.getString(R.string.toudirenshu);
	}
	
	public String mAge;//年龄要求
	public String getmAgeKey(BaseActivity activity) {
		return activity.getString(R.string.nianlingyaoqiu);
	}
	private String mMin_Age;// 最小年龄
	private String mMax_Age;// 最大年龄
	private String mContactPerson;// 联系人
	private String mContactPhone;// 联系电话
	private String mCompanyFax;// 公司传真
	private String mEmail;// 联系信箱
	private String mAddress;// 公司地址、
	private String mZipCode;// 邮编
	private String mLianxifangshi;// 联系方式
	private String mJobUrlString;
	private String workTimeString;//工作时间
	
	public String getmJobUrlString() {
		return mJobUrlString;
	}

	public void setmJobUrlString(String mJobUrlString) {
		this.mJobUrlString = mJobUrlString;
	}
	private String jobRequire;//职位要求
	
	
	
	public String getJobRequire() {
		return jobRequire;
	}

	public void setJobRequire(String jobRequire) {
		this.jobRequire = jobRequire;
	}

	public String getworkTimeStringKey(BaseActivity activity) {
		return activity.getString(R.string.gongzuoshijian);
	}
	
	public String getWorkTimeString() {
		return workTimeString;
	}


	public String getmSuozaidiString() {
		return getmSuozaiCityString()+" "+getmSuozaiAreaString();
	}

	public void setmSuozaidiString(String mSuozaidiString) {
		this.mSuozaidiString = mSuozaidiString;
	}

	public String getmSuozaiCityString() {
		return mSuozaiCityString;
	}

	public void setmSuozaiCityString(String mSuozaiCityString) {
		this.mSuozaiCityString = mSuozaiCityString;
	}

	public String getmSuozaiAreaString() {
		return mSuozaiAreaString;
	}

	public void setmSuozaiAreaString(String mSuozaiAreaString) {
		this.mSuozaiAreaString = mSuozaiAreaString;
	}

	public void setWorkTimeString(String workTimeString) {
		this.workTimeString = workTimeString;
	}


	public String getmChizhuqingkuangString() {
		return mChizhuqingkuangString;
	}


	public void setmChizhuqingkuangString(String mChizhuqingkuangString) {
		this.mChizhuqingkuangString = mChizhuqingkuangString;
	}
	public String mChizhuqingkuangString;//吃住情况
	public String getmChizhuqingkuangStringKey(BaseActivity activity) {
		return activity.getString(R.string.chizhuqingkuang);
	}


	private ArrayList<String> singleList;
	private ArrayList<String> doubleList;

	private boolean isLong = false;

	private boolean isSubscribe = false;

	public JobInfo() {

	}

	
	

	public String getmJobId() {
		return mJobId;
	}


	public void setmJobId(String mJobId) {
		this.mJobId = mJobId;
	}


	public String getmComId() {
		return mComId;
	}


	public void setmComId(String mComId) {
		this.mComId = mComId;
	}


	public String getmGongsimingchen() {
		return mGongsimingchen;
	}


	public void setmGongsimingchen(String mGongsimingchen) {
		this.mGongsimingchen = mGongsimingchen;
	}


	public String getmGongzuoleixingString() {
		return mGongzuoleixingString;
	}


	public void setmGongzuoleixingString(String mGongzuoleixingString) {
		this.mGongzuoleixingString = mGongzuoleixingString;
	}


	public String getmXinzidaiyu() {
		return mXinzidaiyu;
	}


	public void setmXinzidaiyu(String mXinzidaiyu) {
		this.mXinzidaiyu = mXinzidaiyu;
	}


	public String getmZhaopinmumenString() {
		return mZhaopinmumenString;
	}


	public void setmZhaopinmumenString(String mZhaopinmumenString) {
		this.mZhaopinmumenString = mZhaopinmumenString;
	}


	public String getmZhiweimingcheng() {
		return mZhiweimingcheng;
	}


	public void setmZhiweimingcheng(String mZhiweimingcheng) {
		this.mZhiweimingcheng = mZhiweimingcheng;
	}


	public String getmZhaopinrenshu() {
		return mZhaopinrenshu;
	}


	public void setmZhaopinrenshu(String mZhaopinrenshu) {
		this.mZhaopinrenshu = mZhaopinrenshu;
	}


	public String getmJiezhiriqi() {
		return mJiezhiriqi;
	}


	public void setmJiezhiriqi(String mJiezhiriqi) {
		this.mJiezhiriqi = mJiezhiriqi;
	}


	public String getmGongzuodidian() {
		return getmAreaString()+" "+getmCityString();
	}


	public void setmGongzuodidian(String mGongzuodidian) {
		this.mGongzuodidian = mGongzuodidian;
	}


	public String getmCityString() {
		return mCityString;
	}


	public void setmCityString(String mCityString) {
		this.mCityString = mCityString;
	}


	public String getmAreaString() {
		return mAreaString;
	}


	public void setmAreaString(String mAreaString) {
		this.mAreaString = mAreaString;
	}


	public String getmZhiweimiaoshu() {
		return mZhiweimiaoshu;
	}


	public void setmZhiweimiaoshu(String mZhiweimiaoshu) {
		this.mZhiweimiaoshu = mZhiweimiaoshu;
	}


	public String getmXueliyaoqiu() {
		return mXueliyaoqiu;
	}


	public void setmXueliyaoqiu(String mXueliyaoqiu) {
		this.mXueliyaoqiu = mXueliyaoqiu;
	}


	public String getmXingbie() {
		return mXingbie;
	}


	public void setmXingbie(String mXingbie) {
		this.mXingbie = mXingbie;
	}


	public String getmGongzuonianxianString() {
		return mGongzuonianxianString;
	}


	public void setmGongzuonianxianString(String mGongzuonianxianString) {
		this.mGongzuonianxianString = mGongzuonianxianString;
	}


	public String getmFaburiqi() {
		return mFaburiqi;
	}


	public void setmFaburiqi(String mFaburiqi) {
		this.mFaburiqi = mFaburiqi;
	}


	public String getmViewClicks() {
		return mViewClicks;
	}


	public void setmViewClicks(String mViewClicks) {
		this.mViewClicks = mViewClicks;
	}


	public String getmSendClickString() {
		return mSendClickString;
	}


	public void setmSendClickString(String mSendClickString) {
		this.mSendClickString = mSendClickString;
	}


	public String getmAge() {
		if(Util.checkString(mMax_Age)){
			if(!"0".equals(mMax_Age)){
				return mMin_Age+"-"+mMax_Age;
			}else{
				return "";
			}
		}else{
			return "";
		}
		
	}


	public void setmAge(String mAge) {
		this.mAge = mAge;
	}


	public String getmMin_Age() {
		return mMin_Age;
	}


	public void setmMin_Age(String mMin_Age) {
		this.mMin_Age = mMin_Age;
	}


	public String getmMax_Age() {
		return mMax_Age;
	}


	public void setmMax_Age(String mMax_Age) {
		this.mMax_Age = mMax_Age;
	}


	public String getmContactPerson() {
		return mContactPerson;
	}


	public void setmContactPerson(String mContactPerson) {
		this.mContactPerson = mContactPerson;
	}


	public String getmContactPhone() {
		return mContactPhone;
	}


	public void setmContactPhone(String mContactPhone) {
		this.mContactPhone = mContactPhone;
	}


	public String getmCompanyFax() {
		return mCompanyFax;
	}


	public void setmCompanyFax(String mCompanyFax) {
		this.mCompanyFax = mCompanyFax;
	}


	public String getmEmail() {
		return mEmail;
	}


	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}


	public String getmAddress() {
		return mAddress;
	}


	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}


	public String getmZipCode() {
		return mZipCode;
	}


	public void setmZipCode(String mZipCode) {
		this.mZipCode = mZipCode;
	}


	public ArrayList<String> getSingleList() {
		return singleList;
	}


	public void setSingleList(ArrayList<String> singleList) {
		this.singleList = singleList;
	}


	public ArrayList<String> getDoubleList() {
		return doubleList;
	}


	public void setDoubleList(ArrayList<String> doubleList) {
		this.doubleList = doubleList;
	}


	public boolean isLong() {
		return isLong;
	}


	public void setLong(boolean isLong) {
		this.isLong = isLong;
	}


	public boolean isSubscribe() {
		return isSubscribe;
	}


	public void setSubscribe(boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}


	public void setmLianxifangshi(String mLianxifangshi) {
		this.mLianxifangshi = mLianxifangshi;
	}


	public String getmProvinceString() {
		return mProvinceString;
	}

	public void setmProvinceString(String mProvinceString) {
		this.mProvinceString = mProvinceString;
	}

	public String getmLianxifangshi(BaseActivity activity) {
		String textString = activity.getString(R.string.jobinfo_lianxiren) + getmContactPerson() + "\n"
				+ activity.getString(R.string.jobinfo_dianhua)+ getmContactPhone() + "\n" + activity.getString(R.string.jobinfo_chuanzhen) + getmCompanyFax() + "\n"
				+ activity.getString(R.string.jobinfo_youxiang) + getmEmail() + "\n" + activity.getString(R.string.jobinfo_dizhi) + getmAddress() + "\n";

		return textString;
	}
	private void createDoubleList(BaseActivity mActivity) {
		if (doubleList == null) {
			doubleList = new ArrayList<String>();
		}
		doubleList.clear();
		doubleList.add(getmGongzuoleixingKey(mActivity)+SplitString
					+getmGongzuoleixingString());
		doubleList.add(getmXinzidaiyuKey(mActivity)+SplitString
				+getmXinzidaiyu());
		doubleList.add(getworkTimeStringKey(mActivity)+SplitString
				+getWorkTimeString());
		doubleList.add(getmChizhuqingkuangStringKey(mActivity)+SplitString
				+getmChizhuqingkuangString());
		doubleList.add(getmZhaopinmumenKey(mActivity)+SplitString
				+getmZhaopinmumenString());		
		doubleList.add(getmZhaopinrenshuKey(mActivity)+SplitString
				+getmZhaopinrenshu());		
		if(getmFaburiqi()!=null&&!"".equals(getmFaburiqi().trim())){
			doubleList.add(getmFaburiqiKey(mActivity) + SplitString
					+ getmFaburiqi().substring(0, 10));
		}else{
			doubleList.add(getmFaburiqiKey(mActivity) + SplitString
					);
		}
		if(getmJiezhiriqi()!=null&&!"".equals(getmJiezhiriqi().trim())){
			doubleList.add(getmJiezhiriqiKey(mActivity) + SplitString
					+ getmFaburiqi().substring(0, 10));
		}else{
			doubleList.add(getmJiezhiriqiKey(mActivity) + SplitString
					);
		}
//		doubleList.add(getmJiezhiriqiKey(mActivity) + SplitString
//				+ getmJiezhiriqi().substring(0, 10));
		doubleList.add(getmGongzuodidianKey(mActivity) + SplitString
				+ getmGongzuodidian());
		doubleList.add(getmSuozaidiStringKey(mActivity) + SplitString
				+ getmSuozaidiString());		
		doubleList.add(getmXueliyaoqiuKey(mActivity) + SplitString
				+ getmXueliyaoqiu());
		doubleList.add(getmGongzuonianxianStringKey(mActivity) + SplitString
				+ getmGongzuonianxianString());
		doubleList.add(getmAgeKey(mActivity) + SplitString
				+ getmAge());
		doubleList.add(getmXingbieKey(mActivity) + SplitString
				+ getmXingbie());
	}

	public void createLongView(LinearLayout lay, BaseActivity mActivity) {
		// TODO Auto-generated method stub
		final LinearLayout _Layout = lay;
		createDoubleList(mActivity);
		int num = doubleList.size() % 2;
		int _Size = doubleList.size() / 2;
		for (int i = 0; i < _Size; i++) {
			int doubleNum = i * 2;
			LinearLayout _TextLayout = (LinearLayout) mActivity
					.getLayoutInflater().inflate(R.layout.jobinfoitem_double,
							null);
			TextView _LefTextView = (TextView) _TextLayout
					.findViewById(R.id.jobinfoitem_double_textleft);
			_LefTextView.setText(doubleList.get(doubleNum));
			TextView _RightTextView = (TextView) _TextLayout
					.findViewById(R.id.jobinfoitem_double_textright);
			_RightTextView.setText(doubleList.get(doubleNum + 1));
			lay.addView(_TextLayout);
		}
		final BaseActivity _Activity = mActivity;
	}

	// 订阅职位列表，搜索结果列表item
	public LinearLayout getSubscribeItemView(BaseActivity mActivity) {
		LinearLayout _Layout = (LinearLayout) mActivity.getLayoutInflater()
				.inflate(R.layout.poistionsubscribeitem, null);
		TextView _jobText = (TextView) _Layout.findViewById(R.id.jobname);
		_jobText.setText(getmZhiweimingcheng());
		TextView _addressText = (TextView) _Layout.findViewById(R.id.address);
		_addressText.setText(getmGongzuodidian());
		TextView _companyText = (TextView) _Layout.findViewById(R.id.company);
		_companyText.setText(getmGongsimingchen());
		TextView _timesText = (TextView) _Layout.findViewById(R.id.times);
		if("".equals(getmViewClicks())||getmViewClicks()==null){
			_timesText.setText("0");
		}else{
			_timesText.setText(getmViewClicks());
		}
		
		TextView _dateText = (TextView) _Layout.findViewById(R.id.date);
		if(Util.checkString(getmFaburiqi())){
			_dateText.setText(getmFaburiqi().subSequence(0, 10));
		}
		
		return _Layout;
	}

	// 带delete按钮的收藏项----收藏管理
		public LinearLayout getCollectionItemViewWithDelete(BaseActivity mActivity,PositionSubscribeAdapter adapter) {
			final BaseActivity _Activity = mActivity;
			final PositionSubscribeAdapter _Adapter = adapter;
			LinearLayout _Layout = (LinearLayout) mActivity.getLayoutInflater()
					.inflate(R.layout.collectioncandeleteitem, null);
			TextView _jobText = (TextView) _Layout.findViewById(R.id.jobname);
			_jobText.setText(getmZhiweimingcheng());
			TextView _addressText = (TextView) _Layout.findViewById(R.id.address);
			_addressText.setText(getmGongzuodidian());
			TextView _companyText = (TextView) _Layout.findViewById(R.id.company);
			_companyText.setText(getmGongsimingchen());
			TextView _timesText = (TextView) _Layout.findViewById(R.id.times);
			_timesText.setText(getmViewClicks());
			TextView _dateText = (TextView) _Layout.findViewById(R.id.date);
			_dateText.setText(getmFaburiqi().subSequence(0, 10));
			ImageButton _Button = (ImageButton) _Layout.findViewById(R.id.delete);
			_Button.setVisibility(View.VISIBLE);
			_Button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					DBHelper _Helper = new DBHelper(_Activity);
					_Helper.deleteJob(JobInfo.this);
					_Adapter.getmList().remove(JobInfo.this);
					_Adapter.notifyDataSetChanged();
				}
			});
			return _Layout;
		} 
	// 公司招聘职位列表item
	public LinearLayout getJobsItemView(BaseActivity mActivity) {
		LinearLayout _Layout = (LinearLayout) mActivity.getLayoutInflater()
				.inflate(R.layout.jobsitem, null);
		TextView _jobName = (TextView) _Layout.findViewById(R.id.jobname);
		TextView _jobShortInfo = (TextView) _Layout
				.findViewById(R.id.shortinfo);
		_jobName.setText(getmZhiweimingcheng());
		_jobShortInfo.setText(getmGongzuodidian() + " "
				+ getmFaburiqi().substring(0, 10));
		return _Layout;

	}
}
