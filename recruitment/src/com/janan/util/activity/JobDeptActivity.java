package com.janan.util.activity;

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
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.adapter.JobDeptAdapter;
import com.janan.adapter.PositionSubscribeAdapter;
import com.janan.adapter.ProvinceAdapter;
import com.janan.data.bean.JobInfo;
import com.janan.data.bean.search.SearchCondition;
import com.janan.data.data.Province;
import com.janan.net.JobDeptEngine;
import com.janan.net.ProvinceEngine;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.searchactivity.JobInfoActivity;
import com.janan.recruit.searchactivity.SearchActivity;
import com.janan.view.mamager.TitleBarManager;

public class JobDeptActivity extends BaseActivity {

	private LinearLayout rootLayout;
	private ListView mListView;
	private TitleBarManager mBarUtil;
	private Bundle mBundle;
	JobDeptAdapter mAdapter;

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
		mAdapter = new JobDeptAdapter(mActivity, mBundle);

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
//		mTitle =  _Intent.getStringExtra(STRING_TITLE);
//		if(mTitle!=null){
//			mTitle = "请选择"+mTitle;
//		}else{
//			mTitle = "请选择工作类别";
//		}
		mTitle = getString(R.string.selectjobclass);
		mBundle = _Intent.getBundleExtra(TAG_BUNDLE);
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
		getProvices();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == REQUEST_SEARCHCHEK) {
			if (resultCode == VALUE_RESULT_OK) {			
				mActivity.setResult(VALUE_RESULT_OK, data);
				finishSelf();
			}
		}

	}

	JobDeptEngine mEngine;

	public void getProvices() {
		showProgress();
		mEngine = new JobDeptEngine(mActivity, true);
		mEngine.start();
	}

}
