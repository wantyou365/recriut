package com.janan.recruit.companyactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.search.ResumeNearSearch;
import com.janan.recruit.BaseActivity;
import com.janan.view.mamager.SearchConditionsManager;
import com.janan.view.mamager.TitleBarManager;

public class SearchResumeActivity extends BaseActivity implements OnClickListener{
	
	private SearchConditionsManager mSearchManager;
	private TitleBarManager mBarUtil;// 顶工具栏管理器
	
	private ScrollView mScroll;
	

	@Override
	public void finishSelf() {
		// TODO Auto-generated method stub
		this.finish();
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		super.showSelf();
		mSearchManager = new SearchConditionsManager(mActivity);
		mSearchManager.setmTag(mSearchManager.mSearchResume);
		mSearchManager.setHasButton(true);
		mSearchManager.setAdvanced(true);
		mSearchManager.initSearchResumeView();
		
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
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		initView();
		showSelf();
		//
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
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
