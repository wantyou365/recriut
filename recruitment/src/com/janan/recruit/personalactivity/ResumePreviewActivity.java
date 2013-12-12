package com.janan.recruit.personalactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.personal.Resume;
import com.janan.net.ResumeInfoEngine;
import com.janan.recruit.BaseActivity;
import com.janan.util.Util;

public class ResumePreviewActivity extends BaseActivity{
	
	private TextView mRealNameTextView;
	private TextView mSexTextView;
	private TextView mPhoneNumTextView;
	private TextView mBirthdayTextView;
	private TextView mLocusTextView;
	private TextView mIntentJobTextView;
	private TextView mIntentJobTextView1;
	private TextView mIntentJobTextView2;
	private TextView mIntentJobPlaceTextView;
	private TextView mIntentJobPlaceTextView1;
	private TextView mIntentJobPlaceTextView2;
	private TextView mQiuzhixinTextView;				
	private Resume mResume = null;
	private ResumeInfoEngine mEngine;
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout)findViewById(R.id.rootlay_resumainfoactivity);
		mRealNameTextView = (TextView)findViewById(R.id.realname);
		mSexTextView = (TextView)findViewById(R.id.xingbie);
		mPhoneNumTextView = (TextView)findViewById(R.id.minzu);
		mBirthdayTextView = (TextView)findViewById(R.id.brithday);
		mLocusTextView = (TextView)findViewById(R.id.liveplace);
		mIntentJobTextView = (TextView)findViewById(R.id.qiwanggongzuo);
		mIntentJobTextView1 = (TextView)findViewById(R.id.qiwanggongzuo1);
		mIntentJobTextView2 = (TextView)findViewById(R.id.qiwanggongzuo2);
		mIntentJobPlaceTextView = (TextView)findViewById(R.id.qiwanggongzuodidian);
		mIntentJobPlaceTextView1 = (TextView)findViewById(R.id.qiwanggongzuodidian1);
		mIntentJobPlaceTextView2 = (TextView)findViewById(R.id.qiwanggongzuodidian2);
		mQiuzhixinTextView = (TextView)findViewById(R.id.qiuzhixin);
	}

	private void showResume(Resume resume){
		mRealNameTextView.setText(resume.getmNameString());
		mSexTextView.setText(resume.getmSexString());
		mPhoneNumTextView.setText(resume.getmPhoneNum());
		mBirthdayTextView.setText(resume.getmBirthday());
		if(Util.checkString(resume.getmResidence_City())){
			mLocusTextView.setText(resume.getmResidence_City()+resume.getmResidence_Area());
		}else{
			mLocusTextView.setText("");
		}
		
	
		mIntentJobTextView.setText(mResume.getmIntentionJob());
		mIntentJobTextView1.setText(mResume.getmIntentionJob1());
		mIntentJobTextView2.setText(mResume.getmIntentionJob2());
		mIntentJobPlaceTextView.setText(mResume.getmIntentionPlaceString());
		mIntentJobPlaceTextView1.setText(mResume.getmIntentionPlaceString1());
		mIntentJobPlaceTextView2.setText(mResume.getmIntentionPlaceString2());
		mQiuzhixinTextView.setText(mResume.getmJobContentString());
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resuminfoactivity);
		mTitle = mActivity.getString(R.string.personalResumePreview);
		initView();
		showSelf();
		mResume = (Resume)getIntent().getSerializableExtra("resume");
		if(mResume == null || mResume.getmUserId()!=null){
			startRequest();
		}else{
			showResume(mResume);
		}
		
		mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				closeProgress();
				if(msg.what == CallbackSuccess){
					mResume = mEngine.getmResume();
					showResume(mResume);
					
				}else if(msg.what == CallbackError){
					showToast(msg);
				}
			}
			
		};
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		super.showSelf();
	}

	@Override
	public void startRequest() {
		// TODO Auto-generated method stub
		showProgress();
		mEngine = new ResumeInfoEngine(mActivity,true);
		if(mResume!=null&&mResume.getmUserId()!=null){
			mEngine.setPerId(mResume.getmUserId());
		}
		mEngine.start();
	}
	
	
	
}
