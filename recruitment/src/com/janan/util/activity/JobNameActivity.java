package com.janan.util.activity;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.gazecloud.dyteam.R;
import com.janan.adapter.JobNameAdapter;
import com.janan.net.JobNameEngine;
import com.janan.recruit.BaseActivity;
import com.janan.view.mamager.TitleBarManager;

public class JobNameActivity extends BaseActivity {
	private LinearLayout rootLayout;
	private ListView mListView;
	private TitleBarManager mBarUtil;
	JobNameAdapter mAdapter;
	private Bundle mBundle;
	private String pId;

	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarUtil = new TitleBarManager(this);
		rootLayout.addView(
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
		mAdapter = new JobNameAdapter(mActivity,mBundle);
		// _Adapter = new CityAdapter(mActivity,pId);
		// mListView.setAdapter(_Adapter);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		rootLayout = (LinearLayout) findViewById(R.id.rootlay_searchcheckinfoactivity);
		mListView = (ListView) findViewById(R.id.listview_searchcheckinfoactivity);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchcheckinfoactivity);
		mActivity = this;
		Intent _Intent = getIntent();
		mBundle = _Intent.getBundleExtra(TAG_BUNDLE);
		mTitle = mBundle.getString(TAG_JOBDEPT);
		pId = mBundle.getString(TAG_JOBDEPTID);
		initView();
		showSelf();
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				closeProgress();
				if (msg.what == CallbackError) {
					showToast(msg);
				} else if (msg.what == CallbackSuccess) {					
					mAdapter.setmList(mEngine.getmList());
					mListView.setAdapter(mAdapter);
					mAdapter.notifyDataSetChanged();
				}

			}

		};
		getJobs();
	}

	JobNameEngine mEngine;

	public void getJobs() {
		showProgress();
		mEngine = new JobNameEngine(mActivity, true);
		mEngine.setDpId(pId);
		mEngine.start();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

	}
}
