package com.janan.data.bean.personal;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.BaseBean;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.personalactivity.ResumePreviewActivity;
import com.janan.util.Util;

public class Resume extends BaseBean {

	public static final String TAG_CHINESE = "chinese";
	public static final String TAG_ENGLISH = "english";
	private int mPage;
	private String mUserId;
	private String mUsername;
	private String mEmailString;

	public String getmUsername() {
		return mUsername;
	}

	public void setmUsername(String mUsername) {
		this.mUsername = mUsername;
	}

	public String getmEmailString() {
		return mEmailString;
	}

	public void setmEmailString(String mEmailString) {
		this.mEmailString = mEmailString;
	}

	private String mNameString;// 名字
	private String mSexString;// 性别
	private String mMinZuString;// 民族
	private String mAgeString;// 年龄
	private String mEducationString;// 学历
	private String mLastSchoolString;// 毕业学校
	private String mZhuanYeString;
	private String mZhuanyeClass;// zy_class
	private String mBiyeYear;
	private String mJobContentString;// 求职信

	public String getmJobContentString() {
		return mJobContentString;
	}

	public void setmJobContentString(String mJobContentString) {
		this.mJobContentString = mJobContentString;
	}

	public String getmBiyeYear() {
		return mBiyeYear;
	}

	public void setmBiyeYear(String mBiyeYear) {
		this.mBiyeYear = mBiyeYear;
	}

	private String mHukousuozaidi;
	private String mPhoneNum;// 手机好号码
	private String mBirthday;// 出生日期
	private String mBirth_Year;
	private String mBirth_Month;
	private String mBirth_Day;
	private String mResidence;// 居住地
	private String mResidence_Province;
	private String mResidence_City;
	private String mResidence_Area;
	private String mIntentionPlaceString;// 意向地点
	private String mIntentionPlace_Province;
	private String mIntentionPlace_City;
	private String mIntentionPlace_Area;
	private String mIntentionPlaceString1;// 意向地点
	private String mIntentionPlace_Province1;
	private String mIntentionPlace_City1;
	private String mIntentionPlace_Area1;
	private String mIntentionPlaceString2;// 意向地点
	private String mIntentionPlace_Province2;
	private String mIntentionPlace_City2;
	private String mIntentionPlace_Area2;

	private String mDoor;
	private String mDoor_Province;
	private String mDoor_City;
	private String mDoor_AreaString;
	private String mMsnOrQQ;

	public String getmMsnOrQQ() {
		return mMsnOrQQ;
	}

	public void setmMsnOrQQ(String mMsnOrQQ) {
		this.mMsnOrQQ = mMsnOrQQ;
	}

	public String getmDoor() {
		return mDoor;
	}

	public void setmDoor(String mDoor) {
		this.mDoor = mDoor;
	}

	public String getmDoor_Province() {
		return mDoor_Province;
	}

	public void setmDoor_Province(String mDoor_Province) {
		this.mDoor_Province = mDoor_Province;
	}

	public String getmDoor_City() {
		return mDoor_City;
	}

	public void setmDoor_City(String mDoor_City) {
		this.mDoor_City = mDoor_City;
	}

	public String getmDoor_AreaString() {
		return mDoor_AreaString;
	}

	public void setmDoor_AreaString(String mDoor_AreaString) {
		this.mDoor_AreaString = mDoor_AreaString;
	}

	private String mIntentionJobClass;
	private String mIntentionJob;// 意向职位
	private String mIntentionJob1;// 意向职位
	private String mIntentionJob2;// 意向职位
	private String mWorkingYears;

	private String mHeightString;// 身高
	private String mQiwangyuexin;// 期望月薪
	private String qqString;
	private String msnString;
	// 照片
	private CoverLetter mCoverLetter;// 求职信
	private String mJianjie;// 个人简介

	private String mYihun;// 已婚

	public String getmIntentionPlaceString1() {
		return mIntentionPlaceString1;
	}

	public void setmIntentionPlaceString1(String mIntentionPlaceString1) {
		this.mIntentionPlaceString1 = mIntentionPlaceString1;
	}

	public String getmIntentionPlace_Province1() {
		return mIntentionPlace_Province1;
	}

