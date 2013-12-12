package com.janan.recruit.personalactivity;

import java.util.Calendar;



import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.BaseBean;
import com.janan.data.bean.personal.Resume;
import com.janan.net.ResumeInfoChangeEngine;
import com.janan.net.ResumeInfoEngine;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.searchactivity.SearchCheckInfoActivity;
import com.janan.util.Util;
import com.janan.util.activity.JobDeptActivity;
import com.janan.util.activity.ProvinceActivity;
import com.janan.view.mamager.TitleBarManager;

public class ResumeChangeActivity extends BaseActivity implements
		DatePickerDialog.OnDateSetListener {
	private LinearLayout mRootLayout;
	private ScrollView mScrollView;
	private LinearLayout mScrollChild;
	private LinearLayout mCoverLayout;
	private TextView titleTextView;
	private Button sendButton;
	
	private EditText mRealNameEdit;
	private EditText mPhoneNumEdit;
	private EditText mJobContentEditText;
	private TextView mSexText;
	private TextView mBirthText;
	private TextView mLivePlaceText;
	private TextView mQiwanggongzuoText;
	private TextView mQiwanggongzuoText1;
	private TextView mQiwanggongzuoText2;
	private TextView mQiwanggongzuodidian;
	private TextView mQiwanggongzuodidian1;
	private TextView mQiwanggongzuodidian2;
	private TitleBarManager mBarUtil;

	private Resume mResume;
	private ResumeInfoChangeEngine changeEngine;
	private ResumeInfoEngine mInfoEngine;
	private int mYear = 0;
	private int mMonth = 0;
	private int mDay = 0;
	private boolean isSearch = false;
	private boolean isBirthday = true;
	private static final int mLimit = 2;
	private int stepNow = 1;
//	private TextView mWorkingYearsText;
//	private TextView mQiwangxinziText;
	
//	private EditText mQQMSNEdit;
//	private TextView mQiwanggongzuoleixingText;
//	private EditText mGerenjianjieEdit;
	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarUtil = new TitleBarManager(mActivity);
		mRootLayout.addView(
				mBarUtil.createBar(mTitle,
						getResources().getDrawable(R.drawable.homeback), null),
				0);
		mBarUtil.setLeftButtonListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finishSelf();
			}
		});
	}

	@Override
	public void updateUI() {
		// TODO Auto-generated method stub
		super.updateUI();
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		super.saveData();
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		addBar();
		setLisnter();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout) findViewById(R.id.rootlay_resumactivity);
		mScrollView = (ScrollView) findViewById(R.id.part2);
		mScrollChild = (LinearLayout) findViewById(R.id.scrollchild);
		mCoverLayout = (LinearLayout)findViewById(R.id.coverLay);
		titleTextView = (TextView)findViewById(R.id.titletextview);
		sendButton = (Button)findViewById(R.id.confirmbutton);
		mRealNameEdit = (EditText) findViewById(R.id.realname);
		mSexText = (TextView) findViewById(R.id.xingbie);
		mPhoneNumEdit = (EditText)findViewById(R.id.phoneNum);
		mJobContentEditText = (EditText)findViewById(R.id.qiuzhixin);
		mBirthText = (TextView) findViewById(R.id.brithday);
		mLivePlaceText = (TextView) findViewById(R.id.liveplace);
		mQiwanggongzuoText = (TextView) findViewById(R.id.qiwanggongzuo);
		mQiwanggongzuoText1 = (TextView) findViewById(R.id.qiwanggongzuo1);
		mQiwanggongzuoText2 = (TextView) findViewById(R.id.qiwanggongzuo2);
		mQiwanggongzuodidian = (TextView) findViewById(R.id.qiwanggongzuodidian);
		mQiwanggongzuodidian1 = (TextView) findViewById(R.id.qiwanggongzuodidian1);
		mQiwanggongzuodidian2 = (TextView) findViewById(R.id.qiwanggongzuodidian2);
//		mMinzuEdit = (EditText) findViewById(R.id.minzu);
//		mEdusText = (TextView) findViewById(R.id.xueli);
//		mNianlingEditText = (EditText) findViewById(R.id.nianling);
//		mBiyexuexiaEditText = (EditText)findViewById(R.id.biyexuexiao);
//		mZhuanyeleibieEditText = (EditText)findViewById(R.id.zhuanyeleibie);
//		mZhuanyeEditText = (EditText)findViewById(R.id.zhuanye);
//		mBiyeshijianEditText = (TextView)findViewById(R.id.biyeshijian);
		
