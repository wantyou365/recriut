package com.janan.recruit.personalactivity;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.gazecloud.dyteam.R;
import com.janan.adapter.ApplyRecordsAdapter;
import com.janan.data.bean.JobInfo;
import com.janan.net.ApplyRecoedsEngine;
import com.janan.recruit.BaseActivity;
import com.janan.view.mamager.TitleBarManager;

public class JobsListActivity extends BaseActivity{

	private LinearLayout mRootLayout;
	private ListView mJobsListView;
	private TitleBarManager mBarUtil;
	private ArrayList<JobInfo> mList;
	private boolean isCollection = false;
	private ApplyRecoedsEngine applyEngine;
	private int mPage = 1;
	private ApplyRecordsAdapter appAdapter;
	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarUtil = new TitleBarManager(this);
		mRootLayout.addView(mBarUtil.createBar(mTitle, getResources().getDrawable(R.drawable.homeback), null), 0);
		mBarUtil.setLeftButtonListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finishSelf();
			}
		});
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
	}
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout)findViewById(R.id.rootlay_jobslistactivity);		
		mJobsListView = (ListView)findViewById(R.id.jobslist);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jobslistactivity);
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		isCollection = getIntent().getBooleanExtra("conllection", false);
		mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				closeProgress();
				
				appAdapter = new ApplyRecordsAdapter(mActivity);
				appAdapter.setmList(applyEngine.getmList());
				mJobsListView.setAdapter(appAdapter);
				mJobsListView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			
		};
		if(!isCollection){
			startApply();
		}
		initView();
		showSelf();
	}
	
	private void startApply(){
		if(applyEngine == null){
			applyEngine = new ApplyRecoedsEngine(mActivity,true);
		}
		applyEngine.setmPage(mPage);
		applyEngine.start();
	}
	@Override
	public void showProgress() {
		// TODO Auto-generated method stub
		super.showProgress();
	}
	@Override
	public void closeProgress() {
		// TODO Auto-generated method stub
		super.closeProgress();
	}
	
	
}
