package com.janan.recruit.personalactivity;

import java.util.ArrayList;


import android.content.Intent;
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
import com.janan.adapter.PositionSubscribeAdapter;
import com.janan.data.bean.JobInfo;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.searchactivity.JobInfoActivity;
import com.janan.util.DBHelper;
import com.janan.view.mamager.TitleBarManager;

public class JobsCollectionActivity extends BaseActivity{

	private ArrayList<JobInfo> mList = null;
	private ListView mListView;
	private LinearLayout mRootLayout;
	private TitleBarManager mBarUtil;
	private PositionSubscribeAdapter mAdapter;
	private boolean canDelete;
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
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout) findViewById(R.id.rootlay_seemyresumecompany);
		mListView = (ListView)findViewById(R.id.comslist);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seemyresumecompanyactivity);
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		canDelete = getIntent().getBooleanExtra("candelete", false);
	
		mAdapter = new PositionSubscribeAdapter(mActivity,canDelete);
		mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				closeProgress();
				if(msg.what == 1){
					mListView.setAdapter(mAdapter);
					mAdapter.notifyDataSetChanged();
					mListView.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							Intent _Intent = new Intent();
							_Intent.setClass(mActivity, JobInfoActivity.class);
							_Intent.putExtra(STRING_TITLE, getResources()
									.getString(R.string.jobinfotitle));
							JobInfo _JobInfo = mList.get(arg2);
							_Intent.putExtra("job", _JobInfo);
							startActivity(_Intent);
						}
					});
				}
			}
			
		};
		initView();
		addBar();
		
		getJobList();
	}

	private void getJobList(){
		showProgress();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				DBHelper _Helper = new DBHelper(mActivity);
				mList = _Helper.getJobs();
				if(mList == null){
					Message msg = mHandler.obtainMessage();
					msg.what = 2;
					mHandler.sendMessage(msg);
				}else{
					mAdapter.setmList(mList);
					updateUI();
				}
				
			}
		}).start();
	}

	@Override
	public void updateUI() {
		// TODO Auto-generated method stub
		Message msg = mHandler.obtainMessage();
		msg.what = 1;
		mHandler.sendMessage(msg);
	}
	
	
}
