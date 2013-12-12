package com.janan.recruit.companyactivity;

import java.util.ArrayList;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.gazecloud.dyteam.R;
import com.janan.adapter.JobsAdapter;
import com.janan.data.bean.JobInfo;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.searchactivity.JobInfoActivity;
import com.janan.view.mamager.SubScribeJobManager;
import com.janan.view.mamager.TitleBarManager;

public class JobsActivity extends BaseActivity {
	private ListView mListView;
	private ArrayList<JobInfo> mList;
	private BaseActivity mActivity;
	@Override
	public void finishSelf() {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		addBar();
		if (mList == null) {
			mList = new ArrayList<JobInfo>();
		}
		JobsAdapter _JobsAdapter = new JobsAdapter(this);
		_JobsAdapter.setmList(mList);
		mListView.setAdapter(_JobsAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				_Intent.setClass(mActivity, JobInfoActivity.class);
				_Intent.putExtra(STRING_TITLE,
						getResources().getString(R.string.jobinfotitle));
				startActivity(_Intent);
			}

		});

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout) findViewById(R.id.rootlay_jobsactivity);
		mListView = (ListView) findViewById(R.id.listview_josactivity);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jobsactivity);
		mActivity = this;
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		initView();
		showSelf();

	}

}
