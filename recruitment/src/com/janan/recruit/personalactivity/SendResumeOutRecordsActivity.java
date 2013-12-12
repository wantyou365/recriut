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
import com.janan.adapter.OutResumeRecordAdapter;
import com.janan.adapter.PositionSubscribeAdapter;
import com.janan.data.bean.JobInfo;
import com.janan.data.bean.OutResumeRecord;
import com.janan.data.data.DataForSearchCondition;
import com.janan.net.SendResumeOutRecordsEngine;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.searchactivity.JobInfoActivity;
import com.janan.util.Util;
import com.janan.view.mamager.TitleBarManager;

public class SendResumeOutRecordsActivity extends BaseActivity{
	private LinearLayout mRootLayout;
	private ListView mListView;
	private TitleBarManager mBarUtil;
	private OutResumeRecordAdapter mAdapter;
	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarUtil = new TitleBarManager(this);

		mRootLayout.addView(
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
	public void showSelf() {
		// TODO Auto-generated method stub
		addBar();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout)findViewById(R.id.rootlay_sendresumeoutrecordsactivity);
		mListView = (ListView)findViewById(R.id.recordslist);
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendresumeoutrecordsactivity);
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				closeProgress();
				if (msg.what == CallbackError) {
					showToast(msg);
					
				} else if (msg.what == CallbackSuccess) {
					ArrayList<OutResumeRecord> _List = mEngine.getmList();
					if(mAdapter == null){
						mAdapter = new OutResumeRecordAdapter(mActivity);
					}
					mAdapter.setmList(_List);
					mListView.setAdapter(mAdapter);
					mAdapter.notifyDataSetChanged();
//					int size = _List.size();
//					
//					if(isButton){
//						mAdapter = new PositionSubscribeAdapter(mActivity,false);
//						mListView.setAdapter(mAdapter);
//						isButton = false;
//						if(mSearch.getmIntentPlace() == null||DataForSearchCondition.defaultAllString.equals(mSearch.getmIntentPlace())){
//							mTitle = defaultTitle;
//						}else{
//							if(Util.checkZhixiashi(mProvinceString)){
//								mTitle = mProvinceString+"-"+mAreaString;
//							}else{
//								mTitle = mCityString+"-"+mAreaString;
//							}
//						}
//					}else{
//						if(mAdapter == null){
//							mAdapter = new PositionSubscribeAdapter(mActivity,false);
//							mListView.setAdapter(mAdapter);
//						}
//					}
//					
//					
//					mList = mAdapter.getmList();
//					for(int i=0;i<size;i++){
//						mList.add(_List.get(i));
//					}					
////					if (mTitle == null) {
////						mBarUtil.setTitle(defaultTitle);
////					} else {
//						mBarUtil.setTitle(mTitle);
////					}
//					
//					mListView.setOnItemClickListener(new OnItemClickListener() {
//						@Override
//						public void onItemClick(AdapterView<?> arg0, View arg1,
//								int arg2, long arg3) {
//							// TODO Auto-generated method stub
//							if(mListView.getFooterViewsCount()>0&&arg2 == mListView.getCount()-1){
//								startSearch();
//							}else{
//								Intent _Intent = new Intent();
//								_Intent.setClass(mActivity, JobInfoActivity.class);
//								_Intent.putExtra(STRING_TITLE, getResources()
//										.getString(R.string.jobinfotitle));
//								JobInfo _JobInfo = mList.get(arg2);
//								_Intent.putExtra("job", _JobInfo);
//								startActivity(_Intent);
//							}
//							
//
//						}
//					});
					

					mAdapter.notifyDataSetChanged();
				}else{
					showToast(msg);
				}

			}

		};
		initView();
		showSelf();
		startSearch();
	}
	SendResumeOutRecordsEngine mEngine;
	public void startSearch(){
		showProgress();
		mEngine = new SendResumeOutRecordsEngine(this,true);
		mEngine.start();
	}
	
}
