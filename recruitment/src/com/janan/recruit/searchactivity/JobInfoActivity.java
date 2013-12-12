package com.janan.recruit.searchactivity;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.JobInfo;
import com.janan.data.bean.search.NearSearch;
import com.janan.net.ApplyEngine;
import com.janan.net.BaseEngine;
import com.janan.net.CityEngine;
import com.janan.net.JobInfoEngine;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.personalactivity.PersonalAcitivty;
import com.janan.util.DBHelper;
import com.janan.util.Util;
import com.janan.view.mamager.TitleBarManager;

/**
 * 具体职位信息activity
 * */
public class JobInfoActivity extends BaseActivity {

	private LinearLayout mRootLayout;
	private LinearLayout mJobInfoLayout;
	private TitleBarManager mBarUtil;
	private TextView mJobNameView;
//	private TextView mConpanyNameView;
	private TextView mJobDes;
	private TextView mContactInfoView;
	private TextView mMoreJobTextView;
	
	private TextView mApplyTextView;
	private TextView mCollentionTextView;
	private TextView mShareTextView;
	private String mTitle;
	JobInfo mInfo;
	
	private ApplyEngine mApplyEngine;
	private JobInfoEngine mEngine;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jobinfoactivity);
		Intent _Intent = getIntent();
		mTitle = _Intent.getStringExtra(STRING_TITLE);
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				closeProgress();
				
				if (msg.what == CallbackSuccess) {
					mInfo = mEngine.getmJobInfo();
					mJobNameView.setText(mInfo.getmZhiweimingcheng());
					mMoreJobTextView.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							toSearchResultActivity();
						}
					});
					mInfo.createLongView(mJobInfoLayout, mActivity);
					
					mJobDes.setText(Util.replaceString(mInfo.getmZhiweimiaoshu()));
					mContactInfoView.setText(mInfo.getmLianxifangshi(mActivity));
					
				}else if(msg.what == CallbackError){
					showToast(msg);
				}else if(msg.what == CallbackApplySuccess){
					String text = getString(R.string.toastregistersuccess);
					showToast(text);
				}
			}
		};
		initView();
		showSelf();
		
	}
	
	
	
	public void toSearchResultActivity(){
		NearSearch _Search = new NearSearch();
		_Search.setmComid(mInfo.getmComId());
		Intent _Intent = new Intent();
		_Intent.setClass(JobInfoActivity.this, SearchResultActivity.class);
		_Intent.putExtra(STRING_TITLE, mInfo.getmGongsimingchen());
		_Intent.putExtra("searchcondition", _Search);
		startActivity(_Intent);
	}
	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarUtil = new TitleBarManager(this);
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
	public void finishSelf() {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		addBar();
		setInfo();

	}

	public void initView() {
		mRootLayout = (LinearLayout) findViewById(R.id.layout_poistionactivity);
		mJobInfoLayout = (LinearLayout) findViewById(R.id.jobinfolay);
		mJobNameView = (TextView) findViewById(R.id.jobnametext);
		mJobDes = (TextView) findViewById(R.id.jobdescribe);
		mContactInfoView = (TextView) findViewById(R.id.contactinfo);
		mMoreJobTextView = (TextView)findViewById(R.id.morejob);
		
		mApplyTextView = (TextView)findViewById(R.id.jobapply);
		mApplyTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startApply();
			}
		});
		
		mCollentionTextView = (TextView)findViewById(R.id.jobconllection);
		mCollentionTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DBHelper _Helper = new DBHelper(mActivity);
				if(_Helper.checkJob(mInfo)){
					Toast.makeText(mActivity,mActivity.getString(R.string.jobcollected), Toast.LENGTH_SHORT).show();
				}else{
					_Helper.setJob(mInfo);
					Toast.makeText(mActivity, mActivity.getString(R.string.jobcollectedsuccess), Toast.LENGTH_SHORT).show();
				}								
			}
		});
		mShareTextView = (TextView)findViewById(R.id.jobshare);
		mShareTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Util.shareWork(mInfo.getmJobUrlString(), mInfo, mActivity);
			}
		});
	}

	public void setInfo() {

		Intent intent = getIntent();
		// 信息data JobInfoData
		mInfo = (JobInfo)intent.getSerializableExtra("job");
		startSearch();

	}
	public void startSearch() {
		showProgress();
		mEngine = new JobInfoEngine(mActivity, true);
		mEngine.setJid(mInfo.getmJobId());
		mEngine.start();
	}
	public void startApply(){
		if(BaseEngine.mPersonCookie == null){
			Intent _Intent = new Intent();
			_Intent.setClass(mActivity, PersonalAcitivty.class);
			_Intent.putExtra(TAG_STARTFROMAPPLY, true);
			startActivityForResult(_Intent,applyStartLoginRequestCode);
		}else{
			showProgress();
			mApplyEngine = new ApplyEngine(mActivity,true);
			mApplyEngine.setmInfo(mInfo);
			mApplyEngine.start();
		}
		
	}
	
	private void startCollect(){
		
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == applyStartLoginRequestCode){
			if(resultCode == RESULT_OK){
				if(data!=null&&data.getBooleanExtra(TAG_STARTFROMAPPLY, false)){
					startApply();
				}
			}
		}
	}
	
	
	
}
