package com.janan.recruit.subscribeactivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.gazecloud.dyteam.R;
import com.janan.recruit.BaseActivity;
import com.janan.view.mamager.SearchConditionsManager;
import com.janan.view.mamager.TitleBarManager;

public class SubScribeSearch extends BaseActivity {
	private SearchConditionsManager mSearchManager;
	private TitleBarManager mBarUtil;// 顶工具栏管理器
	private LinearLayout mRootLayout;
	private ScrollView mScroll;
	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		
		mBarUtil = new TitleBarManager(this);   
		mRootLayout.addView(
				mBarUtil.createBar(getString(R.string.tab_subscribe),
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
	public void finishSelf() {
		// TODO Auto-generated method stub
		this.finish();
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		addBar();
		mSearchManager = new SearchConditionsManager(mActivity);
		mSearchManager.setmTag(mSearchManager.mSubscribe);
		mSearchManager.setHasButton(true);
		mSearchManager.setAdvanced(true);
		mSearchManager.initView();

		ScrollView.LayoutParams lp = new ScrollView.LayoutParams(
				ScrollView.LayoutParams.MATCH_PARENT,
				ScrollView.LayoutParams.WRAP_CONTENT);
		lp.setMargins(20, 55, 20, 40);
		mScroll.addView(mSearchManager.getmLayout(),  lp);
	}

	@Override
	public void initView() {
		mRootLayout = (LinearLayout) findViewById(R.id.root_subscribesearchactivity);
		mScroll = (ScrollView)findViewById(R.id.scroll_subscribesearch);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subscribesearchactivity);
		initView();
		showSelf();
		//
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_SEARCHCHEK){
			if(resultCode == VALUE_RESULT_OK){
				Bundle _Bundle = data.getBundleExtra(TAG_BUNDLE);
				mSearchManager.updateUI(_Bundle);
				
			}
		}
	}

}
