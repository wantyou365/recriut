package com.janan.recruit.searchactivity;

import java.util.ArrayList;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.adapter.PositionSubscribeAdapter;
import com.janan.data.bean.JobInfo;
import com.janan.data.bean.search.NearSearch;
import com.janan.data.bean.search.SearchCondition;
import com.janan.data.data.DataForSearchCondition;
import com.janan.net.SearchEngine;
import com.janan.recruit.BaseActivity;
import com.janan.util.Util;
import com.janan.util.activity.ProvinceActivity;
import com.janan.view.mamager.SearchConditionsManager;
import com.janan.view.mamager.TitleBarManager;


public class SearchResultActivity extends BaseActivity {
	private ArrayList<JobInfo> mList ;
	private LinearLayout mRootLayout;
	private ListView mListView;
	private TextView mAddressTextView;
	private TitleBarManager mBarUtil;
	private SearchConditionsManager mSearchUtil;
	private BaseActivity mActivity;
	private PositionSubscribeAdapter mAdapter;
	private ImageButton mSearchButton;
	private EditText mKeyWordEdit;
	private boolean isButton = false;
	int mPage = 1;
	int midPage;
	NearSearch mSearch;
	SearchEngine mEngine;
	String defaultTitle;
	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarUtil = new TitleBarManager(this);
		
		mRootLayout.addView(
				mBarUtil.createBar(mTitle,
						getResources().getDrawable(R.drawable.homeback),
						// getResources().getDrawable(R.drawable.img28_03)
						null), 0);
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
		mSearchUtil = new SearchConditionsManager(mActivity);
		mSearchUtil.setAdvanced(true);
		mSearchUtil.setHasButton(false);
		mSearchUtil.initView();

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout) findViewById(R.id.rootlay_searchresultactivity);
		mAddressTextView = (TextView) findViewById(R.id.addresstext);
		mKeyWordEdit = (EditText) findViewById(R.id.keywordedit);
		
		if (mSearch.getmIntentPlace() == null) {
			mAddressTextView.setText(DataForSearchCondition.defaultAllString);
		} else {
			mAddressTextView.setText(mSearch.getmIntentPlace().substring(0, 2));
		}
		mSearchButton = (ImageButton) findViewById(R.id.resultactivity_search);
		mSearchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isButton = true;   
				if (mKeyWordEdit.getText() != null
						&& !"".equals(mKeyWordEdit.getText().toString())) {
					String place = mSearch.getmIntentPlace();
					mSearch = new NearSearch();
					mSearch.setmKeyWord(mKeyWordEdit.getText().toString());
					mSearch.setmIntentPlace(place);
					midPage = mPage;
					mPage = 1;
					startSearch();
				}else{
					showToast(getString(R.string.pleaseeditkeyword));
				}
				
				
			}
		});
		mAddressTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				_Intent.putExtra(STRING_TITLE, getString(R.string.pleaseselectintentplace));
				Bundle _Bundle = new Bundle();
//				_Bundle.putInt(TAG_SEARCHKEY, mTag);
				_Bundle.putBoolean(TAG_HASAREA, true);
				_Bundle.putBoolean(TAG_HASCITY, true);
				_Intent.putExtra(TAG_BUNDLE, _Bundle);
				_Intent.setClass(mActivity, ProvinceActivity.class);
				startActivityForResult(_Intent, REQUEST_SEARCHCHEK);
			}
		});
		mListView = (ListView) findViewById(R.id.listviewsearchresultactivity);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchresultactivity);
		defaultTitle = getString(R.string.searchresult);
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		if(mTitle == null||DataForSearchCondition.defaultAllString.equals(mTitle)){
			mTitle = defaultTitle;
		}
		mActivity = this;
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				closeProgress();
				if (msg.what == CallbackError) {
					showToast(msg);
					if(isButton){
						mPage = midPage;
						isButton = false;
					}
				} else if (msg.what == CallbackSuccess) {
					ArrayList<JobInfo> _List = mEngine.getmList();
					int size = _List.size();
					if(size < mEngine.mPageNum){
						
						if(mListView.getFooterViewsCount()>0){
							mListView.removeFooterView(mFooter);
						}						
					}else{
						
						mPage ++;
						if(mListView.getFooterViewsCount()==0){
							mListView.addFooterView(mFooter);
						}
					}
					if(isButton){
						mAdapter = new PositionSubscribeAdapter(mActivity,false);
						mListView.setAdapter(mAdapter);
						isButton = false;
						if(mSearch.getmIntentPlace() == null||DataForSearchCondition.defaultAllString.equals(mSearch.getmIntentPlace())){
							mTitle = defaultTitle;
						}else{
							if(Util.checkZhixiashi(mProvinceString)){
								mTitle = mProvinceString+"-"+mAreaString;
							}else{
								mTitle = mCityString+"-"+mAreaString;
							}
						}
					}else{
						if(mAdapter == null){
							mAdapter = new PositionSubscribeAdapter(mActivity,false);
							mListView.setAdapter(mAdapter);
						}
					}
					
					
					mList = mAdapter.getmList();
					for(int i=0;i<size;i++){
						mList.add(_List.get(i));
					}					
//					if (mTitle == null) {
//						mBarUtil.setTitle(defaultTitle);
//					} else {
						mBarUtil.setTitle(mTitle);
//					}
					
					mListView.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							if(mListView.getFooterViewsCount()>0&&arg2 == mListView.getCount()-1){
								startSearch();
							}else{
								Intent _Intent = new Intent();
								_Intent.setClass(mActivity, JobInfoActivity.class);
								_Intent.putExtra(STRING_TITLE, getResources()
										.getString(R.string.jobinfotitle));
								JobInfo _JobInfo = mList.get(arg2);
								_Intent.putExtra("job", _JobInfo);
								startActivity(_Intent);
							}
							

						}
					});
					

					mAdapter.notifyDataSetChanged();
				}else{
					showToast(msg);
				}

			}

		};
		mSearch = (NearSearch) getIntent().getSerializableExtra(
				"searchcondition");
		initView();
		showSelf();
		startSearch();

	}

	private void startSearch() {
		showProgress();
		if(mEngine== null){
			mEngine = new SearchEngine(mActivity,true);
		}
		
		if (mSearch == null) {
			mSearch = (NearSearch) getIntent().getSerializableExtra(
					"searchcondition");
		}
		if (mSearch != null) {
			mEngine.setmPage(mPage);
			mEngine.setmSearchkeys(mSearch);
			mEngine.start();
		} else {
			mSearch = new NearSearch();
			mEngine.setmPage(mPage);
			mEngine.setmSearchkeys(mSearch);
			mEngine.start();
		}
	}

	public ArrayList<JobInfo> getmList() {
		return mList;
	}

	public void setmList(ArrayList<JobInfo> mList) {
		this.mList = mList;
	}
	String mProvinceString ;
	String mCityString  ;
	String mAreaString  ;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_SEARCHCHEK) {
			if (resultCode == VALUE_RESULT_OK) {
				Bundle _Bundle = data.getBundleExtra(TAG_BUNDLE);
				mProvinceString = _Bundle.getString(TAG_PROVINCE);
				mCityString = _Bundle.getString(TAG_CITY);
				mAreaString = _Bundle.getString(TAG_AREA);
//				if(Util.checkZhixiashi(_ProvinceString)){
					mSearch.setmIntentPlace(mAreaString);
					mAddressTextView.setText(mAreaString
							.substring(0, 2));
//				}else{
//					
//					mAddressTextView.setText(_CityString
//							.substring(0, 2));
//				}
				
			}
		}
	}

}
