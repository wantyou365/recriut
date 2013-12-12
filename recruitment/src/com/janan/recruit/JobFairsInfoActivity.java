package com.janan.recruit;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.JobFairs;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JobFairsInfoActivity extends BaseActivity{

	private JobFairs mInfo;
	private TextView mTitleView;
	private TextView mShortTitleView;
	private TextView mCopyfromView;
	private TextView mHitsView;
	private TextView mContentView;
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout)findViewById(R.id.rootlay_jobfairsinfo);
		mTitleView = (TextView)findViewById(R.id.title);
		mShortTitleView = (TextView)findViewById(R.id.shorttitle);
		mCopyfromView = (TextView)findViewById(R.id.copyfrom);
		mHitsView = (TextView)findViewById(R.id.times);
		mContentView = (TextView)findViewById(R.id.content);
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		super.showSelf();
		mTitleView.setText(mInfo.getmTitle());
		mShortTitleView.setText(mInfo.getmTypeName());
		mCopyfromView.setText(getString(R.string.jobfairsinfoactivity_comefrom)+mInfo.getmCopyFrom());
		mHitsView.setText(mInfo.getmHitString());
		mContentView.setText(mInfo.getmContent());
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jobfairsinfo);
		mInfo = (JobFairs)getIntent().getSerializableExtra("info");
		initView();
		showSelf();
	}
	
	
}
