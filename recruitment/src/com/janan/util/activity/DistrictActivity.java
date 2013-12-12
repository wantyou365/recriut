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
import com.janan.adapter.DistrictAdapter;
import com.janan.net.CityEngine;
import com.janan.net.DistrictEngine;
import com.janan.recruit.BaseActivity;
import com.janan.view.mamager.TitleBarManager;

public class DistrictActivity extends BaseActivity {
	private LinearLayout rootLayout;
	private ListView mListView;
	private TitleBarManager mBarUtil;
	private String cId;
	DistrictAdapter mAdapter;
	private Bundle mBundle;
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
		mAdapter = new DistrictAdapter(mActivity,mBundle);
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
		mTitle = mBundle.getString(TAG_CITY);
		cId = mBundle.getString(TAG_CITYID);
		initView();
		showSelf();
		getCities();
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
	}
	DistrictEngine mEngine;

	public void getCities() {
		showProgress();
		mEngine = new DistrictEngine(mActivity, true);
		mEngine.setpId(cId);
		mEngine.start();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

	}
}