	public void setmIntentionPlace_Province1(String mIntentionPlace_Province1) {
		this.mIntentionPlace_Province1 = mIntentionPlace_Province1;
	}

	public String getmIntentionPlace_City1() {
		return mIntentionPlace_City1;
	}

	public void setmIntentionPlace_City1(String mIntentionPlace_City1) {
		this.mIntentionPlace_City1 = mIntentionPlace_City1;
	}

	public String getmIntentionPlace_Area1() {
		return mIntentionPlace_Area1;
	}

	public void setmIntentionPlace_Area1(String mIntentionPlace_Area1) {
		this.mIntentionPlace_Area1 = mIntentionPlace_Area1;
	}

	public String getmIntentionPlaceString2() {
		return mIntentionPlaceString2;
	}

	public void setmIntentionPlaceString2(String mIntentionPlaceString2) {
		this.mIntentionPlaceString2 = mIntentionPlaceString2;
	}

	public String getmIntentionPlace_Province2() {
		return mIntentionPlace_Province2;
	}

	public void setmIntentionPlace_Province2(String mIntentionPlace_Province2) {
		this.mIntentionPlace_Province2 = mIntentionPlace_Province2;
	}

	public String getmIntentionPlace_City2() {
		return mIntentionPlace_City2;
	}

	public void setmIntentionPlace_City2(String mIntentionPlace_City2) {
		this.mIntentionPlace_City2 = mIntentionPlace_City2;
	}

	public String getmIntentionPlace_Area2() {
		return mIntentionPlace_Area2;
	}

	public void setmIntentionPlace_Area2(String mIntentionPlace_Area2) {
		this.mIntentionPlace_Area2 = mIntentionPlace_Area2;
	}

	public String getmIntentionJob1() {
		return mIntentionJob1;
	}

	public void setmIntentionJob1(String mIntentionJob1) {
		this.mIntentionJob1 = mIntentionJob1;
	}

	public String getmIntentionJob2() {
		return mIntentionJob2;
	}

	public void setmIntentionJob2(String mIntentionJob2) {
		this.mIntentionJob2 = mIntentionJob2;
	}

	// 企业收到简历使用
	private String mTitle;
	private String mComid;
	private String mJobid;
	private String mAddDate;
	private String mMemo;
	private String mJobName;
	private boolean isSelected = false;

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getmComid() {
		return mComid;
	}

	public void setmComid(String mComid) {
		this.mComid = mComid;
	}

	public String getmJobid() {
		return mJobid;
	}

	public void setmJobid(String mJobid) {
		this.mJobid = mJobid;
	}

	public String getmAddDate() {
		return mAddDate;
	}

	public void setmAddDate(String mAddDate) {
		this.mAddDate = mAddDate;
	}

	public String getmMemo() {
		return mMemo;
	}

	public void setmMemo(String mMemo) {
		this.mMemo = mMemo;
	}

	public String getmJobName() {
		return mJobName;
	}

	public void setmJobName(String mJobName) {
		this.mJobName = mJobName;
	}

	public String getmYihun() {
		return mYihun;
	}

	public void setmYihun(String mYihun) {
		this.mYihun = mYihun;
	}

	public int getmPage() {
		return mPage;
	}

	public void setmPage(int mPage) {
		this.mPage = mPage;
	}

	public String getmNameString() {
		return mNameString;
	}

	public void setmNameString(String mNameString) {
		this.mNameString = mNameString;
	}

	public String getmSexString() {
		return mSexString;
	}

	public void setmSexString(String mSexString) {
		this.mSexString = mSexString;
	}

	public String getmPhoneNum() {
		return mPhoneNum;
	}

	public void setmPhoneNum(String mPhoneNum) {
		this.mPhoneNum = mPhoneNum;
	}

	public String getmBirthday() {
		return mBirthday;
	}

	public void setmBirthday(String mBirthday) {
		this.mBirthday = mBirthday;
	}

	public String getmResidence() {
		return mResidence;
	}

	public void setmResidence(String mResidence) {
		this.mResidence = mResidence;
	}

	public String getmIntentionPlaceString() {
		return mIntentionPlaceString;
	}

