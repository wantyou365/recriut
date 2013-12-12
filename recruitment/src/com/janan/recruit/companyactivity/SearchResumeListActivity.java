package com.janan.recruit.companyactivity;

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
import com.janan.adapter.ResumeListAdapter;
import com.janan.data.bean.personal.Resume;
import com.janan.data.bean.search.NearSearch;
import com.janan.net.SearchResumeEngine;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.personalactivity.ResumePreviewActivity;

public class SearchResumeListActivity extends BaseActivity{

	private SearchResumeEngine mEngine;
	private ListView mListView;
	private NearSearch mSearch;
	private int mPage = 1;
	private ResumeListAdapter mAdapter;
	private ArrayList<Resume> mList;
	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		super.showSelf();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout)findViewById(R.id.layout_searchresumelistactivity);
		mListView = (ListView)findViewById(R.id.listview_searchresumelistactivity);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchresumelistactivity);
		mTitle = getString(R.string.searchresumeresult);
		initView();
		showSelf();
		startSearch();
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == CallbackError) {
					closeProgress();
					showToast(msg);
				}  else if (msg.what == CallbackSuccess) {
					closeProgress();
					if(mAdapter == null){
						mAdapter = new ResumeListAdapter(mActivity);
						mListView.setAdapter(mAdapter);
					}
					setListenter();
					ArrayList<Resume> _List = mEngine.getmList();
					int _Size = _List.size();
					if (_Size < mEngine.mPageNum) {
						if (mListView.getFooterViewsCount() > 0) {
							mListView.removeFooterView(mFooter);
						}
					} else {
						mPage++;
						if (mListView.getFooterViewsCount() == 0) {
							mListView.addFooterView(mFooter);
						}
					}
					mList = mAdapter.getmList();
					for(int i=0;i<_Size;i++){
						mList.add(_List.get(i));
					}
					mAdapter.notifyDataSetChanged();
				}

			}

		};
	}

	@Override
	public void setListenter() {
		// TODO Auto-generated method stub
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Resume _Resume = mList.get(arg2);
				Intent _Intent = new Intent();
				_Intent.setClass(mActivity, ResumePreviewActivity.class);
				_Intent.putExtra("resume", _Resume);
				startActivity(_Intent);
			}
		});
	}
	
	public void startSearch(){
		showProgress();
		mSearch = (NearSearch)getIntent().getSerializableExtra(TAG_SEARCHCONDITIONKEY);
		if(mEngine == null){
			mEngine = new SearchResumeEngine(mActivity, false);
		}		
		mEngine.setmPage(mPage);
		mEngine.setmSearchkeys(mSearch);
		mEngine.start();
	}
	
}
