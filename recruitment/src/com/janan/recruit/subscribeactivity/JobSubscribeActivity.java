package com.janan.recruit.subscribeactivity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.gazecloud.dyteam.R;
import com.janan.adapter.PositionSubscribeAdapter;
import com.janan.data.bean.JobInfo;
import com.janan.data.bean.search.NearSearch;
import com.janan.net.BaseEngine;
import com.janan.net.SearchEngine;
import com.janan.net.SubscribeJobEngine;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.searchactivity.JobInfoActivity;
import com.janan.view.mamager.TitleBarManager;

/**
 * 职位订阅activity，显示职位订阅信息
 * */
public class JobSubscribeActivity extends BaseActivity {

	private LinearLayout mRootLayout; // 跟节点
	private TitleBarManager mBarUtil;// 顶工具栏管理器
	private ListView mListView;// 订阅列表
	private LinearLayout mNoscribeLayout;
	private ArrayList<JobInfo> mList;// 职位信息bean列表
	private BaseActivity mActivity;
	private NearSearch mSearch;
	SubscribeJobEngine mSearchEngine;
	private int mPage = 1;
	private boolean isSubed = false;
	private PositionSubscribeAdapter mAdapter;

	private void setHeader(int size){
		if (size < mSearchEngine.mPageNum) {
			if (mListView.getFooterViewsCount() > 0) {
				mListView.removeFooterView(mFooter);
			}
		} else {
			if (mListView.getFooterViewsCount() == 0) {
				mListView.addFooterView(mFooter);
			}
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.positionsubscribeactivity);
		mActivity = this;
		initView();
		showSelf();
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				closeProgress();
				if (msg.what == CallbackError) {
					
					isSubed = false;
					showToast(msg);
				} else if (msg.what == CallbackSubscribeSearch) {
					
					isSubed = false;
					startSubscribe(isSubed);					
				} else if (msg.what == CallbackSuccess) {
					ArrayList<JobInfo> _List = mSearchEngine.getmList();
					int size = _List.size();
					setHeader(size);
					if (mAdapter == null) {
						mAdapter = new PositionSubscribeAdapter(mActivity,
								false);
						mListView.setAdapter(mAdapter);
					}
					if(mPage>1){
						mList = mAdapter.getmList();
						for (int i = 0; i < size; i++) {
							mList.add(_List.get(i));
						}
					}else{
						mList = _List;
						mAdapter.setmList(mList);
					}
					mPage++;
					setListViewLis();

					mAdapter.notifyDataSetChanged();
				}

			}

		};

	}

	/**
	 * 初始化activity的view信息
	 * */
	public void initView() {
		mRootLayout = (LinearLayout) findViewById(R.id.layout_positionsubscribeactivity);
		mListView = (ListView) findViewById(R.id.listview_positionsubscribeactivity);
		mNoscribeLayout = (LinearLayout) findViewById(R.id.noscribelay);
	}

	private void setListViewLis() {
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (mListView.getFooterViewsCount() > 0
						&& arg2 == mListView.getCount() - 1) {
					startSubscribe(isSubed);
				} else {
					Intent _Intent = new Intent();
					_Intent.setClass(mActivity, JobInfoActivity.class);
					_Intent.putExtra(STRING_TITLE,
							getResources().getString(R.string.jobinfotitle));
					JobInfo _JobInfo = mAdapter.getmList().get(arg2);
					_Intent.putExtra("job", _JobInfo);
					startActivity(_Intent);
				}

			}
		});
	}

	/**
	 * 初始化别表信息
	 * */
	public void initListView() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				_Intent.setClass(mActivity, JobInfoActivity.class);
				_Intent.putExtra(STRING_TITLE, getResources()
						.getString(R.string.jobinfotitle));
				JobInfo _JobInfo = mList.get(arg2);
				_Intent.putExtra("job", _JobInfo);
				startActivity(_Intent);
//				Intent intent = new Intent();
//				intent.putExtra(STRING_TITLE, mActivity.getResources()
//						.getString(R.string.jobinfotitle));
//				intent.setClass(mActivity, JobInfoActivity.class);
//				startActivity(intent);
			}
		});

	}

	/**
	 * 添加顶部工具栏
	 * */
	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarUtil = new TitleBarManager(this);
		mRootLayout.addView(
				mBarUtil.createBar(getString(R.string.tab_subscribe), null,
						getResources().getDrawable(R.drawable.ic_dy)), 0);
		mBarUtil.setRightButtonListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (checkUser()) {
					
					Intent _Intent = new Intent();
					_Intent.setClass(mActivity, SubScribeSearch.class);
					mActivity.startActivityForResult(_Intent, 66);
				} else {
					showToast(toastSureLogin);
				}

			}
		});
	}

	private boolean checkUser() {
		if (mUser != null && mUser.getmUserID() != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 显示页面
	 * */
	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		addBar();

		BaseEngine _Engine = new BaseEngine(this, true);
//		if (!_Engine.isConnected()) {
//			mNoscribeLayout.setVisibility(View.VISIBLE);
//			mListView.setVisibility(View.GONE);
//		} else {
			mNoscribeLayout.setVisibility(View.GONE);
			mListView.setVisibility(View.VISIBLE);
			initListView();

//		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 66) {
			if (resultCode == RESULT_OK) {
				mSearch = (NearSearch) data
						.getSerializableExtra("searchcondition");
				if(mSearch!=null){
					isSubed = true;
					mSearchEngine.setmSearchkeys(mSearch);
					startSubscribe(isSubed);
				}
				
			}else{
				
			}
		}
	}

//	private void startSubscribe() {
//		showProgress();
//		if (_Engine == null) {
//			_Engine = new SubscribeJobEngine(mActivity, true);
//		}
//		_Engine.setSubscribe(true);
//		if (mSearch == null) {
//			mSearch = (NearSearch) getIntent().getSerializableExtra(
//					"searchcondition");
//		}
//		if (mSearch != null) {
//			_Engine.setmPage(mPage);
//			_Engine.setmSearchkeys(mSearch);
//			_Engine.start();
//		} else {
//			mSearch = new NearSearch();
//			_Engine.setmPage(mPage);
//			_Engine.setmSearchkeys(mSearch);
//			_Engine.start();
//		}
//	}

	private void startSubscribe(boolean subscribe) {
		if (checkUser()) {
			showProgress();
			if (mSearchEngine == null) {
				mSearchEngine = new SubscribeJobEngine(mActivity, true);
			}
			if(subscribe){
				mPage = 1;
			}
			mSearchEngine.setSubscribe(subscribe);
			mSearchEngine.setmPage(mPage);
			mSearchEngine.start();

		} else {
			isResumed = false;
			showToast(toastSureLogin);
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	boolean isResumed = false;
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(!isResumed){
			isResumed = true;
			startSubscribe(isSubed);
		}
		

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("janan", "onStop");
	}

}