	public void setmIntentionPlaceString(String mIntentionPlaceString) {
		this.mIntentionPlaceString = mIntentionPlaceString;
	}

	public String getmIntentionJob() {
		return mIntentionJob;
	}

	public void setmIntentionJob(String mIntentionJob) {
		this.mIntentionJob = mIntentionJob;
	}

	public String getmEducationString() {
		return mEducationString;
	}

	public void setmEducationString(String mEducationString) {
		this.mEducationString = mEducationString;
	}

	public String getmHeightString() {
		return mHeightString;
	}

	public void setmHeightString(String mHeightString) {
		this.mHeightString = mHeightString;
	}

	public String getmQiwangyuexin() {
		return mQiwangyuexin;
	}

	public void setmQiwangyuexin(String mQiwangyuexin) {
		this.mQiwangyuexin = mQiwangyuexin;
	}

	public CoverLetter getmCoverLetter() {
		return mCoverLetter;
	}

	public void setmCoverLetter(CoverLetter mCoverLetter) {
		this.mCoverLetter = mCoverLetter;
	}

	public String getmUserId() {
		return mUserId;
	}

	public void setmUserId(String mUserId) {
		this.mUserId = mUserId;
	}

	public LinearLayout createView() {

		return null;
	}

	public String getmMinZuString() {
		return mMinZuString;
	}

	public void setmMinZuString(String mMinZuString) {
		this.mMinZuString = mMinZuString;
	}

	public String getmAgeString() {
		return mAgeString;
	}

	public void setmAgeString(String mAgeString) {
		this.mAgeString = mAgeString;
	}

	public String getmLastSchoolString() {
		return mLastSchoolString;
	}

	public void setmLastSchoolString(String mLastSchoolString) {
		this.mLastSchoolString = mLastSchoolString;
	}

	public String getmZhuanYeString() {
		return mZhuanYeString;
	}

	public void setmZhuanYeString(String mZhuanYeString) {
		this.mZhuanYeString = mZhuanYeString;
	}

	public static String getTagChinese() {
		return TAG_CHINESE;
	}

	public static String getTagEnglish() {
		return TAG_ENGLISH;
	}

	public String getmHukousuozaidi() {
		return mHukousuozaidi;
	}

	public void setmHukousuozaidi(String mHukousuozaidi) {
		this.mHukousuozaidi = mHukousuozaidi;
	}

	public String getmWorkingYears() {
		return mWorkingYears;
	}

	public void setmWorkingYears(String mWorkingYears) {
		this.mWorkingYears = mWorkingYears;
	}

	public String getQqString() {
		return qqString;
	}

	public void setQqString(String qqString) {
		this.qqString = qqString;
	}

	public String getMsnString() {
		return msnString;
	}

	public void setMsnString(String msnString) {
		this.msnString = msnString;
	}

	public String getmIntentionJobClass() {
		return mIntentionJobClass;
	}

	public void setmIntentionJobClass(String mIntentionJobClass) {
		this.mIntentionJobClass = mIntentionJobClass;
	}

	public String getmJianjie() {
		return mJianjie;
	}

