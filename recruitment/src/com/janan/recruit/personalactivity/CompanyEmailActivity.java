package com.janan.recruit.personalactivity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.gazecloud.dyteam.R;
import com.janan.adapter.SeeMyResumeAdapter;
import com.janan.data.bean.JobInfo;
import com.janan.data.bean.personal.CoverLetter;
import com.janan.data.bean.personal.User;
import com.janan.net.CompanyEmailEngine;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.searchactivity.JobInfoActivity;
import com.janan.view.mamager.TitleBarManager;

public class CompanyEmailActivity extends BaseActivity{

	private LinearLayout mRootLayout;
	private TitleBarManager mBarUtil;
//	private TextView mUserTitleView;
	private TextView mNocomTextView;
	private ListView mComsListView;
	private ArrayList<CoverLetter> mList;
	private SeeMyResumeAdapter mAdapter;
	private User mUser;
	private CompanyEmailEngine mEngine;
	private int mPage = 1;
//	private boolean hasMore = false;
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
		
		mComsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(arg2 == mList.size()){
					startSearch();
				}else{
					
				}
			}
		});
		mNocomTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//修改简历界面
				Intent _Intent = new Intent();
				_Intent.setClass(mActivity, ResumeChangeActivity.class);
				_Intent.putExtra(mActivity.STRING_TITLE, getString(R.string.xiugaiweijianli));
				mActivity.startActivity(_Intent);
			}
		});
	}

	
	@Override
	public void initView() {
		// TODO Auto-generated method stub		
		mRootLayout = (LinearLayout) findViewById(R.id.rootlay_seemyresumecompany);
		mNocomTextView = (TextView)findViewById(R.id.nocom);
		mComsListView = (ListView)findViewById(R.id.comslist);
	}
	int tag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seemyresumecompanyactivity);
//		mList = new ArrayList<CoverLetter>();
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		mUser = (User)getIntent().getSerializableExtra("user");
		tag = getIntent().getIntExtra("tag", 1);
		mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				closeProgress();
				if(msg.what == CallbackSuccess){					
					ArrayList<CoverLetter> _List = mEngine.getmList();
					int size = _List.size();
					if(size < mEngine.mPageNum){
						if(mComsListView.getFooterViewsCount()>0){
							mComsListView.removeFooterView(mFooter);
						}						
					}else{					
						mPage ++;
						if(mComsListView.getFooterViewsCount()==0){
							mComsListView.addFooterView(mFooter);
						}
					}
					if(mAdapter == null){
						mAdapter = new SeeMyResumeAdapter(mActivity);
						mComsListView.setAdapter(mAdapter);	
					}
					mList = mAdapter.getmList();
					for(int i=0;i<_List.size();i++){
						mList.add(_List.get(i));
					}									
					mAdapter.notifyDataSetChanged();
					if(mPage<=1){
						mNocomTextView.setVisibility(View.GONE);
						mComsListView.setVisibility(View.VISIBLE);
					}
					
				}else if(msg.what == CallbackError){
					if(tag == 1){
						mNocomTextView.setVisibility(View.VISIBLE);
					}
					
					mComsListView.setVisibility(View.GONE);
					showToast(msg);
				}
			}
			
		};
		startSearch();
		initView();
		showSelf();
	}
	
	public void startSearch(){
		if(mEngine==null){
			mEngine = new CompanyEmailEngine(mActivity,true);
		}
		mEngine.setmPage(mPage);
		mEngine.setmTag(tag);
		mEngine.start();
		showProgress();
	}
	
}