//		mHukouPlaceText = (TextView) findViewById(R.id.hukouplace);
		
//		mWorkingYearsText = (TextView) findViewById(R.id.gongzuonianxian);
//		mQiwangxinziText = (TextView) findViewById(R.id.qiwangxinzi);
//		mQQMSNEdit = (EditText) findViewById(R.id.msnqq);
//		
//		mQiwanggongzuoleixingText = (TextView) findViewById(R.id.qiwanggongzuoleixing);
		
//		mGerenjianjieEdit = (EditText) findViewById(R.id.gerenjianjie);

	}

	class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent _Intent = new Intent();
			Bundle _Bundle = new Bundle();
			_Intent.putExtra(TAG_BUNDLE, _Bundle);
			int id = v.getId();
			if (id == mSexText.getId()) {
				_Bundle.putInt(TAG_SEARCHKEY,
						BaseBean.TAG_CONDITION_XINGBIEYAOQIU);
				_Intent.putExtra(STRING_TITLE, getString(R.string.select)
						+ getString(R.string.xingbie));
				_Intent.setClass(mActivity, SearchCheckInfoActivity.class);
			} else if (id == mLivePlaceText.getId()) {
				_Bundle.putInt(TAG_SEARCHKEY, BaseBean.TAG_JUZHUDI);
				_Bundle.putBoolean(TAG_HASAREA, true);
				_Bundle.putBoolean(TAG_HASCITY, true);
				_Intent.setClass(mActivity, ProvinceActivity.class);
				_Intent.putExtra(STRING_TITLE, getString(R.string.select)
						+ getString(R.string.xianzaizhudi));
			} else if (id == mQiwanggongzuoText.getId()) {
				_Intent.putExtra(STRING_TITLE, mActivity.getString(R.string.jobname));
				_Bundle.putInt(TAG_SEARCHKEY, mQiwanggongzuoText.getId());
				_Bundle.putBoolean(TAG_HASJOBNAME, true);
				_Intent.setClass(mActivity, JobDeptActivity.class);
			} else if (id == mQiwanggongzuoText1.getId()) {
				_Intent.putExtra(STRING_TITLE, mActivity.getString(R.string.jobname));
				_Bundle.putInt(TAG_SEARCHKEY, mQiwanggongzuoText1.getId());
				_Bundle.putBoolean(TAG_HASJOBNAME, true);
				_Intent.setClass(mActivity, JobDeptActivity.class);
			}  else if (id == mQiwanggongzuoText2.getId()) {
				_Intent.putExtra(STRING_TITLE, mActivity.getString(R.string.jobname));
				_Bundle.putInt(TAG_SEARCHKEY, mQiwanggongzuoText2.getId());
				_Bundle.putBoolean(TAG_HASJOBNAME, true);
				_Intent.setClass(mActivity, JobDeptActivity.class);
			}  else if (id == mQiwanggongzuodidian.getId()) {
				_Bundle.putInt(TAG_SEARCHKEY,
						mQiwanggongzuodidian.getId());
				_Bundle.putBoolean(TAG_HASAREA, true);
				_Bundle.putBoolean(TAG_HASCITY, true);
				_Intent.setClass(mActivity, ProvinceActivity.class);
				_Intent.putExtra(STRING_TITLE, getString(R.string.select)
						+ getString(R.string.qiwanggongzuodidian));
			}else if (id == mQiwanggongzuodidian1.getId()) {
				_Bundle.putInt(TAG_SEARCHKEY,
						mQiwanggongzuodidian1.getId());
				_Bundle.putBoolean(TAG_HASAREA, true);
				_Bundle.putBoolean(TAG_HASCITY, true);
				_Intent.setClass(mActivity, ProvinceActivity.class);
				_Intent.putExtra(STRING_TITLE, getString(R.string.select)
						+ getString(R.string.qiwanggongzuodidian));
			}else if (id == mQiwanggongzuodidian2.getId()) {
				_Bundle.putInt(TAG_SEARCHKEY,
						mQiwanggongzuodidian2.getId());
				_Bundle.putBoolean(TAG_HASAREA, true);
				_Bundle.putBoolean(TAG_HASCITY, true);
				_Intent.setClass(mActivity, ProvinceActivity.class);
				_Intent.putExtra(STRING_TITLE, getString(R.string.select)
						+ getString(R.string.qiwanggongzuodidian));
			}