	public void setmJianjie(String mJianjie) {
		this.mJianjie = mJianjie;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getmBirth_Year() {
		return mBirth_Year;
	}

	public void setmBirth_Year(String mBirth_Year) {
		this.mBirth_Year = mBirth_Year;
	}

	public String getmBirth_Month() {
		return mBirth_Month;
	}

	public void setmBirth_Month(String mBirth_Month) {
		this.mBirth_Month = mBirth_Month;
	}

	public String getmBirth_Day() {
		return mBirth_Day;
	}

	public void setmBirth_Day(String mBirth_Day) {
		this.mBirth_Day = mBirth_Day;
	}

	public String getmResidence_Province() {
		return mResidence_Province;
	}

	public void setmResidence_Province(String mResidence_Province) {
		this.mResidence_Province = mResidence_Province;
	}

	public String getmResidence_City() {
		return mResidence_City;
	}

	public void setmResidence_City(String mResidence_City) {
		this.mResidence_City = mResidence_City;
	}

	public String getmResidence_Area() {
		return mResidence_Area;
	}

	public void setmResidence_Area(String mResidence_Area) {
		this.mResidence_Area = mResidence_Area;
	}

	public String getmIntentionPlace_Province() {
		return mIntentionPlace_Province;
	}

	public void setmIntentionPlace_Province(String mIntentionPlace_Province) {
		this.mIntentionPlace_Province = mIntentionPlace_Province;
	}

	public String getmIntentionPlace_City() {
		return mIntentionPlace_City;
	}

	public void setmIntentionPlace_City(String mIntentionPlace_City) {
		this.mIntentionPlace_City = mIntentionPlace_City;
	}

	public String getmIntentionPlace_Area() {
		return mIntentionPlace_Area;
	}

	public void setmIntentionPlace_Area(String mIntentionPlace_Area) {
		this.mIntentionPlace_Area = mIntentionPlace_Area;
	}

	public View getRecieveResumeView(final BaseActivity activity,
			boolean hasCheckBox, View _Layout) {

		LinearLayout _LeftLayout = (LinearLayout) _Layout
				.findViewById(R.id.leftlay);
		
		_Layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Resume _Resume = Resume.this;
				Intent _Intent = new Intent();
				_Intent.setClass(activity, ResumePreviewActivity.class);
				_Intent.putExtra("resume", _Resume);
				activity.startActivity(_Intent);
			}
		});
		TextView titleView = (TextView) _Layout.findViewById(R.id.title);
		titleView.setText(getmTitle());
		TextView nameTextView = (TextView) _Layout
				.findViewById(R.id.text1_coveritem);
		nameTextView.setText(getmNameString());
		TextView dateTextView = (TextView) _Layout
				.findViewById(R.id.text2_coveritem);
		dateTextView.setText(getmAddDate().substring(0, 10).replace(" ", "-"));
		TextView contentTextView = (TextView) _Layout
				.findViewById(R.id.content);
		contentTextView.setText(getmMemo());
		if (hasCheckBox) {
			CheckBox _Box = (CheckBox) _Layout.findViewById(R.id.checkBox);			
			_Box.setVisibility(View.VISIBLE);			
			_Box.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					setSelected(isChecked);
					System.out.println("点击改变了"+isChecked);
				}
			});
			_Box.setChecked(isSelected);
		}

		return _Layout;
	}

	public View getSearchResumeView(BaseActivity activity,View _Layout) {
//		LinearLayout _Layout = (LinearLayout) activity.getLayoutInflater()
//				.inflate(R.layout.searchresumeitemview, null);
		TextView _NameText = (TextView) _Layout.findViewById(R.id.name);

		_NameText.setText(activity.getString(R.string.resume_xingming)
				+ Util.handleString(getmNameString()));

		TextView _SexText = (TextView) _Layout.findViewById(R.id.sex);

		_SexText.setText(activity.getString(R.string.resume_xingbie)
				+ Util.handleString(getmSexString()));

		TextView _NationText = (TextView) _Layout.findViewById(R.id.nation);
		_NationText.setText(activity.getString(R.string.resume_minzu)
				+ Util.handleString(getmMinZuString()));
		TextView _EdusText = (TextView) _Layout.findViewById(R.id.edus);
		_EdusText.setText(activity.getString(R.string.resume_xueli)
				+ getmEducationString());
		TextView _WorkingYearsText = (TextView) _Layout
				.findViewById(R.id.woringyears);
		_WorkingYearsText.setText(activity
				.getString(R.string.resume_gongzuonianxian)
				+ Util.handleString(getmWorkingYears()));
		TextView _LastSchoolText = (TextView) _Layout
				.findViewById(R.id.lastschool);
		_LastSchoolText.setText(activity.getString(R.string.resume_biyexuexiao)
				+ getmLastSchoolString());
		TextView _ZhuanyeText = (TextView) _Layout.findViewById(R.id.zhuanye);
		_ZhuanyeText.setText(activity.getString(R.string.resume_zhuanye)
				+ Util.handleString(getmZhuanYeString()));
		return _Layout;
	}

	public String getmZhuanyeClass() {
		return mZhuanyeClass;
	}

	public void setmZhuanyeClass(String mZhuanyeClass) {
		this.mZhuanyeClass = mZhuanyeClass;
	}

}
