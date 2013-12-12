package com.janan.resume.workdynactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.News;
import com.janan.data.bean.ShiXi;
import com.janan.net.WorkDynamicsInfoEngine;
import com.janan.recruit.BaseActivity;
import com.janan.util.Util;

public class WorkDynamicsInfoActivity extends BaseActivity{

	private String newIdString;
	private News mNews;
	private WorkDynamicsInfoEngine mEngine;
	private TextView mTitleView;
	private TextView mCopyfromView;
	private TextView mHitsView;
	private TextView mContentView;
	
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout)findViewById(R.id.rootlay_newsinfo);
		mTitleView = (TextView)findViewById(R.id.title);
		mCopyfromView = (TextView)findViewById(R.id.copyfrom);
		mHitsView = (TextView)findViewById(R.id.times);
		mContentView = (TextView)findViewById(R.id.content);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newsinfo);
		newIdString = getIntent().getStringExtra("newsid");
		mTitle = getString(R.string.zixuanxiangqing);
		initView();
		showSelf();
		if(Util.checkString(newIdString)){
			startRequest();
			mHandler = new Handler(){

				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					closeProgress();
					if(CallbackSuccess == msg.what){
						mNews = mEngine.getmNews();
						mTitleView.setText(mNews.getmTitle());
						if(Util.checkString(mNews.getmCopyFrom())){
							mCopyfromView.setText(getString(R.string.jobfairsinfoactivity_comefrom)+mNews.getmCopyFrom());
						}else{
							mCopyfromView.setText(mNews.getfName());
						}
						
//						mHitsView.setVisibility(View.VISIBLE);
						mHitsView.setText(mNews.getmHitString());
						mContentView.setText(Util.replaceString(mNews.getmContent()));
					}else{
						showToast(msg);
					}
				}
				
			};
		}else{
			ShiXi _ShiXi = (ShiXi)getIntent().getSerializableExtra("shixi");
			mTitleView.setText(_ShiXi.getTitle());
			mCopyfromView.setText(_ShiXi.getPoster());
			mHitsView.setText(_ShiXi.getClickcount());
			mContentView.setText(_ShiXi.getContent());
		}
		
	}

	@Override
	public void startRequest() {
		// TODO Auto-generated method stub
		super.startRequest();
		showProgress();
		if(mEngine == null){
			mEngine = new WorkDynamicsInfoEngine(mActivity,true);
		}
		mEngine.setNewsId(newIdString);
		mEngine.start();
	}

	
	
}