//			else if (id == mEdusText.getId()) {
//				_Bundle.putInt(TAG_SEARCHKEY,
//						BaseBean.TAG_CONDITION_XUELIYAOQIU);
//				_Intent.putExtra(STRING_TITLE, getString(R.string.select)
//						+ getString(R.string.zuigaoxueli));
//				_Intent.setClass(mActivity, SearchCheckInfoActivity.class);
//			} else if (id == mHukouPlaceText.getId()) {
//				_Bundle.putInt(TAG_SEARCHKEY, BaseBean.TAG_HUKOUSUOZAIDI);
//				_Bundle.putBoolean(TAG_HASAREA, true);
//				_Bundle.putBoolean(TAG_HASCITY, true);
//				_Intent.setClass(mActivity, ProvinceActivity.class);
//				_Intent.putExtra(STRING_TITLE, getString(R.string.select)
//						+ getString(R.string.hukousuozaidi));
//			} 
			
//			else if (id == mWorkingYearsText.getId()) {
//				_Bundle.putInt(TAG_SEARCHKEY,
//						BaseBean.TAG_CONDITION_GONGZUONIANXIAN);
//				_Intent.putExtra(STRING_TITLE, getString(R.string.select)
//						+ getString(R.string.gongzuonianxian));
//				_Intent.setClass(mActivity, SearchCheckInfoActivity.class);
//			} else if (id == mQiwangxinziText.getId()) {
//				_Bundle.putInt(TAG_SEARCHKEY,
//						BaseBean.TAG_SALARY);
//				_Intent.putExtra(STRING_TITLE, getString(R.string.select)
//						+ getString(R.string.qiwangxinzi));
//				_Intent.setClass(mActivity, SearchCheckInfoActivity.class);
//			} else if (id == mQiwanggongzuoleixingText.getId()) {
//				_Intent.putExtra(STRING_TITLE, mActivity.getString(R.string.jobtype));
//				_Bundle.putInt(TAG_SEARCHKEY, BaseBean.TAG_JOBCLASS);
//				_Bundle.putBoolean(TAG_HASJOBNAME, false);
//				_Intent.setClass(mActivity, JobDeptActivity.class);
//			} 
			
			mActivity.startActivityForResult(_Intent,
					mActivity.REQUEST_SEARCHCHEK);
		}

	}

	public void setLisnter() {
		MyListener _Listener = new MyListener();

		mSexText.setOnClickListener(_Listener);
		mLivePlaceText.setOnClickListener(_Listener);
		mQiwanggongzuoText.setOnClickListener(_Listener);
		mQiwanggongzuoText1.setOnClickListener(_Listener);
		mQiwanggongzuoText2.setOnClickListener(_Listener);
		mQiwanggongzuodidian.setOnClickListener(_Listener);
		mQiwanggongzuodidian1.setOnClickListener(_Listener);
		mQiwanggongzuodidian2.setOnClickListener(_Listener);
		mBirthText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isBirthday = true;
					Calendar _Calendar = Calendar.getInstance();
					mYear = _Calendar.get(Calendar.YEAR);
					mMonth = _Calendar.get(Calendar.MONTH);
					mDay = _Calendar.get(Calendar.DAY_OF_MONTH);
					
				
				DatePickerDialog dlg = new DatePickerDialog(
						ResumeChangeActivity.this, ResumeChangeActivity.this, mYear,
						mMonth, mDay);
				dlg.show();

			}
		});
