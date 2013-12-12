package com.janan.recruit.searchactivity;

import com.gazecloud.dyteam.R;
import com.janan.data.data.City;

import com.janan.recruit.BaseActivity;
import com.janan.util.DBHelper;
import com.janan.util.RequestAndResultCode;
import com.janan.view.mamager.FastNavigationViewUtil;
import com.janan.view.mamager.NearSearchManager;
import com.janan.view.mamager.SearchConditionsManager;
import com.janan.view.mamager.TitleBarManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

/**
ËÑË÷acitivty
*/
public class SearchActivity extends BaseActivity{
	private LinearLayout mRootLayout;
	private LinearLayout mBodyLayout;
	private TitleBarManager mBarManager;
	private SearchConditionsManager mSearchManager;
	private FastNavigationViewUtil mFastNavigationViewUtil;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchactivity);
		initView();
		showSelf();
	}
		/**
		 * Ìí¼Ótitle¹¤¾ßÀ¸
		 * */
	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarManager = new TitleBarManager(this);
		mRootLayout.addView(mBarManager.createBar(getResources().getString(R.string.activitytitle_search), 
				null, getResources().getDrawable(R.drawable.more_search01)), 0);
		mBarManager.setRightButtonListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
//				textDB();
				// TODO Auto-generated method stub
				if(!mBarManager.ismRightClick()){
					mSearchManager.setAdvanced(true);
					mSearchManager.initView();
					mBarManager.getmRightButton().setImageDrawable(getResources().getDrawable(R.drawable.simple_search01));
					mBarManager.setmRightClick(true);
				}else{
					mSearchManager.setAdvanced(false);
					mSearchManager.initView();
					mBarManager.getmRightButton().setImageDrawable(getResources().getDrawable(R.drawable.more_search01));
					mBarManager.setmRightClick(false);
				}
				
			}
		});
	}
	
	@Override
	public void updateUI() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Ìí¼ÓËÑË÷¿ò
	 * */
	public void addSearchLay() {
		// TODO Auto-generated method stub
		mSearchManager = new SearchConditionsManager(mActivity);
		mSearchManager.setmTag(mSearchManager.mSearch);
		mSearchManager.setHasButton(true);
		mSearchManager.setAdvanced(false);
		mSearchManager.initView();
		LinearLayout.LayoutParams lp = 
				new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(20, 55, 20, 40);
		mBodyLayout.addView(mSearchManager.getmLayout(),mBodyLayout.getChildCount(),lp);		
	}
	

	/**
	 * Ìí¼Ó¿ìËÙËÑË÷À¸
	 * */
	public void addFastNavigationView(){
		mFastNavigationViewUtil = new FastNavigationViewUtil(this);
	
		LinearLayout.LayoutParams lp = 
				new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(20, 0, 20, 40);
		mBodyLayout.addView(mFastNavigationViewUtil.getFastNavigationView(),mBodyLayout.getChildCount(),lp);
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
		addSearchLay();
//		addNearSearch();
		addFastNavigationView();
	}
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout)findViewById(R.id.layout_searchactivity);
		mBodyLayout = (LinearLayout)findViewById(R.id.bodylayout_searchactivity);
	
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
