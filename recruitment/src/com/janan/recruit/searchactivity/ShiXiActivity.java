package com.janan.recruit.searchactivity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.gazecloud.dyteam.R;
import com.janan.adapter.ShixiAdapter;
import com.janan.data.bean.News;
import com.janan.data.bean.ShiXi;
import com.janan.net.BaseEngine;
import com.janan.net.WorkDynamicsListEngine;
import com.janan.recruit.BaseActivity;
import com.janan.resume.workdynactivity.WorkDynamicsInfoActivity;
import com.janan.view.mamager.TitleBarManager;

public class ShiXiActivity extends BaseActivity {
	private TitleBarManager mBarUtil;
	private ListView mListView;
	private ShixiAdapter mAdapter;
	private ArrayList<ShiXi> mList;
	private WorkDynamicsListEngine mListEngine;
	private int mPage = 1;
	private boolean isFirstSearch = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workdynamics);
		initView();
		showSelf();

		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				closeProgress();
				if (CallbackSuccess == msg.what) {
					isFirstSearch = false;
					ArrayList<ShiXi> _List = mListEngine.getShiList();
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
						mAdapter = new ShixiAdapter(mActivity);
						mListView.setAdapter(mAdapter);
					}

					mList = mAdapter.getmList();
					for (int i = 0; i < _List.size(); i++) {
						mList.add(_List.get(i));
					}
					mAdapter.notifyDataSetChanged();
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
								 WorkDynamicsInfoActivity.class);
								
								 _Intent.putExtra("shixi", ((ShiXi) mAdapter
								 .getItem(arg2)));
								 mActivity.startActivity(_Intent);
							}

						}

					});
				} else {
					showToast(msg);
				}
			}

		};
	}

	public void addBarThis() {
		// TODO Auto-generated method stub
		mTitle = getResources().getString(R.string.shixishengpaiqian);
		addBar();
		
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub

		addBarThis();

	}

	@Override
	public void finishSelf() {
		// TODO Auto-generated method stub
		super.finishSelf();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout) findViewById(R.id.rootlay_workdynamics);
		mListView = (ListView) findViewById(R.id.dynamicslist);
	}

	@Override
	public void startRequest() {
		showProgress();
		if (mListEngine == null) {
			mListEngine = new WorkDynamicsListEngine(mActivity, true);
		}
		mListEngine.setShixi(true);
		mListEngine.setmPage(mPage);
		mListEngine.start();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (isFirstSearch) {
			startRequest();
		}

	}

}