//		mEdusText.setOnClickListener(_Listener);
//		mHukouPlaceText.setOnClickListener(_Listener);
//		mWorkingYearsText.setOnClickListener(_Listener);
//		mQiwangxinziText.setOnClickListener(_Listener);
//		mQiwanggongzuoleixingText.setOnClickListener(_Listener);
//		mBiyeshijianEditText.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				isBirthday = false;
//					Calendar _Calendar = Calendar.getInstance();
//					mYear = _Calendar.get(Calendar.YEAR);
//					mMonth = _Calendar.get(Calendar.MONTH);
//					mDay = _Calendar.get(Calendar.DAY_OF_MONTH);
//					
//				
//				DatePickerDialog dlg = new DatePickerDialog(
//						ResumeChangeActivity.this, ResumeChangeActivity.this, mYear,
//						mMonth, mDay);
//				dlg.show();
//
//			}
//		});
		
	}

	
	public void startSearch() {
		// TODO Auto-generated method stub
		showProgress();
		mInfoEngine = new ResumeInfoEngine(mActivity,true);
		mInfoEngine.start();
		isSearch = true;
	}
	private void showResume(Resume resume){
		mRealNameEdit.setText(resume.getmNameString());
		mPhoneNumEdit.setText(resume.getmPhoneNum());
		mJobContentEditText.setText(resume.getmJobContentString());
		setValue(resume.getmSexString(),mSexText);	
		setValue(resume.getmBirthday(),mBirthText);
		if(Util.checkString(resume.getmResidence_City())){
			setValue(resume.getmResidence_City()+resume.getmResidence_Area(),mLivePlaceText);
		}else{
			setValue("",mLivePlaceText);
		}
		setValue(resume.getmIntentionPlaceString(),mQiwanggongzuodidian);
		setValue(resume.getmIntentionPlaceString1(),mQiwanggongzuodidian1);
		setValue(resume.getmIntentionPlaceString2(),mQiwanggongzuodidian2);
		setValue(resume.getmIntentionJob(),mQiwanggongzuoText);
		setValue(resume.getmIntentionJob1(),mQiwanggongzuoText1);
		setValue(resume.getmIntentionJob2(),mQiwanggongzuoText2);
//		mMinzuEdit.setText(resume.getmMinZuString());
//		mNianlingEditText.setText(resume.getmAgeString());
//		setValue(resume.getmEducationString(),mEdusText);
		
//		mBiyexuexiaEditText.setText(resume.getmLastSchoolString());
//		mZhuanyeleibieEditText.setText(resume.getmZhuanyeClass());
//		mZhuanyeEditText.setText(resume.getmZhuanYeString());
		
//		setValue(resume.getmBiyeYear(),mBiyeshijianEditText);
//		if(Util.checkString(resume.getmDoor_City())){
//			setValue(resume.getmDoor_City()+resume.getmDoor_AreaString(),mHukouPlaceText);
//		}else{
//			setValue("",mHukouPlaceText);
//		}
		
//		mHukouPlaceText.setText(resume.getmDoor_City()+resume.getmDoor_AreaString());
		
//		mLivePlaceText.setText(resume.getmResidence_City()+resume.getmResidence_Area());
//		setValue(resume.getmWorkingYears(),mWorkingYearsText);
//		mWorkingYearsText.setText(resume.getmWorkingYears());
//		setValue(resume.getmQiwangyuexin(),mQiwangxinziText);
//		mQiwangxinziText.setText(resume.getmQiwangyuexin());
//		mQQMSNEdit.setText(resume.getmMsnOrQQ());
//		setValue(resume.getmIntentionJobClass(),mQiwanggongzuoleixingText);
//		mQiwanggongzuoleixingText.setText(resume.getmIntentionJobClass());
		
//		mQiwanggongzuoText.setText(mResume.getmIntentionJob());
		
//		mQiwanggongzuodidian.setText(mResume.getmIntentionPlaceString());
//		mGerenjianjieEdit.setText(mResume.getmJianjie());
		
	}
	private void setValue(String value,TextView text){
		if(value!=null&&!"".equals(value.trim())){
			text.setText(value);
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resumchangeactivity);
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		if(mTitle == null){
			mTitle = getString(R.string.xiugaiweijianli);
		}
		initView();
		showSelf();
		startSearch();
		mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				closeProgress();
				if(msg.what == CallbackSuccess){
					if(isSearch){
						mResume = mInfoEngine.getmResume();
						showResume(mResume);
						isSearch = false;
					}else{
						if(stepNow<mLimit){
							stepNow++;
							mScrollView.setVisibility(View.GONE);
							mCoverLayout.setVisibility(View.VISIBLE);
							titleTextView.setText(getResources().getString(R.string.changresumetitle2));
							sendButton.setText(getResources().getString(R.string.confirmchange));
						}else{
							showToast(msg);
							finishSelf();
						}
						
					}										
				}else if(msg.what == CallbackError){
					isSearch = false;
					showToast(msg);
				}
			}
			
		};
	}
	private void checkResume(){
		if(mResume==null){
			mResume = new Resume();
		}
	}
	public void changeOnclick(View view) {
		checkResume();
		
		mResume.setmNameString(mRealNameEdit.getText().toString().trim());
		mResume.setmSexString(mSexText.getText().toString());
		mResume.setmBirthday(mBirthText.getText().toString());
		mResume.setmIntentionJob(mQiwanggongzuoText.getText().toString());
		mResume.setmIntentionJob1(mQiwanggongzuoText1.getText().toString());
		mResume.setmIntentionJob2(mQiwanggongzuoText2.getText().toString());		
		mResume.setmPhoneNum(mPhoneNumEdit.getText().toString().trim());
		mResume.setmJobContentString(mJobContentEditText.getText().toString().trim());
		if(Util.checkString(mResume.getmNameString())&&Util.checkString(mResume.getmSexString())){
			if(Util.checkString(mResume.getmPhoneNum())){
				if(!(mResume.getmPhoneNum().length()==11)){
					showToast(getResources().getString(R.string.toastnumerror));
				}else{
					startChange();
				}
			}else{
				startChange();
			}
			
		}else{
			showToast(getString(R.string.toastchangeresumeerror));
		}
		
//		mResume.setmMinZuString(mMinzuEdit.getText().toString());
//		mResume.setmAgeString(mNianlingEditText.getText().toString());		
//		mResume.setmEducationString(mEdusText.getText().toString());
//		mResume.setmLastSchoolString(mBiyexuexiaEditText.getText().toString());
//		mResume.setmZhuanYeString(mZhuanyeleibieEditText.getText().toString());
//		mResume.setmZhuanyeClass(mZhuanyeEditText.getText().toString());
//		mResume.setmBiyeYear(mBiyeshijianEditText.getText().toString());		
		
		
//		mResume.setmHukousuozaidi(mHukouPlaceText.getText().toString());
//		mResume.setmResidence(mLivePlaceText.getText().toString());
//		mResume.setmWorkingYears(mWorkingYearsText.getText().toString());
//		mResume.setmQiwangyuexin(mQiwangxinziText.getText().toString());
//		mResume.setmMsnOrQQ(mQQMSNEdit.getText().toString());
//		mResume.setmIntentionJobClass(mQiwanggongzuoleixingText.getText()
//				.toString());
		
//		mResume.setmJianjie(mGerenjianjieEdit.getText().toString());
		
	}

	

	public void startChange() {
	
			showProgress();
			changeEngine = new ResumeInfoChangeEngine(mActivity, true);
			changeEngine.setmResume(mResume);
			changeEngine.start();
		
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			Bundle _Bundle = data.getBundleExtra(TAG_BUNDLE);
			if (_Bundle != null) {
				if (requestCode == REQUEST_SEARCHCHEK) {
					checkResume();
					if (resultCode == VALUE_RESULT_OK) {
						int _Tag = _Bundle.getInt(TAG_SEARCHKEY, -1);
						if (_Tag == BaseBean.TAG_CONDITION_XINGBIEYAOQIU) {
							mSexText.setText(_Bundle.getString(TAG_VALUE));
						} else if (_Tag == BaseBean.TAG_CONDITION_XUELIYAOQIU) {
//							mEdusText.setText(_Bundle.getString(TAG_VALUE));
						} else if (_Tag == BaseBean.TAG_HUKOUSUOZAIDI) {
							if(Util.checkZhixiashi(_Bundle
									.getString(TAG_PROVINCE))){
								mResume.setmDoor_City(_Bundle.getString(TAG_PROVINCE));
							}else{
								mResume.setmDoor_City(_Bundle.getString(TAG_CITY));
							}
							mResume.setmDoor_AreaString(_Bundle.getString(TAG_AREA));
//							mHukouPlaceText.setText(_Bundle
//									.getString(TAG_PROVINCE)
//									+ ","
//									+ _Bundle.getString(TAG_CITY)
//									+ ","
//									+ _Bundle.getString(TAG_AREA));
						} else if (_Tag == BaseBean.TAG_JUZHUDI) {
							if(Util.checkZhixiashi(_Bundle
									.getString(TAG_PROVINCE))){
								mResume.setmResidence_City(_Bundle.getString(TAG_PROVINCE));
							}else{
								mResume.setmResidence_City(_Bundle.getString(TAG_CITY));
							}
							mResume.setmResidence_Area(_Bundle.getString(TAG_AREA));
							mLivePlaceText.setText(_Bundle
									.getString(TAG_PROVINCE)
									+ ","
									+ _Bundle.getString(TAG_CITY)
									+ ","
									+ _Bundle.getString(TAG_AREA));
						} else if (_Tag == BaseBean.TAG_CONDITION_GONGZUONIANXIAN) {
//							mWorkingYearsText.setText(_Bundle
//									.getString(TAG_VALUE));
						} else if (_Tag == BaseBean.TAG_SALARY) {
//							mQiwangxinziText.setText(_Bundle
//									.getString(TAG_VALUE));
						} else if (_Tag == BaseBean.TAG_JOBCLASS) {
//							mQiwanggongzuoleixingText.setText(_Bundle
//									.getString(TAG_JOBDEPT));
						} else if (_Tag == mQiwanggongzuoText.getId()) {
							mQiwanggongzuoText.setText(_Bundle
									.getString(TAG_JOBNAME));
						} else if (_Tag == mQiwanggongzuoText1.getId()) {
							mQiwanggongzuoText1.setText(_Bundle
									.getString(TAG_JOBNAME));
						} else if (_Tag == mQiwanggongzuoText2.getId()) {
							mQiwanggongzuoText2.setText(_Bundle
									.getString(TAG_JOBNAME));
						} else if (_Tag == mQiwanggongzuodidian.getId()) {
							if(Util.checkZhixiashi(_Bundle
									.getString(TAG_PROVINCE))){
								mResume.setmIntentionPlaceString(_Bundle
										.getString(TAG_PROVINCE));
							}else{
								mResume.setmIntentionPlaceString(_Bundle.getString(TAG_CITY));
							}
							
							mQiwanggongzuodidian.setText(_Bundle
									.getString(TAG_PROVINCE)
									+ ","
									+ _Bundle.getString(TAG_CITY)
									+ ","
									+ _Bundle.getString(TAG_AREA));
						}else if (_Tag == mQiwanggongzuodidian1.getId()) {
							if(Util.checkZhixiashi(_Bundle
									.getString(TAG_PROVINCE))){
								mResume.setmIntentionPlaceString1(_Bundle
										.getString(TAG_PROVINCE));
							}else{
								mResume.setmIntentionPlaceString1(_Bundle.getString(TAG_CITY));
							}
							
							mQiwanggongzuodidian1.setText(_Bundle
									.getString(TAG_PROVINCE)
									+ ","
									+ _Bundle.getString(TAG_CITY)
									+ ","
									+ _Bundle.getString(TAG_AREA));
						}else if (_Tag == mQiwanggongzuodidian2.getId()) {
							if(Util.checkZhixiashi(_Bundle
									.getString(TAG_PROVINCE))){
								mResume.setmIntentionPlaceString2(_Bundle
										.getString(TAG_PROVINCE));
							}else{
								mResume.setmIntentionPlaceString2(_Bundle.getString(TAG_CITY));
							}
							
							mQiwanggongzuodidian2.setText(_Bundle
									.getString(TAG_PROVINCE)
									+ ","
									+ _Bundle.getString(TAG_CITY)
									+ ","
									+ _Bundle.getString(TAG_AREA));
						}
					}
				}

			}
		}

	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		checkResume();
		mResume.setmBirth_Day(dayOfMonth+"");
		mResume.setmBirth_Month(monthOfYear+"");
		mResume.setmBirth_Year(year+"");
		if(isBirthday){
			mBirthText.setText(Integer.toString(year) + "-"
					+ Integer.toString((monthOfYear + 1)) + "-"
					+ Integer.toString(dayOfMonth) );
		}else{
//			mBiyeshijianEditText.setText(Integer.toString(year) + "-"
//					+ Integer.toString((monthOfYear + 1)) + "-"
//					+ Integer.toString(dayOfMonth) + "");
		}
		
	}

	private void searchResume(){
		
	}
}
