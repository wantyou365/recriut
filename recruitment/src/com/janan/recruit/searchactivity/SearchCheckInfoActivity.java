package com.janan.recruit.searchactivity;

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
import com.janan.adapter.SearchCheckInfoAdapter;
import com.janan.data.bean.search.SearchCondition;
import com.janan.data.data.DataForSearchCondition;
import com.janan.recruit.BaseActivity;
import com.janan.view.mamager.TitleBarManager;

public class SearchCheckInfoActivity extends BaseActivity{

	private LinearLayout rootLayout;
	private ListView mListView;
	private TitleBarManager mBarUtil;
	private Bundle mBundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchcheckinfoactivity);
		Intent _Intent = getIntent();	
		mBundle = _Intent.getBundleExtra(TAG_BUNDLE);
//		mTag = _Intent.getIntExtra(mActivity.TAG_SEARCHKEY, -1);
		mTitle = getString(R.string.select)+_Intent.getStringExtra(STRING_TITLE);	
		initView();
		showSelf();
	}
	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarUtil = new TitleBarManager(this);
		rootLayout.addView(mBarUtil.createBar(mTitle, getResources().getDrawable(R.drawable.homeback), null),0);
		mBarUtil.setLeftButtonListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SearchCheckInfoActivity.this.finish();
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
		setListData();
		
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		rootLayout = (LinearLayout)findViewById(R.id.rootlay_searchcheckinfoactivity);
		mListView = (ListView)findViewById(R.id.listview_searchcheckinfoactivity);
	}
	SearchCheckInfoAdapter mAdapter;
	ArrayList<String> mList;
	private void setListData(){
		mList = new ArrayList<String>();
		String[] _ValuesStrings = DataForSearchCondition.getDataByTag(mBundle.getInt(TAG_SEARCHKEY));
		if(_ValuesStrings!=null){
			for(int i=0;i<_ValuesStrings.length;i++){
				mList.add(_ValuesStrings[i]);
			}
		}
		mAdapter = new SearchCheckInfoAdapter(this);
		mAdapter.setmList(mList);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				mBundle.putString(TAG_VALUE, mList.get(arg2));
				_Intent.putExtra(TAG_BUNDLE, mBundle);				
				setResult(VALUE_RESULT_OK, _Intent);
				finishSelf();
			}
		});
	}
	
	
	
}
