package com.janan.recruit;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.gazecloud.dyteam.R;
import com.janan.adapter.JobFairsTitleAdapter;
import com.janan.adapter.NewsTitleAdapter;
import com.janan.data.bean.JobFairs;
import com.janan.data.bean.News;
import com.janan.net.BaseEngine;
import com.janan.net.JobFairsListEngine;


public class JobFairsListActivity extends BaseActivity{

	private JobFairsListEngine mListEngine;
	private int mPage = 1;
	private ListView mListView;
	private JobFairsTitleAdapter mAdapter;
	private ArrayList<JobFairs> mList;
	@Override
	public void initView() {
		
		mRootLayout = (LinearLayout) findViewById(R.id.rootlay_jobfairslistactivity);
		mListView = (ListView)findViewById(R.id.jobfairsslist);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jobfairslistactivity);
		mTitle = getString(R.string.jobfairs_title);
		startRequest();
		initView();
		showSelf();
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				closeProgress();
				if (CallbackSuccess == msg.what) {
					
					ArrayList<JobFairs> _List = mListEngine.getmList();
					if (_List.size() == BaseEngine.mPageNum) {
						mPage++;
						if (mListView.getFooterViewsCount() == 0) {
							mListView.addFooterView(mFooter);
							
						}
					} else {
						if (mListView.getFooterViewsCount() > 0) {
							mListView.removeFooterView(mFooter);
						}
					}
					if (mAdapter == null) {
						mAdapter = new JobFairsTitleAdapter(mActivity);
						mListView.setAdapter(mAdapter);
					}

					mList = mAdapter.getmList();
					for (int i = 0; i < _List.size(); i++) {
						mList.add(_List.get(i));
					}
					mAdapter.notifyDataSetChanged();
					setListenter();
				} else {
					showToast(msg);
				}
			}

		};
	}

	@Override
	public void startRequest() {
		// TODO Auto-generated method stub
		super.startRequest();
		showProgress();
		if (mListEngine == null) {
			mListEngine = new JobFairsListEngine(mActivity, true);
		}
		mListEngine.setmPage(mPage);
		mListEngine.start();
	}

	@Override
	public void setListenter() {
		// TODO Auto-generated method stub
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub

				if (mListView.getFooterViewsCount() > 0
						&& arg2 == mListView.getCount() - 1) {
					startRequest();
				} else {
					Intent _Intent = new Intent();
					_Intent.setClass(mActivity,
							JobFairsInfoActivity.class);
					_Intent.putExtra("info", (JobFairs) mAdapter
							.getItem(arg2));
					mActivity.startActivity(_Intent);
				}

			}

		});
	}
	
	
	
}
