package com.janan.recruit.companyactivity;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.JobInfo;
import com.janan.data.bean.search.SearchCondition;
import com.janan.net.SendJobsInfoEngine;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.personalactivity.ResumeChangeActivity;
import com.janan.recruit.searchactivity.SearchCheckInfoActivity;
import com.janan.util.Util;
import com.janan.util.activity.JobDeptActivity;
import com.janan.util.activity.ProvinceActivity;

public class SendJobsInfoActivity extends BaseActivity implements
		DatePickerDialog.OnDateSetListener {

	private EditText jobNameEditView;
	private TextView jobClassTextView;
	private TextView jobPlaceTextView;
	private TextView woringYearsTextView;
	private TextView mZhaopinduixiangTextView;
	private TextView mZhaopinleibieTextView;
	private TextView mBaochizhuTextView;
	private TextView mGongzuoshijianTextView;
	private TextView mJiezhiriqiTextView;
	private TextView jobDealTextView;
	private EditText countEditText;
	private EditText requiredEditText;

	private MyClickListener mListener;
	private JobInfo mInfo;

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		super.showSelf();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendjobsinfoactivity);
		mInfo = new JobInfo();
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				closeProgress();
				if (msg.what == CallbackSuccess) {

					showToast(toastFabuSuccess);

					finishSelf();
				} else if (msg.what == CallbackError) {
					showToast(msg);
				}

			}
		};
		initView();
		showSelf();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout) findViewById(R.id.layout_sendjobsinfoactivity);
		jobNameEditView = (EditText) findViewById(R.id.jobname);
		jobClassTextView = (TextView) findViewById(R.id.gongzuoleibie);
		jobPlaceTextView = (TextView) findViewById(R.id.jobarea);
		woringYearsTextView = (TextView) findViewById(R.id.workingyears);
		jobDealTextView = (TextView) findViewById(R.id.jobdeal);
		countEditText = (EditText) findViewById(R.id.countofperson);
		requiredEditText = (EditText) findViewById(R.id.zhiweimianshu);
		mZhaopinduixiangTextView = (TextView) findViewById(R.id.zhaopinduixiang);
		mZhaopinleibieTextView = (TextView) findViewById(R.id.zhaopinleibie);
		mBaochizhuTextView = (TextView) findViewById(R.id.baochizhu);
		mGongzuoshijianTextView = (TextView) findViewById(R.id.gongzuoshijian);
		mJiezhiriqiTextView = (TextView) findViewById(R.id.jiezhiriqi);

		setListenter();
	}

	public void setListenter() {
		mListener = new MyClickListener();
		jobClassTextView.setOnClickListener(mListener);
		jobPlaceTextView.setOnClickListener(mListener);
		woringYearsTextView.setOnClickListener(mListener);
		jobDealTextView.setOnClickListener(mListener);
		mZhaopinduixiangTextView.setOnClickListener(mListener);
		mZhaopinleibieTextView.setOnClickListener(mListener);
		mBaochizhuTextView.setOnClickListener(mListener);
		mGongzuoshijianTextView.setOnClickListener(mListener);
		mJiezhiriqiTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar _Calendar = Calendar.getInstance();
				mYear = _Calendar.get(Calendar.YEAR);
				mMonth = _Calendar.get(Calendar.MONTH);
				mDay = _Calendar.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog dlg = new DatePickerDialog(
						SendJobsInfoActivity.this,
						SendJobsInfoActivity.this, mYear, mMonth, mDay);
				dlg.show();
			}
		});

	}

	private int mYear = 0;
	private int mMonth = 0;
	private int mDay = 0;

	private class MyClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			Intent _Intent = new Intent();
			Bundle _Bundle = new Bundle();
			_Intent.putExtra(TAG_BUNDLE, _Bundle);
			if (id == jobClassTextView.getId()) {
				_Bundle.putInt(TAG_SEARCHKEY, id);
				_Bundle.putBoolean(TAG_HASJOBNAME, true);
				_Intent.setClass(mActivity, JobDeptActivity.class);
			} else if (id == jobPlaceTextView.getId()) {
				_Bundle.putInt(TAG_SEARCHKEY, id);
				_Bundle.putBoolean(TAG_HASAREA, true);
				_Bundle.putBoolean(TAG_HASCITY, true);
				_Intent.setClass(mActivity, ProvinceActivity.class);
			} else if (id == woringYearsTextView.getId()) {
				_Bundle.putInt(TAG_SEARCHKEY,
						SearchCondition.TAG_CONDITION_GONGZUONIANXIAN);
				_Intent.setClass(mActivity, SearchCheckInfoActivity.class);
			} else if (id == jobDealTextView.getId()) {
				_Bundle.putInt(TAG_SEARCHKEY,
						SearchCondition.TAG_CONDITION_QIWANGYUEXIN);
				_Intent.setClass(mActivity, SearchCheckInfoActivity.class);
			} else if (id == mZhaopinduixiangTextView.getId()) {
				_Bundle.putInt(TAG_SEARCHKEY,
						SearchCondition.TAG_CONDITION_ZHAOPINDUIXIANG);
				_Intent.setClass(mActivity, SearchCheckInfoActivity.class);
			} else if (id == mZhaopinleibieTextView.getId()) {
				_Bundle.putInt(TAG_SEARCHKEY,
						SearchCondition.TAG_CONDITION_ZHAOPINLEIBIE);
				_Intent.setClass(mActivity, SearchCheckInfoActivity.class);
			} else if (id == mBaochizhuTextView.getId()) {
				_Bundle.putInt(TAG_SEARCHKEY,
						SearchCondition.TAG_CONDITION_BAOCHIZHU);
				_Intent.setClass(mActivity, SearchCheckInfoActivity.class);
			} else if (id == mGongzuoshijianTextView.getId()) {
				_Bundle.putInt(TAG_SEARCHKEY,
						SearchCondition.TAG_CONDITION_GONGZUOSHIJIAN);
				_Intent.setClass(mActivity, SearchCheckInfoActivity.class);
			}
			startActivityForResult(_Intent, REQUEST_SEARCHCHEK);

		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_SEARCHCHEK) {
			if (resultCode == VALUE_RESULT_OK) {
				Bundle _Bundle = data.getBundleExtra(TAG_BUNDLE);
				int _Id = _Bundle.getInt(TAG_SEARCHKEY);
				if (_Id == jobClassTextView.getId()) {

					mInfo.setmZhaopinmumenString(_Bundle.getString(TAG_JOBNAME)+"-"+_Bundle.getString(TAG_JOBNAME));
//					jobClassTextView.setText(_Bundle.getString(TAG_JOBDEPT));
//					 mInfo.setmZhiweimingcheng(_Bundle.getString(TAG_JOBNAME));
					 jobClassTextView.setText(_Bundle.getString(TAG_JOBDEPT)
					 + "-" + _Bundle.getString(TAG_JOBNAME));

				} else if (_Id == jobPlaceTextView.getId()) {
					mInfo.setmCityString(_Bundle.getString(TAG_CITY));
					mInfo.setmAreaString(_Bundle.getString(TAG_AREA));
					mInfo.setmProvinceString(_Bundle.getString(TAG_PROVINCE));
					if (Util.checkZhixiashi(mInfo.getmProvinceString())) {
						jobPlaceTextView.setText(mInfo.getmProvinceString()
								+ "," + mInfo.getmAreaString());
					} else {
						jobPlaceTextView.setText(mInfo.getmProvinceString() + ","
								+ mInfo.getmCityString());
					}
				} else if (_Id == SearchCondition.TAG_CONDITION_GONGZUONIANXIAN) {
					woringYearsTextView.setText(_Bundle.getString(TAG_VALUE));
				} else if (_Id == SearchCondition.TAG_CONDITION_QIWANGYUEXIN) {
					jobDealTextView.setText(_Bundle.getString(TAG_VALUE));
				} else if (_Id == SearchCondition.TAG_CONDITION_ZHAOPINDUIXIANG) {
					mZhaopinduixiangTextView.setText(_Bundle
							.getString(TAG_VALUE));
					mInfo.setmGongzuoleixingString(_Bundle.getString(TAG_VALUE));
				} else if (_Id == SearchCondition.TAG_CONDITION_ZHAOPINLEIBIE) {
					mZhaopinleibieTextView
							.setText(_Bundle.getString(TAG_VALUE));
					mInfo.setmZhaopinleibieString(_Bundle.getString(TAG_VALUE));
				} else if (_Id == SearchCondition.TAG_CONDITION_BAOCHIZHU) {
					mBaochizhuTextView.setText(_Bundle.getString(TAG_VALUE));
					mInfo.setmChizhuqingkuangString(_Bundle
							.getString(TAG_VALUE));
				} else if (_Id == SearchCondition.TAG_CONDITION_GONGZUOSHIJIAN) {
					mGongzuoshijianTextView.setText(_Bundle
							.getString(TAG_VALUE));
					mInfo.setWorkTimeString(_Bundle.getString(TAG_VALUE));
				} 
//				else if (_Id == SearchCondition.TAG_CONDITION_GONGZUOSHIJIAN) {
//
//					Calendar _Calendar = Calendar.getInstance();
//					mYear = _Calendar.get(Calendar.YEAR);
//					mMonth = _Calendar.get(Calendar.MONTH);
//					mDay = _Calendar.get(Calendar.DAY_OF_MONTH);
//
//					DatePickerDialog dlg = new DatePickerDialog(
//							SendJobsInfoActivity.this,
//							SendJobsInfoActivity.this, mYear, mMonth, mDay);
//					dlg.show();
//				}

			}
		}
	}

	SendJobsInfoEngine mEngine;

	public void buttonOnclick(View view) {

		mInfo.setmGongzuonianxianString(woringYearsTextView.getText()
				.toString());
		mInfo.setmXinzidaiyu(jobDealTextView.getText().toString());
		mInfo.setmZhaopinrenshu(countEditText.getText().toString());
		mInfo.setJobRequire(requiredEditText.getText().toString());
		mInfo.setmZhiweimingcheng(jobNameEditView.getText().toString());
		mInfo.setmZhiweimiaoshu(requiredEditText.getText().toString().trim());

		startRequest();
	}

	@Override
	public void startRequest() {
		// TODO Auto-generated method stub
		if (Util.checkString(mInfo.getmZhaopinmumenString())
				&& Util.checkString(mInfo.getmZhiweimingcheng())
				&& Util.checkString(mInfo.getmAreaString())
				&& Util.checkString(mInfo.getmCityString())
				&& Util.checkString(mInfo.getmGongzuonianxianString())
				&& Util.checkString(mInfo.getmXinzidaiyu())
				&& Util.checkString(mInfo.getmZhaopinrenshu())
				&& Util.checkString(mInfo.getJobRequire())) {
			showProgress();
			mEngine = new SendJobsInfoEngine(mActivity, false);
			mEngine.setmInfo(mInfo);
			mEngine.start();
		} else {
			showToast(getString(R.string.toastzhangpinerror));
		}

	}

	@Override
	public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		if(arg1!=0){
			String date = Integer.toString(arg1) + "-"
					+ Integer.toString(arg2 + 1) + "-"
					+ Integer.toString(arg3);
			mJiezhiriqiTextView.setText(date);
			mInfo.setmEndYear(arg1+"");
			mInfo.setmEndMonth( Integer.toString(arg2 + 1));
			mInfo.setmEndDay(arg3+"");
			mInfo.setmJiezhiriqi(date);
		}
	}

}
